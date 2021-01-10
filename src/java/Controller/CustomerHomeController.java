/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.Restaurants;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CustomerHomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         CustomerHome(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Servlets are incapable of calling a doGet from doPost therefore handleSendHome is called in all types of requests
        CustomerHome(request, response);
    }
    private void CustomerHome (HttpServletRequest request, HttpServletResponse response)
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
