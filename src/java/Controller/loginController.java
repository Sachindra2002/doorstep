/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class loginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            DAO dao = new DAO();
            Users user = new Users(email, password);
            Users authenticatedUser = dao.authenticate(user);
            if (authenticatedUser == null) {
                request.getRequestDispatcher("loginFail.html").forward(request, response);
            } else {
                switch (authenticatedUser.getRole()) {
                    case "admin": {
                        HttpSession session = request.getSession();
                        session.setAttribute("email", email);
                        session.setAttribute("role", authenticatedUser.getRole());
                        session.setAttribute("firstName", authenticatedUser.getFirstName());
                        session.setAttribute("lastName", authenticatedUser.getLastName());
                        session.setAttribute("phone", authenticatedUser.getPhone());
                        session.setAttribute("address", authenticatedUser.getAddress());
                        session.setAttribute("profilePic", authenticatedUser.getProfilePic());
                        response.sendRedirect("AdminController");
                        break;
                    }
                    case "customer": {
                        HttpSession session = request.getSession();
                        session.setAttribute("email", email);
                        session.setAttribute("role", authenticatedUser.getRole());
                        session.setAttribute("firstName", authenticatedUser.getFirstName());
                        session.setAttribute("lastName", authenticatedUser.getLastName());
                        response.sendRedirect("CustomerController");
                        break;
                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
