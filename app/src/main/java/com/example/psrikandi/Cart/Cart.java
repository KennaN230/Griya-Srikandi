package com.example.psrikandi.Cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    // Konstruktor tanpa parameter
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    // Konstruktor dengan parameter
    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public void removeItem(int position) {
        cartItems.remove(position);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
