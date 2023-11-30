package com.example.psrikandi.Cart;

public class CartItem {
    private String productName;
    private Double price;
    private int quantity;
    private double totalPrice;

    public CartItem(String productName, Double price, int quantity, double totalPrice) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
