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

public class AddToCartServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // User is not logged in, redirect to login page
            response.sendRedirect("Login.html"); // Replace "login.jsp" with your login page URL
            return;
        }

        String type = request.getParameter("type");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String productId = request.getParameter("productId"); // Retrieve product ID
        
        String userId = String.valueOf(session.getAttribute("userId"));
        
        String url = "jdbc:mysql://localhost:3306/refresh_trendz";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO cart (type, description, price, product_id, user_id) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
            	statement.setString(1, type);
                statement.setString(2, description);
                statement.setDouble(3, price);
                statement.setString(4, productId);
                statement.setString(5, userId);// Set product_id parameter
                statement.executeUpdate();
            }

            // Set session attribute for the added product ID
            session.setAttribute("addedToCartId", productId);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("ItemListServlet?type=" + type);
    }
}

