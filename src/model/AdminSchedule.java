package model;

public class AdminSchedule {
	int booking_ID;
	int course_code;
	int user_ID;
	String room_ID;
	String user_name;
	String user_type;
	String date;
	String start_time;
	String end_time;
	String room_type;
	String status;
	
	public AdminSchedule(int booking_ID, int CRN, int user_ID, String host, String user_type, String room_ID,String date, String start_time, String end_time, String room_type, String Status) {
		super();
		this.booking_ID = booking_ID;
		this.course_code = CRN;
		this.user_ID = user_ID;
		this.user_name = host;
		this.user_type=user_type;
		this.room_ID = room_ID;
		this.date =date;
		this.start_time=start_time;
		this.end_time=end_time;
		this.room_type=room_type;
		this.status=Status;
	}
	
	public AdminSchedule(int booking_ID, int CRN, int user_ID, String host, String user_type, String room_ID,String date, String start_time, String end_time, String room_type) {
		super();
		this.booking_ID = booking_ID;
		this.course_code = CRN;
		this.user_ID = user_ID;
		this.user_name = host;
		this.user_type=user_type;
		this.room_ID = room_ID;
		this.date =date;
		this.start_time=start_time;
		this.end_time=end_time;
		this.room_type=room_type;
	}
	
	public int getBooking_ID() {
		return booking_ID;
	}
	public void setBooking_ID(int booking_ID) {
		this.booking_ID = booking_ID;
	}
	public int getCourse_code() {
		return course_code;
	}
	public void setCourse_code(int course_code) {
		this.course_code = course_code;
	}
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public String getRoom_ID() {
		return room_ID;
	}
	public void setRoom_ID(String room_ID) {
		this.room_ID = room_ID;
	}
	public String getEnd() {
		return end_time;
	}
	public void setEnd(String end_time) {
		this.end_time = end_time;
	}
	public String getUserType() {
		return user_type;
	}
	public void setUserType(String user_type) {
		this.user_type = user_type;
	}
	public String getRoomType() {
		return room_type;
	}
	public void setRoomType(String room_type) {
		this.room_type = room_type;
	}
	public String getStart() {
		return start_time;
	}
	public void setStart(String start_time) {
		this.start_time = start_time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser_Name() {
		return user_name;
	}
	public void setUser_Name(String user_name) {
		this.user_name = user_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
public AdminSchedule(){
	
}


}
