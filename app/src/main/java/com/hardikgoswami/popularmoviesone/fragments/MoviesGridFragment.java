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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesGridFragment extends Fragment {
    @BindView(R.id.gridview_home_frag)
    GridView gridView;
    private MovieGridAdapter mMovieGridAdapter;
    public MoviesGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movies_grid,container,false);
        ButterKnife.bind(this,rootView);
        updateAdapterData();
        if(gridView == null){
            Log.d("TAG","gridview is null");
        }else {
            gridView.setAdapter(mMovieGridAdapter);
        }
        return rootView;
    }

    private void updateAdapterData() {
        Log.d("TAG","update adater called");
        Call<Popular> callPopularMovies = PopularMovieApplication.getsService().getPopularMovies(PopularMovieApplication.TMDB_API_KEY);
        callPopularMovies.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                if(response.isSuccessful()){
                    Popular movieObject = response.body();
                    List<Movie> movieList = movieObject.getResults();
                    Log.d("TAG","size of result :"+movieList.size());
                    if(mMovieGridAdapter != null){
                        mMovieGridAdapter.clear();
                        Log.d("TAG","grid adapter not null is : "+mMovieGridAdapter.getCount());
                    }
                    mMovieGridAdapter = new MovieGridAdapter(getContext(),0,movieList);
                    Log.d("TAG","grid adapter size is : "+mMovieGridAdapter.getCount());
                    mMovieGridAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<Popular> call, Throwable t) {
                Log.d("TAG","retrofit failed execute");
            }
        });

    }

}
