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
public class Order {
    private String orderID;
    private String orderDate;
    private String acceptedStatus;
    private String orderReadyStatus;
    private String orderType;
    private double totalPrice;
    private String customerEmailAddress;

    public Order(String orderID, String orderDate, String orderType, double totalPrice, String customerEmailAddress) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.totalPrice = totalPrice;
        this.customerEmailAddress = customerEmailAddress;
    }

    public Order(String orderDate, String acceptedStatus, String orderType, String customerEmailAddress, double totalPrice) {
        this.orderDate = orderDate;
        this.acceptedStatus = acceptedStatus;
        this.orderType = orderType;
        this.customerEmailAddress = customerEmailAddress;
        this.totalPrice = totalPrice;
    }

    public Order(String orderID, String orderDate, String acceptedStatus, String orderReadyStatus, String orderType, double totalPrice) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.acceptedStatus = acceptedStatus;
        this.orderReadyStatus = orderReadyStatus;
        this.orderType = orderType;
        this.totalPrice = totalPrice;
    }

    public Order(String orderID, String orderDate, String acceptedStatus, String orderReadyStatus, String orderType, double totalPrice, String customerEmailAddress) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.acceptedStatus = acceptedStatus;
        this.orderReadyStatus = orderReadyStatus;
        this.orderType = orderType;
        this.totalPrice = totalPrice;
        this.customerEmailAddress = customerEmailAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getAcceptedStatus() {
        return acceptedStatus;
    }

    public void setAcceptedStatus(String acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
    }

    public String getOrderReadyStatus() {
        return orderReadyStatus;
    }

    public void setOrderReadyStatus(String orderReadyStatus) {
        this.orderReadyStatus = orderReadyStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }


}
