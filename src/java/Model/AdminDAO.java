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


    @Override
    public void update(String username, String message) {
        ArrayList<Users> customerList = getAllCustomers();
        //initializing an arraylist of customers and catching the return from the getAllCustomer method in it
        Mail theMail = Mail.getMailInstance();
        //Creating an object  of the MailClass
        for (int i = 0; i < customerList.size(); i++) {
            theMail.sendMail("TurnOut", message, customerList.get(i).getEmail());
            //sending the mail to every customers in the array list
        }
    }

    private ArrayList<Users> getAllCustomers() {
        return null;
    }

    
}
