package com.demo.craftscc.checkout.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.demo.craftscc.R;
import com.demo.craftscc.checkout.adapters.CartAdapter;
import com.demo.craftscc.checkout.models.CartItem;
import com.demo.craftscc.checkout.presenters.CartPresenter;
import com.demo.craftscc.checkout.utils.CartHelper;
import com.demo.craftscc.checkout.views.CartView;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.activities.BaseActivity;
import com.demo.craftscc.core.widgets.TextViewPlus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends BaseActivity implements CartView, CartAdapter.ItemActionListener {

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.empty_view)
    TextViewPlus empty;

    @BindView(R.id.proceed)
    TextViewPlus proceed;

    CartPresenter cartPresenter;

    CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        initViews();
        CartHelper cartHelper = CraftsCCApplication.getInstance()
                .getDataProvider()
                .getCartHelper();
        setPresenter(new CartPresenter(this, cartHelper
                .getCart().getCartItems(), cartHelper));
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        list.setLayoutManager(new LinearLayoutManager(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(getResources().getDimension(R.dimen.spacing_normal));
        }
        getSupportActionBar().setTitle(R.string.cart_title_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().fetchData();

    }

    @Override
    public void setPresenter(CartPresenter presenter) {
        this.cartPresenter = presenter;
    }

    @Override
    public CartPresenter getPresenter() {
        return cartPresenter;
    }

    @Override
    public void setData(HashMap<Integer, CartItem> cartItemSparseArray) {

        list.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        proceed.setVisibility(View.VISIBLE);
        if (cartAdapter == null) {
            cartAdapter = new CartAdapter(cartItemSparseArray, this, CraftsCCApplication.getInstance().getDataProvider().getCartHelper());
            list.setAdapter(cartAdapter);
        } else {
            cartAdapter.resetData(cartItemSparseArray);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showEmptyView() {
        list.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
        proceed.setVisibility(View.GONE);
    }

    @Override
    public void notifyItemChanged(int pos) {
        cartAdapter.notifyItemChanged(pos);
    }

    @Override
    public void notifyItemRemoved(int pos) {
        cartAdapter.notifyItemRemoved(pos);
    }

    @Override
    public void navigateToAddressView() {
        Intent intent = new Intent(this, AddressActivity.class);
        startActivity(intent);
    }

    @Override
    public void decrementItem(int pos, int drugId) {
        cartPresenter.decrementItem(pos, drugId);
    }

    @Override
    public void incrementItem(int pos, int drugId) {
        cartPresenter.incrementItem(pos, drugId);
    }

    @Override
    public void removeItem(int pos, int drugId) {
        cartPresenter.removeItem(pos, drugId);
    }

    @OnClick(R.id.proceed)
    void onProceedClicked() {

        getPresenter().onClickProceed();
    }
}
