package com.mycompany.project1popularmoviesstage1.provider.reviews;

import com.mycompany.project1popularmoviesstage1.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * reviews associated with movies in the favorites table
 */
public interface ReviewsModel extends BaseModel {

    /**
     * Reviews for the movie
     * Can be {@code null}.
     */
    @Nullable
    String getMovieReview();

    /**
     * Movie ID for this review
     * Can be {@code null}.
     */
    @Nullable
    String getMovieId();
}
