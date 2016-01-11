package com.mycompany.project1popularmoviesstage1.provider.current;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractSelection;

/**
 * Selection for the {@code current} table.
 */
public class CurrentSelection extends AbstractSelection<CurrentSelection> {
    @Override
    protected Uri baseUri() {
        return CurrentColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CurrentCursor} object, which is positioned before the first entry, or null.
     */
    public CurrentCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CurrentCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CurrentCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CurrentCursor} object, which is positioned before the first entry, or null.
     */
    public CurrentCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CurrentCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CurrentCursor query(Context context) {
        return query(context, null);
    }


    public CurrentSelection id(long... value) {
        addEquals("current." + CurrentColumns._ID, toObjectArray(value));
        return this;
    }

    public CurrentSelection idNot(long... value) {
        addNotEquals("current." + CurrentColumns._ID, toObjectArray(value));
        return this;
    }

    public CurrentSelection orderById(boolean desc) {
        orderBy("current." + CurrentColumns._ID, desc);
        return this;
    }

    public CurrentSelection orderById() {
        return orderById(false);
    }

    public CurrentSelection movieId(String... value) {
        addEquals(CurrentColumns.MOVIE_ID, value);
        return this;
    }

    public CurrentSelection movieIdNot(String... value) {
        addNotEquals(CurrentColumns.MOVIE_ID, value);
        return this;
    }

    public CurrentSelection movieIdLike(String... value) {
        addLike(CurrentColumns.MOVIE_ID, value);
        return this;
    }

    public CurrentSelection movieIdContains(String... value) {
        addContains(CurrentColumns.MOVIE_ID, value);
        return this;
    }

    public CurrentSelection movieIdStartsWith(String... value) {
        addStartsWith(CurrentColumns.MOVIE_ID, value);
        return this;
    }

    public CurrentSelection movieIdEndsWith(String... value) {
        addEndsWith(CurrentColumns.MOVIE_ID, value);
        return this;
    }

    public CurrentSelection orderByMovieId(boolean desc) {
        orderBy(CurrentColumns.MOVIE_ID, desc);
        return this;
    }

    public CurrentSelection orderByMovieId() {
        orderBy(CurrentColumns.MOVIE_ID, false);
        return this;
    }

    public CurrentSelection movieTitle(String... value) {
        addEquals(CurrentColumns.MOVIE_TITLE, value);
        return this;
    }

    public CurrentSelection movieTitleNot(String... value) {
        addNotEquals(CurrentColumns.MOVIE_TITLE, value);
        return this;
    }

    public CurrentSelection movieTitleLike(String... value) {
        addLike(CurrentColumns.MOVIE_TITLE, value);
        return this;
    }

    public CurrentSelection movieTitleContains(String... value) {
        addContains(CurrentColumns.MOVIE_TITLE, value);
        return this;
    }

    public CurrentSelection movieTitleStartsWith(String... value) {
        addStartsWith(CurrentColumns.MOVIE_TITLE, value);
        return this;
    }

    public CurrentSelection movieTitleEndsWith(String... value) {
        addEndsWith(CurrentColumns.MOVIE_TITLE, value);
        return this;
    }

    public CurrentSelection orderByMovieTitle(boolean desc) {
        orderBy(CurrentColumns.MOVIE_TITLE, desc);
        return this;
    }

    public CurrentSelection orderByMovieTitle() {
        orderBy(CurrentColumns.MOVIE_TITLE, false);
        return this;
    }

    public CurrentSelection posterImg(String... value) {
        addEquals(CurrentColumns.POSTER_IMG, value);
        return this;
    }

    public CurrentSelection posterImgNot(String... value) {
        addNotEquals(CurrentColumns.POSTER_IMG, value);
        return this;
    }

    public CurrentSelection posterImgLike(String... value) {
        addLike(CurrentColumns.POSTER_IMG, value);
        return this;
    }

    public CurrentSelection posterImgContains(String... value) {
        addContains(CurrentColumns.POSTER_IMG, value);
        return this;
    }

    public CurrentSelection posterImgStartsWith(String... value) {
        addStartsWith(CurrentColumns.POSTER_IMG, value);
        return this;
    }

    public CurrentSelection posterImgEndsWith(String... value) {
        addEndsWith(CurrentColumns.POSTER_IMG, value);
        return this;
    }

    public CurrentSelection orderByPosterImg(boolean desc) {
        orderBy(CurrentColumns.POSTER_IMG, desc);
        return this;
    }

    public CurrentSelection orderByPosterImg() {
        orderBy(CurrentColumns.POSTER_IMG, false);
        return this;
    }

    public CurrentSelection movieOverview(String... value) {
        addEquals(CurrentColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public CurrentSelection movieOverviewNot(String... value) {
        addNotEquals(CurrentColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public CurrentSelection movieOverviewLike(String... value) {
        addLike(CurrentColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public CurrentSelection movieOverviewContains(String... value) {
        addContains(CurrentColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public CurrentSelection movieOverviewStartsWith(String... value) {
        addStartsWith(CurrentColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public CurrentSelection movieOverviewEndsWith(String... value) {
        addEndsWith(CurrentColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public CurrentSelection orderByMovieOverview(boolean desc) {
        orderBy(CurrentColumns.MOVIE_OVERVIEW, desc);
        return this;
    }

    public CurrentSelection orderByMovieOverview() {
        orderBy(CurrentColumns.MOVIE_OVERVIEW, false);
        return this;
    }

    public CurrentSelection movieReleaseDate(String... value) {
        addEquals(CurrentColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public CurrentSelection movieReleaseDateNot(String... value) {
        addNotEquals(CurrentColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public CurrentSelection movieReleaseDateLike(String... value) {
        addLike(CurrentColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public CurrentSelection movieReleaseDateContains(String... value) {
        addContains(CurrentColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public CurrentSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(CurrentColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public CurrentSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(CurrentColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public CurrentSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(CurrentColumns.MOVIE_RELEASE_DATE, desc);
        return this;
    }

    public CurrentSelection orderByMovieReleaseDate() {
        orderBy(CurrentColumns.MOVIE_RELEASE_DATE, false);
        return this;
    }

    public CurrentSelection movieAvgRating(String... value) {
        addEquals(CurrentColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public CurrentSelection movieAvgRatingNot(String... value) {
        addNotEquals(CurrentColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public CurrentSelection movieAvgRatingLike(String... value) {
        addLike(CurrentColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public CurrentSelection movieAvgRatingContains(String... value) {
        addContains(CurrentColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public CurrentSelection movieAvgRatingStartsWith(String... value) {
        addStartsWith(CurrentColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public CurrentSelection movieAvgRatingEndsWith(String... value) {
        addEndsWith(CurrentColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public CurrentSelection orderByMovieAvgRating(boolean desc) {
        orderBy(CurrentColumns.MOVIE_AVG_RATING, desc);
        return this;
    }

    public CurrentSelection orderByMovieAvgRating() {
        orderBy(CurrentColumns.MOVIE_AVG_RATING, false);
        return this;
    }
}
