package com.demo.craftscc.core;

import android.app.Application;

import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.DataProvider;

import java.util.ArrayList;


public class CraftsCCApplication extends Application {

    private static CraftsCCApplication instance;

    private ArrayList<Drug> items;

    private DataProvider dataProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataProvider = new DataProvider();

    }

    public static CraftsCCApplication getInstance() {
        return instance;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }
}
