package com.jhancarlos.appmovies.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jhancarlos.appmovies.fragments.GetMoviesFragment;

/**
 * Created by Jhancarlos on 30/05/2015.
 */
public class MovieActivity extends BaseActivity {


    @Override
    protected Fragment createFragment() {
        return GetMoviesFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
