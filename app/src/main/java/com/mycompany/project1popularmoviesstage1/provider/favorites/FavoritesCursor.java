package com.mycompany.project1popularmoviesstage1.provider.favorites;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code favorites} table.
 */
public class FavoritesCursor extends AbstractCursor implements FavoritesModel {
    public FavoritesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(FavoritesColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Unique ID in moviedb API
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieId() {
        String res = getStringOrNull(FavoritesColumns.MOVIE_ID);
        return res;
    }

    /**
     * Title of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieTitle() {
        String res = getStringOrNull(FavoritesColumns.MOVIE_TITLE);
        return res;
    }

    /**
     * Poster image path for the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getPosterImg() {
        String res = getStringOrNull(FavoritesColumns.POSTER_IMG);
        return res;
    }

    /**
     * Movie overview text
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieOverview() {
        String res = getStringOrNull(FavoritesColumns.MOVIE_OVERVIEW);
        return res;
    }

    /**
     * Movie release date
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieReleaseDate() {
        String res = getStringOrNull(FavoritesColumns.MOVIE_RELEASE_DATE);
        return res;
    }

    /**
     * Average user rating for the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieAvgRating() {
        String res = getStringOrNull(FavoritesColumns.MOVIE_AVG_RATING);
        return res;
    }
}
