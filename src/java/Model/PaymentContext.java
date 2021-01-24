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
public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy)
    {
        this.paymentStrategy = strategy;
    }
    
    public PaymentStrategy getPaymentStrategy()
    {
        return paymentStrategy;
    }
    
    public double pay(double amount)
    {
        return paymentStrategy.pay(amount);
    }
    
    
    
}
