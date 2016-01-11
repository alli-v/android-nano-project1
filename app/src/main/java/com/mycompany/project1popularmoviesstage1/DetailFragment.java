package com.mycompany.project1popularmoviesstage1;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesColumns;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesContentValues;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesCursor;
import com.mycompany.project1popularmoviesstage1.provider.favorites.FavoritesSelection;
import com.mycompany.project1popularmoviesstage1.provider.reviews.ReviewsContentValues;
import com.mycompany.project1popularmoviesstage1.provider.trailers.TrailersContentValues;
import com.squareup.picasso.Picasso;

/**
 * Movie detail view fragment
 */
public class DetailFragment extends Fragment {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    //the Movie we are viewing details for
    private Movie thisMovie;
    ImageButton favButton;
    Button trailerBtn;
    private ShareActionProvider mShareActionProvider;

    static final String DETAIL_URI = "URI";

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle arguments = getArguments();
        if(arguments != null) {
            thisMovie = (Movie) arguments.getSerializable(DetailFragment.DETAIL_URI);

            ((TextView) rootView.findViewById(R.id.detail_title_text))
                    .setText(thisMovie.getTitle());
            ((TextView) rootView.findViewById(R.id.detail_release_date))
                    .setText(thisMovie.getReleaseDate());
            ((TextView) rootView.findViewById(R.id.detail_vote_average))
                    .setText(thisMovie.getVoteAvg());
            ((TextView) rootView.findViewById(R.id.detail_overview_text))
                    .setText(thisMovie.getOverview());

            //add buttons for each trailer in the list
            String[] thisMovieTrailers = thisMovie.getTrailers();
            LinearLayout trailersView = (LinearLayout) rootView.findViewById(R.id.detail_trailer_list);
            Log.d("TRAILERS", thisMovieTrailers.toString());
            for(int i=0; i < thisMovieTrailers.length; i++) {
                Button trailerBtn = new Button(getContext());
                int index = i + 1;
                trailerBtn.setText("Trailer " + index);
                trailerBtn.setId(R.id.trailer_list_item);
                trailerBtn.setTag(R.id.TAG_ONLINE_ID, thisMovieTrailers[i]);
                trailersView.addView(trailerBtn);
            }

            String[] thisMovieReviews = thisMovie.getReviews();
            if(thisMovieReviews != null) {
                LinearLayout reviewsView = (LinearLayout) rootView.findViewById(R.id.detail_review_list);
                if(thisMovieReviews.length == 0) {
                    TextView reviewText = new TextView(getContext());
                    reviewText.setText("No reviews available for this film.");
                    reviewText.setTextColor(Color.BLACK);
                    reviewsView.addView(reviewText);
                }
                     else {
                        for (int i = 0; i < thisMovieReviews.length; i++) {
                            TextView reviewText = new TextView(getContext());
                            int index = i + 1;
                            reviewText.setText("Review " + index + ": " + thisMovieReviews[i]);
                            reviewText.setTextColor(Color.BLACK);
                            reviewsView.addView(reviewText);
                        }
                    }
            }

            ImageView posterImg = (ImageView) rootView.findViewById(R.id.detail_poster_image);
            Picasso.with(getContext()).load(thisMovie.getPosterPath()).into(posterImg);
        }

