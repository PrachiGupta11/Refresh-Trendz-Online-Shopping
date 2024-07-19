        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Refresh Trendz</title>
            <link rel="stylesheet" href="CSS_Files/RefreshTrendz.css">
            <link rel="stylesheet" href="CSS_Files/circle.css">
            <style>
            	.profile{
        	position: relative;
        }
        .profile .useracc{
        	display: flex;
		    align-items: center;
		    border: 1px solid transparent;
		    box-sizing: border-box;
		    padding: 8px;
        }
        .profile:hover .useracc{
        	background: #fafafa;
   			border-radius: 8px;
  		    border-color: #e0e0e0;
        } 
       
        .profile .list{
        	position: absolute;
		    top: 100%;
		    padding: 0; 
		    margin: 0;
		    list-style: none;
		    background-color: #fff; 
		    border: 1px solid #f0f0f0;
		    min-width: 100px;
		    width: 160px;
		    box-shadow: 0 4px 16px 0 rgb(0 0 0/20%);
		    z-index: 100; 
		    display: none;
		    border-bottom-left-radius: 8px;
		    border-bottom-right-radius: 8px;  
        }
        .profile:hover .arrow{
        	transform: rotate(180deg);
   			transition: transform .3s ease-in-out;
        }
        .profile:hover .list{
        	display:block;
        }
        .listlink{
        	color: #000;
		    text-decoration: none; 
		    text-align: center; 
		     font-size: 14px;
		    line-height: 20px;
		    cursor: pointer; 
        }
       .listitem:last-child{
        	border-bottom: none;
        }
        .listitem:first-child{
        	border-bottom: none;
        } 
       
        .list{
        	background-color:black;
        	/* padding:10px; 
        	 margin: 10px; */
        	  display: flex;
        	align-items:left; 
        	
        }
        .listlink{
        	/* background-color: lightpink; */
        	 display: flex;
        	/*  padding:10px; 
        	  margin: 10px; */
        	 /*  row-gap:10px; */
        	align-items:left; 
        	justify-content:left;
        }
        .listitem{
        	 /* background-color: skyblue;  */ 
        	 padding: 8px 12px;
        	
		     border-bottom: 1px solid #f0f0f0; 
		    display: flex;
		     align-items: left;
        }
          .listitemdiv{
          		display: flex;
          }
          .listitemdiv a{
          	text-decoration:none;
          	font-size: 12px;
		    font-weight: 600;
		    font-family: 'arial';
		    color: black;
		    letter-spacing: 0.5px;
		    text-transform: capitalize;
		     text-align: center;  
		   /*  background-color:lightpink; */
		    line-height: 20px;
		    cursor: pointer; 
          }
          
            </style>
        </head>
        <body>
            <header>
                <div id="flex-container">
                    <div class="leftheader">
                        <img src="Assets/RefreshTrendzLogo.png" alt="RefreshTrendzLogo">
                    </div>
                    <div class="rightheader">
                
                    <ul>       
                            <li><a href="">Home</a></li>
                            <li><a href="">Product</a></li>
                            <li><a href="">Contact</a></li>
                            <li><a href="cart"><img src="Assets/cart.png" alt="cart" height="27px" width="27px"></a></li>
                            <li><a href=""><img src="Assets/Search.png" alt="search" height="27px" width="27px"></a></li>
                            <li><%// Retrieve username from session attribute
    String username = (String) session.getAttribute("username");
    if (username != null) { 
    %>
        
        <!-- Add personalized content or options for authenticated users -->
          
       <div class="profile">
                    <div class="useracc">
                        <a class="account">
                            <a><img src="Assets/userprofile.png" height="20px" width="20px" style="margin-right: 8px;"></a>
                            <span><strong>Account</strong></span>
                        </a>
                        <img src="Assets/arrow.png" class="arrow" height="16px" width="16 px">
                    </div>
                    <ul class="list">
                        
                        	<li class="listitem">
	                            <div class="listitemdiv">
	                            	<a href="userProfile"><img src="Assets/userprofile.png" height="18px" width="18px" ></a>
	                           		<a href="userProfile" style="padding-left:10px">My Profile</a>
	                            </div>
                        	</li>
                        
                        	<li class="listitem">
	                           <div class="listitemdiv">
	                           		 <a><img src="Assets/logout.png" height="18px" width="18px"></a>
	                            	 <a href="logoutUser" style="padding-left:10px;">Logout</a>
	                           </div>
                       		</li>     
                    </ul>    
           </div>
        
    <% } else { %>
        
        <!-- Add login form or prompt for unauthenticated users -->
       <a href="Login.html" class="loginbtn">Log in</a>
    <% } %></li>
    <!-- <li><a href=""><img src="Assets/cart.png" alt="cart" height="27px" width="27px"></a></li>
                            <li><a href=""><img src="Assets/Search.png" alt="search" height="27px" width="27px"></a></li> -->
                    </ul>
                    </div>
          
                </div>
            </header>
            <div id="banner1">
                <div id="subBanner1">
                    <h1> <span style="color: #f7444e;">Sale 20% Off</span>
                        <span style="color: #002c3e;">On Everything</span> </h1>
                    <p>Explicabo esse amet tempora quibusdam laudantium, laborum eaque magnam fugiat hic? Esse dicta aliquid error repudiandae earum suscipit fugiat molestias, veniam, vel architecto veritatis delectus repellat modi impedit sequi.</p>
                    <button type="submit"><a href="">Shop Now</a></button>
                </div>
            </div>    
            <div id="features">
                <h1 class="shopHeading">Why Shop With Us</h1>
                <hr>
                <div id="flex-container2">
                    <div class="shop">
                        <img src="Assets/DeliveryTruck.png" alt="Truck">
                        <h5>Fast Delivery</h5>
                        <p>variations of passages of Lorem Ipsum available</p>
                    </div>
                    <div class="shop">
                        <img src="Assets/FreeShipping.png" alt="Free-shipping">
                        <h5>Free Shipping</h5>
                        <p>variations of passages of Lorem Ipsum available</p>
                    </div>
                    <div class="shop">
                        <img src="Assets/BestQuality.png" alt="best-quality">
                        <h5>Best Quality</h5>
                        <p>variations of passages of Lorem Ipsum available</p>
                    </div>
                </div>
            </div>
            <div id="banner2">
                <div id="flex-container3">
                    <h1>#NewArrivals</h1>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit natus nam dolorem voluptatibus similique quidem, labore error aut saepe inventore? Sint dolor beatae non tempore vel illo consequuntur vitae magnam.</p>
                    <button type="submit"><a href="">Shop Now</a></button>
                </div>
            </div>
            <div id="products">
                <h1>Our <span style="color: #f7444e;">products</span> </h1>
                <hr>
                    
              		<section>
    	<div class="circle">
			<div class="item">
				<div class="cir">
					<a href="ItemListServlet?type=dress"><img
						src="Assets/ComboOf4_Dresses.jpg" alt="dress"></a>
				</div>
				<div class="box">Dress</div>
			</div>
			<div class="item">
				<div class="cir">
					<a href="ItemListServlet?type=jeans"><img
						src="Assets/SlimFitDenimJeans.jpg" alt="Jeans"></a>
				</div>
				<div class="box">Jeans</div>
			</div>
			<div class="item">
				<div class="cir">
					<a href="ItemListServlet?type=skirt"><img src="Assets/KaliPatternLongSkirt.jpg"
						alt="skirt"></a>
				</div>
				<div class="box">Skirt</div>
			</div>
			<div class="item">
				<div class="cir">
					<a href="ItemListServlet?type=mens_shirt"><img src="Assets/MensShirtCreamColor.jpg"
						alt="men's shirt"></a>
				</div>
				<div class="box">Men's Shirt</div>
			</div>
			<div class="item">
				<div class="cir">
					<a href="ItemListServlet?type=mens_trouser"><img src="Assets/HikingTrouser.jpg"
						alt="men's trouser"></a>
				</div>
				<div class="box">Men's Trouser</div>
			</div>
			
		
		</div>
		
    </section> 
                
                
            </div>
            <div id="banner3">
                <div> 
                    <a href="#"><img src="Assets/RefreshTrendzLogo.png" alt="RefreshTrendzLogo"></a>
                    <p> <strong>TELEPHONE:</strong> 1112223330</p>
                    <p> <strong>EMAIL:</strong> RefreshTrendz@gmail.com</p>
                </div>
                <div>
                    <p style="font-size: 20px;"> <strong>MENU</strong> </p>
                    <ul>
                        <li><a href="">Home</a></li>
                        <li><a href="">About</a></li>
                        <li><a href="">Contact</a></li>
                    </ul>
                </div>
                <div>
                    <p style="font-size: 20px;"> <strong>ACCOUNT</strong> </p>
                    <ul>
                        <li><a href="Login.html">Account</a></li>
                        <li><a href="">Checkout</a></li>
                        <li><a href="Login.html">Login</a></li>
                        <li><a href="SignIn.html">Register</a></li>
                        <li><a href="">Shopping</a></li>
                    </ul>
                </div>
            </div>
            <footer id="footer">
                <label>&copy; All Rights Reserved By Refresh Trendz</label>
            </footer>
        </body>
        </html>