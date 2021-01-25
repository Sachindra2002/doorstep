<%-- 
    Document   : addRestaurant
    Created on : Jan 19, 2021, 1:29:03 PM
    Author     : Sachindra Rodrigo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="CSS/addRestaurant.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="centerHeader">
            <a href="AdminController"><img src="Images/Capture3.PNG"  width="230"></a>
        </div>
        <h1 style="text-align: center; margin-top: 50px;">Add Restaurant</h1>
        <div class="container">
            <div id="sign-up-form">
                <form id="form" action="AdminController" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="ADDRESTAURANT">
                    <div class="profile-pic-div">
                        <img src="Images/upload.jpg" id="previewPhoto" height="50">
                        <input type="file" id="file" name="photo" size="50" />
                        <label for="file" id="uploadBtn">Choose Photo</label>
                    </div>                    
                    <label for="restaurantName">Restaurant Name</label>
                    <input type="text" name="restaurantName"required />
                    <label for="category">Select Restaurant Category</label>
                    <select name="category" style="margin-left: 200px; font-size: 20px;">
                        <option value="srilankan">Sri-Lankan</option>
                        <option value="indian">Indian</option>
                        <option value="italian">Italian</option>
                        <option value="japanese">Japanese</option>
                        <option value="fastfood">Fast Food</option>
                        <option value="chinese">Chinese</option>
                        <option value="bakery">Bakery</option>
                    </select>
                    <label for="restaurantEmail">Restaurant Email</label>
                    <input type="email" name="restaurantemail"  required />
                    <span id="text"></span>
                    <label for="phone">Restaurant Phone</label>
                    <input  name="phone" maxlength="10" required>
                    <label for="city">Select Restaurant City</label>
                    <select name="city" style="margin-left: 200px; font-size: 20px;">
                        <option value="srilankan">Colombo</option>
                        <option value="indian">Negombo</option>
                    </select>
                    <label for="restaurantaddress">Restaurant Address</label>
                    <input type="text" name="restaurantaddress"  required />
                    <input type="submit" class="button" name="submit" value="Create Restaurant">
                </form>
            </div>
        </div>
        <script src="app.js"></script>
    </body>
</html>
