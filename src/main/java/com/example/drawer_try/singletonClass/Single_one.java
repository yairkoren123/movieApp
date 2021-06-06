package com.example.drawer_try.singletonClass;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.drawer_try.modle.The_movies;

import java.util.ArrayList;

public class Single_one {

    // values
    String now_login_email;

    String userImage = "";

    String toSearch ="";
    String now_login_pass;
    ArrayList<The_movies> movies_list = null;
    The_movies value_movie  = new The_movies("none");
    ArrayList<The_movies> the_love_movies = new ArrayList<>();
    String the_same_movie_id = "";

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
            for (int i = 0; i < the_love_movies.size(); i++) {
                if (the_love_movies.get(i).getTitle().equals(movie.getTitle())) {
                    Log.d("loves", "seeiflove: in the list already" );
                    return true;
                }
            }
        return false;
    }

    // id

    public String getThe_same_movie_id() {

        return the_same_movie_id;
    }

    public void setThe_same_movie_id(String the_same_movie_id) {
        this.the_same_movie_id = the_same_movie_id;
    }


    // login


    public String getNow_login_email() {
        return now_login_email;
    }

    public void setNow_login_email(String now_login_email) {
        this.now_login_email = now_login_email;
    }

    public String getNow_login_pass() {
        return now_login_pass;
    }

    public void setNow_login_pass(String now_login_pass) {
        this.now_login_pass = now_login_pass;
    }

    // Search


    public String getToSearch() {
        return toSearch;
    }

    public void setToSearch(String toSearch) {
        this.toSearch = toSearch;
    }

    // user image


    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
