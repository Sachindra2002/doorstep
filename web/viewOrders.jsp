<%-- 
    Document   : viewOrders
    Created on : Jan 25, 2021, 1:39:26 PM
    Author     : Sachindra Rodrigo
--%>

<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/viewOrders.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="orderTableContainer">
            <h3>View Orders</h3>
            <form method="GET" action="AdminController" style="text-align: center; margin-bottom: 10px; font-family: sans-serif;">
                <input type="hidden" name="command" value="SORTORDERS">
                <p style="color: black; font-size: 20px;">Select a sorting criteria and click Search to get results</p>
                <select name ="orderDate" class="search_categories">
                    <option name="Ascending">Ascending</option>
                    <option name="Descending">Descending</option>
                </select>
                <input type="submit" value="Search" class="btn" style="background-color: white;">
            </form>
            <table align="center" class="orderTable">
                <tr>
                    <th>ID</th>
                    <th>Order Date</th>
                    <th>Order Type</th>
                    <th>Total Price of Order</th>
                    <th>Order Made By</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <c:url var="viewOrderInfo" value="StockManagerController">
                        <c:param name="COMMAND" value="VIEW_ORDER_INFO"/>
                        <c:param name="orderID" value="${order.orderID}"/>
                        <c:param name="customerEmailAddress" value="${order.customerEmailAddress}"/>
                    </c:url>
                    <tr>
                        <td>${order.orderID}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.orderType}</td>
                        <td>LKR - ${order.totalPrice}</td>
                        <td>${order.customerEmailAddress}</td>
                        <td><a class="linkButton" href="${viewOrderInfo}">View Details</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
