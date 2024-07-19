<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
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
                           
                            <li><a href="cart"><img src="Assets/cart.png" alt="cart" height="27px" width="27px"></a></li>
                      <!--  <li><a href=""><img src="Assets/Search.png" alt="search" height="27px" width="27px"></a></li> -->
                           
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
</body>
</html>