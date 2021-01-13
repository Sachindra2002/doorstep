/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.Items;
import Model.Restaurants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sachindra Rodrigo
 */
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String command = request.getParameter("command");
            if (command == null) {
                command = "VIEWMENU";
            }
            switch (command) {
                case "VIEWMENU":
                    viewRestaurantMenu(request, response);
                    break;
            }
        } catch (Exception ex) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void viewRestaurantMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String restaurantName = request.getParameter("restaurantName");
        DAO dao = new DAO();
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
