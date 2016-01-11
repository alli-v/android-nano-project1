package com.mycompany.project1popularmoviesstage1.provider.reviews;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code reviews} table.
 */
public class ReviewsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ReviewsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ReviewsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ReviewsSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Reviews for the movie
     */
    public ReviewsContentValues putMovieReview(@Nullable String value) {
        mContentValues.put(ReviewsColumns.MOVIE_REVIEW, value);
        return this;
    }

    public ReviewsContentValues putMovieReviewNull() {
        mContentValues.putNull(ReviewsColumns.MOVIE_REVIEW);
        return this;
    }

    /**
     * Movie ID for this review
     */
    public ReviewsContentValues putMovieId(@Nullable String value) {
        mContentValues.put(ReviewsColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewsContentValues putMovieIdNull() {
        mContentValues.putNull(ReviewsColumns.MOVIE_ID);
        return this;
    }
}
