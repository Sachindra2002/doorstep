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
    String itemId, itemName, itemCategory , restaurant, itemPic;
    Double unitPrice, totalPrice;
    int itemQty;

    public Items(String itemId, String itemName, String itemCategory, String restaurant, String itemPic, Double unitPrice, Double totalPrice, int itemQty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.restaurant = restaurant;
        this.itemPic = itemPic;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.itemQty = itemQty;
    }

    Items(String itemId, String itemName, String itemCategory, Double unitPrice, int itemQty, Double totalPrice, String itemPic) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.itemQty = itemQty;
        this.itemPic = itemPic;
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

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }
    
    
}
