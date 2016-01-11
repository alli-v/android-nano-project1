package com.mycompany.project1popularmoviesstage1.provider.trailers;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractSelection;

/**
 * Selection for the {@code trailers} table.
 */
public class TrailersSelection extends AbstractSelection<TrailersSelection> {
    @Override
    protected Uri baseUri() {
        return TrailersColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailersCursor} object, which is positioned before the first entry, or null.
     */
    public TrailersCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailersCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TrailersCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailersCursor} object, which is positioned before the first entry, or null.
     */
    public TrailersCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailersCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TrailersCursor query(Context context) {
        return query(context, null);
    }


    public TrailersSelection id(long... value) {
        addEquals("trailers." + TrailersColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailersSelection idNot(long... value) {
        addNotEquals("trailers." + TrailersColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailersSelection orderById(boolean desc) {
        orderBy("trailers." + TrailersColumns._ID, desc);
        return this;
    }

    public TrailersSelection orderById() {
        return orderById(false);
    }

    public TrailersSelection movieTrailer(String... value) {
        addEquals(TrailersColumns.MOVIE_TRAILER, value);
        return this;
    }

    public TrailersSelection movieTrailerNot(String... value) {
        addNotEquals(TrailersColumns.MOVIE_TRAILER, value);
        return this;
    }

    public TrailersSelection movieTrailerLike(String... value) {
        addLike(TrailersColumns.MOVIE_TRAILER, value);
        return this;
    }

    public TrailersSelection movieTrailerContains(String... value) {
        addContains(TrailersColumns.MOVIE_TRAILER, value);
        return this;
    }

    public TrailersSelection movieTrailerStartsWith(String... value) {
        addStartsWith(TrailersColumns.MOVIE_TRAILER, value);
        return this;
    }

    public TrailersSelection movieTrailerEndsWith(String... value) {
        addEndsWith(TrailersColumns.MOVIE_TRAILER, value);
        return this;
    }

    public TrailersSelection orderByMovieTrailer(boolean desc) {
        orderBy(TrailersColumns.MOVIE_TRAILER, desc);
        return this;
    }

    public TrailersSelection orderByMovieTrailer() {
        orderBy(TrailersColumns.MOVIE_TRAILER, false);
        return this;
    }

    public TrailersSelection movieId(String... value) {
        addEquals(TrailersColumns.MOVIE_ID, value);
        return this;
    }

    public TrailersSelection movieIdNot(String... value) {
        addNotEquals(TrailersColumns.MOVIE_ID, value);
        return this;
    }

    public TrailersSelection movieIdLike(String... value) {
        addLike(TrailersColumns.MOVIE_ID, value);
        return this;
    }

    public TrailersSelection movieIdContains(String... value) {
        addContains(TrailersColumns.MOVIE_ID, value);
        return this;
    }

    public TrailersSelection movieIdStartsWith(String... value) {
        addStartsWith(TrailersColumns.MOVIE_ID, value);
        return this;
    }

    public TrailersSelection movieIdEndsWith(String... value) {
        addEndsWith(TrailersColumns.MOVIE_ID, value);
        return this;
    }

    public TrailersSelection orderByMovieId(boolean desc) {
        orderBy(TrailersColumns.MOVIE_ID, desc);
        return this;
    }

    public TrailersSelection orderByMovieId() {
        orderBy(TrailersColumns.MOVIE_ID, false);
        return this;
    }
}
