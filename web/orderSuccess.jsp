<%-- 
    Document   : orderSuccess
    Created on : Jan 25, 2021, 6:36:01 PM
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
    <c:url var="viewcart" value="CustomerController">
        <c:param name="command" value="VIEWCART"/>
    </c:url>
    <c:url var="allorders" value="CustomerController">
        <c:param name="command" value="ORDERS"/>
    </c:url>
    <div class="topnav" id="mytopnav">
        <div id="mySidebar" class="sidebar">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
            <a href="LogoutController">Sign Out</a>
            <a href="${allorders}">Your Orders</a>
            <a href="${viewcart}">Your Cart</a>
            <a href="#">Your Profile</a>
            <a id="sidebarbottom" href="inquiry.jsp">Contact Us</a>
            <a  href="#">About Us</a>
        </div>
        <div id="main">

            <button class="openbtn" onclick="openNav()">☰</button> 
            <div class="centerHeader">
                <a href="CustomerController"><img src="Images/Capture3.PNG"  width="230"></a>
            </div>
            <a id="right" href="LogoutController">Sign Out</a>
            <div class="search-container">
                <form action="#">
                    <input type="text" placeholder="Search.." name="search">
                    <button type="submit"><i class="fa fa-search"></i></button>
                </form>
            </div>
        </div>
    </div>
    <h2 class="topic" style="margin-top: 300px; margin-left: 650px">Order successful!</h2>
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
