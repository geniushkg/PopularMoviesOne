package com.hardikgoswami.popularmoviesone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hardikgoswami.popularmoviesone.R;
import com.hardikgoswami.popularmoviesone.model.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetail extends Fragment {
    Movie model;
    TextView tvTitle,tvRating,tvDate,tvOverview;
    ImageView ivPoster;
    String posterUrl,rating,date,originalTitle,overview;
    public MovieDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            model = (Movie) bundle.getParcelable("mDetail");
            posterUrl = "http://image.tmdb.org/t/p/w185" + model.getPoster_path();
            rating = model.getVoteAverage() + "/ 10";
            date = model.getReleaseDate();
            originalTitle = model.getOriginalTitle();
            overview = model.getOverview();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_movie_detail, container, false);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_movie_title);
        tvTitle.setText(originalTitle);
        tvRating = (TextView) rootView.findViewById(R.id.tv_movie_rating);
        tvRating.setText(rating);
        tvDate = (TextView) rootView.findViewById(R.id.tv_movie_release_date);
        tvDate.setText(date);
        ivPoster = (ImageView) rootView.findViewById(R.id.iv_movie_detail);
        Picasso.with(getContext())
                .load(posterUrl)
                .placeholder(R.drawable.progress_animation)
                .into(ivPoster);
        tvOverview = (TextView) rootView.findViewById(R.id.tv_movie_overview);
        tvOverview.setText(overview);

        return rootView;
    }

}
