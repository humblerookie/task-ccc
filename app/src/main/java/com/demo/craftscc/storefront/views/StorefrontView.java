package com.demo.craftscc.storefront.views;

import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.views.BaseView;
import com.demo.craftscc.storefront.presenters.StorefrontPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anvith on 10/3/16.
 */

public interface StorefrontView extends BaseView<StorefrontPresenter> {

    void showProgress();

    void showError();

    void shareApp();

    void showData(List<String> types, ArrayList<ArrayList<Drug>> drug);

    void navigateToDrugDetailsActivity(Drug drug);

    void navigateToSearchActivity();

    void navigateToCartActivity();
}
