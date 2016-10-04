package com.demo.craftscc.storefront.presenters;

import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.presenters.BasePresenter;
import com.demo.craftscc.storefront.views.SearchView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anvith on 10/4/16.
 */

public class SearchPresenter extends BasePresenter<SearchView> {

    private WeakReference<SearchView> view;
    private List<Drug> items;

    public SearchPresenter(SearchView view, List<Drug> items) {
        super(view);
        this.items = items;
        this.view = new WeakReference<>(view);
    }

    public void onSearchFieldChanged(String s) {

        if (s.trim().length() != 0) {
            List<Drug> filteredList = new ArrayList<>();
            for (Drug d :
                    items) {
                if (d.getName().toLowerCase().contains(s.toLowerCase())) {
                    filteredList.add(d);
                }
            }
            if (filteredList.size() > 0) {
                view.get().setData(filteredList);
            } else {
                view.get().showEmptyView();
            }
        } else {
            view.get().setData(new ArrayList<Drug>());
        }
    }

    public void onItemClicked(Drug drug) {
        view.get().navigateToDrugDetailActivity(drug);
    }
}
