package tests;

//Part 1
//Define example class in file MyStringLibrary.java 
	public class MyStringLibrary{

//Method returns starting character
public char startChar(String s) {
   return s.charAt(0);
}
//method returns last character 
public char endChar(String s)
{
return s.charAt(s.length()-1);
}
//method returns string passed
public String returnString(String s)
{
return s;
} 

}