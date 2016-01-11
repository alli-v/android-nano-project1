package com.mycompany.project1popularmoviesstage1.provider.current;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code current} table.
 */
public class CurrentContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CurrentColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CurrentSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CurrentSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Unique ID in moviedb API
     */
    public CurrentContentValues putMovieId(@Nullable String value) {
        mContentValues.put(CurrentColumns.MOVIE_ID, value);
        return this;
    }

    public CurrentContentValues putMovieIdNull() {
        mContentValues.putNull(CurrentColumns.MOVIE_ID);
        return this;
    }

    /**
     * Title of the movie
     */
    public CurrentContentValues putMovieTitle(@Nullable String value) {
        mContentValues.put(CurrentColumns.MOVIE_TITLE, value);
        return this;
    }

    public CurrentContentValues putMovieTitleNull() {
        mContentValues.putNull(CurrentColumns.MOVIE_TITLE);
        return this;
    }

    /**
     * Poster image path for the movie
     */
    public CurrentContentValues putPosterImg(@Nullable String value) {
        mContentValues.put(CurrentColumns.POSTER_IMG, value);
        return this;
    }

    public CurrentContentValues putPosterImgNull() {
        mContentValues.putNull(CurrentColumns.POSTER_IMG);
        return this;
    }

    /**
     * Movie overview text
     */
    public CurrentContentValues putMovieOverview(@Nullable String value) {
        mContentValues.put(CurrentColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public CurrentContentValues putMovieOverviewNull() {
        mContentValues.putNull(CurrentColumns.MOVIE_OVERVIEW);
        return this;
    }

    /**
     * Movie release date
     */
    public CurrentContentValues putMovieReleaseDate(@Nullable String value) {
        mContentValues.put(CurrentColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public CurrentContentValues putMovieReleaseDateNull() {
        mContentValues.putNull(CurrentColumns.MOVIE_RELEASE_DATE);
        return this;
    }

    /**
     * Average user rating for the movie
     */
    public CurrentContentValues putMovieAvgRating(@Nullable String value) {
        mContentValues.put(CurrentColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public CurrentContentValues putMovieAvgRatingNull() {
        mContentValues.putNull(CurrentColumns.MOVIE_AVG_RATING);
        return this;
    }
}
