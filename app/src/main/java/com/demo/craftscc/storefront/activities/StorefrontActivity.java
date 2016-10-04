package com.demo.craftscc.storefront.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.demo.craftscc.R;
import com.demo.craftscc.checkout.activities.CartActivity;
import com.demo.craftscc.core.activities.BaseActivity;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.Constants;
import com.demo.craftscc.core.utils.DrugItemClickListener;
import com.demo.craftscc.core.widgets.TextViewPlus;
import com.demo.craftscc.storefront.adapters.StoreFrontAdapter;
import com.demo.craftscc.storefront.presenters.StorefrontPresenter;
import com.demo.craftscc.storefront.views.StorefrontView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StorefrontActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, DrugItemClickListener, StorefrontView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.error_text)
    TextViewPlus errorText;

    private StorefrontPresenter storefrontPresenter;

    private StoreFrontAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storefront);
        ButterKnife.bind(this);
        initViews();
        setPresenter(new StorefrontPresenter(this));
        getPresenter().fetchDataToDisplay();

    }

    private void initViews() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().onOptionsItemSelected(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        storefrontPresenter.onNavigationItemSelected(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(Drug drug) {
        getPresenter().onItemClicked(drug);
    }

    @Override
    public void showProgress() {
        errorText.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        errorText.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        list.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<String> types, ArrayList<ArrayList<Drug>> drugs) {
        if (adapter == null) {
            adapter = new StoreFrontAdapter(types, drugs, this);
            list.setAdapter(adapter);
        } else {
            adapter.resetData(types, drugs);
        }
        errorText.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToDrugDetailsActivity(Drug drug) {
        Intent intent = new Intent(this, DrugDetailsActivity.class);
        intent.putExtra(DrugDetailsActivity.DATA, drug);
        startActivity(intent);
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
    public void shareApp() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.storefront_extra_subject));
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        String shareBody = getString(R.string.storefront_body_prefix) + Constants.APP_URL + appPackageName;
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.storefront_action_share)));
    }

    @Override
    public void setPresenter(StorefrontPresenter presenter) {
        this.storefrontPresenter = presenter;
    }

    @Override
    public StorefrontPresenter getPresenter() {
        return storefrontPresenter;
    }
}
