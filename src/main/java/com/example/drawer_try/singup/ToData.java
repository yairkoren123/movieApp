package com.example.drawer_try.singup;

import com.example.drawer_try.modle.The_movies;

import java.util.ArrayList;

public class ToData {

    private String email;

    private ArrayList<The_movies> the_moviesArrayList = new ArrayList<>();

    private String last_add;

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
                "email='" + email + '\'' +
                ", the_moviesArrayList=" + the_moviesArrayList +
                ", last_add='" + last_add + '\'' +
                '}';
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
