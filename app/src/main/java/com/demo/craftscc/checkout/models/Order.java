package com.demo.craftscc.checkout.models;

/**
 * Created by anvith on 10/4/16.
 */

public class Order {

    private Cart cart;

    private String orderId;

    private ShippingDetails details;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ShippingDetails getDetails() {
        return details;
    }

    public void setDetails(ShippingDetails details) {
        this.details = details;
    }

}
