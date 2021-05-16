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

@WebServlet("/ApproveDenyReservationServlet")
public class ApproveDenyReservationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ApproveDenyReservationServlet() 
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
		
		String[] booking_IDs = request.getParameterValues("booking_ID");
		
		DataHandler data = (DataHandler)session.getAttribute("data");
		
		if(booking_IDs==null)
		{
			System.out.println("Empty Array");
			RequestDispatcher req = request.getRequestDispatcher("adminindex.jsp");
			req.include(request, response);
		}
			for(String ID : booking_IDs )
			{
				try
				{
					System.out.println(ID);
					data.approveReservation(Integer.parseInt(ID));
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					RequestDispatcher req = request.getRequestDispatcher("error.jsp");
					req.include(request, response);
				}
			}
			RequestDispatcher req = request.getRequestDispatcher("adminindex.jsp");
			req.include(request, response);
	}
		
	}

