/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Sachindra Rodrigo
 */
public class RestaurantDAO implements UserDAO {
    private final Connection connection;
    public RestaurantDAO() {
        //Get the singleton database_connection by using the static method
        Database Database_Connection = Database.getDBConnection();
        //connect to the database by using the object
        connection = Database_Connection.connectToDatabase();
    }

    @Override
    public void update(String username, String message) {
    }

    public boolean addItem(Items item) {
        try {
            PreparedStatement ps = connection.prepareStatement(" insert into menu (itemName,itemCategory,unitPrice,itemQty,totalPrice,restaurant)" + " values (?,?,?,?,?,?)");
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemCategory());
            ps.setDouble(3, item.getUnitPrice());
            ps.setInt(4, item.getItemQty());
            ps.setDouble(5, item.getTotalPriceInCart());
            ps.setObject(6, item.getRestaurant());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
}
