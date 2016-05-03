package com.hardikgoswami.popularmoviesone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.hardikgoswami.popularmoviesone.fragments.MoviesGridFragment;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container_movie_activity, new MoviesGridFragment());
        ft.commit();
    }


    public void switchContent(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_movie_activity,fragment)
                .addToBackStack("movies")
                .commit();
    }

}
