package com.mycompany.project1popularmoviesstage1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mycompany.project1popularmoviesstage1.provider.current.CurrentContentValues;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesColumns;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesCursor;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesSelection;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsColumns;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsContentValues;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsCursor;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsSelection;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersColumns;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersContentValues;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersCursor;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersSelection;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Fragment containing the grid view of movies for the MainActivity of the app
 */

public class MovieGridFragment extends Fragment {

    private MovieAdapter mMovieAdapter;
    private String LOG_TAG = "MOVIE_GRID_FRAG";
    OnMovieSelectedListener mCallback;
    final String API_KEY = "";

    public MovieGridFragment() {
        setHasOptionsMenu(true);
    }

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface OnMovieSelectedListener {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onMovieSelected(int moviePos, Movie movie);
    }

    @Override
    public void onAttach(Activity activity) {
    super.onAttach(activity);
        //makes sure the container activity has implemented the callback interface
        try {
            mCallback = (OnMovieSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnMovieSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mMovieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_movies);
        gridView.setAdapter(mMovieAdapter);

        // On Movie GridView item click, start the Detail Activity and pass the Movie Object to it
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie details = mMovieAdapter.getItem(position);
                mCallback.onMovieSelected(position, details);
                Log.d(LOG_TAG, "Clicked: " + details);
            }
        });
        return rootView;
    }

    /* Update Movie GridView by getting preferences and re-executing FetchMoviesTask */
    private void updateMovies() {
        FetchMoviesTask movieTask = new FetchMoviesTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sort_type = prefs.getString("sort", "popularity");
        movieTask.execute(sort_type);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    /* Fetch movie info by querying themoviedb API */
    public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {
        private Movie[] getMovieDataFromJson(String movieJsonStr) throws JSONException {
            final int NUM_RESULTS = 10;


            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = movieJson.getJSONArray("results");

            Movie[] listOfMovies = new Movie[movieArray.length()];

            //iterate through the results array
            for(int i=0; i< movieArray.length(); i++) {

                JSONObject currentJson = movieArray.getJSONObject(i);

                Movie currentMovie = new Movie(currentJson.getInt("id"),
                        currentJson.getString("poster_path"),
                        currentJson.getString("title"),
                        currentJson.getString("overview"),
                        currentJson.getString("release_date"),
                        currentJson.getString("vote_average"));

                CurrentContentValues values = new CurrentContentValues();
                values.putMovieId(Integer.toString(currentMovie.getId()));
                values.putPosterImg(currentMovie.getPosterPath());
                values.putMovieTitle(currentMovie.getTitle());
                values.putMovieOverview(currentMovie.getOverview());
                values.putMovieReleaseDate(currentMovie.getReleaseDate());
                values.putMovieAvgRating(currentMovie.getVoteAvg());


                listOfMovies[i] = currentMovie;

                FetchVideosReviewsTask videosReviewsTask = new FetchVideosReviewsTask();
                videosReviewsTask.execute(listOfMovies[i]);

                TrailersSelection tSelection = new TrailersSelection();
                tSelection.movieIdNot("abc");
                String[] projection = {TrailersColumns._ID, TrailersColumns.MOVIE_ID, TrailersColumns.MOVIE_TRAILER};

                ArrayList<String> trailersList = new ArrayList<String>();

                TrailersCursor tC = tSelection.query(getContext().getContentResolver(), projection);
                if(tC == null) {
                    Log.d(LOG_TAG, "CURSOR NULL");
                } else {
                    while (tC.moveToNext()) {
                        trailersList.add(tC.getMovieTrailer());
                    }
                }

                String[] trailersArray = new String[trailersList.size()];
                for(int j=0; j< trailersList.size(); j++) {
                    trailersArray[j] = trailersList.get(j);
                }
                currentMovie.setTrailers(trailersArray);

                Log.d(LOG_TAG, currentMovie.toString());

            }

            return listOfMovies;
        }

        /* This all happens on the background thread to avoid "clogging" the UI thread */
        protected Movie[] doInBackground(String... params) {

            //Load detail information from the favorites database
            if(params[0].equals("favorites")) {
                Log.d(LOG_TAG, "Sorting based on favorites");
                ArrayList<Movie> favoriteMovies = new ArrayList<Movie>();

                FavoritesSelection favoritesSelection = new FavoritesSelection();
                favoritesSelection.movieIdNot("abc");
                String[] projection = {FavoritesColumns._ID, FavoritesColumns.MOVIE_ID, FavoritesColumns.MOVIE_TITLE, FavoritesColumns.MOVIE_OVERVIEW, FavoritesColumns.MOVIE_AVG_RATING,
                        FavoritesColumns.MOVIE_RELEASE_DATE, FavoritesColumns.POSTER_IMG
                };
                FavoritesCursor c = favoritesSelection.query(getContext().getContentResolver(), projection);
                if(c == null) {
                    Log.d(LOG_TAG, "CURSOR NULL");
                    return new Movie[0];
                }

                while(c.moveToNext()){
                    String id = c.getMovieId();
                    String title = c.getMovieTitle();
                    String overview = c.getMovieOverview();
                    String poster = c.getPosterImg();
                    String rating = c.getMovieAvgRating();
                    String release = c.getMovieReleaseDate();
                    Movie currentFav = new Movie(Integer.parseInt(id), poster, title, overview, release, rating);
                    Log.d(LOG_TAG, "SAVED TITLE 2: " + c.getMovieTitle());
                    favoriteMovies.add(currentFav);


                    //add trailer information from the DB to the view
                    TrailersSelection trailersSelection = new TrailersSelection();
                    trailersSelection.movieId(id);
                    String[] tProjection = {TrailersColumns.MOVIE_TRAILER};
                    TrailersCursor tC = trailersSelection.query(getContext().getContentResolver(), tProjection);

                    if(tC == null) {
                        return new Movie[0];
                    }

                    Set<String> trailerSet = new HashSet<>();
                    ArrayList<String> trailersArray = new ArrayList<String>();

                    while(tC.moveToNext()) {
                        trailerSet.add(tC.getMovieTrailer());
                    }
                    trailersArray.addAll(trailerSet);
                    String[] currentTrailers = new String[trailersArray.size()];
                    for(int i=0; i< trailersArray.size(); i++) {
                        currentTrailers[i] = trailersArray.get(i);
                        Log.d("why", currentTrailers[i]);
                    }
                    currentFav.setTrailers(currentTrailers);

                    //add review information from the DB to the view
                    ReviewsSelection reviewsSelection = new ReviewsSelection();
                    reviewsSelection.movieId(id);
                    String[] rProjection = {ReviewsColumns.MOVIE_REVIEW};
                    ReviewsCursor rC = reviewsSelection.query(getContext().getContentResolver(), rProjection);

                    if(rC == null) {
                        return new Movie[0];
                    }

                    Set<String> reviewSet = new HashSet<>();
                    ArrayList<String> reviewArray = new ArrayList<String>();

                    while(rC.moveToNext()) {
                        reviewSet.add(rC.getMovieReview());
                    }
                    reviewArray.addAll(reviewSet);
                    String[] currentReviews = new String[reviewArray.size()];
                    for(int i=0; i< reviewArray.size(); i++) {
                        currentReviews[i] = reviewArray.get(i);
                        Log.d("why", currentReviews[i]);
                    }
                    currentFav.setReviews(currentReviews);

                }

                Movie[] array = new Movie[favoriteMovies.size()];

                for(int i=0; i< favoriteMovies.size(); i++) {
                    array[i] = favoriteMovies.get(i);
                }
                return array;
            }

            //nothing to look up so return from the method
            if(params.length == 0) {
                return null;
            }

            // Query the MoviesDB API to show most popular or highest rating, depending on param[0] value
            // These need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJsonStr = null;

            try {
                //construct movieDB query using sort type and api key
                final String BASE_URL = "https://api.themoviedb.org/3/discover/movie?";
                final String SORT_QUERY = "sort_by";
                final String SORT_POPULARITY = "popularity.desc";
                final String SORT_RATING = "vote_avg.desc";
                final String VOTE_QUERY = "vote_count.gte";
                final String VOTE_VAL = "10";
                final String API_QUERY = "api_key";


                Uri builtUri = null;

                Log.d(LOG_TAG, "Sort param: " + params[0]);

                //check param[0], which is the SharedPref data for sort and build URI accordingly
                if(params[0].equals("popularity")) {
                    builtUri = Uri.parse(BASE_URL).buildUpon()
                            .appendQueryParameter(SORT_QUERY, SORT_POPULARITY)
                            .appendQueryParameter(API_QUERY, API_KEY)
                            .build();
                    Log.d(LOG_TAG, "Popularity URI: " + builtUri.toString());
                }
                else if (params[0].equals("rating")) {
                    builtUri = Uri.parse(BASE_URL).buildUpon()
                            .appendQueryParameter(SORT_QUERY, SORT_RATING)
                            .appendQueryParameter(VOTE_QUERY, VOTE_VAL)
                            .appendQueryParameter(API_QUERY, API_KEY)
                            .build();
                    Log.d(LOG_TAG, "Rating URI: " + builtUri.toString());
                }
                else {
                    Log.d(LOG_TAG, "Not a valid sort type");
                    return null;
                }

                URL url = new URL(builtUri.toString());

                // Create the request to movieDB and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                movieJsonStr = buffer.toString();

            }
            catch (IOException e) {
                Log.e(LOG_TAG, "IO Error " + e);
                //return out if the data wasn't fetched successfully
                return null;
            }
            finally {
                //no matter what, close the stream
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream ", e);
                    }
                }
            }

            try {
                Log.d(LOG_TAG, movieJsonStr);
                return getMovieDataFromJson(movieJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the movie data
            return null;
        }

        /* Data is back from the server and will now be added to the adapter */
        @Override
        protected void onPostExecute(Movie[] result) {
            if (result != null) {
                mMovieAdapter.clear();
                for(Movie movie : result) {
                    mMovieAdapter.add(movie);
                }
            }
        }
    }

    /* Fetch movie trailer and review info by querying themoviedb API */
    public class FetchVideosReviewsTask extends AsyncTask<Movie, Void, Movie> {
        private void getMovieDataFromMovie(String movieJsonStr) throws JSONException { }

        /* This all happens on the background thread to avoid "clogging" the UI thread */
        protected Movie doInBackground(Movie... params) {

            //nothing to look up so return from the method
            if(params.length == 0) {
                return null;
            }
            String videos;
            String reviews;
            Movie currentMov = (Movie) params[0];
            String currentId = Integer.toString(params[0].getId());

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnectionV = null;
            HttpURLConnection urlConnectionR = null;
            BufferedReader readerV = null;
            BufferedReader readerR = null;


            //get trailers
            try {
                //construct movieDB query using sort type and api key
                final String BASE_URL_VID = "https://api.themoviedb.org/3/movie/" + currentId + "/videos";
                final String BASE_URL_REV = "https://api.themoviedb.org/3/movie/" + currentId + "/reviews";
                final String API_QUERY = "api_key";

                Uri builtUriVid = null;
                Uri builtUriRev = null;

                Log.d(LOG_TAG, "Current ID: " + currentId);
                Log.d(LOG_TAG, "Current Movie: " + currentMov.toString());

                //build the URIs and URLs
                builtUriVid = Uri.parse(BASE_URL_VID).buildUpon()
                            .appendQueryParameter(API_QUERY, API_KEY)
                            .build();
                Log.d(LOG_TAG, "Video URI: " + builtUriVid.toString());

                builtUriRev = Uri.parse(BASE_URL_REV).buildUpon()
                        .appendQueryParameter(API_QUERY, API_KEY)
                        .build();
                Log.d(LOG_TAG, "Review URI: " + builtUriRev.toString());

                URL urlV = new URL(builtUriVid.toString());
                URL urlR = new URL(builtUriRev.toString());

                // Create the request to movieDB and open the connection
                urlConnectionV = (HttpURLConnection) urlV.openConnection();
                urlConnectionV.setRequestMethod("GET");
                urlConnectionV.connect();

                urlConnectionR = (HttpURLConnection) urlR.openConnection();
                urlConnectionR.setRequestMethod("GET");
                urlConnectionR.connect();

                // Read the input stream into a String
                InputStream inputStreamV = urlConnectionV.getInputStream();
                InputStream inputStreamR = urlConnectionR.getInputStream();

                StringBuffer bufferV = new StringBuffer();
                StringBuffer bufferR = new StringBuffer();

                if (inputStreamV == null) {
                    // Nothing to do.
                    return null;
                }
                if (inputStreamR == null) {
                    // Nothing to do.
                    return null;
                }
                readerV = new BufferedReader(new InputStreamReader(inputStreamV));
                readerR = new BufferedReader(new InputStreamReader(inputStreamR));

                String lineV;
                String lineR;
                while ((lineV = readerV.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    bufferV.append(lineV + "\n");
                }

                if (bufferV.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                while ((lineR = readerR.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    bufferR.append(lineR + "\n");
                }

                if (bufferR.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                videos = bufferV.toString();
                reviews = bufferR.toString();

                //set videos and reviews properties on current Movie object
                try {
                    JSONObject movieJson = new JSONObject(videos);
                    JSONArray movieJSONArray = movieJson.getJSONArray("results");
                    String[] movieArray = new String[movieJSONArray.length()];

                    for(int i=0; i< movieJSONArray.length(); i++) {
                        JSONObject currentMovJson = movieJSONArray.getJSONObject(i);
                        movieArray[i] = currentMovJson.getString("id");
                    }
                    currentMov.setTrailers(movieArray);

                    JSONObject reviewJson = new JSONObject(reviews);
                    JSONArray reviewsJSONArray = reviewJson.getJSONArray("results");
                    String[] reviewsArray = new String[reviewsJSONArray.length()];
                    for(int i=0; i< reviewsJSONArray.length(); i++) {
                        JSONObject currentReviewJson = reviewsJSONArray.getJSONObject(i);
                        reviewsArray[i] = currentReviewJson.getString("content");
                    }
                    currentMov.setReviews(reviewsArray);

                    Log.d(LOG_TAG, currentMov.toString());



                    if(movieArray != null && movieArray.length > 0){
                        for(int i=0; i< movieArray.length; i++) {
                            //add each trailer to the trailers table
                            TrailersContentValues trailersVals = new TrailersContentValues();
                            trailersVals.putMovieTrailer(movieArray[i]);
                            trailersVals.putMovieId(currentId);
                            trailersVals.insert(getContext().getContentResolver());
                        }
                    }

                    if(reviewsArray != null && reviewsArray.length > 0) {
                        for(int i=0; i<reviewsArray.length; i++) {
                            //add each review to the reviews table
                            ReviewsContentValues reviewsVals = new ReviewsContentValues();
                            reviewsVals.putMovieReview(reviewsArray[i]);
                            reviewsVals.putMovieId(currentId);
                            reviewsVals.insert(getContext().getContentResolver());
                        }
                    }
                }
                catch(JSONException e) {
                    Log.e(LOG_TAG, "Invalid movie JSON");
                }

            }
            catch (IOException e) {
                Log.e(LOG_TAG, "IO Error " + e);

                //return out if the data wasn't fetched successfully
                return null;
            }
            finally {
                //no matter what, close the stream
                if (urlConnectionV != null) {
                    urlConnectionV.disconnect();
                }
                if (readerV != null) {
                    try {
                        readerV.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing video stream ", e);
                    }
                }

                if (urlConnectionR != null) {
                    urlConnectionR.disconnect();
                }
                if (readerR != null) {
                    try {
                        readerR.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing review stream ", e);
                    }
                }
            }


            // This will only happen if there was an error getting or parsing the movie data
            return null;
        }

        /* Data is back from the server and will now be added to the adapter */
        @Override
        protected void onPostExecute(Movie result) {
            if (result != null) {

            }
        }

    }

    /* Custom adapter to accommodate Movie objects */
    public class MovieAdapter extends ArrayAdapter<Movie> {

        public MovieAdapter(Context context, ArrayList<Movie> movies) {
            super(context, 0, movies);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Movie movie = getItem(position);

            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_movie, parent, false);
            }

            ImageView posterImg = (ImageView) convertView.findViewById(R.id.grid_item_movie_image);
            Picasso.with(getContext()).load(movie.getPosterPath()).into(posterImg);
            return convertView;
        }
    }

}
