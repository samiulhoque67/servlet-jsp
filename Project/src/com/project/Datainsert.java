package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Datainsert")
public class Datainsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement stmt;
  
   public void init()
   {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {

		e1.printStackTrace();
	}
	try {
		con = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","");
		stmt=con.prepareStatement("insert into user2 values(?,?,?)");
		System.out.println("connection established");
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	
   }
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String comments=request.getParameter("comment");
	HttpSession session = request.getSession();
	String name=String.valueOf(session.getAttribute("username"));
	
	int id2 = -1;
	
		Statement stmt1;
		try {
			stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery("select max(id) as max_id from user2");
			
			
			while(rs.next()){
				int i=rs.getInt("max_id");
				
				id2=i+1;
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		System.out.println(id2);
		try{
			stmt.setInt(1, id2);
			stmt.setString(2, name);
			stmt.setString(3, comments);
			
		
		
		int result;
		
		result = stmt.executeUpdate();
		if(result>0)
		{
			response.sendRedirect("Display.jsp");
		
	}
	
		
		}catch (SQLException e) {
			System.out.println("mars");
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		}

