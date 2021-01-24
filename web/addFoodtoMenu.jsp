<%-- 
    Document   : addFoodtoMenu
    Created on : Jan 19, 2021, 3:59:19 PM
    Author     : Sachindra Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <form action="AdminController" method="GET">
                <input type="hidden" name="command" value="ADDFOOD" 
                <label for="itemName">Name of food</label>
                <input type="text" name="itemName"  required />
                <select name="category">
                    <option value="srilankan">Sri-Lankan</option>
                    <option value="indian">Indian</option>
                    <option value="italian">Italian</option>
                    <option value="japanese">Japanese</option>
                    <option value="fastfood">Fast Food</option>
                    <option value="chinese">Chinese</option>
                    <option value="bakery">Bakery</option>
                </select>
                <label for="itemprice">Price per item</label>
                <input type="number" name="itemprice"  required />
                <input type="submit" class="button" name="submit" value="Add To Menu!">
            </form>
        </div>
    </body>
</html>
