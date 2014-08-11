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
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet 
{
	   Connection con=null;
	   Statement st=null,st2=null;
	   ResultSet rs=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init()
	{
		try
		{
			System.out.println("init() called");
			   Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mlearning","root","root");
		        st=con.createStatement();
		        st2=con.createStatement();
		 System.out.println("Connected to database");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Not connected to Database in init()");
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 response.setContentType("text/html;charset=UTF-8");
	       PrintWriter out=response.getWriter();
		try
		{
		        System.out.println("Regisration servlet");
	               
	                String username=request.getParameter("username");
	                String password=request.getParameter("password");
	                String emailId=request.getParameter("email");
	                
	                
	                System.out.println("the value"+username);
	               // System.out.println("the value"+pass);
	                /*
		        Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/internalexam","root","root");
		        st=con.createStatement();
		       */
	  rs = st.executeQuery("select username from registration where username='"+username+"'");
	                if(rs.next())
	                {
	                 out.println("UserId Exists");
	                }
	              
	        else
	            {
	                rs=null;
	               String sql="insert into registration(username,password,emailId) values('"+username+"','"+password+"','"+emailId+"')";
	               System.out.println("check");
	               int i=st2.executeUpdate(sql);
	             if(i>=0)
	                  {
                         out.println("Registered");
	                  }

	            }
	                
	    }
	           catch(Exception e)
			{
                    e.printStackTrace();
	               System.out.println("Not connected to Database and in Register servlet     "+e);
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
