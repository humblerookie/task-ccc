package com.demo.craftscc.checkout.models;

import com.demo.craftscc.core.utils.NonObfuscable;

import java.util.HashMap;

/**
 * Created by anvith on 10/4/16.
 */

public class Cart implements NonObfuscable {

    private HashMap<Integer, CartItem> cartItems = new HashMap<>();

    public HashMap<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
