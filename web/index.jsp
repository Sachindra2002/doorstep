<%-- 
    Document   : index
    Created on : Nov 28, 2020, 7:19:08 PM
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
        <link type="text/css" rel="stylesheet" href="CSS/index.css">
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
                <a href="#">Sign in</a>
                <a href="#">Add your restaurant</a>
                <a href="#">Log to your restaurant</a>
                <a id="sidebarbottom" href="#">Contact Us</a>
                <a  href="#">About Us</a>
            </div>
            <div id="main">

                <button class="openbtn" onclick="openNav()">☰</button> 
                <div class="centerHeader">
                    <a href="#"><img src="Images/Capture3.PNG"  width="230"></a>
                </div>
                <a id="right" href="#">Sign in</a>
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
        <div class="buttons">
            <div class="center">
                <a href="#">
                    <button class="button">Create new Account</button>
                </a>
            </div>
            <div class="center">
                <a href="#">
                    <button class="button">Sign in</button>
                </a>
            </div>
            <div class="center">
                <a href="#">
                    <button class="button">Continue as guest</button>
                </a>
            </div>
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
        </script>
    </body>
</html>
