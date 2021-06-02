package com.example.drawer_try.singletonClass;

import android.util.Log;

import com.example.drawer_try.modle.The_movies;

import java.util.ArrayList;

public class Single_one {
    ArrayList<The_movies> movies_list = null;
    The_movies value_movie  = new The_movies("none");
    private static final Single_one ourInstance = new Single_one();
    public static Single_one getInstance() {
        return ourInstance;
    }
    private Single_one() { }

    public Single_one(ArrayList<The_movies> movies_list) {
        this.movies_list = movies_list;
    }


    public Single_one(ArrayList<The_movies> movies_list, The_movies value_movie) {
        this.movies_list = movies_list;
        this.value_movie = value_movie;
    }

    public The_movies getValue_movie() {
        return value_movie;
    }

    public void setValue_movie(The_movies value_movie) {
        this.value_movie = value_movie;
    }

    public ArrayList<The_movies> getMovies_list() {
        return movies_list;
    }

    public void setMovies_list(ArrayList<The_movies> movies_list) {
        this.movies_list = movies_list;
    }
}
