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

public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/refresh_trendz";
        String user = "root";
        String password = "root";
        String ProductId = request.getParameter("productId");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cart</title>");
        out.println("<style>");
        out.println(".container {");
        out.println("    max-width: 800px;");
        out.println("    margin: 0 auto;");
        out.println("    padding: 20px;");
        out.println("}");
        out.println(".cart-item {");
        out.println("    display: flex;");
        out.println("    align-items: center;");
        out.println("    margin-bottom: 20px;");
        out.println("    border-bottom: 1px solid #ccc;");
        out.println("}");
        out.println(".item-image {");
        out.println("    width: 100px;");
        out.println("    margin-right: 20px;");
        out.println("}");
        out.println(".item-details {");
        out.println("    flex: 1;");
        out.println("}");
        out.println(".quantity {");
        out.println("    display: flex;");
        out.println("    align-items: center;");
        out.println("}");
        out.println(".quantity input {");
        out.println("    width: 40px;");
        out.println("    text-align: center;");
        out.println("}");
        out.println("#bill-panel {");
        out.println("    margin-top: 20px;");
        out.println("    padding: 10px;");
        out.println("    background-color: #f9f9f9;");
        out.println("    border: 1px solid #ccc;");
        out.println("}");
        out.println(".button {");
        out.println("    padding: 15px 30px;");
        out.println("    font-size: 18px;");
        out.println("    color: #fff;");
        out.println("    background-color: #4CAF50;");
        out.println("    border: none;"
        		+"margin-top:20px;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("    transition: background-color 0.3s;");
        out.println("}");
        out.println(".button:hover {");
        out.println("    background-color: #45a049;");
        out.println("}");
        out.println(".remove-button {");
        out.println("    padding: 10px 20px;");
        out.println("    font-size: 14px;");
        out.println("    color: #fff;");
        out.println("    background-color: #e53935;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("    transition: background-color 0.3s, color 0.3s;");
        out.println("    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);");
        out.println("}");
        out.println(".remove-button:hover {");
        out.println("    background-color: #c62828;");
        out.println("}");
        out.println(" button {\r\n"
        		+ "            padding: 10px 20px;\r\n"
        		+ "            font-size: 16px;\r\n"
        		+ "            color: #fff;\r\n"
        		+ "            background-color: #4CAF50;\r\n"
        		+ "            border: none;\r\n"
        		+ "            border-radius: 5px;\r\n"
        		+ "            cursor: pointer;\r\n"
        		+ "            transition: background-color 0.3s;\r\n"
        		+ "        }\r\n"
        		+ "        button:hover {\r\n"
        		+ "            background-color: #45a049;\r\n"
        		+ "        }");
        out.println(" h2 {\r\n"
        		+ "            color: #333;\r\n"
        		+ "            text-align: center;\r\n"
        		+ "        }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class='container'>");

        out.println("<h1>Cart</h1>");
        double totalPrice = 0.0;
        String query1 = "SELECT SUM(price) AS total_price FROM cart";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM cart";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String type = resultSet.getString("type");
                        String description = resultSet.getString("description");
                        double price = resultSet.getDouble("price");
                        String productId = resultSet.getString("product_id");
                        String imageName = description.replace(" ", "");
                        String imageUrl = "Assets/"  + imageName + ".jpg";
                        out.println("<div class='cart-item'>");
                        out.println("<img src='" + imageUrl + "' class='item-image' alt='Product Image'>");
                        out.println("<div class='item-details'>");
                        out.println("<h3>" + description + "</h3>");
                        out.println("<p>Price: " + price + "</p>");
                        out.println("<button class='remove-button' onclick='removeFromCart(\"" + productId + "\")'>Remove</button>");
                        
                        out.println("</div>");
                        out.println("</div>");
                    }
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(query1)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        totalPrice = resultSet.getDouble("total_price");
                    }
                }
            }
           
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
      
         // Side panel for bill
        out.println("<div id='bill-panel'>");
        out.println("<h2>Bill</h2>");
        out.println("<p>Total Price: " + totalPrice + "</p>");
        out.println("</div>");
        out.println("  <h2>Payment Options</h2>");
        out.println("<button onclick='payWithCOD(" + ProductId + ")'>Pay with COD</button>");
        out.println("<button onclick='payWithCard(" + ProductId + ")'>Pay with Card</button>");
 out.println("</div>");
        out.println("<script>");
        out.println(" function payWithCOD(productId) {\r\n"
        		+ "    // Redirect to COD payment page with productId parameter\r\n"
        		+ "    window.location.href = 'codPage.jsp?productId=' + productId;\r\n"
        		+ "}\r\n"
        		+ "\r\n"
        		+ "function payWithCard(productId) {\r\n"
        		+ "    // Redirect to card payment page with productId parameter\r\n"
        		+ "    window.location.href = 'cardPage.jsp?productId=' + productId;\r\n"
        		+ "}");
        out.println("function removeFromCart(productId) {");
        out.println("if (confirm('Are you sure you want to remove this item from your cart?')) {");
        out.println("window.location.href = 'removeItem?productId=' + productId;");
        out.println("}");
        out.println("}");
        out.println("</script>");

        out.println("</body>");
        out.println("</html>");
    }
}
