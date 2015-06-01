package com.jhancarlos.appmovies.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.NestedActivityResultFragment;
import com.jhancarlos.appmovies.R;



/**
 * Created by DAM on 21/5/15.
 */
public class HomeFragment extends NestedActivityResultFragment {


    public HomeFragment() {
    }

    public static HomeFragment newInstance() {

        HomeFragment homeFragment = new HomeFragment();
        Bundle extraArguments = new Bundle();
        homeFragment.setArguments(extraArguments);
        return homeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Add your code here

    }


}
