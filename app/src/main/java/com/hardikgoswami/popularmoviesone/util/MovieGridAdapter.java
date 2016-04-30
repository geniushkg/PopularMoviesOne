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

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by geniushkg on 4/30/2016.
 */
public class MovieGridAdapter extends ArrayAdapter<Movie> {
    public MovieGridAdapter(Context context, int resource, List<Movie> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        Movie currentMovie = getItem(position);
        String posterUrl = "http://image.tmdb.org/t/p/w185"+ currentMovie.getPoster_path();
        Log.d("TAG","url is : "+posterUrl);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item,parent);
        }
        ViewHolder vh = new ViewHolder(convertView);
        Picasso.with(getContext())
                .load(posterUrl)
                .placeholder(R.drawable.progress_animation)
                .into(vh.imageView);
        return convertView;
    }   

    static class ViewHolder{
        @BindView(R.id.iv_grid_item_row)
        ImageView imageView;
        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

}
