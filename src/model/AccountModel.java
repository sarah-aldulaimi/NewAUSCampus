package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbAccess.DataHandler;

public class AccountModel 
{
	DataHandler data = new DataHandler();
	boolean validLogin = false;
	ResultSet rs;
	
	public boolean validateUser(String user, String pwd) 
	{
		try 
		{
			ArrayList<String> accounts = data.getAccounts();
			int listSize = accounts.size();
			for (int i = 0; i < listSize; i++) {
				if (user.equals(accounts.get(i)) && pwd.equals(accounts.get(i + 1))) 
				{
					return true;
				}
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}