package com.RefreshTrendz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get address details from the form
        String street = request.getParameter("streetNo");
        String houseNumber = request.getParameter("houseNo");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");
        String country = request.getParameter("country");

        // Combine address components into a single string
        String address = "H No: "+houseNumber + ", Street No: " + street + ", City:  " + city + ", State:  " + state + ", Pincode:  " + pincode + ", Country:  " + country;

        // Retrieve user ID from the session
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            // If user ID is not found in session, redirect to login page or handle accordingly
            response.sendRedirect("login.html");
            return;
        }

        // Define database connection parameters
        String url = "jdbc:mysql://localhost:3306/refresh_trendz";
        String user = "root";
        String password = "root";

        // Update address details in userdetails table
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE userdetails SET address = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, address);
                statement.setInt(2, userId);  // Set user ID as integer
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Address updated successfully for user ID: " + userId);
                } else {
                    System.out.println("No rows updated for user ID: " + userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
            // Optionally, redirect to an error page or display an error message
        }

        // Redirect to the order confirmation page
        response.sendRedirect("orderConfirmation.jsp");
    }

}
