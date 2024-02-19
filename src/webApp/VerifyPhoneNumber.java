package webApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/VerifyPhoneNumber")

public class VerifyPhoneNumber extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		//To fetch the user information from html to servlet class
		String phone = req.getParameter("phone");
		PrintWriter out = resp.getWriter();
		
		//jdbc connection
		String url = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		String query = "select * from hb_student_tracker.student where email=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");//To connect servlet with DB & to register the mySql Driver
			Connection connection = DriverManager.getConnection(url,user,pass);
			System.out.println("connected successfully!!");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				out.println("Valid!!");
			}
			else
			{
				out.println("Invalid!!");
			}
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
