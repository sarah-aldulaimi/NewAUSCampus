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


@WebServlet("/RegistrationServletFinal")
public class RegistrationServletFinal extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();

    public RegistrationServletFinal() 
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
		
		String fname = (String)session.getAttribute("fname");
		String lname = (String)session.getAttribute("lname");
		String email = (String)session.getAttribute("email");
		String phone = (String)session.getAttribute("phone");
		String dept = (String)session.getAttribute("dept");
		String password = (String)session.getAttribute("password");
		String type = (String)session.getAttribute("type");
		
		//validation
		System.out.println("fname: " + fname + " " + " lname: " + lname + " email: " + email + " phone: " + phone + " dept: " + dept + " password: " + password + " type: " + type);
		
		try
		{
			String[] crns = request.getParameterValues("crn");
			if(type.equals("Staff"))
			{
				String[] course_names = request.getParameterValues("course");
				data.registerProfessorAccount(fname, lname, email, phone, dept, crns, course_names, password, 1);
			}

			else if(type.equals("Student"))
			{
				data.registerStudentAccount(fname, lname, email, phone, dept, crns, password, 0);
			}
		}
		catch(SQLException e)
		{
			response.sendRedirect("error.html");
			e.printStackTrace();
			RequestDispatcher req = request.getRequestDispatcher("error.jsp");
			req.include(request, response);
		}
		
		session.removeAttribute("fname");
		session.removeAttribute("lname");
		session.removeAttribute("email");
		session.removeAttribute("phone");
		session.removeAttribute("dept");
		session.removeAttribute("password");
		session.removeAttribute("type");
		response.sendRedirect("index.html");
	}
}
