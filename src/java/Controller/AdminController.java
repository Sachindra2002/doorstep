/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDAO;
import Model.AscendingSort;
import Model.DAO;
import Model.DAOFactory;
import Model.DescendingSort;
import Model.Items;
import Model.Order;
import Model.RestaurantFactory;
import Model.Restaurants;
import Model.StrategySorter;
import Model.UserDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Sachindra Rodrigo
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) { //if the variable retrieved is NULL
            command = "HOMEPAGE";
        }
        switch (command) {
            case "HOMEPAGE": {
                AdminHome(request, response);
                break;
            }
            case "ADDFOOD": {
                addItemMenu(request, response);
                break;
            }
            case "ADDRESTAURANT": {
                addRestaurant(request, response);
                break;
            }

            case "VIEWALLORDERS": {
                viewAllOrders(request, response);
                break;
            }

            case "SORTORDERS": {
                sortOrder(request, response);
                break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command) {
            case "ADDRESTAURANT": {
                addRestaurant(request, response);
                break;
            }
        }
    }

    private void AdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
        dispatcher.forward(request, response);
    }

    private void addItemMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        String category = request.getParameter("category");
        double unitPrice = Double.parseDouble(request.getParameter("itemprice"));
        String restaurantName = request.getParameter("restaurant");
        double totalPrice = unitPrice;
        String itemPic = "empty";
        int qty = 1;
        //creating a Restaurant object using the flyweight design pattern implementation
        Restaurants restaurantObject = RestaurantFactory.getRestaurant(restaurantName);

        //creating an object of Items class
        Items item = new Items(itemName, category, itemPic, unitPrice, qty, restaurantObject, totalPrice);

        UserDAO daoObj = DAOFactory.createDAO("admin");
        //creating an object of the UserDAO object which will be of the Admin sub class type
        boolean isItemAdded = ((AdminDAO) daoObj).addItem(item);

        if (isItemAdded) {
            request.getRequestDispatcher("itemsaddedSuccess.jsp").include(request, response);//display success message
        } else {
            request.getRequestDispatcher("itemsaddednonsuccess.jsp").include(request, response);//display error message
        }
    }

    private void addRestaurant(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String restaurantName = request.getParameter("restaurantName");
            System.out.println(restaurantName);
            String category = request.getParameter("category");
            String restaurantEmail = request.getParameter("restaurantEmail");
            String restaurantPhone = request.getParameter("phone");
            String address = request.getParameter("restaurantaddress");
            String city = request.getParameter("city");
            int ratings = 0;
            String password = "empty"; //To be developed in future
            Part filePart = request.getPart("photo");
            String status = "Available";
            String fileName = null;
            String photo = "";
            String path = "D:\\doorstep\\web\\restaurantImages";
            File file = new File(path);
            file.mkdir();
            if (filePart == null) {
                fileName = null;
            } else {
                fileName = restaurantName + ".jpg";
            }
            OutputStream out = null;
            InputStream filecontent = null;
            PrintWriter writer = response.getWriter();
            try {

                out = new FileOutputStream(new File(path + File.separator + fileName));

                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                    //photo = path + "\\" + fileName;
                    photo = "restaurantImages\\" + fileName;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            Restaurants restaurant = new Restaurants(restaurantName, photo, restaurantPhone, restaurantEmail, address, status, category, city, password, ratings);
            UserDAO daoObj = DAOFactory.createDAO("admin");
            boolean isRestaurantAdded = ((AdminDAO) daoObj).addRestaurant(restaurant);
            if (isRestaurantAdded) {
                request.getRequestDispatcher("restaurantAddedSuccess.html").include(request, response);//display success message
            } else {
                request.getRequestDispatcher("restaurantaddednonsuccess.jsp").include(request, response);//display error message
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void viewAllOrders(HttpServletRequest request, HttpServletResponse response) {
        try {
            //method used to get all the pending orders
            PrintWriter out = response.getWriter(); //get an object of printwriter
            response.setContentType("text/html"); //setting the resposne type to html

            //DAO theDAO = new DAO(); //create an instance of DAO
            //<Order> orderList = theDAO.getAllOrders();
            UserDAO daoObj = DAOFactory.createDAO("admin");
            ArrayList<Order> orderList = ((AdminDAO) daoObj).getAllOrders();

            if (orderList.isEmpty()) {
                //check if list is empty
                out.println("<center><h2>No Orders Present In The System. Please Check Again Later</h2></center>"); //display error message to user
                request.getRequestDispatcher("viewOrders.jsp").include(request, response); //show error in stock manager home page
            } else {
                request.setAttribute("orders", orderList);
                request.getRequestDispatcher("viewOrders.jsp").forward(request, response); //forward the request to user
            }
        } catch (Exception ex) {
            System.out.println("Error :" + ex);
        }
    }

    private void sortOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortType = request.getParameter("orderDate");

        if (sortType.equals("Ascending")) {
            StrategySorter sSorter = new StrategySorter();

            sSorter.setSortObject(new AscendingSort());

            List<Order> orderList = sSorter.sortOrderDate();

            request.setAttribute("orders", orderList);
            request.getRequestDispatcher("/viewOrders.jsp").forward(request, response);

        } else {

            StrategySorter sSorter = new StrategySorter();

            sSorter.setSortObject(new DescendingSort());

            List<Order> orderList = sSorter.sortOrderDate();

            request.setAttribute("orders", orderList);
            request.getRequestDispatcher("/viewOrders.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>  

}
