/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Sachindra Rodrigo
 */
public class StrategySorter {
    StrategySort sortObject;

    public StrategySort getSortObject() {
        return sortObject;
    }

    public void setSortObject(StrategySort sortObject) {
        this.sortObject = sortObject;
    }

    public List<Order> sortOrderDate(){
        return sortObject.SortOrderDate();
    }
    
}
