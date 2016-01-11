package com.mycompany.project1popularmoviesstage1.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.mycompany.project1popularmoviesstage1.BuildConfig;
import com.mycompany.project1popularmoviesstage1.provider.current.CurrentColumns;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesColumns;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsColumns;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersColumns;

public class MoviesSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = MoviesSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static MoviesSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final MoviesSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_CURRENT = "CREATE TABLE IF NOT EXISTS "
            + CurrentColumns.TABLE_NAME + " ( "
            + CurrentColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CurrentColumns.MOVIE_ID + " TEXT, "
            + CurrentColumns.MOVIE_TITLE + " TEXT, "
            + CurrentColumns.POSTER_IMG + " TEXT, "
            + CurrentColumns.MOVIE_OVERVIEW + " TEXT, "
            + CurrentColumns.MOVIE_RELEASE_DATE + " TEXT, "
            + CurrentColumns.MOVIE_AVG_RATING + " TEXT "
            + ", CONSTRAINT unique_id UNIQUE (movie_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_FAVORITES = "CREATE TABLE IF NOT EXISTS "
            + FavoritesColumns.TABLE_NAME + " ( "
            + FavoritesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FavoritesColumns.MOVIE_ID + " TEXT, "
            + FavoritesColumns.MOVIE_TITLE + " TEXT, "
            + FavoritesColumns.POSTER_IMG + " TEXT, "
            + FavoritesColumns.MOVIE_OVERVIEW + " TEXT, "
            + FavoritesColumns.MOVIE_RELEASE_DATE + " TEXT, "
            + FavoritesColumns.MOVIE_AVG_RATING + " TEXT "
            + ", CONSTRAINT unique_id UNIQUE (movie_id) ON CONFLICT REPLACE"
            + " );";

    public static final String SQL_CREATE_TABLE_REVIEWS = "CREATE TABLE IF NOT EXISTS "
            + ReviewsColumns.TABLE_NAME + " ( "
            + ReviewsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ReviewsColumns.MOVIE_REVIEW + " TEXT, "
            + ReviewsColumns.MOVIE_ID + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_TRAILERS = "CREATE TABLE IF NOT EXISTS "
            + TrailersColumns.TABLE_NAME + " ( "
            + TrailersColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TrailersColumns.MOVIE_TRAILER + " TEXT, "
            + TrailersColumns.MOVIE_ID + " TEXT "
            + " );";

    // @formatter:on

    public static MoviesSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static MoviesSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static MoviesSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new MoviesSQLiteOpenHelper(context);
    }

    private MoviesSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new MoviesSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static MoviesSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new MoviesSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private MoviesSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new MoviesSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CURRENT);
        db.execSQL(SQL_CREATE_TABLE_FAVORITES);
        db.execSQL(SQL_CREATE_TABLE_REVIEWS);
        db.execSQL(SQL_CREATE_TABLE_TRAILERS);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
