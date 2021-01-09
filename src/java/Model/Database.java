/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sachindra Rodrigo
 */
public class Database {
    private final static Database DATABASE_CONNECTION = new Database();    
    private Connection connection;
    private final String URL, username, password;

    // Private constructor to make sure class cannot be instantiated seperately.
    private Database() {
        URL = "jdbc:mysql://localhost:3306/doorstep";
        username = "root";
        password = "root";
    }
    
    //Static method to get the created instance of the singleton object
    public static Database getDBConnection() {
        return DATABASE_CONNECTION;
    }

    /* To connect to the database and get the connection*/
    public Connection connectToDatabase() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // New connection is made to the database.
            connection = DriverManager.getConnection(URL, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            // Any errors are caught and displayed
            e.printStackTrace();
        }

        // The connection is returned
        return connection;
    }
    
}
