package com.demo.craftscc.storefront.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.craftscc.R;
import com.demo.craftscc.core.activities.BaseActivity;
import com.demo.craftscc.core.model.Drug;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

public class DrugListActivity extends BaseActivity {

    public static final String DATA = "data";

    public static final int COLUMN_COUNT = 2;

    @BindView(R.id.list)
    RecyclerView list;

    @State
    ArrayList<Drug> drugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_list);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(DATA)) {
            drugs = getIntent().getParcelableArrayListExtra(DATA);
        }
        initViews();
    }

    private void initViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COLUMN_COUNT);
        list.setLayoutManager(gridLayoutManager);

    }
}
