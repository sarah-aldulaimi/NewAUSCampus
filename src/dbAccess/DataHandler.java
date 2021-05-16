package dbAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.InputStream;
public class DataHandler 
{
	DBConnection dbCon;
	ResultSet rs;
	
	public DataHandler() 
	{
		//connect to the database
		dbCon = new DBConnection();
	}
	
	public boolean checkAccount(String email, String pass) throws SQLException 
	{
		String query = "SELECT email, password FROM users where email = '" + email + "' and password = '" + pass + "'";
		System.out.println(query);
		
		rs = dbCon.executeStatement(query);
		if(rs.isBeforeFirst())
			return true;
		
		return false;
	}
	
	public boolean checkIDUsername(String ID, String email) throws SQLException 
	{
		int ID_int=Integer.parseInt(ID);
		String query = "SELECT user_ID FROM users where email = '" + email + "'";
		System.out.println(query);
		
		rs = dbCon.executeStatement(query);
		if(rs.isBeforeFirst())
			return false;
		rs.next();
		if(rs.getInt("user_ID")==ID_int)
			return true;
		else
		return false;
	}
	public boolean checkPass(int id, String pass) throws SQLException 
	{
		String query = "SELECT user_id, password FROM users where user_ID = " + id + " and password = '" + pass + "'";
		System.out.println(query);
		
		rs = dbCon.executeStatement(query);
		if(rs.isBeforeFirst())
			return true;
		
		return false;
	}
	public void registerProfessorAccount(String fname, String lname, String email, String phone, String dept, String[] CRNS, String[] course_names, String password, int type) throws SQLException
	{
		String query = "insert into users (fname, lname, email, phone, dept, password, type) values ('" + fname + "', '" + lname + "', '" + email + "', '" + phone + "', '" + dept + "', '" + password + "', " + type + ")";
		System.out.println(query);

		int result = dbCon.executeUpdate(query);
		
		int user_ID = this.getAccountID(email, password);
		for(int i = 0; i < CRNS.length; i++)
		{
			if(CRNS[i].isEmpty() || course_names[i].isEmpty())
				continue;
			
			query = "insert into avail_courses values (" + Integer.parseInt(CRNS[i]) + ", '" + course_names[i] + "', " + user_ID + ")";
			int res = dbCon.executeUpdate(query);
		}

}
	
	public void registerStudentAccount(String fname, String lname, String email, String phone, String dept, String[] CRNS, String password, int type) throws SQLException
	{
		String query = "insert into users (fname, lname, email, phone, dept, password, type) values ('" + fname + "', '" + lname + "', '" + email + "', '" + phone + "', '" + dept + "', '" + password + "', " + type + ")";
		System.out.println(query);
int result = dbCon.executeUpdate(query);
		
		int user_ID = this.getAccountID(email, password);
		
		for(int i = 0; i < CRNS.length; i++)
		{
			if(CRNS[i].isEmpty())
				continue;
			
			query = "insert into stud_courses values (" + user_ID + ", " + Integer.parseInt(CRNS[i]) + ")";
			System.out.println(query);
			int res = dbCon.executeUpdate(query);
		}
	}
	
	public int checkAccountType(String email, String pass) throws SQLException
	{
		String query = "select type from users where email = '" + email + "' and password = '" + pass + "'";
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		
		return rs.getInt("type");
	}
	
	public int checkAccountType(int ID) throws SQLException
	{
		String query = "select type from users where user_ID = " + ID + "";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		
		return rs.getInt("type");
	}
	
	public void insertProof(int user_ID, InputStream inputstream) throws SQLException
	{
		String query = "insert into user_proof (user_ID, photo) values (?, ?)";
		int res = dbCon.executePrepared(query, user_ID, inputstream);
	}
	
	public ResultSet getPhoto(int pic_ID) throws SQLException
	{
		String query = "SELECT photo FROM user_proof WHERE pic_ID=" + pic_ID + "";
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		return rs;
	}
	
	public ResultSet getProofs() throws SQLException
	{
		String query = "select * from user_proof";
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		return rs;
	}
	
