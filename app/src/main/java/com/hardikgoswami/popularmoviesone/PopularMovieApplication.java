package com.hardikgoswami.popularmoviesone;

import android.app.Application;
import com.hardikgoswami.popularmoviesone.util.TheMovieDbService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by geniushkg on 4/27/2016.
 */
public class PopularMovieApplication extends Application {
    public static final String BASE_URL ="http://api.themoviedb.org/3/";
    public static final String TMDB_API_KEY = "361a356d738a66e728af762399fc25bd";
    private static TheMovieDbService sService;
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sService = retrofit.create(TheMovieDbService.class);
    }
    public static TheMovieDbService getsService(){
        if (sService == null){
            throw new NullPointerException("Service Null");
        }
        return sService;
    }
}
