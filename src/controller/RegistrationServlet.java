package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbAccess.DataHandler;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();

    public RegistrationServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String user = request.getParameter("username");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String password = request.getParameter("password");
		//String passwordconfirm = request.getParameter("passwordconfirm");
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("user: " + user + "\n" + "lname: " + lname + "\n" + "fname: " + fname + "\n" + "pass: " + password + "\n" + "pconfirm: " + type + "\n");
		
		//validation
		
		if(request.getParameter("readterms") == null)
		{
			System.out.println("NOT CHECKED");
		}
		
		try
		{
			if(data.registerAccount(fname, lname, user, password, type))
			{
				response.sendRedirect("staffindex.jsp");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

