package com.hardikgoswami.popularmoviesone;

import android.app.Application;

/**
 * Created by geniushkg on 4/27/2016.
 */
public class PopularMovieApplication extends Application {
    public static final String BASE_URL ="http://api.themoviedb.org/3/";
    public static final String TMDB_API_KEY = BuildConfig.TMDB_API;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
