package com.appmovies.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.appmovies.R;


public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CharSequence mTitle;
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
            getSupportActionBar().setElevation(8);


            if (mTitle != null){
                toolbar.setTitle(mTitle);
            }else {
                toolbar.setTitle(R.string.app_name);
            }

            mTitle = toolbar.getTitle();
        }

        loadFragment();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
    }




    protected void setActionBarIcon(int iconRes){
        toolbar.setNavigationIcon(iconRes);
    }

}
