package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.*;

import dbAccess.DataHandler;

class TestCases {	
	MyStringLibrary example; 
	DataHandler db;
	String s,n,p,rid,new_rid, building,old_rid;
	int type;
	boolean avail;
	@BeforeEach
	void setup() throws Exception{
		example = new MyStringLibrary();
		db = new DataHandler();
		s = "g00075368@aus.edu";
		n = "0501234567";
		p = "123";
		rid = "ESB1015";
		old_rid = "ESB1020";
		new_rid = "ART123";
		type = 1;
		building = "Art Building";
		avail = true;
	}
	@Test
	void testReturnEmail() {
		//Check if input is not blank
		assertTrue(!example.returnString(s).isBlank());
		//Check if email contains "@aus.edu"
		assertTrue(example.returnString(s).contains("@aus.edu"));
	}
	@Test
	void testPhoneNumber() throws SQLException {
		String str = db.checkPhone(s, p);
		int number = Integer.parseInt(str);
		//Check if number is an integer
		assertTrue(Integer.class.isInstance(number));	
		//Check if number is 10 digits
		assertEquals(str.length(),10);
		//Check if number starts with 0
		assertEquals(example.startChar(str),'0');
	}
	
	@Test
	void testAccountTyple() {
		try {
			assertEquals(db.checkAccountType(s,p),0);
			System.out.println("This person is a student");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void testRemoveRoom() throws SQLException {
		assertTrue(	 db.RemoveRoom(rid));	
	}
	@Test
	void testModifyRoom() throws SQLException {
		assertTrue(db.ModifyRoom(old_rid, new_rid, type, building, avail));	
	}
}
