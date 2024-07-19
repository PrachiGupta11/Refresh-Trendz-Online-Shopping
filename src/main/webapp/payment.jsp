<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        .product-item {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        .product-item img {
            width: 100px;
            border-radius: 5px;
            margin-right: 20px;
        }
        .product-details {
            flex: 1;
        }
        .product-details p {
            margin: 5px 0;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Payment</h1>
        <!-- Display bill with product details -->
        <div id="payment-options">
            <h2>Bill Details</h2>
            <% 
                // Fetch product details from the database based on productId
                String productId = request.getParameter("productId");
                String image = "";
                String description = "";
                double price = 0.0;
                try {
                    // Establish database connection
                    String url = "jdbc:mysql://localhost:3306/refresh_trendz";
                    String user = "root";
                    String password = "root";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, user, password);
                    String query = "SELECT image, description, price FROM products WHERE product_id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, productId);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                image = resultSet.getString("image");
                                description = resultSet.getString("description");
                                price = resultSet.getDouble("price");
                            }
                        }
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            %>
            <!-- Display product details -->
            <div class="product-item">
                <img src="<%= image %>" alt="Product Image">
                <div class="product-details">
                    <p>Product Name: <%= description %></p>
                    <p>Price: $<%= price %></p>
                </div>
            </div>
            <!-- Payment options -->
            <h2>Payment Options</h2>
            <!-- Option for COD -->
            <button onclick="payWithCOD(<%= productId %>)">Pay with COD</button>
<button onclick="payWithCard(<%= productId %>)">Pay with Card</button>
        </div>
    </div>
    <script>
        // JavaScript functions for payment options
      function payWithCOD(productId) {
    // Redirect to COD payment page with productId parameter
    window.location.href = 'codPage.jsp?productId=' + productId;
}

function payWithCard(productId) {
    // Redirect to card payment page with productId parameter
    window.location.href = 'cardPage.jsp?productId=' + productId;
}
    </script>
</body>
</html>
