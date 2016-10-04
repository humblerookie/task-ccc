package com.demo.craftscc.checkout.models;

import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.NonObfuscable;

/**
 * Created by anvith on 10/4/16.
 */

public class CartItem implements NonObfuscable{

    private Drug drug;

    private int quantity;


    public CartItem(Drug drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
