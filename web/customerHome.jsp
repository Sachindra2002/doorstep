<%-- 
    Document   : customerHome
    Created on : Jan 10, 2021, 5:54:27 PM
    Author     : Sachindra Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>doorstep</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="CSS/customerHome.css">
        <link rel="icon" href="Images/Capture4.PNG">
    </head>
    <body>
        <!--<header>
            <div class="centerHeader">
                <img src="images/Capture3.PNG"  width="230">
            </div>
        </header>-->
        <div class="topnav" id="mytopnav">
            <div id="mySidebar" class="sidebar">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
                <a href="#">Sign Out</a>
                <a href="#">Your Orders</a>
                <a href="#">Your Profile</a>
                <a id="sidebarbottom" href="#">Contact Us</a>
                <a  href="#">About Us</a>
            </div>
            <div id="main">

                <button class="openbtn" onclick="openNav()">☰</button> 
                <div class="centerHeader">
                    <a href="#"><img src="Images/Capture3.PNG"  width="230"></a>
                </div>
                <a id="right" href="#">Sign Out</a>
                <div class="search-container">
                    <form action="#">
                        <input type="text" placeholder="Search.." name="search">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
            </div>
        </div>
        <div class="banner">
            <img src="Images/banner.png" width="1300">
        </div>
        <h2 class="greetings"><div id="time"></div><%=session.getAttribute("firstName")%> <%=session.getAttribute("lastName")%></h2>
        <div class="categories">

        </div>
        <h2 class="topic">Popular Near You</h2>
        <div class="Popular">
            <c:forEach var="restaurant" items="${RESTAURANT_LIST}">
                <c:url var="orderAction" value="OrderController">
                    <c:param name="command" value="VIEWMENU"/>
                    <c:param name="restaurantPic" value="${restaurant.restaurantPic}"/>
                    <c:param name="restaurantName" value="${restaurant.restaurantName}"/>
                    <c:param name="restaurantAddress" value="${restaurant.restaurantAddress}"/>
                    <c:param name="restaurantPhone" value="${restaurant.restaurantPhone}"/>
                    <c:param name="restaurantEmail" value="${restaurant.restaurantEmail}"/>
                    <c:param name="restaurantCategory" value="${restaurant.restaurantCategory}"/>
                    <c:param name="restaurantStatus" value="${restaurant.restaurantStatus}"/>
                </c:url>
                <div class="restaurantBox">
                    <img src="${restaurant.restaurantPic}" alt="image" width="280"/>
                    <div><h3 class="restaurantName">${restaurant.restaurantName}</h3></div>
                    <div class="category">${restaurant.restaurantCategory}</div>
                    <div class="status">Status - ${restaurant.restaurantStatus}</div> 
                    <div class="order"><a href="${orderAction}">Order Now</a></div>
                </div>
            </c:forEach>
        </div>
        <h2 class="topic">Sri-Lankan Culture</h2>
        <div class="srilankan">
            <c:forEach var="srilankan" items="${SRILANKAN_LIST}">
                <c:url var="orderAction" value="OrderController">
                    <c:param name="command" value="VIEWMENU"/>
                    <c:param name="restaurantPic" value="${srilankan.restaurantPic}"/>
                    <c:param name="restaurantName" value="${srilankan.restaurantName}"/>
                    <c:param name="restaurantAddress" value="${srilankan.restaurantAddress}"/>
                    <c:param name="restaurantPhone" value="${srilankan.restaurantPhone}"/>
                    <c:param name="restaurantEmail" value="${srilankan.restaurantEmail}"/>
                    <c:param name="restaurantCategory" value="${srilankan.restaurantCategory}"/>
                    <c:param name="restaurantStatus" value="${srilankan.restaurantStatus}"/>
                </c:url>
                <div class="restaurantBox">
                    <img src="${srilankan.restaurantPic}" alt="image" width="280"/>
                    <div><h3 class="restaurantName">${srilankan.restaurantName}</h3></div>
                    <div class="category">${srilankan.restaurantCategory}</div>
                    <div class="status">Status - ${srilankan.restaurantStatus}</div> 
                    <div class="order"><a href="${orderAction}">Order Now</a></div>
                </div>
            </c:forEach>
        </div>
        <h2 class="topic">Indian Culture</h2>
        <div class="srilankan">
            <c:forEach var="indian" items="${INDIAN_LIST}">
                <div class="restaurantBox">
                    <img src="${indian.restaurantPic}" alt="image" width="280"/>
                    <div><h3 class="restaurantName">${indian.restaurantName}</h3></div>
                    <div class="category">${indian.restaurantCategory}</div>
                    <div class="status">Status - ${indian.restaurantStatus}</div> 
                    <div class="order"><a href="${orderAction}">Order Now</a></div>
                </div>
            </c:forEach>
        </div>
        <button onclick="topFunction()" id="myBtn" title="Go to top">Up</button>
        <script>
            function openNav() {
                document.getElementById("mySidebar").style.width = "250px";
                document.getElementById("main").style.marginLeft = "250px";
            }

            function closeNav() {
                document.getElementById("mySidebar").style.width = "0";
                document.getElementById("main").style.marginLeft = "0";
            }


            var mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
            window.onscroll = function () {
                scrollFunction()
            };

            function scrollFunction() {
                if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                    mybutton.style.display = "block";
                } else {
                    mybutton.style.display = "none";
                }
            }

// When the user clicks on the button, scroll to the top of the document
            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }
            var myDate = new Date();
            var hrs = myDate.getHours();

            var greet;

            if (hrs < 12)
                greet = 'Good Morning';
            else if (hrs >= 12 && hrs <= 14)
                greet = 'Good Afternoon';
            else if (hrs >= 14 && hrs <= 24)
                greet = 'Good Evening';

            document.getElementById('time').innerHTML =
                    '<b>' + greet;
        </script>
    </body>
</html>







<%--<div class="restaurantBox">
    <img src="restaurantImages/pizza.jpg" alt="image" width="280"/>
    <div><h3 class="restaurantName">La Pizzeria</h3></div>
    <div class="category">Italian • Pizza</div>
    <div class="status">Status - Available</div> 
    <div class="order"><a href="#">Order Now</a></div>
</div>--%>