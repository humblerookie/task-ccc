package com.demo.craftscc.storefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import com.demo.craftscc.R;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.activities.BaseActivity;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.DrugItemClickListener;
import com.demo.craftscc.core.widgets.EditTextPlus;
import com.demo.craftscc.core.widgets.TextViewPlus;
import com.demo.craftscc.storefront.adapters.SearchAdapter;
import com.demo.craftscc.storefront.presenters.SearchPresenter;
import com.demo.craftscc.storefront.views.SearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchView, DrugItemClickListener {

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.search_box)
    EditTextPlus searchBox;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.empty_view)
    TextViewPlus emptyView;

    private SearchAdapter searchAdapter;

    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setPresenter(new SearchPresenter(this, CraftsCCApplication.getInstance()
                .getDataProvider().getItems(this)));
        initViews();
    }

    private void initViews() {
        list.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = searchBox.getText().toString();
                getPresenter().onSearchFieldChanged(s);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(List<Drug> drugs) {
        emptyView.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        if (searchAdapter == null) {
            searchAdapter = new SearchAdapter(this, drugs);
            list.setAdapter(searchAdapter);
        } else {
            searchAdapter.resetData(drugs);
        }
    }

    @Override
    public void navigateToDrugDetailActivity(Drug drug) {
        Intent intent = new Intent(this, DrugDetailsActivity.class);
        intent.putExtra(DrugDetailsActivity.DATA, drug);
        startActivity(intent);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(SearchPresenter presenter) {
        this.searchPresenter = presenter;
    }

    @Override
    public SearchPresenter getPresenter() {
        return searchPresenter;
    }

    @Override
    public void onItemClick(Drug drug) {
        getPresenter().onItemClicked(drug);
    }
}
