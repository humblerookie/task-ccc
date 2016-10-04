package com.demo.craftscc.checkout.presenters;

import com.demo.craftscc.checkout.models.CartItem;
import com.demo.craftscc.checkout.utils.CartHelper;
import com.demo.craftscc.checkout.views.CartView;
import com.demo.craftscc.core.presenters.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by anvith on 10/4/16.
 */

public class CartPresenter extends BasePresenter<CartView> {

    private HashMap<Integer, CartItem> cartItems;
    private WeakReference<CartView> cartViewWeakReference;
    private CartHelper cartHelper;

    public CartPresenter(CartView view, HashMap<Integer, CartItem> cartItems, CartHelper cartHelper) {
        super(view);
        cartViewWeakReference = new WeakReference<>(view);
        this.cartItems = cartItems;
        this.cartHelper = cartHelper;
    }

    public void fetchData() {
        if (isViewAttached()) {
            if (cartItems.size() > 0) {
                cartViewWeakReference.get().setData(cartItems);
            } else {
                cartViewWeakReference.get().showEmptyView();
            }
        }
    }

    public void decrementItem(int pos, int id) {
        cartHelper.decrementQuantityFromCart(cartItems.get(id).getDrug());

        if (isViewAttached()) {
            if (cartItems.containsKey(id)) {
                view.get().notifyItemChanged(pos);
            } else {
                view.get().notifyItemRemoved(pos);
            }

            if (cartItems.size() == 0) {
                view.get().showEmptyView();
            } else {
                view.get().notifyItemChanged(cartItems.size());
            }
        }
    }

    public void incrementItem(int pos, int id) {
        cartHelper.incrementQuantityInCart(cartItems.get(id).getDrug());
        if (isViewAttached()) {
            view.get().notifyItemChanged(pos);
            view.get().notifyItemChanged(cartItems.size());
        }
    }

    public void removeItem(int pos, int id) {
        cartHelper.removeItemFromCart(cartItems.get(id).getDrug());
        if (isViewAttached()) {
            if (cartItems.size() > 0) {
                view.get().notifyItemChanged(cartItems.size());
                view.get().notifyItemRemoved(pos);
            } else {
                view.get().showEmptyView();
            }
        }
    }

    public void onClickProceed() {
        view.get().navigateToAddressView();
    }
}
