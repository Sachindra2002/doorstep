<%-- 
    Document   : customercart
    Created on : Jan 20, 2021, 9:27:23 PM
    Author     : Sachindra Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>doorstep</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="CSS/customercart.css">
        <link rel="icon" href="Images/Capture4.PNG">
        <script >

        </script>
    </head>
    <body>
        <c:url var="viewcart" value="CustomerController">
            <c:param name="command" value="VIEWCART"/>
        </c:url>

        <div class="topnav" id="mytopnav">
            <div id="mySidebar" class="sidebar">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
                <a href="#">Sign Out</a>
                <a href="#">Your Orders</a>
                <a href="${viewcart}">Your Cart</a>
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
        <div class="Cartcontainer">
            <h1 class="maintopic">Minimum Cart Value - LKR 3000</h1>
            <form action="CustomerController" method="POST">
                <input type="hidden" name="COMMAND" value="FinalizeCart">

                <p class="labels">Total Amount Payable (LKR) -:</p>

               
                <div class="centerin"><input type="number" class="inputBox" id="cartAmount" name="totalCartAmount" value="<%=request.getAttribute("totalPriceOfCart")%>" readonly/></div>
                 <p class="labels">Payment Type</p>
                <div class="centerin"><select class="inputBox" name="paymentType">
                        <option name="creditcard">Credit Card</option>
                        <option name="ondelivery">Cash On delivery</option>
                    </select></div>
                <div class="centerin"><input type="submit" id="order" class="button" value="Place Order" onclick="checkCart()"></div>

            </form>
        </div>
        <h2 class="topic">Your Cart</h2>
        <c:forEach var="product" items="${cartProducts}">
            <c:url var="removeProduct" value="CustomerController">
                <c:param name="productID" value="${product.itemId}"/>
                <c:param name="COMMAND" value="REMOVE_PRODUCT_FROM_CART"/>
            </c:url> 
            <c:url var="minusProduct" value="CustomerController">
                <c:param name="productID" value="${product.itemId}"/>
                <c:param name="COMMAND" value="MINUS_FROM_CART"/>
            </c:url> 
            <c:url var="addOneMore" value="CustomerController">
                <c:param name="productID" value="${product.itemId}"/>
                <c:param name="COMMAND" value="ADD_ONE_MORE"/>
            </c:url> 
            <div class="product">
                <div>${product.itemName}</div><div>LKR - ${product.unitPrice}</div>

                <div>${product.itemQty}</div>
                <div>LKR - ${product.totalPriceInCart}</div>
                <div><a class="linkButton" href="${removeProduct}">X</a> <a class="linkButton" href="${addOneMore}">+
                    </a><a class="linkButton" href="${minusProduct}">&#x2012;</a></div>
            </div>
        </c:forEach>
        <script>
            function checkCart() {
                var total = document.getElementById("cartAmount");
                if (total.value >= 3000) {
                    document.getElementById("order").disabled = false;
                } else {
                    document.getElementById("order").disabled = true;
                }
            }

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
