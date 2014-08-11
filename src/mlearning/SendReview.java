package mlearning;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.DynamicAny.NameValuePair;

/**
 * Servlet implementation class SendReview
 */
public class SendReview extends HttpServlet {
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
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
		        System.out.println("Receive SendReview servlet");
		       
	            String Session_name=request.getParameter("Sessionname");
	            System.out.println("the value"+Session_name);
	            
 	       
 rs = st.executeQuery("select username,review,rating from reviewtable where sessionname='"+Session_name+"'");
 	
       String reviewString1="";
       String reviewString2="";
       String reviewString3="";
       String reviewstr1="";
       String reviewstr2="";
       String reviewstr3="";
       int i=0;
       if(rs.next())
       {
    	   rs.previous();
       while(rs.next())
        {
    	   reviewString1=rs.getString(1);
	        reviewstr1+=reviewString1+"#@*%";
	        reviewString2=rs.getString(2);
	        reviewstr2+=reviewString2+"#@*%";
	        reviewString3=rs.getString(3);
	        reviewstr3+=reviewString3+"#@*%";
    	   /*
	        reviewString=rs.getString(1);
	        reviewstr+=reviewString+"#@*1";
	        reviewString=rs.getString(2);
	        reviewstr+=reviewString+"#@*1";
	        reviewString=rs.getString(3);
	        reviewstr+=reviewString+"#@*1";
	        */
	       // i++;
	        
	       // reviewstr+=reviewString+"#@*1";
        }
      
       
 // out.println(reviewstr);
       out.println("Review Available");
       out.println(reviewstr1);
       out.println(reviewstr2);
       out.println(reviewstr3);
  System.out.println("Review and Rating Send ");
       } 
       
       else
       {
    	   out.println("NoReviewAvailable");
    	   System.out.println("NoReviewAvailable");
       }
	   /*String reviewarray[] = null;
	  int i=0;
	   while(rs.next())
	   {
		   reviewarray[i]=rs.getString(0);
		   reviewarray[i++]=rs.getNString(1);
		   reviewarray[i++]=rs.getNString(2);
		   i++;
	   }
	   */
	  /*out.println(reviewarray);
	   System.out.println("Review and Rating Send ");
            */ 
	              }

             catch(Exception e)
			{
			    e.printStackTrace();
				System.out.println("There is an Exception:");
				out.println("Details Not Stored");
			}
	}

}
