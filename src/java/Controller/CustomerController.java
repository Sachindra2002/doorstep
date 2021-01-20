/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.DAOFactory;
import Model.Items;
import Model.RestaurantDAO;
import Model.UserDAO;
import Model.customerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sachindra Rodrigo
 */
public class CustomerController extends HttpServlet {

    private List<Items> customerCart = new CopyOnWriteArrayList();
    double totalPriceOfCart = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) { //if the variable retrieved is NULL
            command = "VIEWALLPRODUCTS"; //assign it as "VIEW ALL PRODUCTS"
        }
        switch (command) {
            case "ADDTOCART": {
                addToCart(request, response);
                break;
            }
            case "VIEWCART": {
                viewcart(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isPresent = false;
        String productID = request.getParameter("itemID"); //retrieve the id of the product from the jsp page
        UserDAO daoObj = DAOFactory.createDAO("customer");
        for (Items p : customerCart) {
            //iterate through the cart to see if the product is already present
            if (p.getItemId().equals(productID)) {
                int currentQuantity = p.getItemQty(); //if the product already exists in the array list retreive the current quantity

                p.setItemQty(currentQuantity + 1); //add one to the current quantity

                double unitPrice = p.getUnitPrice(); //retrieve unit price of product
                p.setTotalPriceInCart(unitPrice * p.getItemQty()); //total price for that product in cart = Unit Price * Quantity Added To Cart

                isPresent = true; //set the boolean variable to true to indicate that the product was already there in the cart
            }
        }
        if (isPresent == false) {
            //if the product was not in the cart before
            Items theProduct = ((customerDAO) daoObj).getProductInformationForCart(productID);
            //retrieve the product information from the database
            if (theProduct == null) {
                //check if the return object is equal to null
                out.println("<center><h2>Error Adding To Card</h2></center>"); //if null, display an error message on the customer home page
                request.getRequestDispatcher("/customerHomePage.jsp").include(request, response);
            } else {
                //if the product is retrieve successfully, add the product to the list 
                theProduct.setTotalPriceInCart(theProduct.getUnitPrice()); //if its first product, total price == unit price
                customerCart.add(theProduct);
                response.sendRedirect("CustomerHomeController"); //redirect user to the customer home page
            }
        } else {
            response.sendRedirect("CustomerHomeController"); //if product exists and quantity is incremented, send to home page again
        }
    }

    private void viewcart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession theSession = request.getSession(false);//retrieve the existing session


            totalPriceOfCart = 0;

            if (customerCart.isEmpty()) {
                //if cart is empty, display message on the customer cart page
                out.println("<center><h2>Cart is Currently Empty. Add Items To Your Cart To View Them</h2></center>");
                request.setAttribute("totalPriceOfCart", totalPriceOfCart); //attach the total price to request scope
                request.getRequestDispatcher("/carterror.html").include(request, response);

            } else {
                for (Items theProduct : customerCart) {
                    //iterate through the arraylist and retrieve the total price of each product present in the cart and comeup with the total cost for the cart
                    totalPriceOfCart += theProduct.getTotalPriceInCart(); //call the method that contains the total price of the product in the cart.
                }

                theSession.setAttribute("totalPriceOfCart", totalPriceOfCart); //attach the total price object to the session so it can be retireve during cart finalization

                //if arraylist has products, attach the array list to request scope and forward to the customerCart.jsp along with the total price
                request.setAttribute("cartProducts", customerCart);
                request.setAttribute("totalPriceOfCart", totalPriceOfCart); //attach the total price to request scope
                request.getRequestDispatcher("/customercart.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Error in servlet:" + ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
