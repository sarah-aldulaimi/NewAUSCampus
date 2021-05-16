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
import model.StudentSchedule;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/InstructorScheduleServlet")
public class InstructorScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorScheduleServlet() {
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
		List<StudentSchedule> list=new ArrayList<StudentSchedule>();
		try 
		{
			System.out.println("Inside Try Catch");
			int  booking_id,CRN,user_id;
			String uname,room_id,date,start_time,end_time,type;
			rs = data.getInstructorScheduleInformation((int) session.getAttribute("id"));
			while(rs.next())
			{
				booking_id = rs.getInt("booking_ID");
				CRN = rs.getInt("course_code");
				user_id =rs.getInt("ACTIVE_RESERVATIONS.user_ID");
				uname = rs.getString("fname");
				room_id= rs.getString("ACTIVE_RESERVATIONS.room_ID");
				date=rs.getString("reserv_date");
				start_time=rs.getString("start_time");
				end_time=rs.getString("end_time");
				type=rs.getString("BOOKING_TYPE");
				System.out.println(booking_id+" "+CRN+" "+user_id+" "+uname+" "+room_id+" "+date+" "+start_time+" "+end_time+" "+type+" ");
				if(type.equals("1"))
				{
					type="Exam";
				}
				else
				{
					type="Lab";
				}
				System.out.println("Type after change: "+type);
				list.add(new StudentSchedule(booking_id,CRN,user_id,uname,room_id,date,start_time,end_time,type));
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
		RequestDispatcher req = request.getRequestDispatcher("InstructorScheduleView.jsp");
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
