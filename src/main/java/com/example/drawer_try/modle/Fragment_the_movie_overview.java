package com.example.drawer_try.modle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.drawer_try.R;
import com.example.drawer_try.singletonClass.Single_one;
import com.example.drawer_try.singup.ToData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Fragment_the_movie_overview extends Fragment {

    private ArrayList<The_movies> the_movies_list = new ArrayList<>();
    private The_movies the_string_movie;

    private LottieAnimationView add_animationview;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private DocumentReference movie_data_add = db.collection("shopping").document();


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

        Log.d("random", "onViewCreated: " +the_movies_list.size());
        Log.d("random", "onViewCreated: " +the_string_movie);


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

        // anim

        add_animationview = view.findViewById(R.id.add_overview_button);


        if (single_one.seeiflove(the_string_movie) == true){
            add_animationview.setAnimation(R.raw.minus);
        }else {
            add_animationview.setAnimation(R.raw.add);
        }
        // click listener to the add movie button
        add_animationview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (single_one.seeiflove(the_string_movie) == false) {

                    add_animationview.addAnimatorListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            add_animationview.setAnimation(R.raw.minus);
                            super.onAnimationEnd(animation);
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);


                            Log.d("overviewadd", "onAnimationStart: ");
                            Single_one single_one = Single_one.getInstance();
                            single_one.addThe_love_movies(the_string_movie);

                            ToData toData = new ToData();
                            single_one = Single_one.getInstance();
                            toData.setEmail(single_one.getNow_login_email());
                            toData.setBitmap(String.valueOf(single_one.getUserImage()));
                            toData.setThe_moviesArrayList(Single_one.getInstance().getThe_love_movies());


                            movie_data_add = db.collection("good").document(single_one.getNow_login_email());
                            // add to the list
                            // todo
                            movie_data_add.
                                    set(toData).
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                msg(the_string_movie.getTitle() + " was add to your list");
                                            } else {
                                                msg(task.getException().getMessage());
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    msg(e.getMessage());
                                }
                            });


                        }
                    });
                    add_animationview.playAnimation();

                }else {
                    msg("try one more time");
                }
            }
        });


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
    private void msg (String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG)
                .show();
    }



}