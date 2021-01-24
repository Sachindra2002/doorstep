/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Sachindra Rodrigo
 */
public class AdminDAO implements UserDAO {
    
    private final Connection connection;

    public AdminDAO() {
        //Get the singleton database_connection by using the static method
        Database Database_Connection = Database.getDBConnection();
        //connect to the database by using the object
        connection = Database_Connection.connectToDatabase();
    }

    private ArrayList<Users> getAllCustomers() {
        return null;
    }

    public boolean addItem(Items item) {
        try {
            PreparedStatement ps = connection.prepareStatement(" insert into menu (itemName,itemCategory,unitPrice,itemQty,totalPrice,restaurant, itemPic)" + " values (?,?,?,?,?,?,?)");
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemCategory());
            ps.setDouble(3, item.getUnitPrice());
            ps.setInt(4, item.getItemQty());
            ps.setDouble(5, item.getTotalPriceInCart());
            ps.setObject(6, item.getRestaurant().getRestaurantName());
            ps.setString(7, item.getItemPic());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean addRestaurant(Restaurants restaurant) {
        try {
            PreparedStatement ps = connection.prepareStatement(" insert into restaurants (restaurantName,restaurantPic,restaurantPhone,restaurantEmail,restaurantAddress,restaurantStatus, category,city,ratings,password)" + " values (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getRestaurantPic());
            ps.setString(3, restaurant.getRestaurantPhone());
            ps.setString(4, restaurant.getRestaurantEmail());
            ps.setString(5, restaurant.getRestaurantAddress());
            ps.setString(6, restaurant.getRestaurantStatus());
            ps.setString(7, restaurant.getRestaurantCategory());
            ps.setString(8, restaurant.getRestaurantCity());
            ps.setInt(9, restaurant.getRestaurantRatings());
            ps.setString(10, restaurant.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    
}