	public void approveProof(int pic_ID) throws SQLException
	{
		String query = "select user_ID from user_proof where pic_ID = " + pic_ID;
		rs = dbCon.executeStatement(query);
		
		rs.next();
		int user_ID = rs.getInt("user_ID");
		query = "update users set status = true where user_ID = " + user_ID;
		int result = dbCon.executeUpdate(query);
		
		query = "delete from user_proof where user_ID = " + user_ID;
		result = dbCon.executeUpdate(query);
	}
	
	public void approveReservation(int booking_ID) throws SQLException
	{
		String query = "select * from pending_reservations where booking_ID = " + booking_ID;
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		int bookingid=rs.getInt("booking_ID");
		int crn=rs.getInt("course_code");
		int userid=rs.getInt("user_id");
		String roomid=rs.getString("room_ID");
		String date=rs.getString("reserv_date");
		String time=rs.getString("time_ID");
		
		System.out.println(bookingid+" "+crn+" "+userid+" "+roomid+" "+date+" "+time);
		query = "update pending_reservations set processed = true where booking_ID = " + bookingid;
		System.out.println(query);
		int result = dbCon.executeUpdate(query);
		if(result>0)
		{
			System.out.println("Success in updating processed");
		}
	
		result = dbCon.executeUpdate(query);
		query = "insert into active_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) values (" + crn + ", " + userid + ", '" + roomid + "', '"+date+"', "+time+")";
		result = dbCon.executeUpdate(query);
		if(result>0)
		{
			System.out.println("Success in adding active reservations");
		}
	}
	
	public void denyProof(int pic_ID) throws SQLException
	{
		String query = "delete from user_proof where pic_ID = " + pic_ID;
		int result = dbCon.executeUpdate(query);
	}
	
	public int getAccountID(String email, String pass) throws SQLException
	{
		String query = "select user_ID from users where email = '" + email + "' and password = '" + pass + "'";
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		
		return rs.getInt("user_ID");
	}
	
	public ResultSet getAccountInformation(int userID) throws SQLException
	{
		String query = "select fname, lname, user_ID, email, phone, dept, status from users where user_ID = " + userID;
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		return rs;
	}
	
	public ResultSet getScheduleInformation(int userID) throws SQLException
	{
		//String query = "select fname, lname, user_ID, email, phone, dept from users where user_ID = " + userID;
		String query ="select booking_ID , course_code, ACTIVE_RESERVATIONS.user_ID, CONCAT(fname,' ',lname) AS fname,ACTIVE_RESERVATIONS.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, room_type AS BOOKING_TYPE FROM ACTIVE_RESERVATIONS,users,avail_timings,rooms WHERE course_code IN (SELECT CRN FROM STUD_COURSES WHERE stud_id = "+userID+") AND ACTIVE_RESERVATIONS.user_ID=users.user_ID AND ACTIVE_RESERVATIONS.time_ID=avail_timings.time_ID AND ACTIVE_RESERVATIONS.room_ID=rooms.room_ID AND (users.type=1 OR active_reservations.user_ID IN ( select distinct stud_id from stud_courses where stud_id ="+userID+")); ";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		//rs.next();
		return rs;
	}
	
	public ResultSet getInstructorScheduleInformation(int userID) throws SQLException
	{
		//String query = "select fname, lname, user_ID, email, phone, dept from users where user_ID = " + userID;
		String query ="select booking_ID , course_code, ACTIVE_RESERVATIONS.user_ID, CONCAT(fname,' ',lname) AS fname,ACTIVE_RESERVATIONS.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, room_type AS BOOKING_TYPE FROM ACTIVE_RESERVATIONS,users,avail_timings,rooms WHERE course_code IN (SELECT CRN FROM avail_courses WHERE instructor_id = "+userID+") AND ACTIVE_RESERVATIONS.user_ID=users.user_ID AND ACTIVE_RESERVATIONS.time_ID=avail_timings.time_ID AND ACTIVE_RESERVATIONS.room_ID=rooms.room_ID AND users.type=1;";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		//rs.next();
		return rs;
	}
	
