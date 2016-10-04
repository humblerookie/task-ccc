package com.demo.craftscc.checkout.utils;

import com.demo.craftscc.checkout.models.Cart;
import com.demo.craftscc.checkout.models.CartItem;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.PreferenceUtil;

import java.util.Iterator;

/**
 * Created by anvith on 10/4/16.
 */

public class CartHelper {

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public CartHelper(Cart cart) {
        this.cart = cart;
    }

    public void incrementQuantityInCart(Drug drug) {

        if (cart.getCartItems().containsKey(drug.getId())) {
            CartItem cartItem = cart.getCartItems().get(drug.getId());
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            CartItem cartItem = new CartItem(drug);
            cartItem.setQuantity(1);
            cart.getCartItems().put(drug.getId(), cartItem);
        }
        PreferenceUtil.setCart(CraftsCCApplication.getInstance(), cart);
    }

    public void removeItemFromCart(Drug drug) {
        cart.getCartItems().remove(drug.getId());
        PreferenceUtil.setCart(CraftsCCApplication.getInstance(), cart);
    }

    public void decrementQuantityFromCart(Drug drug) {
        if (cart.getCartItems().containsKey(drug.getId())) {
            CartItem cartItem = cart.getCartItems().get(drug.getId());
            if (cartItem.getQuantity() > 0) {
                if (cartItem.getQuantity() == 1) {
                    removeItemFromCart(drug);
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                }
            }
        }
        PreferenceUtil.setCart(CraftsCCApplication.getInstance(), cart);
    }


    public float getCartTotalValue() {
        float price = 0f;
        Iterator<Integer> iterator = cart.getCartItems().keySet().iterator();

        while (iterator.hasNext()) {
            Integer key = iterator.next();
            CartItem cartItem = cart.getCartItems().get(key);
            price += cartItem.getQuantity() * ((int) (cartItem.getDrug().getDiscountedPrice()));

        }
        return price;
    }

    public void clearCart() {
        getCart().getCartItems().clear();
        PreferenceUtil.setCart(CraftsCCApplication.getInstance(), cart);
    }
}


