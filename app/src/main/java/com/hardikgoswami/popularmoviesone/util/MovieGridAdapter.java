package com.hardikgoswami.popularmoviesone.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.hardikgoswami.popularmoviesone.R;
import com.hardikgoswami.popularmoviesone.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by geniushkg on 4/30/2016.
 */
public class MovieGridAdapter extends ArrayAdapter<Movie> {


    public MovieGridAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie currentMovie = getItem(position);
        String posterUrl = "http://image.tmdb.org/t/p/w185" + currentMovie.getPoster_path();

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_grid_item_row);
            convertView.setTag(holder);
        }
        Picasso.with(getContext())
                .load(posterUrl)
                .placeholder(R.drawable.progress_animation)
                .into(holder.imageView);
        
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
