package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbAccess.DataHandler;
import model.Account;

/*
 * Servlet implementation class RServlet
 */
@WebServlet("/RServlet")
public class RServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 	{
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String password = request.getParameter("password");
		//String passwordconfirm = request.getParameter("passwordconfirm");
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("email: " + email + "\n" + "lname: " + lname + "\n" + "fname: " + fname + "\n" + "pass: " + password + "\n" + "pconfirm: " + type + "\n");
		
		//validation
		
		if(request.getParameter("readterms") == null)
		{
			System.out.println("NOT CHECKED");
		}
		
		try
		{
			if(data.registerAccount(fname, lname, email, password, type))
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
