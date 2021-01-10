/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sachindra Rodrigo
 */
public class DAO {
    private final Connection connection;

    public DAO() {
        //Get the singleton database_connection by using the static method
        Database Database_Connection = Database.getDBConnection();
        //connect to the database by using the object
        connection = Database_Connection.connectToDatabase();
    }

    public boolean checkUserExists(String email) {
        boolean st = false;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return st;
        
    }

    public void addUser(Users user) {
        try {
            PreparedStatement ps = connection.prepareStatement(" insert into users (firstName,lastName,email,password,address,dob, phone, role, profilePic)" + " values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getDob());
            ps.setString(7, user.getPhone());
            ps.setString(8, user.getRole());
            ps.setString(9, user.getProfilePic());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Users authenticate(Users user) throws SQLException {
        Users validatedUser;
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT firstName, lastName, email, password, address, phone, role, profilePic FROM users");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String profilePic = rs.getString("profilePic");
                
                if(email.equalsIgnoreCase(user.getEmail()) && password.equalsIgnoreCase(user.getPassword()) && role.equals("customer"))
                {
                    validatedUser = new Users(firstName, lastName, lastName, email, address, role, profilePic);
                    return validatedUser;
                }else if(email.equalsIgnoreCase(user.getEmail()) && password.equalsIgnoreCase(user.getPassword()) && role.equals("admin"))
                {
                    validatedUser = new Users(firstName, lastName, lastName, email, address, role, profilePic);
                    return validatedUser;
                }
            }
        }catch (Exception ex)
        {
            System.out.println("Error Authenticating User: " + ex);
        }
        return null;
   }

    public List<Restaurants> getPopularRestaurants() {
        List<Restaurants> popularRestaurants = new ArrayList<Restaurants>();
        try
        {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM restaurants where ratings>=?");
            ps.setInt(1, 3);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String restaurantName = rs.getString("restaurantName");
                String restaurantPic = rs.getString("restaurantPic");
                String restaurantPhone = rs.getString("restaurantPhone");
                String restaurantEmail = rs.getString("restaurantEmail");
                String restaurantAddress = rs.getString("restaurantAddress");
                String restaurantStatus = rs.getString("restaurantStatus");
                String restaurantCategory = rs.getString("category");
                Restaurants allPopularRestaurants = new Restaurants(restaurantName, restaurantPic, restaurantPhone, restaurantEmail, restaurantAddress, restaurantStatus, restaurantCategory );
                popularRestaurants.add(allPopularRestaurants);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return popularRestaurants;
    }

    public List<Restaurants> getSrilankanRestaurants() {
        List<Restaurants> srilankanRestaurants = new ArrayList<Restaurants>();
        try
        {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM restaurants where category=?");
            ps.setString(1,"srilankan");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String restaurantName = rs.getString("restaurantName");
                String restaurantPic = rs.getString("restaurantPic");
                String restaurantPhone = rs.getString("restaurantPhone");
                String restaurantEmail = rs.getString("restaurantEmail");
                String restaurantAddress = rs.getString("restaurantAddress");
                String restaurantStatus = rs.getString("restaurantStatus");
                String restaurantCategory = rs.getString("category");
                Restaurants allSrilankanRestaurants = new Restaurants(restaurantName, restaurantPic, restaurantPhone, restaurantEmail, restaurantAddress, restaurantStatus, restaurantCategory );
                srilankanRestaurants.add(allSrilankanRestaurants);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return srilankanRestaurants;
    }
    
}
