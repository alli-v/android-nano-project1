package com.mycompany.project1popularmoviesstage1.provider.trailers;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.project1popularmoviesstage1.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code trailers} table.
 */
public class TrailersContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TrailersColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TrailersSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable TrailersSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Trailers and video for the movie
     */
    public TrailersContentValues putMovieTrailer(@Nullable String value) {
        mContentValues.put(TrailersColumns.MOVIE_TRAILER, value);
        return this;
    }

    public TrailersContentValues putMovieTrailerNull() {
        mContentValues.putNull(TrailersColumns.MOVIE_TRAILER);
        return this;
    }

    /**
     * Movie ID for this trailer
     */
    public TrailersContentValues putMovieId(@Nullable String value) {
        mContentValues.put(TrailersColumns.MOVIE_ID, value);
        return this;
    }

    public TrailersContentValues putMovieIdNull() {
        mContentValues.putNull(TrailersColumns.MOVIE_ID);
        return this;
    }
}
