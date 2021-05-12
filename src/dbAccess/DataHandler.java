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
	
	public boolean registerAccount(String fname, String lname, String email, String password, int type) throws SQLException
	{
		String query = "insert into users values ('" + fname + "', '" + lname + "', '" + email + "', '" + password + "', " + type + ")";
		System.out.println(query);

		int result = dbCon.executeUpdate(query);

		if (result == 1) 
		{
			System.out.println("USER ADDED SUCCESSFULLY");
			return true;
		}

		return false;
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
