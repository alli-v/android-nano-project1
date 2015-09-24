package com.mycompany.project1popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
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

/**
 * Fragment containing the grid view of movies for the MainActivity of the app
 */

public class MovieGridFragment extends Fragment {

    private MovieAdapter mMovieAdapter;
    private String LOG_TAG = "MOVIE_GRID_FRAG";

    public MovieGridFragment() {
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
        mMovieAdapter =                 new MovieAdapter(
                getActivity(), // The current context (this activity)
                new ArrayList<Movie>()); // New arraylist of Movie objects

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_movies);
        gridView.setAdapter(mMovieAdapter);

        // On Movie GridView item click, start the Detail Activity and pass the Movie Object to it
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie details = mMovieAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra("Movie", details);
                startActivity(intent);
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

                Movie currentMovie = new Movie(currentJson.getString("poster_path"),
                        currentJson.getString("title"),
                        currentJson.getString("overview"),
                        currentJson.getString("release_date"),
                        currentJson.getString("vote_average"));

                listOfMovies[i] = currentMovie;

                Log.d(LOG_TAG, currentMovie.toString());

            }

            return listOfMovies;
        }

        /* This all happens on the background thread to avoid "clogging" the UI thread */
        protected Movie[] doInBackground(String... params) {

            //nothing to look up so return from the method
            if(params.length == 0) {
                return null;
            }

            // These two need to be declared outside the try/catch
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
                final String API_KEY = "";

                Uri builtUri = null;

                Log.d(LOG_TAG, "Sort param: " + params[0]);

                //check param[0], which is the SharedPref data for sort
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
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
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
