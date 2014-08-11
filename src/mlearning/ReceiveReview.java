package mlearning;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReceiveReview
 */
public class ReceiveReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	Statement st=null;
    ResultSet rs=null;
    
    @Override
	public void init()
	{
		try
		{
			System.out.println("init() called");
			Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mlearning","root","root");
		    st=con.createStatement();
		 System.out.println("Connected to database");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Not connected to Database");
		}
		
	}
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
		        System.out.println("Receive Review servlet");
		        String username=request.getParameter("username");
	            String rating=request.getParameter("rating");
	            String review=request.getParameter("review");
	            String Session_name=request.getParameter("Sessionname");
	            System.out.println("the value"+rating);
	            System.out.println("the value"+review);
		       
	   int inserdata = st.executeUpdate("insert into reviewtable(username,rating,review,sessionname) values('"+username+"','"+rating+"','"+review+"','"+Session_name+"')");
	   System.out.println("Review and Rating Stored successfully");
             out.println("Details Stored");
	              }

             catch(Exception e)
			{
			    e.printStackTrace();
				System.out.println("There is an Exception:");
				out.println("Details Not Stored");
			}
	}

}
