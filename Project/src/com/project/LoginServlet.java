package com.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("name");
	    String password=request.getParameter("pass");
	    UserClass user[] = new UserClass[5];
		user[0]=new UserClass("Alex","234567");
		user[1]=new UserClass("Linda","567890");
		user[2]=new UserClass("Maria","345678");
		user[3]=new UserClass("Tom","123456");
		user[4]=new UserClass("Bela","678912");
		int g=0;
		for(int i=0;i<5;i++){
			if(user[i].getUsername().equals(username)&&user[i].getPassword().equals(password)){
				g=1;
				break;
			}
			
		}
		if(g==1){
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			response.sendRedirect("Comments.jsp");
			
		}
		else
		{
	
		String s="The user and password pair you entered is not in the repository ";
		
		response.sendRedirect("Error.jsp?s="+s);
	
		}
			
	    
	
         
	
	}
	
   

}
