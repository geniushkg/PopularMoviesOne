package com.hardikgoswami.popularmoviesone.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hardikgoswami.popularmoviesone.R;
import com.hardikgoswami.popularmoviesone.model.Movie;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetail extends Fragment {
    Movie model;
    public MovieDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            model = Parcels.unwrap(bundle.getParcelable("mDetail"));
            Log.d("TAG","poster url is:"+model.getPoster_path());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

}
