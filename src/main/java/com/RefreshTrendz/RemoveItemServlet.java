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

public class RemoveItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String url = "jdbc:mysql://localhost:3306/refresh_trendz";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM cart WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, productId);
                statement.executeUpdate();
            }
            response.sendRedirect("cart");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
