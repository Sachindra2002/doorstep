/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO;
import Model.Users;
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
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String Address = request.getParameter("address");
        String password = request.getParameter("password");
        Part filePart = request.getPart("photo");
        String role = "customer";
        if (dao.checkUserExists(email)) {
            
            RequestDispatcher rs = request.getRequestDispatcher("registerError1.html");
            rs.forward(request, response);
        } else {
            String fileName = null;
            String photo = "";
            String path = "D:\\doorstep\\web\\profileImages";
            File file = new File(path);
            file.mkdir();
            //String fileName = getFileName(filePart);
            if (filePart == null)
            {
                fileName = null;
            }
            else
            {
                fileName = email + ".jpg";
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
                    photo = "ProfileImages\\" + fileName;
                }
                
            } catch (Exception e) {
                
            }
            
            Users user = new Users(firstName, lastName, email,password, Address, dob, phone, role, photo);
            dao.addUser(user);
            response.sendRedirect("registerSuccess.html");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
