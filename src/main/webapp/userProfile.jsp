<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.RefreshTrendz.UserDetails" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>
</head>
<body>
<div>
    <h2>User Profile</h2>
    <% 
    UserDetails user = (UserDetails) request.getAttribute("user");
    if (user != null) {
    %>
        <p><strong>First Name:</strong> <%= user.getFirstName() %></p>
        <p><strong>Last Name:</strong> <%= user.getLastName() %></p>
        <p><strong>Email:</strong> <%= user.getEmail() %></p>
        <!-- Add more profile details as needed -->
    <% 
    } else {
        out.println("User details not found.");
    }
    %>
</div>
</body>
</html>