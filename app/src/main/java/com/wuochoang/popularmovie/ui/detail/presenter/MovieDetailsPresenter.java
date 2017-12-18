package com.wuochoang.popularmovie.ui.detail.presenter;

import com.olddog.common.ToastUtils;
import com.wuochoang.popularmovie.base.BasePresenter;
import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.model.Trailer;
import com.wuochoang.popularmovie.model.TrailerList;
import com.wuochoang.popularmovie.model.base.ApiResult;
import com.wuochoang.popularmovie.network.ApiService;
import com.wuochoang.popularmovie.network.entities.CallbackWrapper;
import com.wuochoang.popularmovie.ui.detail.view.MovieDetailsView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CHUNGNHIM on 24/10/2017.
 */

public class MovieDetailsPresenter extends BasePresenter<MovieDetailsView> {

    public MovieDetailsPresenter(ApiService apiService) {
        super(apiService);
    }

    public void getMovieDetails(int movieId, String language) {
        showLoading();
        apiService.searchMovie(movieId, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallbackWrapper<Movie>(getView()) {
                    @Override
                    protected void onSuccess(Movie model) {
                        if(model != null) {
                            getView().getMovieDetails(model);
                        }
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        hideLoading();
                    }
                });
    }

    public void getMovieTrailers(int id) {
        showLoading();
        apiService.getTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallbackWrapper<TrailerList>(getView()) {
                    @Override
                    protected void onSuccess(TrailerList model) {
                        if(model != null) {
                            getView().getTrailers(model.getTrailers());
                            hideLoading();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        hideLoading();
                    }
                });
    }


}
