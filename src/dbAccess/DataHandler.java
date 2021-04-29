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
	
	public boolean checkAccount(String user, String pass) throws SQLException 
	{
		String query = "SELECT username, password FROM testing where username = '" + user + "' and password = '" + pass + "'";
		System.out.println(query);
		
		rs = dbCon.executeStatement(query);
		if(rs.isBeforeFirst())
			return true;
		
		return false;
	}
	
	public boolean registerAccount(String fname, String lname, String username, String password, int type) throws SQLException
	{
		String query = "insert into testing values ('" + fname + "', '" + lname + "', '" + username + "', '" + password + "', " + type + ")";
		System.out.println(query);

		int result = dbCon.executeUpdate(query);

		if (result == 1) 
		{
			System.out.println("USER ADDED SUCCESS");
			return true;
		}

		return false;
	}
	
	public int checkAccountType(String user, String pass) throws SQLException
	{
		String query = "select type from testing where username = '" + user + "' and password = '" + pass + "'";
		rs = dbCon.executeStatement(query);
		rs.beforeFirst();
		rs.next();
		
		return rs.getInt("type");
	}
	
	
	
	
	
	public ArrayList<String> getAccounts() throws SQLException {
		// List of usernames and passwords in the database
		ArrayList<String> users = new ArrayList<String>();
		String query = "SELECT username, password FROM users ORDER BY username ";

		rs = dbCon.executeStatement(query);
		
		rs.beforeFirst();
		while(rs.next()) { 
			users.add(rs.getString("username"));
			users.add(rs.getString("password"));

		}
		return users;

	}
}
