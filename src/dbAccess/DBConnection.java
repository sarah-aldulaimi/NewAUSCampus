package dbAccess;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection 
{
	// modify the URL with the schema name you created
	String DBURL = "jdbc:mysql://localhost:3306/sys";  

	//change to your username and password
	String DBUSER = "root";
	String DBPASS = "Hrsrra3051999!";
	
	boolean validLogin = false;

	Connection con;
	Statement statement;
	PreparedStatement prepStatement;
	ResultSet rs;

	public DBConnection() {
		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");  

			// Connect to the Database
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("Connected to the database");

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("error in DBConnection.");
		}
	}

	public ResultSet executeStatement(String strSQL) throws SQLException 
	{
		// make the result set scrolable forward/backward updatable
		statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		// retrieve username and password from database
		rs = statement.executeQuery(strSQL);
		
		return rs;
	}

	public ResultSet retrieveUserDetails() throws SQLException 
	{
		statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		// retrieve username and password from database
		String query = "SELECT email FROM users ORDER BY email ";
		rs = statement.executeQuery(query);
		return rs;		
	}
	
	public int executeUpdate(String strSQL) throws SQLException 
	{
		statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int result = statement.executeUpdate(strSQL);
		
		return result;
	}

	public int executePrepared(String strSQL) throws SQLException 
	{
		prepStatement = con.prepareStatement(strSQL);
		return prepStatement.executeUpdate();
	}
	
	public int executePrepared(String strSQL, int user_ID, InputStream photo) throws SQLException //only used for upload photo
	{
		prepStatement = con.prepareStatement(strSQL);
		prepStatement.setInt(1, user_ID);
		prepStatement.setBlob(2, photo);
		return prepStatement.executeUpdate();
	}
}
