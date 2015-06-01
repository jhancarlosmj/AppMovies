package com.jhancarlos.appmovies.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.NestedActivityResultFragment;
import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.activities.MovieAddActivity;
import com.jhancarlos.appmovies.activities.MovieDetailActivity;
import com.jhancarlos.appmovies.adapters.MoviesRecycleAdapter;
import com.jhancarlos.appmovies.adapters.MoviesRecycleAdapter.ItemRecycleClickListener;
import com.jhancarlos.appmovies.models.Movie;
import com.jhancarlos.appmovies.util.MovieFactoryJson;
import com.jhancarlos.appmovies.util.SimpleOrientationListener;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.FloatingActionButton.FabRecyclerOnViewScrollListener;

import java.util.ArrayList;

/**
 * Created by DAM on 18/5/15.
 */
public class MoviesFragment extends NestedActivityResultFragment implements ItemRecycleClickListener {

    private RecyclerView movieRecyclerView;
    private MovieFactoryJson readLocalJSON = new MovieFactoryJson();
    ArrayList<Movie> moviesArrayList;

    public MoviesFragment(){

    }

    public static MoviesFragment newInstance(){
        MoviesFragment moviesFragment = new MoviesFragment();
        Bundle extraArguments = new Bundle();
        moviesFragment.setArguments(extraArguments);
        return moviesFragment;
    }

    MoviesRecycleAdapter moviesRecycleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_movies,container,false);

        moviesArrayList = readLocalJSON.getMovie(getActivity());
        moviesRecycleAdapter = new MoviesRecycleAdapter(moviesArrayList, R.layout.item_card_view);
        moviesRecycleAdapter.setItemRecycleClickListener(this);
        movieRecyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        movieRecyclerView.setHasFixedSize(true);

        SimpleOrientationListener mOrientationListener = new SimpleOrientationListener(getActivity()) {

            @Override
            public void onSimpleOrientationChanged(int orientation) {
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    movieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
                }else if(orientation == Configuration.ORIENTATION_PORTRAIT){
                    movieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                }
            }
        };
        mOrientationListener.enable();
        movieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerView.setAdapter(moviesRecycleAdapter);

        com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) root.findViewById(R.id.fab);
        fab.setShadow(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MovieAddActivity.class);
                startActivity(i);
            }
        });


        return root;

    }

    @Override
    public void onResume(){
        super.onResume();
        movieRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void itemRecycleClicked(int position, String name) {
        Intent i = new Intent(getActivity(), MovieDetailActivity.class);
        i.putExtra("KEY_CURRENT_MOVIE",position);
        this.startActivity(i);
        Log.d("Position",""+position);
    }
}
