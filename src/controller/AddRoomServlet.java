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

@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();
       
    public AddRoomServlet() 
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
				
		String avail = request.getParameter("Available");
		boolean available =false;
		
		String type= request.getParameter("room");
		int room_type =1;
		if(type.equals("Lab"))
		{
			room_type=0;
		}
		if(avail.equals("true"))
		{
			available=true;
		}
		
		String building = request.getParameter("Building");
		String room_ID = request.getParameter("room_ID");
		System.out.println(avail);
		System.out.println(room_ID + " " + type+ " " +building + " " + avail + " ");
		
		try 
		{
			if(data.AddRoom(room_ID,room_type, building, available))
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
