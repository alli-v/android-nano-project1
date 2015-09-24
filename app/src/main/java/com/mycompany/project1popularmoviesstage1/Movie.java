package com.mycompany.project1popularmoviesstage1;

import java.io.Serializable;

/**
 * Describes a Movie object
 */
public class Movie implements Serializable {

    String title;
    String releaseDate;
    String posterPath;
    String voteAvg;
    String overview;

    final String RELEASE_DATE_LABEL = "Release Date: ";
    final String RATING_LABEL = "Average Rating: ";
    final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    final String POSTER_SIZE = "w185";

    public Movie(String posterPath, String title, String overview, String releaseDate, String voteAvg) {
        this.posterPath = posterPath;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAvg = voteAvg;
    }

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

    public String toString() {
        return "MOVIE Title: " + this.title + " Overview: " + this.overview + " Poster: " + this.posterPath;
    }
}
