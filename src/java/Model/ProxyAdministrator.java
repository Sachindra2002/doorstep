/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sachindra Rodrigo
 */
public class ProxyAdministrator implements Administrator {
    private final ServletRequest request;
    private final ServletResponse response;
    private final FilterChain chain;
    private AuthenticatedAdmin authenticatedAdmin;

    public ProxyAdministrator(ServletRequest request, ServletResponse response, FilterChain chain) {
        this.request = request;
        this.response = response;
        this.chain = chain;
    }
    @Override
    public void directUser() {
        //Cast the ServletRequest to an HttpServletRequest
        HttpServletRequest http_request = (HttpServletRequest) request;

        try {

            //Get type of user
            
            String type = (String) http_request.getSession().getAttribute("role") == null ? null : 
                    (String) http_request.getSession().getAttribute("role");

            if (type == null || !type.equals("admin")) {

                //If role is not admin forward request to access-denied page
                RequestDispatcher dispatcher = request.getRequestDispatcher("accessDenied.html");
                dispatcher.forward(request, response);
                
            } else {
                
                //Instantiate the Authenticate admin and call directUser() method
                authenticatedAdmin = new AuthenticatedAdmin(request, response, chain);
                authenticatedAdmin.directUser();
            }

        } catch (IOException | ServletException ex) {
            System.out.println(ex);
        }
    }
    
}
