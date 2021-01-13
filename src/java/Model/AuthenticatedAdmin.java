/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Sachindra Rodrigo
 */
class AuthenticatedAdmin implements Administrator {

    private final ServletRequest request;
    private final ServletResponse response;
    private final FilterChain chain;

    AuthenticatedAdmin(ServletRequest request, ServletResponse response, FilterChain chain) {
        this.request = request;
        this.response = response;
        this.chain = chain;
    }

    @Override
    public void directUser() {
        try {
            //Direct admin to respective page
            chain.doFilter(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
        }
    }

}
