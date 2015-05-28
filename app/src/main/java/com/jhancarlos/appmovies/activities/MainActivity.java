package com.jhancarlos.appmovies.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.bus.ActivityResultBus;
import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.bus.ActivityResultEvent;
import com.jhancarlos.appmovies.adapters.AdapterNavigationDrawer;
import com.jhancarlos.appmovies.fragments.HomeFragment;
import com.jhancarlos.appmovies.fragments.MoviesRecycleViewCardView;
import com.jhancarlos.appmovies.fragments.NavigationDrawerFragment;
import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.util.MovieFactoryJson;

import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterNavigationDrawer.ItemRecycleClickListener {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    Toolbar toolbar;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        if (mTitle != null){
            toolbar.setTitle(mTitle);
        }else {
            toolbar.setTitle(R.string.app_name);
        }

        setSupportActionBar(toolbar);

        mTitle = toolbar.getTitle();

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer_layout);

        mNavigationDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.activity_drawer_layout), toolbar, this);


        fragmentInitial();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemRecycleClicked(int position) {

        switch (position) {

            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance())
                        .commit();

                break;
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance())
                        .commit();


                break;
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MoviesRecycleViewCardView.newInstance())
                        .commit();


                break;
        }
        onSectionAttached(position);

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.home);
                break;
            case 1:
                mTitle = getString(R.string.home);
                break;
            case 2:
                mTitle = getString(R.string.movies);
                break;
        }


        if (toolbar != null) {
            toolbar.setTitle(mTitle);
            drawerLayout.closeDrawers();
        }
    }

    private void fragmentInitial() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commit();
        toolbar.setTitle(getResources().getString(R.string.home));
    }
}
