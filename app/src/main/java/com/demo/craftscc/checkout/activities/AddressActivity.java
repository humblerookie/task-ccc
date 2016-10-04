package com.demo.craftscc.checkout.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.demo.craftscc.R;
import com.demo.craftscc.checkout.presenters.AddressPresenter;
import com.demo.craftscc.checkout.views.AddressView;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.activities.BaseActivity;
import com.demo.craftscc.core.utils.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity implements AddressView {

    @BindView(R.id.name)
    MaterialEditText fullName;

    @BindView(R.id.address)
    MaterialEditText address;

    @BindView(R.id.contact)
    MaterialEditText phoneNumber;

    @BindView(R.id.email)
    MaterialEditText email;

    @BindView(R.id.postal_code)
    MaterialEditText postalCode;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private AddressPresenter addressPresenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        initViews();
        setPresenter(new AddressPresenter(this, CraftsCCApplication.getInstance().getDataProvider().getCartHelper()));
    }

    private void initViews() {
        progressDialog = Utils.createProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @OnClick(R.id.confirm)
    void onClickConfirm() {
        addressPresenter.onConfirmOrder(fullName.getText().toString(), address.getText().toString(), postalCode.getText().toString(), email.getText().toString(), phoneNumber.getText().toString());
    }

    @Override
    public void onErrorName() {
        fullName.requestFocus();
        alertCustom(R.string.error_cart_form_full_name);
    }

    @Override
    public void onErrorAddress() {
        address.requestFocus();
        alertCustom(R.string.error_cart_form_address);
    }

    @Override
    public void onErrorPostal() {
        postalCode.requestFocus();
        alertCustom(R.string.error_cart_form_postal);
    }

    @Override
    public void onErrorPhone() {
        phoneNumber.requestFocus();
        alertCustom(R.string.error_cart_form_phone);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        alertCustom(R.string.cart_notify_order_success);
        finish();
    }

    @Override
    public void showError() {
        progressDialog.show();
        alertCustom(R.string.error_cart_fail_order);
    }

    @Override
    public void onErrorEmail() {
        email.requestFocus();
        alertCustom(R.string.error_cart_form_email);
    }

    @Override
    public void setPresenter(AddressPresenter presenter) {
        this.addressPresenter = presenter;
    }

    @Override
    public AddressPresenter getPresenter() {
        return addressPresenter;
    }
}
