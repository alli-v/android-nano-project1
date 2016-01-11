package com.mycompany.project1popularmoviesstage1.provider.reviews;

import android.net.Uri;
import android.provider.BaseColumns;

import com.mycompany.project1popularmoviesstage1.provider.MoviesProvider;
import com.mycompany.project1popularmoviesstage1.provider.current.CurrentColumns;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesColumns;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsColumns;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersColumns;

/**
 * reviews associated with movies in the favorites table
 */
public class ReviewsColumns implements BaseColumns {
    public static final String TABLE_NAME = "reviews";
    public static final Uri CONTENT_URI = Uri.parse(MoviesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Reviews for the movie
     */
    public static final String MOVIE_REVIEW = "movie_review";

    /**
     * Movie ID for this review
     */
    public static final String MOVIE_ID = "movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_REVIEW,
            MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_REVIEW) || c.contains("." + MOVIE_REVIEW)) return true;
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
        }
        return false;
    }

}
