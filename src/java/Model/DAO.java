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
    private Connection connection;

    public DAO() {
        connection = Database.getConnection();
    }
    
}
