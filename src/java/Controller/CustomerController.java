/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Adapter;
import Model.AdapterInterface;
import Model.AdminDAO;
import Model.CashOnDeliveryStrategy;
import Model.CreditCardStrategy;
import Model.PaypalStrategy;
import Model.PaymentContext;
import Model.DAO;
import Model.DAOFactory;
import Model.Inquiry;
import Model.Items;
import Model.Mail;
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
import java.util.Iterator;
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

                case "ADDINQUIRY":
                    inquire(request, response);
                    break;

                case "ORDERS":
                    orders(request, response);
                    break;

                case "FULLORDER":
                    viewFullOrder(request, response);
                    break;

                case "REMOVEITEM":
                    removeItemCart(request, response);
                    break;

                case "MINUSONE":
                    reduceOneFromCart(request, response);
                    break;

                case "ADDONE":
                    addOneFromCart(request, response);
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
                CustomerHome(request, response);
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
                request.setAttribute("totalPriceOfCart", totalPriceOfCart); //attach the total price to request scope
                request.getRequestDispatcher("/carterror.jsp").include(request, response);

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
        AdapterInterface adpt = new Adapter();
        String orderDate = adpt.getDate();
        String orderStatus = adpt.status();

        Order theNewOrder = new Order(orderDate.toString(), orderStatus, orderMethod, email, totalAmount); //create a new order instance
        Order_Product theCart = new Order_Product(theNewOrder, customerCart);

        UserDAO daoObj = DAOFactory.createDAO("customer");
        boolean isOrdered = ((customerDAO) daoObj).placeOrder(theCart);
        Order madeOrder = ((customerDAO) daoObj).getOrder();

        String message = "Dear " + firstName + " " + lastName + ",\n\n" + "Your Order (" + madeOrder.getOrderID() + ") Has Been Placed Successfully!" + "\n\nTotal Amount Payable (LKR): " + String.valueOf(madeOrder.getTotalPrice()) + "\n\nOrder Type: " + madeOrder.getOrderType() + "\n\nBest Regards,\ndoorstep Team";

        if (isOrdered) {
            //if data has been inserted into database successfully, send a confirmation email
            request.getRequestDispatcher("/orderSuccess.jsp").forward(request, response);
            customerCart.clear();
            totalPriceOfCart = 0;
            Mail.getMailInstance().sendMail("Order Placed Successfully: " + madeOrder.getOrderID(), message, email);

        } else {
            //display an error message
            out.println("<center><h2>Order Not Placed, Please Try Again</h2></center");
            request.getRequestDispatcher("/customercart.jsp").include(request, response);
        }

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

    private void inquire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDAO daoObj = DAOFactory.createDAO("customer");
        String number = (String) session.getAttribute("phone");
        System.out.println(number);
        String email = (String) session.getAttribute("email");
        String message = request.getParameter("message");
        AdapterInterface adpt = new Adapter();
        String date = adpt.getDate();
        String status = adpt.status();
        Inquiry inquiry = new Inquiry(email, number, date, message, status);
        boolean isInquired = ((customerDAO) daoObj).addInquiry(inquiry);
        if (isInquired) {
            request.getRequestDispatcher("addedSuccess.jsp").include(request, response);//display success message
        } else {
            request.getRequestDispatcher("addednonsuccess.jsp").include(request, response);//display error message
        }

    }

    private void orders(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession theSession = request.getSession();
            String email = (String) theSession.getAttribute("email");
            UserDAO daoObj = DAOFactory.createDAO("customer");
            ArrayList<Order> orderList = ((customerDAO) daoObj).vieworder(email);

            if (orderList.isEmpty()) {
                out.println("<center><h2>No Orders</h2></center>");
                request.getRequestDispatcher("/customerorders.jsp").forward(request, response);
            } else {
                request.setAttribute("orderlist", orderList);
                request.getRequestDispatcher("/customerorders.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error in Servlet: " + e);
        }
    }

    private void viewFullOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            String orderID = request.getParameter("orderID");
            UserDAO daoObj = DAOFactory.createDAO("customer");

            ArrayList<Items> allOrderProducts = ((customerDAO) daoObj).viewAllOrderItems(orderID);
            if (allOrderProducts.isEmpty()) {
                request.getRequestDispatcher("/NoOrderInfo.jsp").include(request, response);
            } else {
                request.setAttribute("products", allOrderProducts);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("viewFullOrder.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Error in servlet: " + ex);
        }
    }

    private void removeItemCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productID = request.getParameter("productID"); //retrieve product id to remove from cart from jsp

            Iterator<Items> theIterator = customerCart.iterator();

            while (theIterator.hasNext()) {
                Items p = theIterator.next();
                if (p.getItemId().equals(productID)) {
                    customerCart.remove(p);
                }
            }

            viewcart(request, response); //call the view cart method to reupdate the cart
        } catch (Exception ex) {
            System.out.println("Error Removing From Cart: " + ex);
        }
    }

    private void reduceOneFromCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productID = request.getParameter("productID");
            //getting the productID parameter from the webpage.

            for (Items product : customerCart) {
                //using a for each loop to iterate through the thread safe arraylist
                if (product.getItemId().equals(productID)) {
                    //if the productIDs matches
                    if (product.getItemQty() == 1) {
                        //check if quantity of the product is eqaul to zero
                        customerCart.remove(product);
                        //if equal, remove product from the cart
                    } else {
                        product.setItemQty(product.getItemQty() - 1);
                        //reducing the product quantity by one
                        product.setTotalPriceInCart(product.getUnitPrice() * product.getItemQty());
                        //setting the total price of the cart
                    }
                }
            }
            viewcart(request, response);
            //calling viewCart method
        } catch (Exception ex) {
            System.out.println("Error in Serlvet: " + ex);
        }
    }

    private void addOneFromCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productID = request.getParameter("productID"); //retrieve product id passed from the jsp page

            for (Items theProduct : customerCart) {
                //iterate through list and search for the product
                if (theProduct.getItemId().equals(productID)) {
                    //once product is found, increase the quantity by one
                    theProduct.setItemQty(theProduct.getItemQty()+ 1);
                    theProduct.setTotalPriceInCart(theProduct.getUnitPrice() * theProduct.getItemQty()); //calculate the new total for the product in cart
                }
            }

            viewcart(request, response); //call the view cart method to ensure that the cart is loaded to the user

        } catch (Exception ex) {
            System.out.println("Error in Servlet: " + ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
