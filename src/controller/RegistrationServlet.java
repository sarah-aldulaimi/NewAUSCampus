//package controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import dbAccess.DataHandler;
//
//@WebServlet("/RegistrationServlet")
//public class RegistrationServlet extends HttpServlet 
//{
//	private static final long serialVersionUID = 1L;
//	DataHandler data = new DataHandler();
//
//    public RegistrationServlet() 
//    {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		String fname = request.getParameter("fname");
//		String lname = request.getParameter("lname");
//		String email = request.getParameter("email");
//		String phone = request.getParameter("phone");
//		String dept = request.getParameter("dept");
//		String courses = request.getParameter("courses");
//		String password = request.getParameter("password");
//		String confirmPass = request.getParameter("confirmpass");
//		String type = request.getParameter("type");
//		
//		//validation
//		System.out.println("fname: " + fname + " " + " lname: " + lname + " email: " + email + " phone: " + phone + " dept: " + dept + " courses: " + courses + " password: " + password + " type: " + type);
//		
//		System.out.println(password + " " + confirmPass);
//		if(password.equals(confirmPass) == false)
//		{
//			//error
//		}
//		
//		else
//		{
//			try
//			{
//				if(data.registerAccount(fname, lname, email, phone, dept, courses, password, type))
//				{
//					response.sendRedirect("index.html");
//				}
//			}
//			
//			catch(SQLException e)
//			{
//				e.printStackTrace();
//				response.sendRedirect("error.html");
//			}
//		}
//	}
//}
