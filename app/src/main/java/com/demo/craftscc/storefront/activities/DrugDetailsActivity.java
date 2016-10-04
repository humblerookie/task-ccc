package com.demo.craftscc.storefront.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.demo.craftscc.R;
import com.demo.craftscc.checkout.activities.CartActivity;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.activities.BaseActivity;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.storefront.adapters.DrugDetailsAdapter;
import com.demo.craftscc.storefront.presenters.DrugDetailsPresenter;
import com.demo.craftscc.storefront.views.DrugDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.State;

public class DrugDetailsActivity extends BaseActivity implements DrugDetailsView {

    public static final String DATA = "data";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView list;

    @State
    Drug drug;

    DrugDetailsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_details);
        ButterKnife.bind(this);
        if (getIntent().getParcelableExtra(DATA) != null) {
            drug = getIntent().getParcelableExtra(DATA);
        }
        setPresenter(new DrugDetailsPresenter(this, CraftsCCApplication.getInstance()
                .getDataProvider()
                .getCartHelper(), drug));
        initViews();

    }

    private void initViews() {
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(getResources().getDimension(R.dimen.spacing_normal));
        }
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        presenter.fetchData();
    }


    @OnClick(R.id.add_to_cart)
    void addToCartClicked() {
        presenter.onAddedToCart();
    }

    @OnClick(R.id.go_to_cart)
    void goToCartClicked() {
        presenter.onClickedGoToCart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            getPresenter().onOptionsItemSelected(item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        Drawable drawable = menu.findItem(R.id.action_search).getIcon();

        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.colorAccent));
        menu.findItem(R.id.action_search).setIcon(drawable);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void navigateToSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCartActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }


    @Override
    public void showData(Drug drug) {
        getSupportActionBar().setTitle(drug.getName());
        list.setAdapter(new DrugDetailsAdapter(drug));
    }

    @Override
    public void notifyItemAdded() {
        alertCustom(R.string.drugdetails_notify_itemadded);
    }

    @Override
    public void navigateToCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(DrugDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public DrugDetailsPresenter getPresenter() {
        return presenter;
    }
}
