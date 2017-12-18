package com.wuochoang.popularmovie.ui.list.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wuochoang.popularmovie.R;
import com.wuochoang.popularmovie.base.BaseFragment;
import com.wuochoang.popularmovie.base.BasePresenter;
import com.wuochoang.popularmovie.base.EndlessRecyclerOnScrollListener;
import com.wuochoang.popularmovie.common.Constant;
import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.network.ApiService;
import com.wuochoang.popularmovie.ui.detail.view.MovieDetailsActivity;
import com.wuochoang.popularmovie.ui.list.adapter.PopularMovieAdapter;
import com.wuochoang.popularmovie.ui.list.presenter.ListMoviePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by CHUNGNHIM on 20/10/2017.
 */

public class ListFragment extends BaseFragment implements ListMovieView {
    @Inject
    ApiService apiService;
    @BindView(R.id.rvPopularMovieList)
    RecyclerView rvPopularMovieList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private List<Movie> movieList;
    private PopularMovieAdapter movieAdapter;
    private ListMoviePresenter presenter;


    @Override
    public void injectDependence() {
        component.inject(this);
        presenter = new ListMoviePresenter(apiService);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void initView() {
        iniRecyclerView();
        refreshLayout.setOnRefreshListener(() -> {
            iniRecyclerView();
            initData();
            refreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void initData() {
        presenter.showList(1, Constant.SORT_MOST_POPULARITY);
    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        movieAdapter.updateData(movieList);
    }

    private void iniRecyclerView() {
        movieList = new ArrayList<>();
        movieAdapter = new PopularMovieAdapter(getActivity(), movieList, this::openMovieDetails);
        rvPopularMovieList.setAdapter(movieAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvPopularMovieList.setLayoutManager(gridLayoutManager);
        rvPopularMovieList.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                presenter.showList(currentPage, Constant.SORT_MOST_POPULARITY);
            }
        });
    }

    private void openMovieDetails(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra("MovieId", movie.getId());
        startActivity(intent);
    }
}
