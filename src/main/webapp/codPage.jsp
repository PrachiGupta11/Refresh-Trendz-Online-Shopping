<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cash on Delivery</title>
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
            max-width: 600px; /* Increased width of the container */
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
        .input-label {
            display: inline-block; /* Display label and input on the same line */
            width: 120px; /* Adjust label width */
            text-align: left;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .input-field {
            width: calc(100% - 140px); /* Adjust input field width */
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
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
        <h1>Cash on Delivery</h1>
        <p>Your order will be processed with Cash on Delivery.</p>
        <!-- Form to collect user address -->
        <form action="AddressServlet" method="post">
        	 <label for="houseNo" class="input-label">House No:</label>
    <input type="text" id="houseNo" name="houseNo" class="input-field" placeholder="House No"><br>
    <label for="streetNo" class="input-label">Street No:</label>
    <input type="text" id="streetNo" name="streetNo" class="input-field" placeholder="Street No"><br>
     	
             <label for="city" class="input-label">City:</label>
            <input type="text" id="city" name="city" class="input-field" placeholder="City"><br>
            <label for="state" class="input-label">State:</label>
            <input type="text" id="state" name="state" class="input-field" placeholder="State"><br>
            <label for="pincode" class="input-label">Pincode:</label>
            <input type="text" id="pincode" name="pincode" class="input-field" placeholder="Pincode"><br>
            <label for="country" class="input-label">Country:</label>
            <input type="text" id="country" name="country" class="input-field" placeholder="Country"><br>
            <button type="submit" class="btn">Confirm Order</button>
        </form>
    </div>
</body>
</html>
