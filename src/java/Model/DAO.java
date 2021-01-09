/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addUser(Users user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
