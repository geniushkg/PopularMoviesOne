package com.hardikgoswami.popularmoviesone.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import com.hardikgoswami.popularmoviesone.PopularMovieApplication;
import com.hardikgoswami.popularmoviesone.R;
import com.hardikgoswami.popularmoviesone.model.Movie;
import com.hardikgoswami.popularmoviesone.model.Popular;
import com.hardikgoswami.popularmoviesone.util.MovieGridAdapter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesGridFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public sortOrder SORT_ORDER = sortOrder.POPULAR;
    GridView gridView;
    private MovieGridAdapter mMovieGridAdapter;

    public MoviesGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
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
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.activity_menu, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sort_order, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void updateAdapterData() {
        switch (SORT_ORDER) {
            case TOP_RATED: {
                Call<Popular> callPopularMovies = PopularMovieApplication.getsService().getTopRatedMovies(PopularMovieApplication.TMDB_API_KEY);
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
                        Log.d("TAG", "retrofit failed execute with :" + t.getMessage());
                    }
                });
                break;
            }
            case POPULAR: {
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
                        Log.d("TAG", "retrofit failed execute with :" + t.getMessage());
                    }
                });
                break;
            }
            default:
                Log.d("TAG", "default case , sort order issue");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("popular")){
            SORT_ORDER = sortOrder.POPULAR;
        }else {
            SORT_ORDER = sortOrder.TOP_RATED;
        }
        updateAdapterData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getContext(),"nothing selected",Toast.LENGTH_SHORT).show();
    }

    public enum sortOrder {
        TOP_RATED, POPULAR
    }
    
}
