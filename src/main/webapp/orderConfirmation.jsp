<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Confirmation</title>
<style>
body {
  font-family: Arial, sans-serif;
  background-color: #f5f5f5;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.container {
  max-width: 500px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 40px;
  text-align: center;
}
.container h1 {
  margin-bottom: 20px;
  color: #333;
}
.container p {
  color: #666;
  margin-bottom: 30px;
}
.btn {
  background-color: #4CAF50;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.btn:hover {
  background-color: #45a049;
}
</style>
</head>
<body>
<div class="container">
  <h1>Your order has been successfully placed!</h1>
  <p>Thank you for shopping with us.</p>
  <a href="index.jsp" class="btn">Continue Shopping</a>
</div>
</body>
</html>
