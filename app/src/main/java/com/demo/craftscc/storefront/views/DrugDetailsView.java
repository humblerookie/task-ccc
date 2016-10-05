package com.demo.craftscc.storefront.views;

import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.views.BaseView;
import com.demo.craftscc.storefront.presenters.DrugDetailsPresenter;

/**
 * Created by anvith on 10/3/16.
 */

public interface DrugDetailsView extends BaseView<DrugDetailsPresenter> {

    void showData(Drug drug);

    void notifyItemAdded();

    void navigateToCart();

    void navigateToSearchActivity();

    void navigateToCartActivity();

    void notifyMaxQuantityReached();

    void notifyMaxItemsReached();

    void notifyCartValueReached();
}
