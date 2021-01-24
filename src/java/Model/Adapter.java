/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Sachindra Rodrigo
 */
public class Adapter implements AdapterInterface{

    @Override
    public String getDate() {
        LocalDate localdate = LocalDate.now();
        return localdate.toString();
    }

    @Override
    public String status() {
        return "pending";
    }
    
}
