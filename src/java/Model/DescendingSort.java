/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sachindra Rodrigo
 */
public class DescendingSort implements StrategySort {

    private final Connection connection;

    public DescendingSort() {
        //Get the singleton database_connection by using the static method
        Database Database_Connection = Database.getDBConnection();
        //connect to the database by using the object
        connection = Database_Connection.connectToDatabase();
    }

    @Override
    public List<Order> SortOrderDate() {
        ArrayList<Order> orderIDList = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from orders where acceptedStatus = ? ORDER BY OrderDate DESC;");
            ps.setString(1, "Pending");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String orderID = rs.getString("OrderID");
                String orderDate = rs.getString("OrderDate");
                String orderType = rs.getString("orderType");
                Double totalPrice = Double.parseDouble(rs.getString("totalPrice"));
                String customerEmailAddress = rs.getString("email");

                Order orderObject = new Order(orderID, orderDate, orderType, totalPrice, customerEmailAddress);

                orderIDList.add(orderObject);
            }

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return orderIDList;

    }

}