	public ResultSet getPendingAdminScheduleInformation(int userID) throws SQLException
	{
		//String query = "select fname, lname, user_ID, email, phone, dept from users where user_ID = " + userID;
		String query ="select  booking_ID , course_code, pending_reservations.user_ID, CONCAT(fname,' ',lname) AS fname,users.type,pending_reservations.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time,room_type AS BOOKING_TYPE,users.status FROM pending_reservations,users,avail_timings,rooms WHERE pending_reservations.user_ID=users.user_ID AND pending_reservations.time_ID=avail_timings.time_ID AND pending_reservations.room_ID=rooms.room_ID AND pending_reservations.processed=FALSE; ";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		//rs.next();
		return rs;
	}
	
	public ResultSet getActiveAdminScheduleInformation(int userID) throws SQLException
	{
		//String query = "select fname, lname, user_ID, email, phone, dept from users where user_ID = " + userID;
		String query ="select  booking_ID , course_code, active_reservations.user_ID, CONCAT(fname,' ',lname) AS fname,users.type,active_reservations.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, room_type AS BOOKING_TYPE FROM active_reservations,users,avail_timings,rooms WHERE active_reservations.user_ID=users.user_ID AND active_reservations.time_ID=avail_timings.time_ID  AND active_reservations.room_ID=rooms.room_ID ";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		//rs.next();
		return rs;
	}
	
	public boolean AddRoom(String room_ID, int type, String building, boolean avail ) throws SQLException
	{
		String query = "insert into rooms (room_ID, room_type, building, available) values ('" + room_ID + "', " + type + ", '" + building + "', "  + avail + ")";
		System.out.println(query);

		int result = dbCon.executeUpdate(query);

		if (result == 1) 
		{
			System.out.println("LAB ADDED SUCCESS");
			return true;
		}

		return false;
	}
	public boolean RemoveRoom(String room_ID ) throws SQLException
	{
		String query = "delete from rooms where room_ID =  '"  + room_ID + "'";
		System.out.println(query);

		int result = dbCon.executeUpdate(query);

		if (result == 1) 
		{
			System.out.println("ROOM REMOVED SUCCESSFULLY");
			return true;
		}

		return false;
	}
	
	public boolean RemoveUser(String email ) throws SQLException
	{
		
		String query = "delete from USERS where email =  '"  + email + "'";
		System.out.println(query);
		int result = dbCon.executeUpdate(query);

		if (result == 1) 
		{
			System.out.println("USER REMOVED SUCCESSFULLY");
			return true;
		}

		return false;
	}
	
	
	
	public boolean ModifyRoom(String old_id,String room_ID, int type, String building, boolean avail ) throws SQLException
	{
		
		//ArrayList<String> rooms = getRoomDetails(room_ID);
		String query = "UPDATE ROOMS SET room_ID='"+room_ID+"',room_type="+type+",building='"+building+"',available="+avail+" where room_ID='"+old_id+"'";
		System.out.println(query);
		
		
		int result = dbCon.executeUpdate(query);

		if (result == 1) 
		{
			System.out.println("Room Modded SUCCESS");
			return true;
		}

		return false;
	}
	public boolean ModifyUser(String fname,String lname, String phone, String dept,String password, boolean status, String old_email ) throws SQLException
	{
		
		//ArrayList<String> rooms = getRoomDetails(room_ID);
		String query = "UPDATE USERS SET fname='"+fname+"',lname='"+lname+"',phone='"+phone+"',dept= '"+dept+"',password='"+password+"',status="+status+" where email='"+old_email+"'";
		System.out.println(query);
		
		
		int result = dbCon.executeUpdate(query);

		if (result == 1) 
		{
			System.out.println("User Modded SUCCESS");
			return true;
		}

		return false;
	}
	public boolean addLabReservation(String CRN, int userID, int timeID, String date, String roomID) throws SQLException
	{
		int result;
		String query = "select COUNT(booking_ID) AS capacity FROM pending_reservations WHERE reserv_date = '" + date + "' AND time_ID = " + timeID+ " AND room_ID= '"+roomID+"';";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		if(rs.getInt("capacity") == 15)
			{
				System.out.println("FULL Lab Capacity ON THIS DAY & TIME");
				return false;
			}
			
		else
			{	System.out.println(rs.getInt("capacity"));
				query = "insert into pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) values ('" + CRN + "', " + userID + ", '" + roomID + "', '"+date+"', "+timeID+")";
				System.out.println(query);
				result = dbCon.executeUpdate(query);
				if (result>0)
				{
					return true;
				}
				else
				{
					return false;
				}
				//query = "update active_reservations set reserv_count = reserv_count + 1 where room_ID = '" + roomID + "' AND reserv_date = '" + date + "' AND time_ID = " + timeID;
				//System.out.println(query);
				//result2 = dbCon.executeUpdate(query);
			}
			
	}
		
