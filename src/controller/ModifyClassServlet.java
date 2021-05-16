package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.DataHandler;

@WebServlet("/ModifyClassServlet")
public class ModifyClassServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	DataHandler data = new DataHandler();
       
    public ModifyClassServlet() 
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
		
		
		
		
	
	
	//	System.out.println(avail);
	//	System.out.println(room_ID + " " + type+ " " +building + " " + avail + " ");
		
		try 
		{
			String old_room = request.getParameter("AllRooms");
			ArrayList<String> rooms = data.getRoomDetails(old_room);
			boolean available =Boolean.parseBoolean(rooms.get(3));
			int type=Integer.parseInt(rooms.get(1));
			String avail= request.getParameter("Available");
			
			if(avail.equals("true"))
			{
				available=true;
			}
			else if(avail.equals("false"))
			{
				available=false;
			}
			String building = request.getParameter("Building");
			String room_ID=(request.getParameter("room_ID"));
			
			if(room_ID.isEmpty())
			{
				System.out.println("Yes");
				room_ID=rooms.get(0);
			}
			
			if(building.isEmpty())
			{
				System.out.println("Yes");
				building=rooms.get(2);
			}
			if(data.ModifyRoom(old_room,room_ID,type,building,available))
			{
				RequestDispatcher req = request.getRequestDispatcher("adminindex.jsp");
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
