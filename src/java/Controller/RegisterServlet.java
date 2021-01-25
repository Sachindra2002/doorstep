/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminDAO;
import Model.DAO;
import Model.DAOFactory;
import static Model.DAOFactory.daoObject;
import Model.UserDAO;
import Model.Users;
import Model.customerDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String dob = request.getParameter("date");
        String phone = request.getParameter("phone");
        String Address = request.getParameter("address");
        String password = request.getParameter("password");
        String role = "customer";
        String profilepic = "empty";
         UserDAO daoObj = DAOFactory.createDAO("customer");
         boolean isUserExist = ((customerDAO) daoObject).checkUserExist(email);
         if (isUserExist) {
            request.getRequestDispatcher("nonsuccessRegister.jsp").include(request, response);//display success message
        } else {
             Users user = new Users(firstName, lastName, email, password, Address, dob, phone,role, profilepic);
             ((customerDAO) daoObject).addUser(user);
             response.sendRedirect("success.html");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
