package com.jhancarlos.appmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.bus.ActivityResultBus;
import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.bus.ActivityResultEvent;
import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.adapters.AdapterNavigationDrawer;
import com.jhancarlos.appmovies.fragments.NavigationDrawerFragment;


public abstract class BaseActivity extends ActionBarActivity implements AdapterNavigationDrawer.ItemRecycleClickListener {

    private Toolbar toolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    DrawerLayout drawerLayout;
    private Fragment mCurrentFragment;


    protected abstract Fragment createFragment();

    protected void loadFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, createFragment()).commit();
    }

    public Fragment getmCurrentFragment(){
        return mCurrentFragment;
    }

    public void setmCurrentFragment(Fragment mCurrentFragment){
        this.mCurrentFragment = mCurrentFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(8);


            if (mTitle != null){
                toolbar.setTitle(mTitle);
            }else {
                toolbar.setTitle(R.string.app_name);
            }

            mTitle = toolbar.getTitle();
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer_layout);

        mNavigationDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.activity_drawer_layout), toolbar, this);

        loadFragment();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultBus.getInstance().postQueue(new ActivityResultEvent(requestCode, resultCode, data));
    }

    @Override
    public void itemRecycleClicked(int position) {

        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        switch (position) {

            case 0:
                i.setClass(this,MainActivity.class);
               /* getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance())
                        .commit();*/

                break;
            case 1:
                i.setClass(this,MainActivity.class);
               /* getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance())
                        .commit();*/


                break;
            case 2:
                i.setClass(this,MovieActivity.class);
               /* getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MoviesFragment.newInstance())
                        .commit();*/

                break;
        }
        this.startActivity(i);
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


    protected void setActionBarIcon(int iconRes){
        toolbar.setNavigationIcon(iconRes);
    }

}
