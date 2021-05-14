package controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dbAccess.DBConnection;
import dbAccess.DataHandler;

@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		DataHandler data = (DataHandler) request.getSession().getAttribute("data");
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		InputStream in = null;

		response.setContentType("image/jpeg");
		ServletOutputStream out;
		out = response.getOutputStream();

		int pic_ID = Integer.parseInt(request.getParameter("pic_ID"));
		try 
		{
			ResultSet result = data.getPhoto(pic_ID);
			
			if (result.next()) 
			{
				in = result.getBinaryStream("photo");
			}
			bin = new BufferedInputStream(in);
			bout = new BufferedOutputStream(out);
			int ch = 0;
			while ((ch = bin.read()) != -1) {
				bout.write(ch);
			}

		} 
		catch (SQLException ex)
		{
			Logger.getLogger(DisplayImage.class.getName()).log(Level.SEVERE, null, ex);
		} 
		finally 
		{
			try 
			{
				if (bin != null)
					bin.close();
				if (in != null)
					in.close();
				if (bout != null)
					bout.close();
				if (out != null)
					out.close();
			} 
			catch (IOException ex) 
			{
				System.out.println("Error : " + ex.getMessage());
			}
		}

	}
}