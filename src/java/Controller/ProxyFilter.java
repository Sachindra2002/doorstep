/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Administrator;
import Model.ProxyAdministrator;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Sachindra Rodrigo
 */
//only admin would be able to access servlets that should be only accessed by admins
public class ProxyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //The ProxyAdministrator will instantiate AuthenticatedAdmin object and call directUser() if logged in user is an admin
        Administrator director = new ProxyAdministrator(request, response, chain);
        director.directUser();
    }

    @Override
    public void destroy() {
        
    }
    
}
