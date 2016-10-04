package com.demo.craftscc.core.presenters;

import com.demo.craftscc.core.views.BaseView;

import java.lang.ref.WeakReference;

public class BasePresenter<T extends BaseView> {

    protected WeakReference<T> view;

    public BasePresenter(T view) {
        this.view = new WeakReference<T>(view);
    }

    protected boolean isViewAttached() {
        return view != null && view.get() != null && view.get().isAlive();
    }

}