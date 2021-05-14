package dbAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String query = "select fname, lname, user_ID, email, phone, dept from users where user_ID = " + userID;
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		return rs;
	}
	
	public boolean addReservation(String CRN, int userID, int timeID, String date, String roomID) throws SQLException
	{
		int result1 = 0, result2 = 0, result3 = 0, result4 = 0;
		String query = "select reserv_count from active_reservations where room_ID = '" + roomID + "' AND reserv_date = '" + date + "' AND time_ID = " + timeID;
		System.out.println(query);
		rs = dbCon.executeStatement(query);
		
		if(rs.isBeforeFirst())
		{
			rs.next();
			if(rs.getInt("reserv_count") == 15)
			{
				System.out.println("FULL CLASS AND TIME");
			}
			
			else
			{
				query = "insert into reservations (course_code, user_ID, room_ID) values ('" + CRN + "', " + userID + ", '" + roomID + "')";
				System.out.println(query);
				result1 = dbCon.executeUpdate(query);
				
				query = "update active_reservations set reserv_count = reserv_count + 1 where room_ID = '" + roomID + "' AND reserv_date = '" + date + "' AND time_ID = " + timeID;
				System.out.println(query);
				result2 = dbCon.executeUpdate(query);
			}
		}
		
		else
		{
			query = "insert into reservations (course_code, user_ID, room_ID) values ('" + CRN + "', " + userID + ", '" + roomID + "')";
			System.out.println(query);
			result3 = dbCon.executeUpdate(query);
			
			query = "insert into active_reservations values ('" + roomID + "', '" + date + "', " + timeID + "," + 1 + ")";
			System.out.println(query);
			result4 = dbCon.executeUpdate(query);
		}


		if ((result1 == 1 && result2 == 1) || (result3 == 1 && result4 == 1)) 
		{
			System.out.println("RESERVATION ADDED SUCCESS!");
			return true;
		}

		return false;
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
