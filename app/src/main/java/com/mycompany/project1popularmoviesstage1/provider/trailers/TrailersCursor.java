package com.mycompany.project1popularmoviesstage1.provider.trailers;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code trailers} table.
 */
public class TrailersCursor extends AbstractCursor implements TrailersModel {
    public TrailersCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TrailersColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Trailers and video for the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieTrailer() {
        String res = getStringOrNull(TrailersColumns.MOVIE_TRAILER);
        return res;
    }

    /**
     * Movie ID for this trailer
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieId() {
        String res = getStringOrNull(TrailersColumns.MOVIE_ID);
        return res;
    }
}
