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
public class Order_Product {

    private Order theOrder;
    private List<Items> productList;

    public Order_Product(Order theOrder, List<Items> productList) {
        this.theOrder = theOrder;
        this.productList = productList;
    }

    public Order getTheOrder() {
        return theOrder;
    }

    public void setTheOrder(Order theOrder) {
        this.theOrder = theOrder;
    }

    public List<Items> getProductList() {
        return productList;
    }

    public void setProductList(List<Items> productList) {
        this.productList = productList;
    }

}
