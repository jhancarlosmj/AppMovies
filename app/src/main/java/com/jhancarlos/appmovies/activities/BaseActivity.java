package com.jhancarlos.appmovies.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.fragments.NavigationDrawerFragment;


public abstract class BaseActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(8);
        }

    }

    protected abstract int getLayoutResource();

    protected void setActionBarIcon(int iconRes){
        toolbar.setNavigationIcon(iconRes);
    }

}
