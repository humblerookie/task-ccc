package com.demo.craftscc.core.utils;

import android.app.Activity;
import android.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * This is a typical glide wrapper class.
 * Use it as per convenience alongside the
 *
 * @see Config object.
 */

public class GlideUtil {

    public static class Config {

        private int placeholder;

        private String url;

        private ImageView imageView;

        private Fragment fragment;

        private Activity activity;

        private android.support.v4.app.Fragment supportFragment;

        public int getPlaceholder() {
            return placeholder;
        }


        public Config setPlaceholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public ImageView getImageView() {
            return imageView;

        }

        public Config setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public Config setFragment(Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        public Activity getActivity() {
            return activity;
        }

        public Config setActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public android.support.v4.app.Fragment getSupportFragment() {
            return supportFragment;
        }

        public Config setSupportFragment(android.support.v4.app.Fragment supportFragment) {
            this.supportFragment = supportFragment;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Config setUrl(String url) {
            this.url = url;
            return this;
        }
    }


    public static void loadImage(Config config) {

        RequestManager requestManager = null;
        if (config.getActivity() != null) {
            requestManager = Glide.with(config.getActivity());
        } else if (config.getFragment() != null) {
            requestManager = Glide.with(config.getFragment());
        } else if (config.getSupportFragment() != null) {
            requestManager = Glide.with(config.getSupportFragment());
        }
        if (config.getPlaceholder() != 0) {
            requestManager.load(config.getUrl()).placeholder(config.getPlaceholder()).into(config.getImageView());
        } else {
            requestManager.load(config.getUrl()).into(config.getImageView());
        }

    }


}
