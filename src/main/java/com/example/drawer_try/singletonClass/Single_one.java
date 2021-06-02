package com.example.drawer_try.singletonClass;

import android.util.Log;

import com.example.drawer_try.modle.The_movies;

import java.util.ArrayList;

public class Single_one {
    ArrayList<The_movies> movies_list = null;
    The_movies value_movie  = new The_movies("none");
    ArrayList<The_movies> the_love_movies = new ArrayList<>();

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

    public ArrayList<The_movies> getThe_love_movies() {
        return the_love_movies;
    }
    public void addThe_love_movies(The_movies movie) {
        the_love_movies.add(movie);
        Log.d("love", "addThe_love_movies:   + " + movie.getTitle());
    }

    public void setThe_love_movies(ArrayList<The_movies> the_love_movies) {
        this.the_love_movies = the_love_movies;
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

    public boolean seeiflove(The_movies movie){
        the_love_movies = movies_list;
            for (int i = 0; i < the_love_movies.size(); i++) {
                if (the_love_movies.get(i).getTitle().equals(movie.getTitle())) {
                    return true;
                }
            }
        return false;
    }
}
