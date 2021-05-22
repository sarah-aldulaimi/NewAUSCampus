package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class MyStringLibraryTest {
	
	
	MyStringLibrary example; 
	String s,n,p;
	@BeforeEach
	void setup() throws Exception{
		example = new MyStringLibrary();
		s = "g00075368@aus.edu";
		n = "0501234567";
		p = "";
	}
	@Test
	void testReturnEmail() {
		//Check if input is not blank
		assertTrue(!example.returnString(s).isBlank());
		//Check if email contains "@aus.edu"
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
		//Check if number starts with 0
		assertEquals(example.startChar(n),'0');
	}
}
