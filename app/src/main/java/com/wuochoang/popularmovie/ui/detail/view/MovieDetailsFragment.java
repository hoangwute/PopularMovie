package com.wuochoang.popularmovie.ui.detail.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olddog.common.ConvertUtils;
import com.olddog.common.ToastUtils;
import com.squareup.picasso.Picasso;
import com.wuochoang.popularmovie.App;
import com.wuochoang.popularmovie.R;
import com.wuochoang.popularmovie.base.BaseFragment;
import com.wuochoang.popularmovie.base.BasePresenter;
import com.wuochoang.popularmovie.common.Constant;
import com.wuochoang.popularmovie.common.utils.Utils;
import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.model.Trailer;
import com.wuochoang.popularmovie.network.ApiService;
import com.wuochoang.popularmovie.ui.detail.adapter.MovieTrailersAdapter;
import com.wuochoang.popularmovie.ui.detail.presenter.MovieDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by quyenlx on 8/9/2017.
 */

public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView {
    @Inject
    ApiService apiService;

    @BindView(R.id.imgMovieBackdrop)
    ImageView imgMovieBackdrop;
    @BindView(R.id.imgMoviePoster)
    ImageView imgMoviePoster;
    @BindView(R.id.txtMovieTitle)
    TextView txtMovieTitle;
    @BindView(R.id.rbMovie)
    RatingBar rbMovie;
    @BindView(R.id.txtMovieRating)
    TextView txtMovieRating;
    @BindView(R.id.txtMovieReleaseDate)
    TextView txtMovieReleaseDate;
    @BindView(R.id.txtMovieOverview)
    TextView txtMovieOverview;
    @BindView(R.id.rvMovieTrailers)
    RecyclerView rvMovieTrailers;

    private MovieDetailsPresenter presenter;
    private List<Trailer> trailerList;
    private MovieTrailersAdapter trailerAdapter;

    @Override
    public void injectDependence() {
        component.inject(this);
        presenter = new MovieDetailsPresenter(apiService);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_movie_details;
    }

    @Override
    public int getTitle() {
        return R.string.title_menu_device;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void initView() {
        trailerList = new ArrayList<>();
        trailerAdapter = new MovieTrailersAdapter(getActivity(), trailerList);
        rvMovieTrailers.setAdapter(trailerAdapter);
        rvMovieTrailers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void initData() {
        presenter.getMovieDetails(getArguments().getInt("MovieId"), Constant.MOVIE_LANGUAGE);
        presenter.getMovieTrailers(getArguments().getInt("MovieId"));
    }

    @Override
    public void getMovieDetails(Movie movie) {
        txtMovieTitle.setText(movie.getTitle());
        txtMovieReleaseDate.setText(String.format(App.getStringResource(R.string.release_date), movie.getReleaseDate()));
        Glide.with(getContext()).load(movie.getPosterPath()).into(imgMoviePoster);
        Picasso.with(getContext()).load(movie.getBackdropPath()).centerCrop().resize(Utils.getScreenWidth(getContext()), ConvertUtils.px2dp(200)).into(imgMovieBackdrop);
        txtMovieRating.setText(String.valueOf(movie.getVoteAverage()).concat( "/10"));
        rbMovie.setRating((float)movie.getVoteAverage() / 2);
        txtMovieOverview.setText(movie.getOverview());
    }

    @Override
    public void getTrailers(List<Trailer> trailers) {
        trailerAdapter.updateData(trailers);
        ToastUtils.show(trailers.get(0).getKey());
    }

}
