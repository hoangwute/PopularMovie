package com.wuochoang.popularmovie.ui.detail.view;

import com.wuochoang.popularmovie.base.BaseView;
import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.model.Trailer;
import com.wuochoang.popularmovie.model.TrailerList;

import java.util.List;

/**
 * Created by CHUNGNHIM on 24/10/2017.
 */

public interface MovieDetailsView extends BaseView {
    void getMovieDetails(Movie movie);
    void getTrailers(List<Trailer> trailers);
}
