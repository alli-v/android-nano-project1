package com.mycompany.project1popularmoviesstage1.provider.current;

import com.mycompany.project1popularmoviesstage1.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A user's currently visible movie in the grid.
 */
public interface CurrentModel extends BaseModel {

    /**
     * Unique ID in moviedb API
     * Can be {@code null}.
     */
    @Nullable
    String getMovieId();

    /**
     * Title of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getMovieTitle();

    /**
     * Poster image path for the movie
     * Can be {@code null}.
     */
    @Nullable
    String getPosterImg();

    /**
     * Movie overview text
     * Can be {@code null}.
     */
    @Nullable
    String getMovieOverview();

    /**
     * Movie release date
     * Can be {@code null}.
     */
    @Nullable
    String getMovieReleaseDate();

    /**
     * Average user rating for the movie
     * Can be {@code null}.
     */
    @Nullable
    String getMovieAvgRating();
}
