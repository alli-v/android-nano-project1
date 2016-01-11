package com.mycompany.project1popularmoviesstage1;

import android.util.Log;

import java.io.Serializable;

/**
 * Describes a Movie object
 */
public class Movie implements Serializable {

    int id;
    String title;
    String releaseDate;
    String posterPath;
    String voteAvg;
    String overview;
    String[] trailers;
    String[] reviews;

    final String RELEASE_DATE_LABEL = "Release Date: ";
    final String RATING_LABEL = "Average Rating: ";
    final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    final String POSTER_SIZE = "w185";

    public Movie(int id, String posterPath, String title, String overview, String releaseDate, String voteAvg) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAvg = voteAvg;
    }

    public int getId() { return this.id; }

    public String getTitle() {
        return this.title;
    }

    public String getPosterPath() {
        return POSTER_BASE_URL + POSTER_SIZE + this.posterPath;
    }

    public String getOverview() {
        return this.overview;
    }

    public String getReleaseDate() {
        return RELEASE_DATE_LABEL + this.releaseDate;
    }

    public String getVoteAvg() {
        return RATING_LABEL + this.voteAvg;
    }

    public void setTrailers(String[] trailers) {
        this.trailers = trailers;
        for(int i=0; i < trailers.length; i++) {
            Log.d("MOVIESOBJ", this.id + " trailer " + trailers[i]);
        }
    }

    public String[] getTrailers() {
        return this.trailers;
    }

    public void setReviews(String[] reviews) {
        this.reviews = reviews;
        for(int i=0; i < reviews.length; i++) {
            Log.d("MOVIESOBJ", this.id + " review " + reviews[i]);
        }
    }

    public String[] getReviews() {
        return this.reviews;
    }

    public String toString() {
        return "MOVIE ID: " + id + " Title: " + this.title + " Overview: " + this.overview + " Poster: " + this.posterPath + "Reviews: " + this.reviews;
    }
}
