package com.demo.craftscc.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.demo.craftscc.checkout.models.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PreferenceUtil {


    private static final String CART = "pref_cart";

    private static final String USER_EMAIL = "pref_user_email";


    protected static void setString(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(key, value).apply();
    }

    protected static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, defaultValue);
    }


    public static void setEmail(Context context, String email) {
        setString(context, USER_EMAIL, email);
    }

    public static String getUserEmail(Context context) {
        return getString(context, USER_EMAIL, null);
    }

    public static void setCart(Context context, Cart cart) {

        setString(context, CART, new Gson().toJson(cart));

    }

    public static Cart getCart(Context context) {

        String s = getString(context, CART, null);

        if (s != null) {
            return new Gson().fromJson(s, new TypeToken<Cart>() {
            }.getType());

        } else {
            return null;
        }

    }

}
