package com.mycompany.project1popularmoviesstage1.provider.favorites;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractSelection;

/**
 * Selection for the {@code favorites} table.
 */
public class FavoritesSelection extends AbstractSelection<FavoritesSelection> {
    @Override
    protected Uri baseUri() {
        return FavoritesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoritesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoritesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoritesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoritesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoritesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoritesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoritesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoritesCursor query(Context context) {
        return query(context, null);
    }


    public FavoritesSelection id(long... value) {
        addEquals("favorites." + FavoritesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoritesSelection idNot(long... value) {
        addNotEquals("favorites." + FavoritesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoritesSelection orderById(boolean desc) {
        orderBy("favorites." + FavoritesColumns._ID, desc);
        return this;
    }

    public FavoritesSelection orderById() {
        return orderById(false);
    }

    public FavoritesSelection movieId(String... value) {
        addEquals(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdNot(String... value) {
        addNotEquals(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdLike(String... value) {
        addLike(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdContains(String... value) {
        addContains(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdStartsWith(String... value) {
        addStartsWith(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection movieIdEndsWith(String... value) {
        addEndsWith(FavoritesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoritesSelection orderByMovieId(boolean desc) {
        orderBy(FavoritesColumns.MOVIE_ID, desc);
        return this;
    }

    public FavoritesSelection orderByMovieId() {
        orderBy(FavoritesColumns.MOVIE_ID, false);
        return this;
    }

    public FavoritesSelection movieTitle(String... value) {
        addEquals(FavoritesColumns.MOVIE_TITLE, value);
        return this;
    }

    public FavoritesSelection movieTitleNot(String... value) {
        addNotEquals(FavoritesColumns.MOVIE_TITLE, value);
        return this;
    }

    public FavoritesSelection movieTitleLike(String... value) {
        addLike(FavoritesColumns.MOVIE_TITLE, value);
        return this;
    }

    public FavoritesSelection movieTitleContains(String... value) {
        addContains(FavoritesColumns.MOVIE_TITLE, value);
        return this;
    }

    public FavoritesSelection movieTitleStartsWith(String... value) {
        addStartsWith(FavoritesColumns.MOVIE_TITLE, value);
        return this;
    }

    public FavoritesSelection movieTitleEndsWith(String... value) {
        addEndsWith(FavoritesColumns.MOVIE_TITLE, value);
        return this;
    }

    public FavoritesSelection orderByMovieTitle(boolean desc) {
        orderBy(FavoritesColumns.MOVIE_TITLE, desc);
        return this;
    }

    public FavoritesSelection orderByMovieTitle() {
        orderBy(FavoritesColumns.MOVIE_TITLE, false);
        return this;
    }

    public FavoritesSelection posterImg(String... value) {
        addEquals(FavoritesColumns.POSTER_IMG, value);
        return this;
    }

    public FavoritesSelection posterImgNot(String... value) {
        addNotEquals(FavoritesColumns.POSTER_IMG, value);
        return this;
    }

    public FavoritesSelection posterImgLike(String... value) {
        addLike(FavoritesColumns.POSTER_IMG, value);
        return this;
    }

    public FavoritesSelection posterImgContains(String... value) {
        addContains(FavoritesColumns.POSTER_IMG, value);
        return this;
    }

    public FavoritesSelection posterImgStartsWith(String... value) {
        addStartsWith(FavoritesColumns.POSTER_IMG, value);
        return this;
    }

    public FavoritesSelection posterImgEndsWith(String... value) {
        addEndsWith(FavoritesColumns.POSTER_IMG, value);
        return this;
    }

    public FavoritesSelection orderByPosterImg(boolean desc) {
        orderBy(FavoritesColumns.POSTER_IMG, desc);
        return this;
    }

    public FavoritesSelection orderByPosterImg() {
        orderBy(FavoritesColumns.POSTER_IMG, false);
        return this;
    }

    public FavoritesSelection movieOverview(String... value) {
        addEquals(FavoritesColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public FavoritesSelection movieOverviewNot(String... value) {
        addNotEquals(FavoritesColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public FavoritesSelection movieOverviewLike(String... value) {
        addLike(FavoritesColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public FavoritesSelection movieOverviewContains(String... value) {
        addContains(FavoritesColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public FavoritesSelection movieOverviewStartsWith(String... value) {
        addStartsWith(FavoritesColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public FavoritesSelection movieOverviewEndsWith(String... value) {
        addEndsWith(FavoritesColumns.MOVIE_OVERVIEW, value);
        return this;
    }

    public FavoritesSelection orderByMovieOverview(boolean desc) {
        orderBy(FavoritesColumns.MOVIE_OVERVIEW, desc);
        return this;
    }

    public FavoritesSelection orderByMovieOverview() {
        orderBy(FavoritesColumns.MOVIE_OVERVIEW, false);
        return this;
    }

    public FavoritesSelection movieReleaseDate(String... value) {
        addEquals(FavoritesColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public FavoritesSelection movieReleaseDateNot(String... value) {
        addNotEquals(FavoritesColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public FavoritesSelection movieReleaseDateLike(String... value) {
        addLike(FavoritesColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public FavoritesSelection movieReleaseDateContains(String... value) {
        addContains(FavoritesColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public FavoritesSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(FavoritesColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public FavoritesSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(FavoritesColumns.MOVIE_RELEASE_DATE, value);
        return this;
    }

    public FavoritesSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(FavoritesColumns.MOVIE_RELEASE_DATE, desc);
        return this;
    }

    public FavoritesSelection orderByMovieReleaseDate() {
        orderBy(FavoritesColumns.MOVIE_RELEASE_DATE, false);
        return this;
    }

    public FavoritesSelection movieAvgRating(String... value) {
        addEquals(FavoritesColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public FavoritesSelection movieAvgRatingNot(String... value) {
        addNotEquals(FavoritesColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public FavoritesSelection movieAvgRatingLike(String... value) {
        addLike(FavoritesColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public FavoritesSelection movieAvgRatingContains(String... value) {
        addContains(FavoritesColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public FavoritesSelection movieAvgRatingStartsWith(String... value) {
        addStartsWith(FavoritesColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public FavoritesSelection movieAvgRatingEndsWith(String... value) {
        addEndsWith(FavoritesColumns.MOVIE_AVG_RATING, value);
        return this;
    }

    public FavoritesSelection orderByMovieAvgRating(boolean desc) {
        orderBy(FavoritesColumns.MOVIE_AVG_RATING, desc);
        return this;
    }

    public FavoritesSelection orderByMovieAvgRating() {
        orderBy(FavoritesColumns.MOVIE_AVG_RATING, false);
        return this;
    }
}
