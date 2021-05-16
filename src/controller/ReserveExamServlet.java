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

@WebServlet("/ReserveExamServlet")
public class ReserveExamServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();
       
    public ReserveExamServlet() 
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
				
		String CRN = request.getParameter("course");
		String room = request.getParameter("room");
		int timing = Integer.parseInt(request.getParameter("time"));
		String date = request.getParameter("date");
		System.out.println(CRN + " " + room + " " + timing + " " + date);
		
		try 
		{
			if(data.addClassReservation(CRN, (int)session.getAttribute("id"), timing, date, room))
			{
				RequestDispatcher req = request.getRequestDispatcher("staffindex.jsp");
				req.include(request, response);
			}
			else
			{
				RequestDispatcher req = request.getRequestDispatcher("error.html");
				req.include(request, response);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			RequestDispatcher req = request.getRequestDispatcher("error.html");
			req.include(request, response);
		}
	}

}
