package com.hardikgoswami.popularmoviesone.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hardikgoswami.popularmoviesone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesGridFragment extends Fragment {
    @BindView(R.id.gridview_home_frag)
    GridView gridView;
    public MoviesGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_grid,container,false);


        return rootView;
    }

}
