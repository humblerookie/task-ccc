package com.demo.craftscc.storefront.presenters;

import com.demo.craftscc.R;
import com.demo.craftscc.checkout.utils.CartHelper;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.presenters.BasePresenter;
import com.demo.craftscc.storefront.views.DrugDetailsView;

import java.lang.ref.WeakReference;

/**
 * Created by anvith on 10/4/16.
 */

public class DrugDetailsPresenter extends BasePresenter<DrugDetailsView> {

    private WeakReference<DrugDetailsView> drugDetailsViewWeakReference;

    private CartHelper cartHelper;

    private Drug drug;

    public DrugDetailsPresenter(DrugDetailsView view, CartHelper cartHelper, Drug drug) {
        super(view);
        drugDetailsViewWeakReference = new WeakReference<>(view);
        this.cartHelper = cartHelper;
        this.drug = drug;
    }


    public void onAddedToCart() {
        if (cartHelper.hasReachedMaxItems()) {
            view.get().notifyMaxItemsReached();
        } else if (cartHelper.hasReachedMaxQuantity(drug)) {
            view.get().notifyMaxQuantityReached();
        } else if (cartHelper.hasExceededMaxCartValues(drug)) {
            view.get().notifyCartValueReached();
        } else {
            cartHelper.incrementQuantityInCart(drug);
            drugDetailsViewWeakReference.get().notifyItemAdded();
        }
    }

    public void onClickedGoToCart() {
        drugDetailsViewWeakReference.get().navigateToCart();
    }

    public void fetchData() {
        if (isViewAttached()) {
            drugDetailsViewWeakReference.get().showData(drug);
        }
    }

    public void onOptionsItemSelected(int itemId) {

        if (itemId == R.id.action_search) {
            view.get().navigateToSearchActivity();
        } else if (itemId == R.id.action_cart) {
            view.get().navigateToCartActivity();
        }
    }
}
