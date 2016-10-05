package com.demo.craftscc.checkout.presenters;

import android.os.AsyncTask;
import android.util.Log;

import com.demo.craftscc.BuildConfig;
import com.demo.craftscc.checkout.utils.CartHelper;
import com.demo.craftscc.checkout.views.AddressView;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.presenters.BasePresenter;
import com.demo.craftscc.core.utils.PreferenceUtil;
import com.demo.craftscc.core.utils.email.GMailSender;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anvith on 10/4/16.
 */

public class AddressPresenter extends BasePresenter<AddressView> {

    private WeakReference<AddressView> view;

    private CartHelper cartHelper;

    public AddressPresenter(AddressView view, CartHelper cartHelper) {
        super(view);
        this.view = new WeakReference<>(view);
        this.cartHelper = cartHelper;
    }


    public void onConfirmOrder(String name, String address, String postal, String email, String phoneNumber) {

        if (name.trim().length() == 0 || !isValidName(name)) {
            if (isViewAttached()) {
                view.get().onErrorName();
            }
            return;
        }

        if (address.trim().length() == 0) {
            if (isViewAttached()) {
                view.get().onErrorAddress();
            }
            return;
        }

        if (postal.trim().length() != 6 || !isValidPostal(postal)) {
            if (isViewAttached()) {
                view.get().onErrorPostal();
            }
            return;
        }

        if (email.trim().length() == 0 || !isValidEmail(email)) {
            if (isViewAttached()) {
                view.get().onErrorEmail();
            }
            return;
        }

        if (phoneNumber.trim().length() != 10 || !isValidContact(phoneNumber)) {
            if (isViewAttached()) {
                view.get().onErrorPhone();
            }
            return;
        }

        PreferenceUtil.setEmail(CraftsCCApplication.getInstance(), email);
        new SendEmail(this).execute();

    }

    private boolean isValidContact(String contact) {
        try {
            long l = Long.parseLong(contact.trim());
            if (l >= 6000000000l) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    private final boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isValidPostal(String postal) {
        try {
            long l = Long.parseLong(postal.trim());
            if (l != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isValidName(String name) {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    private static class SendEmail extends AsyncTask<Void, Void, Void> {

        private AddressPresenter addressPresenter;
        private boolean isSuccessful = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (addressPresenter.isViewAttached()) {
                addressPresenter.view.get().showProgress();
            }
        }

        private SendEmail(AddressPresenter addressPresenter) {
            this.addressPresenter = addressPresenter;
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                GMailSender sender = new GMailSender(BuildConfig.USERNAME, BuildConfig.PASSWORD);
                sender.sendMail("Order Confirmation via Online Drugstore",
                        "Your Order No." + Calendar.getInstance().getTimeInMillis() + " was confirmed and would be delivered to you within 5 working days",
                        "asterrisk3@gmail.com",
                        PreferenceUtil.getUserEmail(CraftsCCApplication.getInstance()));
            } catch (Exception e) {
                isSuccessful = false;
                Log.e("SendMail", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (addressPresenter.isViewAttached()) {
                if (isSuccessful) {
                    addressPresenter.view.get().showSuccess();
                    addressPresenter.cartHelper.clearCart();

                } else {
                    addressPresenter.view.get().showError();
                }
            }
        }
    }
}
