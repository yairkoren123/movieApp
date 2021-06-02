package com.example.drawer_try.singletonClass;

import android.util.Log;

import com.example.drawer_try.modle.The_movies;

import java.util.ArrayList;

public class Single_one {
    ArrayList<The_movies> movies_list;
    private static final Single_one ourInstance = new Single_one();
    public static Single_one getInstance() {
        return ourInstance;
    }
    private Single_one() { }

    public ArrayList<The_movies> getMovies_list() {
        return movies_list;
    }

    public void setMovies_list(ArrayList<The_movies> movies_list) {
        this.movies_list = movies_list;
    }
}
