package com.example.drawer_try.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.drawer_try.R;
import com.example.drawer_try.databinding.FragmentGalleryBinding;
import com.example.drawer_try.databinding.FragmentSlideshowBinding;
import com.example.drawer_try.modle.The_movies;
import com.example.drawer_try.singletonClass.Single_one;
import com.example.drawer_try.ui.gallery.GalleryViewModel;
import com.example.drawer_try.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlideshowFragment extends Fragment {

    // random

    ArrayList<The_movies> the_moviesArrayList = new ArrayList<>();


    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // get Array from home fragment
        HomeFragment homeFragment = new HomeFragment();

        // get singleton
        Single_one single_one = Single_one.getInstance();


        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        // use  TextView textView = binding.textSlideshow; to get like id

        TextView movie_Random = binding.randomMovieR;

        The_movies the_movies = new The_movies();

        Random rand;
        rand = new Random();
        the_moviesArrayList =  single_one.getMovies_list();
        if(!the_moviesArrayList.isEmpty()) {
            int index = (int) (Math.random() * the_moviesArrayList.size());
            movie_Random.setText(the_moviesArrayList.get(index).getTitle());
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    // get Rndom from Array list


}