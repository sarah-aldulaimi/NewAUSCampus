package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class MyStringLibraryTest {
	
	
	MyStringLibrary example; 
	String s,n;
	@BeforeEach
	void setup() throws Exception{
		example = new MyStringLibrary();
		s = "g00075368@aus.edu";
		n = "0501234567";
	}
//	@Test
//	public void testStartChar() {
//	example.startChar(s); 
//	assertEquals(example.startChar(s),'g');
//	}
//
//	@Test
//	void testEndChar() {
//		example.startChar(s); 
//		assertEquals(example.endChar(s),'u');
//s	}

//	@Test
//	void testReturnString() {
//		example.returnString(s); 
//		assertTrue(example.returnString(s).);
//	}
	@Test
	void testReturnEmail() {
		//example.returnString(s); 
		
		assertTrue(example.returnString(s).contains("@aus.edu"));
	}
	@Test
	void testPhoneNumber() {
		String str = example.returnString(n); 
		int number = Integer.parseInt(str);
		//Check if number is an integer
		assertTrue(Integer.class.isInstance(number));	
		//Check if number is 10 digits
		assertEquals(str.length(),10);
		assertEquals(example.startChar(n),'0');
	}
}
