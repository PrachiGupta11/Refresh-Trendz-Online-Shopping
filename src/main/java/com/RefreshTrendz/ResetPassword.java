package com.RefreshTrendz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@WebServlet("/resetPassword")
public class ResetPassword extends HttpServlet {
    
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Refresh_Trendz";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("pass1");
        String confirmPassword = request.getParameter("pass2");

        // Check if the email is correct by querying the database
        boolean emailVerified;
		try {
			emailVerified = verifyEmail(email);
			if (emailVerified) {
	            // Enable password fields
	            if (newPassword.equals(confirmPassword)) {
	                // Reset the password in the database
	                boolean passwordReset = resetPassword(email, newPassword);
	                if (passwordReset) {
	                    // Password successfully reset
	                	response.sendRedirect("ResetPassword.html");
	                    return;
	                } else {
	                    // Failed to reset password
	                    response.setContentType("text/html");
	                    PrintWriter out = response.getWriter();
	                    out.println("<h2>Failed to reset password. Please try again.</h2>");
	                    out.close();
	                    return;
	                }
	            } else {
	                // Passwords do not match
	                response.setContentType("text/html");
	                PrintWriter out = response.getWriter();
	                out.println("<h2>Passwords do not match. Please enter matching passwords.</h2>");
	                out.close();
	                return;
	            }
	        } else {
	            // Email is incorrect
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<h2>Invalid email address. Please enter a valid email address.</h2>");
	            out.close();
	            return;
	        }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private boolean verifyEmail(String email) throws ClassNotFoundException {
        boolean isValid = false;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // Establishing a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to check if the email exists in the database
            String sql = "SELECT * FROM userdetails WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            // Executing the query
            resultSet = preparedStatement.executeQuery();
            System.out.println("Email is verifield"+resultSet);

            // If the result set contains any rows, it means the email exists
            isValid = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the database connection, statement, and result set
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }

    private boolean resetPassword(String email, String newPassword) {
        boolean isReset = false;
        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to update the password for the given email
            String sql = "UPDATE userdetails SET password = ? WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            
            // Set parameters for the prepared statement
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            // Executing the update
            int rowsAffected = preparedStatement.executeUpdate();

            // If rowsAffected > 0, it means the password was successfully updated
            isReset = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the database connection and statement
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isReset;
    }

}