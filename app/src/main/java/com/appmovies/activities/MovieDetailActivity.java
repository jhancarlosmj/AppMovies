package com.appmovies.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.appmovies.fragments.MovieDetailFragment;

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
