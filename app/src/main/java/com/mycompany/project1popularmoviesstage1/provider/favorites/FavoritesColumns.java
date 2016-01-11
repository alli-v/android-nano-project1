package com.mycompany.project1popularmoviesstage1.provider.favorites;

import android.net.Uri;
import android.provider.BaseColumns;

import com.mycompany.project1popularmoviesstage1.provider.MoviesProvider;
import com.mycompany.project1popularmoviesstage1.provider.current.CurrentColumns;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesColumns;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsColumns;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersColumns;

/**
 * A user's saved movie entry.
 */
public class FavoritesColumns implements BaseColumns {
    public static final String TABLE_NAME = "favorites";
    public static final Uri CONTENT_URI = Uri.parse(MoviesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Unique ID in moviedb API
     */
    public static final String MOVIE_ID = "movie_id";

    /**
     * Title of the movie
     */
    public static final String MOVIE_TITLE = "movie_title";

    /**
     * Poster image path for the movie
     */
    public static final String POSTER_IMG = "poster_img";

    /**
     * Movie overview text
     */
    public static final String MOVIE_OVERVIEW = "movie_overview";

    /**
     * Movie release date
     */
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";

    /**
     * Average user rating for the movie
     */
    public static final String MOVIE_AVG_RATING = "movie_avg_rating";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            MOVIE_TITLE,
            POSTER_IMG,
            MOVIE_OVERVIEW,
            MOVIE_RELEASE_DATE,
            MOVIE_AVG_RATING
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(MOVIE_TITLE) || c.contains("." + MOVIE_TITLE)) return true;
            if (c.equals(POSTER_IMG) || c.contains("." + POSTER_IMG)) return true;
            if (c.equals(MOVIE_OVERVIEW) || c.contains("." + MOVIE_OVERVIEW)) return true;
            if (c.equals(MOVIE_RELEASE_DATE) || c.contains("." + MOVIE_RELEASE_DATE)) return true;
            if (c.equals(MOVIE_AVG_RATING) || c.contains("." + MOVIE_AVG_RATING)) return true;
        }
        return false;
    }

}
