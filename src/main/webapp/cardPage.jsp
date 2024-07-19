<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pay with Card </title>
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
        <h1>Pay with Card</h1>
        <p>Your order will be processed with Card.</p>
        <!-- Form to collect user address -->
      <form id="payment-form" action="PaymentServlet" method="post">
        <input type="hidden" name="productId" value="<%= request.getParameter("productId") %>">
        <label for="card-number" class="input-label">Card Number</label>
        <input type="text" id="card-number" name="card-number" required class="input-field" pattern="[0-9]{14	,16}">

        <label for="expiry" class="input-label">Expiration Date</label>
        <input type="text" id="expiry" name="expiry" placeholder="MM/YY" required class="input-field" pattern="\d{2}\/\d{2}">

        <label for="cvv" class="input-label">CVV</label>
        <input type="password" id="cvv" name="cvv" required class="input-field" pattern="\d{3}">

        <!-- Add more fields if needed (e.g., cardholder name, billing address) -->

        <button type="button" class="btn" onclick="validateForm()">Pay with Card</button>
    </form>

    </div>
    <script>
    function validateForm() {
        var cardNumber = document.getElementById("card-number").value;
        var expiry = document.getElementById("expiry").value;
        var cvv = document.getElementById("cvv").value;

        // Card number pattern: 12 digits
        var cardNumberPattern = /^\d{12}$/;
        // Expiry pattern: MM/YY, month from 01 to 12, year from 25 to 35
        var expiryPattern = /^(0[1-9]|1[0-2])\/(2[5-9]|3[0-5])$/;
        // CVV pattern: 3 digits
        var cvvPattern = /^\d{3}$/;

        if (!cardNumberPattern.test(cardNumber)) {
            alert("Please enter a valid 12-digit card number");
            return false;
        }

        if (!expiryPattern.test(expiry)) {
            alert("Please enter a valid expiry date (MM/YY) Year from 2025-2035");
            return false;
        }

        if (!cvvPattern.test(cvv)) {
            alert("Please enter a valid CVV (3 digits)");
            return false;
        }

        // If all validations pass, submit the form
        document.getElementById("payment-form").submit();
    }
    </script>
</body>
</html>
