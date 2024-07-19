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
import javax.servlet.http.HttpSession;

public class ItemListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the type parameter from the request
        String type = request.getParameter("type");
        
        // Define database connection parameters
        String url = "jdbc:mysql://localhost:3306/refresh_trendz";
        String user = "root";
        String password = "root";

        // Set the content type of the response
        response.setContentType("text/html");

        // Get a PrintWriter to send HTML response
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Item List</title>");
        out.println("<link rel='stylesheet' href='CSS_Files/products.css'>");
        
        out.println("<link rel='stylesheet' href='CSS_Files/Header.css'>");// Include external CSS file
        out.println("</head>");
        out.println("<body>");
        request.getRequestDispatcher("Header.jsp").include(request, response);
        out.println("<div class='maindiv'>");
        out.println("<div class='outerDiv'>");
        
        // Check if item has been added to cart
        HttpSession session = request.getSession(false);
        String addedToCartId = null;
        if (session != null) {
            addedToCartId = (String) session.getAttribute("addedToCartId");
        }
        // Retrieve data from the database based on type parameter
        try  {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT image, price, description, product_id FROM products WHERE type = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, type);
                try (ResultSet resultSet = statement.executeQuery()) {
                    int count = 0;
                    while (resultSet.next()) {
                        if (count % 4 == 0) {
                            if (count != 0) {
                                out.println("</div>");
                            }
                            out.println("<div class='row'>");
                        }
                        String image = resultSet.getString("image");
                        double price = resultSet.getDouble("price");
                        String description = resultSet.getString("description");
                        String productId = resultSet.getString("product_id");
                        // Display item data
                        out.println("<div class='item'>");
                        out.println("<div class='content'>");
                        out.println("<a href='' class='itemlink'>");
                        out.println("<div class='con' style='position: relative;'>");
                        out.println("<img src='" + image + "' alt='item image' class='img'>");
                        out.println("<div class='option_container'>");
                        out.println("<div class='options'>");
                        if (productId.equals(addedToCartId)) {
                            out.println("<span class='addedToCart'>Added to Cart</span>");
                        } else {
                        	out.println("<a href='AddToCartServlet?type=" + type + "&description=" + description + "&price=" + price + "&productId=" + productId + "' class='option1'>Add To Cart</a>");
 }
                        out.println("<a href='BuyNowServlet?productId=" + productId + "' class='option2'>Buy Now</a>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("<a href='' class='productname'>" + description + "</a>");
                        out.println("<div class='category'>" + type + "</div>");
                        out.println("<div class='price'>" + price + "</div>");
                        out.println("</a>");
                        out.println("</div>");
                        out.println("</div>");
                        count++;
                    }
                    out.println("</div>");
                }
            }
        } catch (SQLException e) {
            // Handle database errors
            out.println("<p>Error retrieving data from database: " + e.getMessage() + "</p>");
        } catch (ClassNotFoundException e) {
            // Handle class not found exception
            e.printStackTrace();
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}