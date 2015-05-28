package com.jhancarlos.appmovies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.NestedActivityResultFragment;
import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.activities.MovieAddActivity;
import com.jhancarlos.appmovies.adapters.MoviesRecycleAdapter;
import com.jhancarlos.appmovies.adapters.MoviesRecycleAdapter.ItemRecycleClickListener;
import com.jhancarlos.appmovies.models.Movie;
import com.jhancarlos.appmovies.util.MovieFactoryJson;

import java.util.ArrayList;

/**
 * Created by DAM on 18/5/15.
 */
public class MoviesRecycleViewCardView extends NestedActivityResultFragment implements ItemRecycleClickListener {


    public MoviesRecycleViewCardView(){

    }

    public static MoviesRecycleViewCardView newInstance(){
        MoviesRecycleViewCardView moviesRecycleViewCardView = new MoviesRecycleViewCardView();
        Bundle extraArguments = new Bundle();
        moviesRecycleViewCardView.setArguments(extraArguments);
        return moviesRecycleViewCardView;
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_movies_recycle_view_card_view,container,false);
        return root;

    }



    MoviesRecycleAdapter moviesRecycleAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Movie> moviesArrayList;
        MovieFactoryJson readLocalJSON = new MovieFactoryJson();
        moviesArrayList = readLocalJSON.getMovie(getActivity());
        moviesRecycleAdapter = new MoviesRecycleAdapter(moviesArrayList, R.layout.item_card_view);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesRecycleAdapter);
        moviesRecycleAdapter.setItemRecycleClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setShadow(true);

        fab.attachToRecyclerView(recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), MovieAddActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public void itemRecycleClicked(int position, String name) {
        String n = Integer.toString(position);

        Toast.makeText(getActivity(),n+name ,Toast.LENGTH_LONG).show();

    }
}
