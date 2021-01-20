/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class AddRestaurantController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        createRestaurant();
    }

    private void createRestuarant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("photo");
        String restaurantName = request.getParameter("restaurantName");
        String restaurantEmail = request.getParameter("restaurantemail");
        String category = request.getParameter("category");
        String restaurantPhone = request.getParameter("phone");
        String restaurantAddress = request.getParameter("restaurantaddress");
        String restaurantPassword = request.getParameter("password");
        String fileName = null;
        String photo = "";
        String path = "D:\\lecture slides\\2 year\\esa 2\\ApiitBlogging-main\\web\\ProfileImages";
        File file = new File(path);
        file.mkdir();
        //String fileName = getFileName(filePart);
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
                photo = "ProfileImages\\" + fileName;
            }

        } catch (Exception e) {

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void createRestaurant() {
    }

}
