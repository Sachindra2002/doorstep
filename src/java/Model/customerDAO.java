/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Sachindra Rodrigo
 */
public class customerDAO implements UserDAO {
    private final Connection connection;
    public customerDAO() {
        //Get the singleton database_connection by using the static method
        Database Database_Connection = Database.getDBConnection();
        //connect to the database by using the object
        connection = Database_Connection.connectToDatabase();
    }

    @Override
    public void update(String username, String message) {
    }

    public Items getProductInformationForCart(String productID) {
        try {
            PreparedStatement theStatement = connection.prepareStatement("select * from menu where itemId = ?");
            theStatement.setString(1, productID);

            ResultSet theResult = theStatement.executeQuery();
            while (theResult.next()) {
                //access the content in the generated rows
                String ID = theResult.getString("itemId");
                String productType = theResult.getString("itemCategory");
                String productName = theResult.getString("itemName");
                double unitPrice = Double.parseDouble(theResult.getString("unitPrice"));

                Items theProduct = new Items(ID, productName, productType, unitPrice); //create product object
                theProduct.setItemQty(1);

                return theProduct; //return the retrieved product to the caller class
            }

        } catch (Exception ex) {
            System.out.println("Error retrieving from database: " + ex);
        }
        return null;
        
        
    }
    
}
