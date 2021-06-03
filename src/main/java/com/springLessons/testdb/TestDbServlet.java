package com.springLessons.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setup connection variables
		String user="springstudent";
		String password="springstudent";
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimeZone=UTC";
		String driver="com.mysql.cj.jdbc.Driver";
		
		//get connection to db
		try {
			//to display HTML content on servlet
			response.setContentType("text/html");
			
			PrintWriter writer=response.getWriter();
			
			writer.println("<html><body>");
			   
			writer.println("Connecting to... "+jdbcUrl);
			
			//Registering a driver
			Class.forName(driver);
			
			Connection myConn=DriverManager.getConnection(jdbcUrl,user,password);
			
			writer.println("<br>");
			
			writer.println("SUCCESS!!");
			
			writer.println("</body></html>");
			
			myConn.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
	}

}
