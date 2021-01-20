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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div id="sign-up-form">
                <form id="form" action="AddRestaurantController" method="POST" enctype="multipart/form-data">
                    <div class="profile-pic-div">
                        <img src="images/avatar.png" id="previewPhoto">
                        <input type="file" id="file" name="photo" size="50" />
                        <label for="file" id="uploadBtn">Choose Photo</label>
                    </div>                    
                    <label for="retaurantName">Restaurant Name</label>
                    <input type="text" name="retaurantName"  required />
                    <select name="category">
                        <option value="srilankan">Sri-Lankan</option>
                        <option value="indian">Indian</option>
                        <option value="italian">Italian</option>
                        <option value="japanese">Japanese</option>
                        <option value="fastfood">Fast Food</option>
                        <option value="chinese">Chinese</option>
                        <option value="bakery">Bakery</option>
                    </select>
                    <label for="restaurantemail">Restaurant Email</label>
                    <input type="email" name="restaurantemail"  required />
                    <span id="text"></span>
                    <label for="phone">Restaurant Phone</label>
                    <input type="number" name="phone" maxlength="10" required>
                    <label for="restaurantaddress">Restaurant Address</label>
                    <input type="text" name="restaurantaddress"  required />
                    <label for="password">Password</label>
                    <input id="password" type="password" name="password"  required onkeyup="checkPass();">
                    <label for="password0">Re-Enter Password</label>                    
                    <input id="password0" type="password" name="password0"  required onkeyup="checkPass();">
                    <span id="confirm-message2" class="confirm-message"></span>
                    <input type="submit" class="button" name="submit" value="Create Restaurant">
                </form>
            </div>
        </div>
    </body>
</html>
