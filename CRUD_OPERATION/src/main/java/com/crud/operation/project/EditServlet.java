package com.crud.operation.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final String query="Update user.userdata set name=?,address=?,gender=?,salary=?,birthdate=?  where employeeId=?";
	private static final long serialVersionUID = 1L;
       
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		int employeeId=Integer.parseInt(req.getParameter("employeeId"));
		
		String name=req.getParameter("name");
		String address=req.getParameter("address");
		String gender=req.getParameter("gender");
		Double salary=Double.parseDouble(req.getParameter("salary"));
		String birthdate=req.getParameter("birthdate");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root & password=root");
			
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(6, employeeId);
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, gender);
			pstmt.setDouble(4, salary);
			pstmt.setString(5, birthdate);
			
			int count=pstmt.executeUpdate();
			if(count==1)
			{
				out.println("<center><h2>Record is Edited Sucessfully</center>");
			}
			else
			{
				out.println("<h2>Record is Not  Edited Sucessfully");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h2>");
		}
		out.println("<center><a href=\"employee.html\">Home</a></center>");
		
		out.println("<br>");
		out.println("<center><a href=\"userList\"> User List </a></center>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		doGet(req,resp);
	}


}
