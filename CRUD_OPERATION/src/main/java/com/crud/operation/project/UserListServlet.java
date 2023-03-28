package com.crud.operation.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
	private static final String query="SELECT employeeId,name,address,gender,salary,birthdate FROM user.userdata";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root & password=root");
			
			PreparedStatement pstmt=con.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			out.println("<table border='1' align='center'>");
			out.println("<tr>");
			out.println("<th>Employee Id</th>");
			out.println("<th>Name</th>");
			out.println("<th>Address</th>");
			out.println("<th>Gender</th>");
			out.println("<th>Salary</th>");
			out.println("<th>Birthdate</th>");
			out.println("<th>Edit</th>");
			out.println("<th>Delete</th>");
			
			out.println("</tr>");
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2) +"</td>");
				out.println("<td>"+rs.getString(3) +"</td>");
				out.println("<td>"+ rs.getString(4)+"</td>");
				out.println("<td>"+rs.getDouble(5) +"</td>");
				
				out.println("<td>"+ rs.getDate(6)+"</td>");
				
				out.println("<td><a href='editScreen?employeeId="+rs.getInt(1)+"'>Edit</a></td>");
				out.println("<td><a href='deleteurl?employeeId="+rs.getInt(1)+"' onclick=\"if (!confirm('Are you sure want to delete employee??')) { return false }\">Delete</a></td>");
				
				out.println("</tr>");
				
			}

			out.println("</table>");

		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h2>");
		}
		out.println("<center><a href=\"employee.html\">Home</a></center>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		doGet(req, resp);
	}

}
