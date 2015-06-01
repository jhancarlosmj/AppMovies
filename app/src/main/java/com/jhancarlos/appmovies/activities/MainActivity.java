package com.jhancarlos.appmovies.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.jhancarlos.appmovies.fragments.HomeFragment;
import com.jhancarlos.appmovies.R;


public class MainActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