        // The detail Activity called via intent for small screen sizes
        Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra("Movie")) {
            //get Movie object from Intent and set the values of UI elements according to its properties
            thisMovie = (Movie) intent.getSerializableExtra("Movie");

            ((TextView) rootView.findViewById(R.id.detail_title_text))
                    .setText(thisMovie.getTitle());
            ((TextView) rootView.findViewById(R.id.detail_release_date))
                    .setText(thisMovie.getReleaseDate());
            ((TextView) rootView.findViewById(R.id.detail_vote_average))
                    .setText(thisMovie.getVoteAvg());
            ((TextView) rootView.findViewById(R.id.detail_overview_text))
                    .setText(thisMovie.getOverview());

            //add buttons for each trailer in the list
            String[] thisMovieTrailers = thisMovie.getTrailers();
            LinearLayout trailersView = (LinearLayout) rootView.findViewById(R.id.detail_trailer_list);
            Log.d("TRAILERS", thisMovieTrailers.toString());
            for(int i=0; i < thisMovieTrailers.length; i++) {
                Button trailerBtn = new Button(getContext());
                trailerBtn.setId(R.id.trailer_list_item);
                int index = i + 1;
                trailerBtn.setText("Trailer " + index);
                trailerBtn.setTag(R.id.TAG_ONLINE_ID, thisMovieTrailers[i]);
                trailersView.addView(trailerBtn);
            }

            String[] thisMovieReviews = thisMovie.getReviews();
            if(thisMovieReviews != null) {
                LinearLayout reviewsView = (LinearLayout) rootView.findViewById(R.id.detail_review_list);

                if(thisMovieReviews.length == 0) {
                    TextView reviewText = new TextView(getContext());
                    reviewText.setText("No reviews available for this film.");
                    reviewText.setTextColor(Color.BLACK);
                    reviewsView.addView(reviewText);
                }
                else {
                    for (int i = 0; i < thisMovieReviews.length; i++) {
                        TextView reviewText = new TextView(getContext());
                        int index = i + 1;
                        reviewText.setText("Review " + index + ": " + thisMovieReviews[i]);
                        reviewText.setTextColor(Color.BLACK);
                        reviewsView.addView(reviewText);
                    }
                }
            }

            ImageView posterImg = (ImageView) rootView.findViewById(R.id.detail_poster_image);
            Picasso.with(getContext()).load(thisMovie.getPosterPath()).into(posterImg);
        }



        favoritesListener(rootView);
        trailerListener(rootView);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detail, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if(mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareTrailerIntent());
        }
    }

    private Intent createShareTrailerIntent() {

        if(thisMovie != null && thisMovie.getTrailers() != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            String youtubeLink = "Watch the trailer for " + thisMovie.getTitle() + " at youtube.com/watch?v=" + thisMovie.getTrailers()[0];
            shareIntent.putExtra(Intent.EXTRA_TEXT, youtubeLink);
            return shareIntent;
        }
        return null;
    }

    //open trailer in youtube on button click
    public void trailerListener(View rootView) {
        trailerBtn = (Button) rootView.findViewById(R.id.trailer_list_item);
        if(trailerBtn != null) {
            trailerBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String currentTrailer = trailerBtn.getTag(R.id.TAG_ONLINE_ID).toString();
                    String uri = "https://www.youtube.com/watch?v=" + currentTrailer;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }
    }

    //add movie to favorites db on star icon click
    public void favoritesListener(View rootView) {
        favButton = (ImageButton) rootView.findViewById(R.id.detail_favorite_image);

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Added to favorites", Toast.LENGTH_SHORT).show();

                //if the movie is already in favorites, don't do anything
                FavoritesSelection favoritesSelection = new FavoritesSelection();
                favoritesSelection.movieId(Integer.toString(thisMovie.getId()));
                String[] projection = {FavoritesColumns._ID, FavoritesColumns.MOVIE_ID};
                FavoritesCursor fC = favoritesSelection.query(getContext().getContentResolver(), projection);

//                if(fC != null) {
//                    return;
//                }

                //add all movie attributes to favorites table
                FavoritesContentValues values = new FavoritesContentValues();
                values.putMovieId(Integer.toString(thisMovie.getId()));
                values.putMovieTitle(thisMovie.getTitle());
                values.putPosterImg(thisMovie.getPosterPath());
                values.putMovieAvgRating(thisMovie.getVoteAvg());
                values.putMovieOverview(thisMovie.getOverview());
                values.putMovieReleaseDate(thisMovie.getReleaseDate());
                values.insert(getContext().getContentResolver());

                //add each trailer to the trailers table
                TrailersContentValues trailers = new TrailersContentValues();
                String[] currentTrailers = thisMovie.getTrailers();

                if(currentTrailers != null){

                    for(int i=0; i< currentTrailers.length; i++) {
                        Log.d("CURRENT TRAILERS", currentTrailers[i]);
                        trailers.putMovieTrailer(currentTrailers[i]);
                        trailers.putMovieId(Integer.toString(thisMovie.getId()));
                    }
                    trailers.insert(getContext().getContentResolver());

                }
                //add each review to the reviews table
                ReviewsContentValues reviews = new ReviewsContentValues();
                String[] currentReviews = thisMovie.getReviews();

                if(currentReviews != null && currentReviews.length > 0) {
                    for(int i=0; i<currentReviews.length; i++) {
                        reviews.putMovieReview(currentReviews[i]);
                        reviews.putMovieId(Integer.toString(thisMovie.getId()));
                    }

                    reviews.insert(getContext().getContentResolver());
                }

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }


}
