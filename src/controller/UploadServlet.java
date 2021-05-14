package controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import dbAccess.DataHandler;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;

    public UploadServlet() 
    {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    { 
        InputStream inputStream = null;
        Part vaccFilePart = request.getPart("Vfile");
        Part PCRFilePart = request.getPart("PCRfile");
        if (vaccFilePart.getSize() != 0 && PCRFilePart.getSize() != 0) 
        {           
        	System.out.println("both");
        	request.setAttribute("error", "Please Upload Either a Vaccinination Card, or a valid PCR test.");
        	RequestDispatcher rd=request.getRequestDispatcher("/ViewProfile.jsp");            
        	rd.include(request, response);
            return;
        }
        else if(vaccFilePart.getSize() == 0 && PCRFilePart.getSize() == 0)
        {
        	System.out.println("neither");
        	request.setAttribute("error", "Please Upload Either a Vaccinination Card, or a valid PCR test.");
        	RequestDispatcher rd=request.getRequestDispatcher("/ViewProfile.jsp");            
        	rd.include(request, response);
            return;
        }
        else if(vaccFilePart.getSize() != 0 && PCRFilePart.getSize() == 0)
        {
        	System.out.println("vacc no pcr");
        	inputStream = vaccFilePart.getInputStream();
        }
        else if(vaccFilePart.getSize() == 0 && PCRFilePart.getSize() != 0)
        {
        	System.out.println("pcr no vacc");
        	inputStream = PCRFilePart.getInputStream();
        }
        
        HttpSession session = request.getSession(); 
        
        try 
        {
        	DataHandler data = (DataHandler) session.getAttribute("data");
        	data.insertProof((int)session.getAttribute("id"), inputStream);
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        } 
        finally 
        {
        	request.setAttribute("success", "File uploaded Successfully!");
        	getServletContext().getRequestDispatcher("/ViewProfile.jsp").include(request, response);
        }
    }
}