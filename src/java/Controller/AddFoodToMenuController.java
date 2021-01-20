/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDAO;
import Model.DAOFactory;
import Model.Items;
import Model.RestaurantDAO;
import Model.RestaurantFactory;
import Model.Restaurants;
import Model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sachindra Rodrigo
 */
public class AddFoodToMenuController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addItemMenu(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void addItemMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        String category = request.getParameter("category");
        String unitPrice = request.getParameter("itemPrice");
        HttpSession session = request.getSession();
        String restaurantName = (String) session.getAttribute("restaurantName");

        //creating a Restaurant object using the flyweight design pattern implementation
        Restaurants restaurantObject = RestaurantFactory.getRestaurant(restaurantName);

        //creating an object of Items class
        Items item = new Items(itemName, category, unitPrice, restaurantObject);

        UserDAO daoObj = DAOFactory.createDAO("Restaurant");
        //creating an object of the UserDAO object which will be of the Admin sub class type
        boolean isItemAdded = ((RestaurantDAO) daoObj).addItem(item);
        
        if (isItemAdded) {
            request.getRequestDispatcher("addedSuccess.jsp").include(request, response);//display success message
        } else {
            request.getRequestDispatcher("addednonsuccess.jsp").include(request, response);//display error message
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