	public boolean addClassReservation(String CRN, int userID, int timeID, String date, String roomID) throws SQLException
	{
		boolean result1 =true;
		boolean result2 = true;
		String query = "select * FROM active_reservations  WHERE reserv_date = '" + date + "' AND time_ID = " + timeID+ " AND room_ID= '"+roomID+"';";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		if(rs.isBeforeFirst())
		{
			System.out.println("Active_Reservations List Empty");
			result1=false;
		}
		query = "select * FROM pending_reservations  WHERE reserv_date = '" + date + "' AND time_ID = " + timeID+ " AND room_ID= '"+roomID+"';";
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		if(rs.isBeforeFirst())
		{
			System.out.println("Pending_Reservations List Empty");
			result2=false;
		}
		if(result1 && result2)
			{
				System.out.println("No Existing reservations for this classroom on such time&date");
				query = "insert into pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) values ('" + CRN + "', " + userID + ", '" + roomID + "', '"+date+"', "+timeID+")";
				System.out.println(query);
				int result = dbCon.executeUpdate(query);
				if (result>0)
				{
					return true;
				}
				else
				{
				return false;
				}
			}
			
		else
			{	System.out.println("Reservation Already Exists");
				return false;
			}
			
	}
	
	public ArrayList<String> getAvailableCourseNames(int userID, int type) throws SQLException
	{
		ArrayList<String> coursenames = new ArrayList<String>();
		
		if(type == 0) //student
		{
			String query = "select course_name from avail_courses where CRN in (select CRN from stud_courses where stud_id = " + userID + ")"; //only show courses student is taking
			
			rs = dbCon.executeStatement(query);
			rs.beforeFirst();
			
			while(rs.next())
			{
				String course = rs.getString("course_name");
				coursenames.add(course);
			}
		}
		
		else if(type == 1) //professor
		{
			String query = "select course_name from avail_courses where instructor_ID = " + userID; //only show courses professor is giving
			
			rs = dbCon.executeStatement(query);
			rs.beforeFirst();
			
			while(rs.next())
			{
				String course = rs.getString("course_name");
				coursenames.add(course);
			}
		}
		
		return coursenames;
	}
	
	public ArrayList<String> getAvailableCourseCRNs(int userID, int type) throws SQLException
	{
		ArrayList<String> CRNs = new ArrayList<String>();
		
		if(type == 0) //student
		{
			String query = "select CRN from stud_courses where stud_id = " + userID; //only show courses student is taking
			
			rs = dbCon.executeStatement(query);
			rs.beforeFirst();
			
			while(rs.next())
			{
				String course = rs.getString("CRN");
				CRNs.add(course);
			}
		}
		
		else if(type == 1) //professor
		{
			String query = "select CRN from avail_courses where instructor_ID = " + userID; //only show courses professor is giving
			
			rs = dbCon.executeStatement(query);
			rs.beforeFirst();
			
			while(rs.next())
			{
				String course = rs.getString("CRN");
				CRNs.add(course);
			}
		}
		
		return CRNs;
	}
	
	public ArrayList<String> getAvailableLabs() throws SQLException
	{
		String query = "select room_ID from rooms where room_type = 0 and available = true"; //room_type 0 = lab
		ArrayList<String> labs = new ArrayList<String>();
		
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		
		while(rs.next())
		{
			String course = rs.getString("room_ID");
			labs.add(course);
		}
		
		return labs;
	}
	
