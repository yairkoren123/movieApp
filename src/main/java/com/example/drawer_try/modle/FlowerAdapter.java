package com.example.drawer_try.modle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.drawer_try.R;

import java.util.ArrayList;

public class FlowerAdapter extends BaseAdapter  {
    private Context mContext;

    private ArrayList<The_movies> movies;

    private static LayoutInflater inflate = null;

    public FlowerAdapter(Context mContext, ArrayList<The_movies> movies) {
        this.mContext = mContext;
        this.movies = movies;

        inflate = (LayoutInflater) mContext
                .getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    public FlowerAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return movies.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        itemView = inflate.inflate(R.layout.grid_item,null);

        ImageView imageView_poster_movie = itemView.findViewById(R.id.image_poster_movie_single);
        TextView textView_title_movie = itemView.findViewById(R.id.Title_movie_single);

        The_movies selectedMovie = movies.get(position);

        textView_title_movie.setText(selectedMovie.getTitle());
        String image ="https://image.tmdb.org/t/p/w500" + selectedMovie.image ;
        Log.d("images", "getView: " + image);



//        Picasso.get()
//                .load(image)
//                .fit()
//                .centerCrop()
//
//                .into(imageView_poster_movie);


        Glide.with(mContext)
                .load(image)
                .fitCenter()
                .into(imageView_poster_movie);

        imageView_poster_movie.setScaleType(ImageView.ScaleType.FIT_XY);




        return itemView;
    }

}