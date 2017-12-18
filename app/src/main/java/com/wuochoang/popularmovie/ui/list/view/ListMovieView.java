package com.wuochoang.popularmovie.ui.list.view;

import com.wuochoang.popularmovie.base.BaseView;
import com.wuochoang.popularmovie.model.Movie;

import java.util.List;

/**
 * Created by CHUNGNHIM on 23/10/2017.
 */

public interface ListMovieView extends BaseView {
    void showMovieList(List<Movie> movieList);
}
