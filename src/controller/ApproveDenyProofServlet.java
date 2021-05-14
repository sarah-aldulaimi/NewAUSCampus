package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dbAccess.DataHandler;

@WebServlet("/ApproveDenyProofServlet")
public class ApproveDenyProofServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ApproveDenyProofServlet() 
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
		
		String[] checkedPics = request.getParameterValues("checkedPics");
		DataHandler data = (DataHandler)session.getAttribute("data");
		
		if(request.getParameter("approve") != null)
		{
			for(String pic_ID : checkedPics )
			{
				try
				{
					data.approveProof(Integer.parseInt(pic_ID));
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		else if(request.getParameter("deny") != null)
		{
			for(String pic_ID : checkedPics )
			{
				try
				{
					data.denyProof(Integer.parseInt(pic_ID));
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
