package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccountModel;

@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	AccountModel model = new AccountModel();
	
    public SimpleServlet() 
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
		String password = request.getParameter("pswd");
		request.setAttribute("id", "5");
		System.out.println("username entered is " + user + " and password entered is " + password);
		if (model.validateUser(user, password)) 
		{
			RequestDispatcher req = request.getRequestDispatcher("success.jsp");
			req.include(request, response);
		} 
		
		else 
		{
			response.sendRedirect("error.html");
		}
	}
}
