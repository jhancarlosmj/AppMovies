package com.jhancarlos.appmovies.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.fragments.MovieDetailFragment;
import com.jhancarlos.appmovies.fragments.MoviesFragment;

/**
 * Created by Jhancarlos on 30/05/2015.
 */
public class MovieDetailActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return MovieDetailFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
