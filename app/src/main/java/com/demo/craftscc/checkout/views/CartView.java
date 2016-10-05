package com.demo.craftscc.checkout.views;

import com.demo.craftscc.checkout.models.CartItem;
import com.demo.craftscc.checkout.presenters.CartPresenter;
import com.demo.craftscc.core.views.BaseView;

import java.util.HashMap;

/**
 * Created by anvith on 10/4/16.
 */

public interface CartView extends BaseView<CartPresenter> {

    void setData(HashMap<Integer, CartItem> cartItemSparseArray);

    void showEmptyView();

    void notifyItemChanged(int pos);

    void notifyItemRemoved(int pos);

    void navigateToAddressView();

    void notifyMaxQuantityReached();

    void notifyCartValueReached();
}
