package com.hardikgoswami.popularmoviesone.util;


import com.hardikgoswami.popularmoviesone.model.Popular;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by geniushkg on 4/21/2016.
 */
//http://api.themoviedb.org/3/movie/popular?api_key=361a356d738a66e728af762399fc25bd

public interface TheMovieDbService {
    @GET("movie/popular")
    Call<Popular> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<Popular> getTopRatedMovies(@Query("api_key") String api_key);

}
