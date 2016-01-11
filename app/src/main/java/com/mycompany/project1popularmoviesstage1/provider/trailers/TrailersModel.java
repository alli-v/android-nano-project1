package com.mycompany.project1popularmoviesstage1.provider.trailers;

import com.mycompany.project1popularmoviesstage1.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * trailers associated with movies in the favorites table
 */
public interface TrailersModel extends BaseModel {

    /**
     * Trailers and video for the movie
     * Can be {@code null}.
     */
    @Nullable
    String getMovieTrailer();

    /**
     * Movie ID for this trailer
     * Can be {@code null}.
     */
    @Nullable
    String getMovieId();
}
