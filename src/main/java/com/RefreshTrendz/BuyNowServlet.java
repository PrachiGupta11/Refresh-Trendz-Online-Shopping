package com.RefreshTrendz;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyNowServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        
        // Define database connection parameters
        String url = "jdbc:mysql://localhost:3306/refresh_trendz";
        String user = "root";
        String password = "root";
        
        // Initialize variables to store dish details
        String image = "";
        String description = "";
        String type = "";
        
        // Retrieve dish details from the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT image, description, type FROM products WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, productId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        image = resultSet.getString("image");
                        description = resultSet.getString("description");
                        type = resultSet.getString("type");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        // Set the content type of the response
        response.setContentType("text/html");
        
        // Get a PrintWriter to send HTML response
        PrintWriter out = response.getWriter();
        
        // Start HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Buy Now</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: #f2f2f2;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("}");
        out.println(".container {");
        out.println("    max-width: 800px;");
        out.println("    margin: 50px auto;");
        out.println("    padding: 20px;");
        out.println("    background-color: #fff;");
        out.println("    border-radius: 10px;");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
        out.println("}");
        out.println(".dish-details {");
        out.println("    display: flex;");
        out.println("    align-items: center;");
        out.println("}");
        out.println(".dish-image {");
        out.println("    width: 200px;");
        out.println("    height: 200px;");
        out.println("    border-radius: 10px;");
        out.println("    margin-right: 20px;");
        out.println("}");
        out.println(".dish-description {");
        out.println("    flex: 1;");
        out.println("}");
        out.println(".description-heading {");
        out.println("    font-weight: bold;");
        out.println("}");
        out.println(".button {");
        out.println("    padding: 15px 30px;");
        out.println("    font-size: 18px;");
        out.println("    color: #fff;");
        out.println("    background-color: #4CAF50;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("    transition: background-color 0.3s;");
        out.println("}");
        out.println(".button:hover {");
        out.println("    background-color: #45a049;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        // Display Buy Now message with products details
        out.println("<div class='container'>");
        out.println("<h1>Buy Now</h1>");
        out.println("<div class='dish-details'>");
        out.println("<img src='" + image + "' alt='Dish Image' class='dish-image'>");
        out.println("<div class='dish-description'>");
        out.println("<p class='description-heading'>Description:</p>");
        out.println("<p>" + description + "</p>");
        out.println("<p class='description-heading'>Type:</p>");
        out.println("<p>" + type + "</p>");
        out.println("<button class='button' onclick='redirectToPayment(\"" + productId + "\")'>Pay Now</button>");

        out.println("</div>");
        out.println("</div>");
        
       out.println(" <div id=\"payment-options\"></div>");
        
        out.println("</div>");        
        out.println("<script>");
        out.println("function redirectToPayment(productId) {");
        out.println("    window.location.href = 'payment.jsp?productId=' + productId;");
        out.println("}");
        out.println("</script>");
        
        out.println("</body>");
        out.println("</html>");
    }
}
