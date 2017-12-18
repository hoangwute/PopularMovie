package com.wuochoang.popularmovie.network;

import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.model.Trailer;
import com.wuochoang.popularmovie.model.TrailerList;
import com.wuochoang.popularmovie.model.base.ApiResult;

import java.util.List;
import io.reactivex.Observable;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by quyenlx on 8/9/2017.
 */

public interface ApiService {
    String API_DISCOVER_MOVIE = "discover/movie/";
    String API_SEARCH_MOVIE = "movie/{id}";
    String API_MOVIE_TRAILERS = "movie/{id}/videos";

    @GET(API_DISCOVER_MOVIE)
    Observable<ApiResult<List<Movie>>> discoverMovie(
            @Query("page") int page,
            @Query("sort_by") String sort
    );

    @GET(API_SEARCH_MOVIE)
    Observable<Movie> searchMovie(
            @Path("id") int id,
            @Query("language") String language
    );

    @GET(API_MOVIE_TRAILERS)
    Observable<TrailerList> getTrailers(
            @Path("id") int id
    );

}
