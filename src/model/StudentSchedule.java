package model;

public class StudentSchedule {
	int booking_ID;
	String course_code;
	int user_ID;
	String room_ID;
	public StudentSchedule(int booking_ID, String course_code, int user_ID, String room_ID) {
		super();
		this.booking_ID = booking_ID;
		this.course_code = course_code;
		this.user_ID = user_ID;
		this.room_ID = room_ID;
	}
	public int getBooking_ID() {
		return booking_ID;
	}
	public void setBooking_ID(int booking_ID) {
		this.booking_ID = booking_ID;
	}
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
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

public StudentSchedule(){
	
}


}
