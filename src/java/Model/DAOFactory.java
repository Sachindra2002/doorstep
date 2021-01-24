/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Sachindra Rodrigo
 */
public class DAOFactory {
    public static UserDAO daoObject = null;

    public static UserDAO createDAO(String role) {
        if ("admin".equals(role)) {
            daoObject = new AdminDAO();
        }else if("customer".equals(role))
        {
            daoObject = new customerDAO();
        }
        return daoObject;
    }
    
}
