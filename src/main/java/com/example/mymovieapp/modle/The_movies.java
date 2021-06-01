package com.example.mymovieapp.modle;

public class The_movies {

    boolean adult;

    String title;

    String vote_average;

    String release_date;

    String overview;

    String original_language;

    int vote_count;

    // constructors
    public The_movies() {
    }

    public The_movies(boolean adult, String title, String vote_average, String release_date, String overview, String original_language, int vote_count) {
        this.adult = adult;
        this.title = title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.overview = overview;
        this.original_language = original_language;
        this.vote_count = vote_count;
    }

    @Override
    public String toString() {
        return "movies{" +
                "adult=" + adult +
                ", title='" + title + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", release_date='" + release_date + '\'' +
                ", overview='" + overview + '\'' +
                ", original_language='" + original_language + '\'' +
                ", vote_count=" + vote_count +
                '}';
    }
    // Getter and Setter
    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
