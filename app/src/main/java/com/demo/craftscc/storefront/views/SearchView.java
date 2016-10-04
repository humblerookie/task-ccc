package com.demo.craftscc.storefront.views;

import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.views.BaseView;
import com.demo.craftscc.storefront.presenters.SearchPresenter;

import java.util.List;

/**
 * Created by anvith on 10/4/16.
 */

public interface SearchView extends BaseView<SearchPresenter> {

    void setData(List<Drug> drugs);

    void navigateToDrugDetailActivity(Drug drug);

    void showEmptyView();

}
