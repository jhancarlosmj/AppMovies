package com.jhancarlos.appmovies.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.fragments.MovieAddFragment;
import com.jhancarlos.appmovies.fragments.MoviesFragment;

/**
 * Created by DAM on 28/5/15.
 */
public class MovieAddActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return MovieAddFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_create:
                ((MovieAddFragment) getmCurrentFragment()).addMovie();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
