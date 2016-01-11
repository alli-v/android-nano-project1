package com.mycompany.project1popularmoviesstage1.provider.reviews;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractSelection;

/**
 * Selection for the {@code reviews} table.
 */
public class ReviewsSelection extends AbstractSelection<ReviewsSelection> {
    @Override
    protected Uri baseUri() {
        return ReviewsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewsCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewsCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ReviewsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewsCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewsCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ReviewsCursor query(Context context) {
        return query(context, null);
    }


    public ReviewsSelection id(long... value) {
        addEquals("reviews." + ReviewsColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewsSelection idNot(long... value) {
        addNotEquals("reviews." + ReviewsColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewsSelection orderById(boolean desc) {
        orderBy("reviews." + ReviewsColumns._ID, desc);
        return this;
    }

    public ReviewsSelection orderById() {
        return orderById(false);
    }

    public ReviewsSelection movieReview(String... value) {
        addEquals(ReviewsColumns.MOVIE_REVIEW, value);
        return this;
    }

    public ReviewsSelection movieReviewNot(String... value) {
        addNotEquals(ReviewsColumns.MOVIE_REVIEW, value);
        return this;
    }

    public ReviewsSelection movieReviewLike(String... value) {
        addLike(ReviewsColumns.MOVIE_REVIEW, value);
        return this;
    }

    public ReviewsSelection movieReviewContains(String... value) {
        addContains(ReviewsColumns.MOVIE_REVIEW, value);
        return this;
    }

    public ReviewsSelection movieReviewStartsWith(String... value) {
        addStartsWith(ReviewsColumns.MOVIE_REVIEW, value);
        return this;
    }

    public ReviewsSelection movieReviewEndsWith(String... value) {
        addEndsWith(ReviewsColumns.MOVIE_REVIEW, value);
        return this;
    }

    public ReviewsSelection orderByMovieReview(boolean desc) {
        orderBy(ReviewsColumns.MOVIE_REVIEW, desc);
        return this;
    }

    public ReviewsSelection orderByMovieReview() {
        orderBy(ReviewsColumns.MOVIE_REVIEW, false);
        return this;
    }

    public ReviewsSelection movieId(String... value) {
        addEquals(ReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewsSelection movieIdNot(String... value) {
        addNotEquals(ReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewsSelection movieIdLike(String... value) {
        addLike(ReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewsSelection movieIdContains(String... value) {
        addContains(ReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewsSelection movieIdStartsWith(String... value) {
        addStartsWith(ReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewsSelection movieIdEndsWith(String... value) {
        addEndsWith(ReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewsSelection orderByMovieId(boolean desc) {
        orderBy(ReviewsColumns.MOVIE_ID, desc);
        return this;
    }

    public ReviewsSelection orderByMovieId() {
        orderBy(ReviewsColumns.MOVIE_ID, false);
        return this;
    }
}
