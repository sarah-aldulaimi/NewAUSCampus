package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.DataHandler;

@WebServlet("/RegistrationServletStep")
public class RegistrationServletStep extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();

    public RegistrationServletStep() 
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
		
		session.setAttribute("fname", request.getParameter("fname"));
		session.setAttribute("lname", request.getParameter("lname"));
		session.setAttribute("email", request.getParameter("email"));
		session.setAttribute("phone", request.getParameter("phone"));
		session.setAttribute("dept", request.getParameter("dept"));
		session.setAttribute("password", request.getParameter("password"));
		session.setAttribute("type", request.getParameter("type"));
		
		String password = request.getParameter("password");
		String confirmPass = request.getParameter("confirmpass");
		String type = request.getParameter("type");
		
		if(password.equals(confirmPass) == false)
		{
			//error
		}
		
		else
		{
			if(type.equals("Student"))
			{
				RequestDispatcher req = request.getRequestDispatcher("StudentReg.jsp");
				req.include(request, response);
			}
			else if(type.equals("Staff"))
			{
				RequestDispatcher req = request.getRequestDispatcher("StaffReg.jsp");
				req.include(request, response);
			}
		}
	}
}
