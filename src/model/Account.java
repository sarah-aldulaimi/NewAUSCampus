package model;

import java.sql.SQLException;

import dbAccess.DataHandler;

public class Account 
{
	DataHandler data = new DataHandler();
	boolean validLogin = false;
	
	public boolean validateUser(String email, String pwd) 
	{
		try
		{
			return data.checkAccount(email, pwd);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public int getAccountID(String email, String pwd)
	{
		try
		{
			return data.getAccountID(email, pwd);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getAccountType(String user, String pwd)
	{
		try
		{
			return data.checkAccountType(user, pwd);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
}