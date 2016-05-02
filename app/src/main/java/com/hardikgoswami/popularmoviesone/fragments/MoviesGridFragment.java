package com.hardikgoswami.popularmoviesone.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hardikgoswami.popularmoviesone.PopularMovieApplication;
import com.hardikgoswami.popularmoviesone.R;
import com.hardikgoswami.popularmoviesone.model.Movie;
import com.hardikgoswami.popularmoviesone.model.Popular;
import com.hardikgoswami.popularmoviesone.util.MovieGridAdapter;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesGridFragment extends Fragment {

    GridView gridView;
    private MovieGridAdapter mMovieGridAdapter;

    public MoviesGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movies_grid, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridview_home_frag);
        mMovieGridAdapter = new MovieGridAdapter(getContext(), new ArrayList<Movie>());
        gridView.setAdapter(mMovieGridAdapter);
        updateAdapterData();

        return rootView;
    }


    private void updateAdapterData() {
        Call<Popular> callPopularMovies = PopularMovieApplication.getsService().getPopularMovies(PopularMovieApplication.TMDB_API_KEY);
        callPopularMovies.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                if (response.isSuccessful()) {
                    Popular movieObject = response.body();
                    ArrayList<Movie> movieList = new ArrayList<Movie>(movieObject.getResults());
                    mMovieGridAdapter.clear();
                    mMovieGridAdapter.addAll(movieList);
                    mMovieGridAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {
                Log.d("TAG", "retrofit failed execute with :"+t.getMessage());
            }
        });
    }
}
