package com.mycompany.project1popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Movie detail view fragment
     */
    public static class DetailFragment extends Fragment {

        private static final String LOG_TAG = DetailFragment.class.getSimpleName();

        //the Movie we are viewing details for
        private Movie thisMovie;

        public DetailFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            // The detail Activity called via intent.  Inspect the intent for forecast data.
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
                ImageView posterImg = (ImageView) rootView.findViewById(R.id.detail_poster_image);
                Picasso.with(getContext()).load(thisMovie.getPosterPath()).into(posterImg);
            }

            return rootView;
        }


    }

}
