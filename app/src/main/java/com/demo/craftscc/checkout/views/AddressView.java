package com.demo.craftscc.checkout.views;

import com.demo.craftscc.checkout.presenters.AddressPresenter;
import com.demo.craftscc.core.views.BaseView;

/**
 * Created by anvith on 10/4/16.
 */

public interface AddressView extends BaseView<AddressPresenter> {

    void onErrorName();

    void onErrorAddress();

    void onErrorPostal();

    void onErrorPhone();

    void showProgress();

    void showSuccess();

    void showError();

    void onErrorEmail();
}
