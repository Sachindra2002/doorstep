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
public class CreditCardStrategy implements PaymentStrategy {

    double serviceCharge = 100;
    double deliverCharge = 100;
    double tempAmount = 0;
    double discount = 0.20;
    double totalAmount = 0;
    double discountamount = 0;

    @Override
    public double pay(double amount) {
        tempAmount = (amount + serviceCharge + deliverCharge);
        discountamount = tempAmount*discount;
        totalAmount = tempAmount-discountamount;
        System.out.println(totalAmount);
        return totalAmount;
    }

}
