package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		Account model = new Account();

	    public LoginServlet() 
	    {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			request.setAttribute("id", 5); //stud id with sql
			request.setAttribute("type", 0); //type with sql
			System.out.println("email entered is " + email + " and password entered is " + password);
			if(model.validateUser(email, password)) 
			{
				//check type, then redirect appropriately
				if(model.getAccountType(email, password) == 0)
				{
					request.setAttribute("type", 0);
					RequestDispatcher req = request.getRequestDispatcher("studentindex.html");
					req.include(request, response);
				}
				else if(model.getAccountType(email, password) == 1)
				{
					request.setAttribute("type", 1);
					RequestDispatcher req = request.getRequestDispatcher("staffindex.jsp");
					req.include(request, response);
				}
			} 
			
			else 
			{
				response.sendRedirect("loginerror.html");

			}
		}

	}
