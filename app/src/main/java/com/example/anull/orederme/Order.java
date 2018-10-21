package com.example.anull.orederme;

public class Order {
    private  String ProductName ;
    private  String ProductQuantity ;
    private  String price ;

    public Order() {
    }

    public Order(String productName, String productQuantity, String price) {
        ProductName = productName;
        ProductQuantity = productQuantity;
        this.price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
