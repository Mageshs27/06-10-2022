package com.p1;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(value ="/student" )
public class M1  extends HttpServlet{
	
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con=null;
	
		Class.forName("com.mysql.cj.jdbc.Driver");
	
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
		return con;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		M1 m=new M1();
		Connection con=null;
		Statement stmt=null;
		String query=null;
		
		String fname = req.getParameter("fname");
		
		String lname=req.getParameter("lname");
		
		String email=req.getParameter("email");
		
		String Mnumber=req.getParameter("Mnumber");
		
		System.out.println(fname +","+lname+","+email+","+Mnumber);
		
		
		try {
			con=m.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long number = Long.valueOf(Mnumber);		
		query="INSERT INTO emp(fname,lname,email,Mnumber)VALUES('"+fname+"','"+lname+"','"+email+"',"+number+");";
		
		System.out.println(query);		
		try {
			stmt = con.createStatement();
			 
			 stmt.executeUpdate(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		try {
			con=m.getConnection();
			
			stmt =con.createStatement();
			
			query="select * from emp";
			
			ResultSet RS = stmt.executeQuery(query);
			
			resp.setContentType("text/html");
			
			PrintWriter out=  resp.getWriter();
			
			while(RS.next())
			{
				out.print("<h1>"+RS.getString(1)+","+
						RS.getString(2)+","+RS.getString(3)+","+
						RS.getLong(4)+"</h1>");
				
				out.println();
			}
			
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Done");
	}


	
	
	
	
	}
	
	
	

