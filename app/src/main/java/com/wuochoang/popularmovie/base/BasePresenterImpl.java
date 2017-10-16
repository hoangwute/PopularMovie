package com.wuochoang.popularmovie.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by quyenlx on 8/8/2017.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {
    private T view;
    private CompositeDisposable disposable;

    @Override
    public void onAttachView(T view) {
        this.view = view;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onDetach() {
        view = null;
        disposable.dispose();
    }

    public T getView() {
        return view;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }
}
