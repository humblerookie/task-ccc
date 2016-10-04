package com.demo.craftscc.core.views;


import com.demo.craftscc.core.presenters.BasePresenter;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    T getPresenter();

    boolean isAlive();


}