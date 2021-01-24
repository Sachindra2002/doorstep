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
            PreparedStatement theOrderCreation = connection.prepareStatement("insert into orders(OrderDate,acceptedStatus,orderType,email,totalPrice) values(?,?,?,?,?)");
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


    public boolean addInquiry(Inquiry inquiry) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into inquiry(userID,phone, message, date, status) values(?, ?, ?, ?, ?)");
            //creating an object of the PreparedStatement API and passing the sql INSERT query to it
            ps.setString(1, inquiry.getUserId());//set values into the inquiry table
            ps.setString(2, inquiry.getPhone());
            ps.setString(3, inquiry.getMessage());
            ps.setString(4, inquiry.getDate());
            ps.setString(5, inquiry.getStatus());
            ps.executeUpdate();//execute the above setString statements
            return true; //when insertion is done, return true to the calling class

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }

    public Order getOrder() {
        try {
            //method is used to generate an order inofrmation for a given order id
            String orderID = getOrderID(); //method implemented to retrieve the newly inserted order id

            PreparedStatement theStatement = connection.prepareStatement("select * from orders where orderID=?");
            theStatement.setString(1, orderID);

            ResultSet theResult = theStatement.executeQuery();
            while (theResult.next()) {
                //access the content in the generated rows
                String OrderID = theResult.getString("OrderID");
                String OrderDate = theResult.getString("OrderDate");
                String acceptedStatus = theResult.getString("acceptedStatus");
                String orderReadyStatus = theResult.getString("orderReadyStatus");
                String orderType = theResult.getString("orderType");
                double totalPrice = Double.parseDouble(theResult.getString("totalPrice"));
                String customerEmailAddress = theResult.getString("email");

                Order theOrder = new Order(OrderID, OrderDate, acceptedStatus, orderReadyStatus, orderType, totalPrice, customerEmailAddress);
                return theOrder; //create and return an order object
            }

        } catch (Exception ex) {
            System.out.println("Error retrieveing order: " + ex);
        }
        return null;
    }


    
}
