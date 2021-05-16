package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import dbAccess.DataHandler;
import java.sql.ResultSet;

import model.AdminSchedule;

import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/ActiveAdminScheduleServlet")
public class ActiveAdminScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveAdminScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Replace
		DataHandler data = new DataHandler();
		HttpSession session = request.getSession();
		
		ResultSet rs;
		List<AdminSchedule> list=new ArrayList<AdminSchedule>();
		try 
		{
			System.out.println("Inside Try Catch");
			int  booking_id,CRN,user_id;
			String uname,room_id,date,start_time,end_time,usertype,roomtype;
			rs = data.getActiveAdminScheduleInformation((int) session.getAttribute("id"));
			while(rs.next())
			{
				booking_id = rs.getInt("booking_ID");
				CRN = rs.getInt("course_code");
				user_id =rs.getInt("active_reservations.user_ID");
				uname = rs.getString("fname");
				usertype = rs.getString("users.type");
				room_id= rs.getString("active_reservations.room_ID");
				date=rs.getString("reserv_date");
				start_time=rs.getString("start_time");
				end_time=rs.getString("end_time");
				roomtype=rs.getString("BOOKING_TYPE");
				
				System.out.println(booking_id+" "+CRN+" "+user_id+" "+uname+" "+usertype+" "+room_id+" "+date+" "+start_time+" "+end_time+" "+roomtype);
				if(roomtype.equals("1"))
				{
					roomtype="Exam";
				}
				else
				{
					roomtype="Lab";
				}
				if(usertype.equals("1"))
				{
					usertype="Instructor";
				}
				else
				{
					usertype="Student";
				}
				
				System.out.println("User Type after change: "+usertype);
				System.out.println("Room Type after change: "+roomtype);
				list.add(new AdminSchedule(booking_id,CRN,user_id,uname,usertype,room_id,date,start_time,end_time,roomtype));
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//List<StudentSchedule> list = List.of(new StudentSchedule(103, 10000,31145,"Adham","ESB101"),new StudentSchedule(103, 10000,31145,"Adham","ESB101"),
		//new StudentSchedule(103, 10000,31145,"Adham","ESB101"));
		
		request.setAttribute("list", list);
		RequestDispatcher req = request.getRequestDispatcher("AdminActiveScheduleView.jsp");
		req.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
