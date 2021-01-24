/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDAO;
import Model.CashOnDeliveryStrategy;
import Model.CreditCardStrategy;
import Model.PaypalStrategy;
import Model.PaymentContext;
import Model.DAO;
import Model.DAOFactory;
import Model.Items;
import Model.Order;
import Model.Order_Product;
import Model.Restaurants;
import Model.UserDAO;
import Model.customerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
        try {
            String command = request.getParameter("command");
            if (command == null) { //if the variable retrieved is NULL
                command = "HOMEPAGE"; //assign it as "VIEW ALL PRODUCTS"
            }
            switch (command) {
                case "HOMEPAGE": {
                    CustomerHome(request, response);
                    break;
                }
                case "ADDTOCART": {
                    addToCart(request, response);
                    break;
                }
                case "VIEWCART": {
                    viewcart(request, response);
                    break;
                }
                case "PROCEED": {
                    proceedCart(request, response);
                    break;
                }
                case "VIEWMENU":
                    viewRestaurantMenu(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        boolean isPresent = false;
        String productID = request.getParameter("itemID"); //retrieve the id of the product from the jsp page
        String restaurantName = request.getParameter("restaurantName");
        String restaurantAddress = request.getParameter("restaurantAddress");
        String restaurantPhone = request.getParameter("restaurantPhone");
        String restaurantStatus = request.getParameter("restaurantStatus");
        String restaurantPic = request.getParameter("restaurantPic");
        session.setAttribute("restaurantName", restaurantName);
        session.setAttribute("restaurantAddress", restaurantAddress);
        session.setAttribute("restaurantPhone", restaurantPhone);
        session.setAttribute("restaurantStatus", restaurantStatus);
        session.setAttribute("restaurantPic", restaurantPic);
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
                viewRestaurantMenu(request, response); //redirect user to the customer home page
            }
        } else {
            viewRestaurantMenu(request, response); //if product exists and quantity is incremented, send to home page again
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

    private void proceedCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double Totalamount;
        HttpSession session = request.getSession();
        String firstName = (String) session.getAttribute("firstName");
        String lastName = (String) session.getAttribute("lastName");
        String email = (String) session.getAttribute("email");
        String orderMethod = request.getParameter("paymentType");
        System.out.println(orderMethod);
        double amount = Double.parseDouble(request.getParameter("totalCartAmount"));
        System.out.println(amount);
        PaymentContext ctx = null;
        ctx = new PaymentContext();

        if ("Credit Card".equalsIgnoreCase(orderMethod)) {
            ctx.setPaymentStrategy(new CreditCardStrategy());
        }
        if ("PayPal".equalsIgnoreCase(orderMethod)) {
            ctx.setPaymentStrategy(new PaypalStrategy());
        }
        if ("Cash On Delivery".equalsIgnoreCase(orderMethod)) {
            ctx.setPaymentStrategy(new CashOnDeliveryStrategy());
        }
        System.out.println("Payment context has " + ctx.getPaymentStrategy());
        double totalAmount = ctx.pay(amount);
        System.out.println(totalAmount);
        Date orderDate = new Date(System.currentTimeMillis());
        String orderStatus = "Pending";
        Order theNewOrder = new Order(orderDate.toString(), orderStatus, orderMethod, email, totalAmount); //create a new order instance
        Order_Product theCart = new Order_Product(theNewOrder, customerCart);
        UserDAO daoObj = DAOFactory.createDAO("customer");
        boolean isOrdered = ((customerDAO) daoObj).placeOrder(theCart);

    }

    private void CustomerHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        List<Restaurants> popularRestaurants = new ArrayList<>();
        List<Restaurants> srilankanRestaurants = new ArrayList<>();
        popularRestaurants = dao.getPopularRestaurants();
        srilankanRestaurants = dao.getSrilankanRestaurants();
        request.setAttribute("RESTAURANT_LIST", popularRestaurants);
        request.setAttribute("SRILANKAN_LIST", srilankanRestaurants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customerHome.jsp");
        dispatcher.forward(request, response);

    }

    private void viewRestaurantMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        String restaurantName = request.getParameter("restaurantName");
        DAO dao = new DAO();
        if ("".equals(restaurantName)) {
            restaurantName = (String) session.getAttribute("restaurantName");
        }
        List<Items> menuItems = new ArrayList<>();
        menuItems = dao.getMenuItems(restaurantName);
        request.setAttribute("MENU_LIST", menuItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("restaurantOrder.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
