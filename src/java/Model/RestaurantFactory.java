/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author Sachindra Rodrigo
 */
public class RestaurantFactory {
    static HashMap<String, Restaurants> restaurantMap = new HashMap<>();
    public static Restaurants getRestaurant(String restaurantName) {
        Restaurants result = restaurantMap.get(restaurantName);    
        if(result == null){
            result = new Restaurants(restaurantName);
            restaurantMap.put(restaurantName, result);
        }
        return result;
    }
}
