package com.demo.craftscc.core.utils;

import android.content.Context;

import com.demo.craftscc.checkout.models.Cart;
import com.demo.craftscc.checkout.utils.CartHelper;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.model.Drug;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.demo.craftscc.core.utils.Constants.MIN_RATING;
import static com.demo.craftscc.core.utils.Constants.MOCK_FILE;
import static com.demo.craftscc.core.utils.Constants.TYPE_COUNT;

/**
 * Created by anvith on 10/3/16.
 */

public class DataProvider {

    private static ArrayList<Drug> items;
    private static ArrayList<ArrayList<Drug>> categorizedItems;
    private static CartHelper cartHelper;

    public ArrayList<Drug> getItems(Context context) {

        if (items == null) {
            synchronized (DataProvider.class) {
                if (items == null) {
                    items = new Gson().fromJson(Utils.loadJSONFromAsset(context, MOCK_FILE), new TypeToken<ArrayList<Drug>>() {
                    }.getType());
                }
            }
        }
        return items;
    }


    public ArrayList<ArrayList<Drug>> getCategorizedItems(Context context) {

        if (categorizedItems == null) {
            categorizedItems = new ArrayList<>();
            for (int i = 0; i < TYPE_COUNT; i++) {
                categorizedItems.add(new ArrayList<Drug>());
            }
            if (items == null) {
                items = getItems(context);
            }
            ArrayList<Drug> trendingList = new ArrayList<>();

            for (Drug item : items) {
                categorizedItems.get(item.getCategory() - 1).add(item);
                if (item.getReviews() > MIN_RATING) {
                    trendingList.add(item);
                }
            }
            categorizedItems.add(0, trendingList);

        }
        return categorizedItems;
    }

    public CartHelper getCartHelper() {
        if (cartHelper == null) {
            synchronized (DataProvider.class) {
                if (cartHelper == null) {
                    Cart saveCart = PreferenceUtil.getCart(CraftsCCApplication.getInstance());
                    cartHelper = new CartHelper(saveCart != null ? saveCart : new Cart());
                }
            }
        }
        return cartHelper;
    }


}
