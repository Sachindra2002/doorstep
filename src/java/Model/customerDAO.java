/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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

    public boolean checkUserExist(String email) {
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
            PreparedStatement ps = connection.prepareStatement(" insert into users (firstName ,lastName ,email ,password ,address ,dob ,phone ,role ,profilePic)" + " values (?,?,?,?,?,?,?,?,?)");
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

    public boolean verifyCard(String cardnumber, String cvv, String email) {
        try
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM payments");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                String Email = rs.getString("userID");
                String CardNumber = rs.getString("cardnumber");
                String Cvv = rs.getString("cvv");                
                if(Email.equalsIgnoreCase(email) && CardNumber.equalsIgnoreCase(cardnumber) && Cvv.equals(cvv))
                {
                    return true;
                }else
                {
                    return false;
                }
            }
        }catch (Exception ex)
        {
            System.out.println("Error Authenticating card: " + ex);
        }
        return false;
    }

    public boolean placeOrder(Order_Product theCart) {
        try {
            Order theOrder = theCart.getTheOrder(); //retrieve objects from the cart object
            List<Items> productList = theCart.getProductList();
            //creating the order information in the database
            PreparedStatement theOrderCreation = connection.prepareStatement("insert into orders(OrderDate,acceptedStatus,orderType,customerEmailAddress,totalPrice) values(?,?,?,?,?)");
            theOrderCreation.setString(1, theOrder.getOrderDate());
            theOrderCreation.setString(2, theOrder.getAcceptedStatus());
            theOrderCreation.setString(3, theOrder.getOrderType());
            theOrderCreation.setString(4, theOrder.getCustomerEmailAddress());
            theOrderCreation.setString(5, String.valueOf(theOrder.getTotalPrice()));

            theOrderCreation.executeUpdate(); //create the order info in table
            addToOrderProduct(productList); //this method is used to insert the product information for each order

            return true;
        } catch (Exception ex) {
            System.out.println("Error placing order in dao: " + ex);
        }
        return false;
    }
    
    private void addToOrderProduct(List<Items> productList) {
        //method used to fill the products purchased for each order
        try {
            String NewOrderID = getOrderID();

            for (Items p : productList) {
                //insert each product information into the table
                PreparedStatement insertOrder_Product = connection.prepareStatement("insert into order_product(OrderID,ProductID,quantityOfProduct) values (?,?,?)");
                insertOrder_Product.setString(1, NewOrderID);
                insertOrder_Product.setString(2, p.getItemId());
                insertOrder_Product.setInt(3, p.getItemQty());

                insertOrder_Product.executeUpdate();
            }

            updateProductAfterOrder(productList);
        } catch (Exception ex) {
            System.out.println("Error adding to order_product: " + ex);
        }
    }
    
    private String getOrderID() {
        try {
            PreparedStatement theStatement = connection.prepareStatement("select orderID from orders ORDER BY orderID DESC Limit 1"); //get the last inserted order id
            ResultSet orderIDQuery = theStatement.executeQuery();
            orderIDQuery.next();
            int currentOrderID = orderIDQuery.getInt("orderID");

            return String.valueOf(currentOrderID); //retrun the string value of the order id

        } catch (Exception ex) {
            System.out.println("Error fetching order id: " + ex);
        }
        return null;
    }
    
    private void updateProductAfterOrder(List<Items> productList) {
        //method used to set the quantity for the products after placing the order
        try {
            for (Items product : productList) {
                PreparedStatement prepObject1 = connection.prepareStatement("select quantity from product where productID=?");
                prepObject1.setString(1, product.getItemId());

                ResultSet rs = prepObject1.executeQuery();
                while (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    //update the quantity of the products stored in the product table
                    int newQuantity = quantity - product.getItemQty();

                    PreparedStatement prepObject2 = connection.prepareStatement("update product set quantity = ? where productID=?");
                    prepObject2.setInt(1, newQuantity);
                    prepObject2.setString(2, product.getItemId());

                    prepObject2.executeUpdate();
                }

            }

        } catch (Exception ex) {
            System.out.println("Error updating: " + ex);
        }
    }


    
}
