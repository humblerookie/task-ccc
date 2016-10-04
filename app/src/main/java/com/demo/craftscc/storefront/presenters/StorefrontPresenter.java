package com.demo.craftscc.storefront.presenters;

import android.os.AsyncTask;

import com.demo.craftscc.R;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.presenters.BasePresenter;
import com.demo.craftscc.storefront.views.StorefrontView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anvith on 10/3/16.
 */

public class StorefrontPresenter extends BasePresenter<StorefrontView> {

    private WeakReference<StorefrontView> view;

    public StorefrontPresenter(StorefrontView view) {
        super(view);
        this.view = new WeakReference<>(view);
    }

    public void fetchDataToDisplay() {
        new RetrieveDataTask(this).execute();
    }


    public void onOptionsItemSelected(int itemId) {

        if (itemId == R.id.action_search) {
            view.get().navigateToSearchActivity();
        } else if (itemId == R.id.action_cart) {
            view.get().navigateToCartActivity();
        }
    }

    public void onNavigationItemSelected(int itemId) {
        if (itemId == R.id.nav_share) {
            view.get().shareApp();
        }

    }

    public void onItemClicked(Drug drug) {
        view.get().navigateToDrugDetailsActivity(drug);
    }

    // N/w call would be simulated here
    private static class RetrieveDataTask extends AsyncTask<Void, Void, Void> {

        private StorefrontPresenter storefrontPresenter;
        private ArrayList<ArrayList<Drug>> items;
        boolean isSuccess = true;

        private RetrieveDataTask(StorefrontPresenter storefrontPresenter) {
            this.storefrontPresenter = storefrontPresenter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (storefrontPresenter.isViewAttached()) {
                storefrontPresenter.view.get().showProgress();

            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                items = CraftsCCApplication.getInstance()
                        .getDataProvider()
                        .getCategorizedItems(CraftsCCApplication.getInstance());
            } catch (Exception e) {
                isSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (storefrontPresenter.isViewAttached()) {
                if (isSuccess) {
                    List<String> types = Arrays.asList(CraftsCCApplication.getInstance().getResources().getStringArray(R.array.item_types));
                    storefrontPresenter.view.get().showData(types, items);
                } else {
                    storefrontPresenter.view.get().showError();
                }
            }
        }
    }
}
