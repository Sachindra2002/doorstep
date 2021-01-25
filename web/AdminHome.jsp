<%-- 
    Document   : AdminHome
    Created on : Jan 10, 2021, 5:55:16 PM
    Author     : Sachindra Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="CSS/adminHome.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="centerHeader">
            <a href="AdminController"><img src="Images/Capture3.PNG"  width="230"></a>
        </div>
    <c:url var="orders" value="AdminController">
        <c:param name="command" value="VIEWALLORDERS"/>
    </c:url>
    <div class="container">
        <a href="${orders}"><div class="right" style="margin-left: 190px;">View Orders</div></a>
        <a href="addRestaurant.jsp"><div class="right">Add Restaurants</div></a>
        <a href="addFoodtoMenu.jsp"><div class="right">Add Food to Restaurants</div></a>
        <a href="#"><div class="right">View Inquiries</div></a>
        <a href="LogoutController"><div class="right" style="color: red;">Logout</div></a>
    </div>
</body>
</html>
