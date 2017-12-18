package com.wuochoang.popularmovie.ui.list.presenter;

import com.olddog.common.ToastUtils;
import com.wuochoang.popularmovie.App;
import com.wuochoang.popularmovie.base.BasePresenter;
import com.wuochoang.popularmovie.common.Constant;
import com.wuochoang.popularmovie.di.module.ActivityModule;
import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.model.base.ApiResult;
import com.wuochoang.popularmovie.network.ApiService;
import com.wuochoang.popularmovie.network.entities.CallbackWrapper;
import com.wuochoang.popularmovie.ui.list.view.ListMovieView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CHUNGNHIM on 23/10/2017.
 */

public class ListMoviePresenter extends BasePresenter<ListMovieView> {

    public ListMoviePresenter(ApiService apiService) {
        super(apiService);
    }

    public void showList(int page, String sortType) {
        showLoading();
        apiService.discoverMovie(page, sortType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<ApiResult<List<Movie>>>(getView()) {
                    @Override
                    protected void onSuccess(ApiResult<List<Movie>> model) {
                        if(model.getResults() != null)
                            getView().showMovieList(model.getResults());
                        hideLoading();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        hideLoading();
                    }
                });
    }
}
