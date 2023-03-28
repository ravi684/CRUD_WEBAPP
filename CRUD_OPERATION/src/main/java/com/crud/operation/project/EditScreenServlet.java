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

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	private static final String query = "SELECT name,address,gender,salary,birthdate FROM user.userdata where employeeId=?";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		int employeeId = Integer.parseInt(req.getParameter("employeeId"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root & password=root");
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			out.println("<form action='editurl?employeeId=" + employeeId + "' method='post'>");
			out.println("<table align='center'>");
			out.println("<tr>");
			out.println("<td>Name</td>");
			out.println("<td><input type='text' name='name' value='" + rs.getString(1) + "'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Address</td>");
			out.println("<td><input type='text' name='address' value='" + rs.getString(2) + "'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Gender</td>");
			out.println(
					"<td><input type='radio' name='gender'value=\"male\" value='" + rs.getString(3) + "'>Male</td>");
			out.println("<td><input type='radio' name='gender'value=\"female\" value='" + rs.getString(3)
					+ "'>Female</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Salary</td>");
			out.println("<td><input type='text' name='salary' value='" + rs.getDouble(4) + "'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Birthdate</td>");
			out.println("<td><input type='text' name='birthdate' value='" + rs.getDate(5) + "'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<td><input type='submit' value='Edit'</td>");
			out.println("<td><input type='reset' value='Cancel'</td>");
			out.println("</table>");
			out.println("</form>");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>" + e.getMessage() + "</h2>");
		}
		out.println("<center><a href=\"employee.html\">Home</a></center>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

}
