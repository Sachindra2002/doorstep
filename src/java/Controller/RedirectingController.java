/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sachindra Rodrigo
 */
// Facade pattern to direct users to respective home page according to user type
public class RedirectingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        redirectToHome(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        redirectToHome(request, response);
    }

    private void redirectToHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String role = "";
        // Session attribute of typeOfUser is taken and checked if user is logged in
        if (request.getSession().getAttribute("role") != null) {
            role = (String) request.getSession().getAttribute("role");

            //User is directed to their respective controller to be sent to home page
            switch (role) {
                case "admin":
                    response.sendRedirect("AdminHomeController");
                    break;
                case "customer":
                    response.sendRedirect("CustomerHomeController");
                    break;
            }

        } else {
            //If user is not logged in, they are directed to visitor home page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
