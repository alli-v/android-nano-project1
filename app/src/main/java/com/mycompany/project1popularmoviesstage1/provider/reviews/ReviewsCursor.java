package com.mycompany.project1popularmoviesstage1.provider.reviews;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code reviews} table.
 */
public class ReviewsCursor extends AbstractCursor implements ReviewsModel {
    public ReviewsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ReviewsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Reviews for the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieReview() {
        String res = getStringOrNull(ReviewsColumns.MOVIE_REVIEW);
        return res;
    }

    /**
     * Movie ID for this review
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieId() {
        String res = getStringOrNull(ReviewsColumns.MOVIE_ID);
        return res;
    }
}
