/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Sachindra Rodrigo
 */
public class CashOnDeliveryStrategy implements PaymentStrategy{
    double serviceCharge = 100;
    double deliverCharge = 100;
    double totalAmount = 0;
    @Override
    public double pay(double amount) {
        totalAmount = (amount+serviceCharge+deliverCharge);
        System.out.println(totalAmount);
        return totalAmount;
    }
    
}
