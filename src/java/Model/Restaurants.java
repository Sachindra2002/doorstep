/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Sachindra Rodrigo
 */
public class Restaurants {
    String restaurantName, restaurantPic, restaurantPhone, restaurantEmail, restaurantAddress, restaurantStatus, restaurantCategory, restaurantCity, password;
    int restaurantRatings;

    public Restaurants(String restaurantName, String restaurantPic, String restaurantPhone, String restaurantEmail, String restaurantAddress, String restaurantStatus, String restaurantCategory, String restaurantCity, String password, int restaurantRatings) {
        this.restaurantName = restaurantName;
        this.restaurantPic = restaurantPic;
        this.restaurantPhone = restaurantPhone;
        this.restaurantEmail = restaurantEmail;
        this.restaurantAddress = restaurantAddress;
        this.restaurantStatus = restaurantStatus;
        this.restaurantCategory = restaurantCategory;
        this.restaurantCity = restaurantCity;
        this.password = password;
        this.restaurantRatings = restaurantRatings;
    }

    public Restaurants(String restaurantName, String restaurantPic, String restaurantPhone, String restaurantEmail, String restaurantAddress, String restaurantStatus, String restaurantCategory) {
        this.restaurantName = restaurantName;
        this.restaurantPic = restaurantPic;
        this.restaurantPhone = restaurantPhone;
        this.restaurantEmail = restaurantEmail;
        this.restaurantAddress = restaurantAddress;
        this.restaurantStatus = restaurantStatus;
        this.restaurantCategory = restaurantCategory;
    }
    public Restaurants(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPic() {
        return restaurantPic;
    }

    public void setRestaurantPic(String restaurantPic) {
        this.restaurantPic = restaurantPic;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantStatus() {
        return restaurantStatus;
    }

    public void setRestaurantStatus(String restaurantStatus) {
        this.restaurantStatus = restaurantStatus;
    }

    public String getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(String restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRestaurantRatings() {
        return restaurantRatings;
    }

    public void setRestaurantRatings(int restaurantRatings) {
        this.restaurantRatings = restaurantRatings;
    }
    
}
