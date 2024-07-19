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

public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user ID and product ID from session or request parameters
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Check if userId is null
        if (userId == null) {
            // Handle the case where userId is not set
            // For example, redirect the user to the login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Continue processing with userId
        int productId;
        try {
            String productIdStr = request.getParameter("productId");
            productId = Integer.parseInt(productIdStr); // This is line 34
            System.out.println("Product ID: " + productId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID format");
            return;
        }
        
        String cardNumber = request.getParameter("card-number");
        String expiry = request.getParameter("expiry");
        String cvv = request.getParameter("cvv");

        // Define database connection parameters
        String url = "jdbc:mysql://localhost:3306/refresh_trendz";
        String user = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("User ID: " + userId);
            System.out.println("Product ID: " + productId);
            System.out.println("Card Number: " + cardNumber);
            System.out.println("Expiry: " + expiry);
            System.out.println("CVV: " + cvv);

            String query = "INSERT INTO payment (user_id, product_id, card_number, expiry, cvv) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setInt(2, productId);
                statement.setString(3, cardNumber);
                statement.setString(4, expiry);
                statement.setString(5, cvv);
                statement.executeUpdate();
            }
            System.out.println("Redirecting to order confirmation page...");
            // Redirect to the order confirmation page
            response.sendRedirect("orderConfirmation.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred during payment");
        }
    }
}
