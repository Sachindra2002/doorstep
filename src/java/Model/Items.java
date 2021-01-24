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
public class Items {
    String itemId, itemName, itemCategory , itemPic;
    Double unitPrice;
    int itemQty;
    private Restaurants restaurant;
    private double totalPriceInCart;
    public Items(String itemId, String itemName, String itemCategory, Restaurants restaurant, String itemPic, double unitPrice, double totalPrice, int itemQty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.restaurant = restaurant;
        this.itemPic = itemPic;
        this.unitPrice = unitPrice;
        this.totalPriceInCart = totalPrice;
        this.itemQty = itemQty;
    }

    Items(String itemId, String itemName, String itemCategory, Double unitPrice, int itemQty, double totalPrice, String itemPic) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.unitPrice = unitPrice;
        this.totalPriceInCart = totalPrice;
        this.itemQty = itemQty;
        this.itemPic = itemPic;
    }

    public Items(String itemName, String itemCategory, String itemPic, Double unitPrice, int itemQty, Restaurants restaurant, double totalPriceInCart) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPic = itemPic;
        this.unitPrice = unitPrice;
        this.itemQty = itemQty;
        this.restaurant = restaurant;
        this.totalPriceInCart = totalPriceInCart;
    }
    
    public Items(String itemId, String itemName, String itemCategory, Double unitPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.unitPrice = unitPrice;
    }
    
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public double getTotalPriceInCart() {
        return totalPriceInCart;
    }

    public void setTotalPriceInCart(double totalPriceInCart) {
        this.totalPriceInCart = totalPriceInCart;
    }

}
