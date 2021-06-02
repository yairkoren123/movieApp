package com.example.drawer_try.modle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drawer_try.R;

import org.jetbrains.annotations.NotNull;


public class Fragment_the_movie_overview extends Fragment {

    // TODO: Rename and change types of parameters

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

        //TextView textView =view.findViewById(R.id);
    }
}