	public ArrayList<String> getAllLabs() throws SQLException
	{
		String query = "select room_ID from rooms where room_type = 0"; //room_type 0 = lab
		ArrayList<String> labs = new ArrayList<String>();
		
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		
		while(rs.next())
		{
			String course = rs.getString("room_ID");
			labs.add(course);
		}
		
		return labs;
	}
	
	
	public ArrayList<String> getAvailableRooms() throws SQLException
	{
		String query = "select room_ID from rooms where room_type = 1 and available = true"; //room_type 1 = room
		ArrayList<String> rooms = new ArrayList<String>();
		
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		
		while(rs.next())
		{
			String course = rs.getString("room_ID");
			rooms.add(course);
		}
		
		return rooms;
	}
	
	public ArrayList<String> getAllRooms() throws SQLException
	{
		String query = "select room_ID from rooms where room_type = 1"; //room_type 1 = room
		ArrayList<String> rooms = new ArrayList<String>();
		
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		
		while(rs.next())
		{
			String course = rs.getString("room_ID");
			rooms.add(course);
		}
		
		return rooms;
	}
	public ArrayList<String> getRoomDetails(String room_id) throws SQLException {
		// List of usernames and passwords in the database
		ArrayList<String> details = new ArrayList<String>();
		String query = "SELECT * FROM rooms WHERE room_ID= '"+ room_id +"'";
		
		rs = dbCon.executeStatement(query);
		
		rs.beforeFirst();
		while(rs.next()) { 
			details.add(rs.getString("room_ID"));
			details.add(rs.getString("room_type"));
			details.add(rs.getString("building"));
			if(rs.getBoolean("available"))
			details.add("true");
			else
				details.add("false");
		}
		return details;
	}
	
	public ArrayList<String> getAccountDetailsByEmail(String email) throws SQLException {
		// List of usernames and passwords in the database
		ArrayList<String> details = new ArrayList<String>();
		String query = "SELECT * FROM users WHERE email= '"+ email +"'";
		
		rs = dbCon.executeStatement(query);
		
		rs.beforeFirst();
		while(rs.next()) { 
			details.add(rs.getString("fname"));
			details.add(rs.getString("lname"));
			details.add(rs.getString("phone"));
			details.add(rs.getString("dept"));
			details.add(rs.getString("password"));
			details.add(rs.getString("status"));
			
		}
		return details;
	}
	
	public ArrayList<String> getEveryRoomOnCampus() throws SQLException
	{
		String query = "select room_ID from rooms"; 
		ArrayList<String> rooms = new ArrayList<String>();
		
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		
		while(rs.next())
		{
			String course = rs.getString("room_ID");
			rooms.add(course);
		}
		
		return rooms;
	}
	public ArrayList<String> getAvailableTimings() throws SQLException
	{
		String query = "SELECT TIME_FORMAT(start_time, '%H:%i') as start_time, TIME_FORMAT(end_time, '%H:%i') as end_time from avail_timings"; //don't forget check on capacity
		ArrayList<String> timings = new ArrayList<String>();
		
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		
		while(rs.next())
		{
			String start = rs.getString("start_time");
			String end = rs.getString("end_time");
			timings.add(start + " - " + end);
		}
		
		return timings;
	}
	
	public ArrayList<String> getAvailableTimingIDs() throws SQLException
	{
		String query = "select time_id from avail_timings";
		ArrayList<String> time_ids = new ArrayList<String>();
		
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		
		while(rs.next())
		{
			String course = rs.getString("time_id");
			time_ids.add(course);
		}
		
		return time_ids;
	}
	
	public ArrayList<String> getAccounts() throws SQLException {
		// List of usernames and passwords in the database
		ArrayList<String> users = new ArrayList<String>();
		String query = "SELECT email, pass_word FROM users ORDER BY email ";

		rs = dbCon.executeStatement(query);
		
		rs.beforeFirst();
		while(rs.next()) { 
			users.add(rs.getString("email"));
			users.add(rs.getString("pass_word"));
		}
		return users;
	}
}
//new commit