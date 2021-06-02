package com.example.drawer_try.modle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.drawer_try.R;
import com.example.drawer_try.singletonClass.Single_one;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Fragment_the_movie_overview extends Fragment {

    private ArrayList<The_movies> the_movies_list = new ArrayList<>();
    private The_movies the_string_movie;

    public Fragment_the_movie_overview() {
        // Required empty public constructor
    }


    public static Fragment_the_movie_overview newInstance() {
        Fragment_the_movie_overview fragment = new Fragment_the_movie_overview();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_the_movie_overview, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get the list of the movies
        Single_one single_one = Single_one.getInstance();

        the_string_movie = single_one.getValue_movie();
        the_movies_list = single_one.getMovies_list();


        if (the_string_movie.getTitle().equals("none")) {

            if (!the_movies_list.isEmpty()) {
                int index = (int) (Math.random() * the_movies_list.size());
                the_string_movie = the_movies_list.get(index);
            }

        }


        // the TEXT views
        TextView the_name_movie_overview = view.findViewById(R.id.textview_name_movie_overview);
        the_name_movie_overview.setText(the_string_movie.getTitle().toString().trim());

        TextView description_text_overview = view.findViewById(R.id.textview_description_overview);
        description_text_overview.setText(the_string_movie.getOverview().toString().trim());

        TextView release_date = view.findViewById(R.id.textview_release_date_overview);
        release_date.setText("Date release : "+the_string_movie.getRelease_date().toString().trim());

        TextView avrage_text_overview = view.findViewById(R.id.textview_vote_average_overview);
        avrage_text_overview.setText(the_string_movie.getVote_average().toString().trim());

        TextView language_text_overview = view.findViewById(R.id.textview_language_overview);
        language_text_overview.setText(the_string_movie.getOriginal_language().toString().trim());


        // 18 +
        TextView adult_overview = view.findViewById(R.id.adult_overview);
        if(the_string_movie.isAdult()){
            adult_overview.setVisibility(View.VISIBLE);
        }else {
            adult_overview.setVisibility(View.GONE);
        }


        // IMAGE
        String image_background ="https://image.tmdb.org/t/p/w500" + the_string_movie.getImage_sec() ;
        ImageView imageView_poster_background =  view.findViewById(R.id.imageview_background_overview);
        Glide.with(getContext())
                .load(image_background)
                .fitCenter()
                .into(imageView_poster_background);


        String image_poster ="https://image.tmdb.org/t/p/w500" + the_string_movie.getImage() ;
        ImageView imageView_poster_poster =  view.findViewById(R.id.image_view_poster_overview);
        Glide.with(getContext())
                .load(image_poster)
                .fitCenter()
                .into(imageView_poster_poster);


        // scrollview
        ScrollView scrollView_overview = view.findViewById(R.id.scrool_view_overview);
        scrollView_overview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView_overview.getScrollY(); // For ScrollView

                Log.d("ppp", "onScrollChanged: " + scrollY);
                // DO SOMETHING WITH THE SCROLL COORDINATES
                if (scrollY > 30){
                    imageView_poster_poster.setAlpha((float) 0.4);
                }else {
                    imageView_poster_poster.setAlpha((float) 0.9);

                }
            }
        });

        The_movies cool = new The_movies();
        cool.setTitle("none");
        single_one.setValue_movie(cool);
        Log.d("TAG12", "onViewCreated: " + the_string_movie.getTitle());

    }
}