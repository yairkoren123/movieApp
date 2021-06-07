package com.example.drawer_try.singup;

import android.graphics.Bitmap;

import com.example.drawer_try.modle.The_movies;

import java.util.ArrayList;

public class ToData {

    private String bitmap;

    private String email;

    private ArrayList<The_movies> the_moviesArrayList = new ArrayList<>();

    private String last_add;

    private ArrayList<String> friends = new ArrayList<>();

    public ToData() {
    }

    public ToData(String email, ArrayList<The_movies> the_moviesArrayList, String last_add) {
        this.email = email;
        this.the_moviesArrayList = the_moviesArrayList;
        this.last_add = last_add;
    }

    @Override
    public String toString() {
        return "ToData{" +
                "bitmap=" + bitmap +
                ", email='" + email + '\'' +
                ", the_moviesArrayList=" + the_moviesArrayList +
                ", last_add='" + last_add + '\'' +
                '}';
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<The_movies> getThe_moviesArrayList() {
        return the_moviesArrayList;
    }

    public void setThe_moviesArrayList(ArrayList<The_movies> the_moviesArrayList) {
        this.the_moviesArrayList = the_moviesArrayList;
    }

    public String getLast_add() {
        return last_add;
    }

    public void setLast_add(String last_add) {
        this.last_add = last_add;
    }
}
