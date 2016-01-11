package com.mycompany.project1popularmoviesstage1.provider.favorites;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code favorites} table.
 */
public class FavoritesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return FavoritesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable FavoritesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable FavoritesSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Unique ID in moviedb API
     */
    public FavoritesContentValues putMovieId(@Nullable String value) {
        mContentValues.put(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesContentValues putMovieIdNull() {
        mContentValues.putNull(FavoritesColumns.MOVIE_ID);
        return this;
    }

    /**
     * Title of the movie
     */
    public FavoritesContentValues putMovieTitle(@Nullable String value) {
        mContentValues.put(FavoritesColumns.MOVIE_TITLE, value);
        return this;
    }

    public FavoritesContentValues putMovieTitleNull() {
        mContentValues.putNull(FavoritesColumns.MOVIE_TITLE);
        return this;
    }

    /**
     * Poster image path for the movie
     */
    public FavoritesContentValues putPosterImg(@Nullable String value) {
        mContentValues.put(FavoritesColumns.POSTER_IMG, value);
        return this;
    }

    public FavoritesContentValues putPosterImgNull() {
        mContentValues.putNull(FavoritesColumns.POSTER_IMG);
        return this;
    }

    /**
     * Movie overview text
     */
    public FavoritesContentValues putMovieOverview(@Nullable String value) {
        mContentValues.put(FavoritesColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public FavoritesContentValues putMovieOverviewNull() {
        mContentValues.putNull(FavoritesColumns.MOVIE_OVERVIEW);
        return this;
    }

    /**
     * Movie release date
     */
    public FavoritesContentValues putMovieReleaseDate(@Nullable String value) {
        mContentValues.put(FavoritesColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public FavoritesContentValues putMovieReleaseDateNull() {
        mContentValues.putNull(FavoritesColumns.MOVIE_RELEASE_DATE);
        return this;
    }

    /**
     * Average user rating for the movie
     */
    public FavoritesContentValues putMovieAvgRating(@Nullable String value) {
        mContentValues.put(FavoritesColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public FavoritesContentValues putMovieAvgRatingNull() {
        mContentValues.putNull(FavoritesColumns.MOVIE_AVG_RATING);
        return this;
    }
}
