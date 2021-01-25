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
        <div class="centerHeader">
            <a href="AdminController"><img src="Images/Capture3.PNG"  width="230"></a>
            <link type="text/css" rel="stylesheet" href="CSS/addFoodToMenu.css">
        </div>
        <h1 style="text-align: center; margin-top: 50px;">Add Food To Restaurant Menu</h1>
        <div class="container">
            <form action="AdminController" method="GET">
                <input type="hidden" name="command" value="ADDFOOD"/>
                 <label for="itemName">Select Restaurant to add food</label>
                <select name="restaurant" style="margin-left: 200px; font-size: 20px;">
                    <option value="Burger Hut">Burger Hut</option>
                    <option value="Curry Pot">Curry Pot</option>
                    <option value="La Pizzeria">La Pizzeria</option>
                    <option value="O.F.C">O.F.C</option>
                    <option value="Banana Leaf">Banana Leaf</option>
                    <option value="Hoppers">Hoppers</option>
                </select>
                <label for="itemName">Name of food</label>
                <input type="text" name="itemName"  required />
                <label for="itemName">Select Category of item</label>
                <select name="category" style="margin-left: 200px; font-size: 20px;">
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
