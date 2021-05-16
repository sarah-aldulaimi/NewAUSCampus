package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.DataHandler;

@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();
       
    public ModifyUserServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		
		
		
	
	
	//	System.out.println(avail);
	//	System.out.println(room_ID + " " + type+ " " +building + " " + avail + " ");
		
		try 
		{
			String old_email = request.getParameter("email");
			ArrayList<String> details = data.getAccountDetailsByEmail(old_email);
			boolean status =Boolean.parseBoolean(details.get(5));
			String fname=details.get(0);
			String lname=details.get(1);
			String phone=details.get(2);
			String dept=details.get(3);
			String password=details.get(4);
			System.out.println("DB Details: ");
			System.out.println(fname+" "+lname+" "+phone+" "+dept+" "+password);
			String new_fname=request.getParameter("fname");
			String new_lname=request.getParameter("lname");
			String new_phone=request.getParameter("phone");
			String new_dept=request.getParameter("dept");
			String new_password=request.getParameter("password");
			System.out.println("Form Details: ");
			System.out.println(new_fname+" "+new_lname+" "+new_phone+" "+new_dept+" "+new_password);
			if(new_fname.isEmpty())
			{
				System.out.println("No New Fname Added");
				new_fname=fname;
			}
			
			if(new_lname.isEmpty())
			{
				System.out.println("No New Lname Added");
				new_lname=lname;
			}
			if(new_phone.isEmpty())
			{
				System.out.println("No New Phone Added");
				new_phone=phone;
			}
			if(new_dept.isEmpty())
			{
				System.out.println("No New Dept Added");
				new_dept=dept;
			}
			if(new_password.isEmpty())
			{
				System.out.println("No New Pass Added");
				new_password=password;
			}
			System.out.println("Final Query Details: ");
			System.out.println(new_fname+" "+new_lname+" "+new_phone+" "+new_dept+" "+new_password);
			if(data.ModifyUser(new_fname,new_lname,new_phone,new_dept,new_password,status,old_email))
			{
				RequestDispatcher req = request.getRequestDispatcher("adminindex.jsp");
				req.include(request, response);
			}
			else
			{
				RequestDispatcher req = request.getRequestDispatcher("error.jsp");
				req.include(request, response);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			RequestDispatcher req = request.getRequestDispatcher("error.jsp");
			req.include(request, response);
		}
	}

}
