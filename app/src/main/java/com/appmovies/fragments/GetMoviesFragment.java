package com.appmovies.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appmovies.HttpRequest.GetMoviesCallback;
import com.appmovies.Manager.MoviesManagerGET;
import com.appmovies.R;
import com.appmovies.activities.MovieAddActivity;
import com.appmovies.activities.MovieDetailActivity;
import com.appmovies.adapters.MoviesRecycleAdapter;
import com.appmovies.adapters.MoviesRecycleAdapter.ItemRecycleClickListener;
import com.appmovies.models.Movie;
import com.appmovies.util.SimpleOrientationListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetMoviesFragment extends Fragment implements ItemRecycleClickListener, GetMoviesCallback {

    private RecyclerView movieRecyclerView;
    private View root;
    private List<Movie> moviesArrayList;
    private FloatingActionButton fabBtn;

    public GetMoviesFragment(){

    }

    public static GetMoviesFragment newInstance(){
        GetMoviesFragment moviesFragment = new GetMoviesFragment();
        Bundle extraArguments = new Bundle();
        moviesFragment.setArguments(extraArguments);
        return moviesFragment;
    }

    MoviesRecycleAdapter moviesRecycleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        root = inflater.inflate(R.layout.fragment_movies, container, false);



        return root;

    }

    @Override
    public void onResume(){
        super.onResume();

        MoviesManagerGET.getInstance().setGetMoviesCallback(this);
        MoviesManagerGET.getInstance().loadMoviesFromServer();

        if (movieRecyclerView != null && movieRecyclerView.getAdapter() != null) {
            movieRecyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void itemRecycleClicked(int position, String name) {
        Intent i = new Intent(getActivity(), MovieDetailActivity.class);
        i.putExtra("KEY_CURRENT_MOVIE", moviesArrayList.get(position).getId());
        this.startActivity(i);
        Log.d("Position",""+position);
    }

    @Override
    public void onGetMoviesResult(Movie[] movies) {
        moviesArrayList = new ArrayList<>(Arrays.asList(movies));
        moviesRecycleAdapter = new MoviesRecycleAdapter(moviesArrayList, R.layout.item_card_view);
        moviesRecycleAdapter.setItemRecycleClickListener(this);
        movieRecyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        movieRecyclerView.setHasFixedSize(true);


        SimpleOrientationListener mOrientationListener = new SimpleOrientationListener(getActivity()) {

            @Override
            public void onSimpleOrientationChanged(int orientation) {
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    movieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    movieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                }
            }
        };

        mOrientationListener.enable();
        movieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerView.setAdapter(moviesRecycleAdapter);

        fabBtn = (FloatingActionButton) getActivity().findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MovieAddActivity.class);
                startActivity(i);
            }
        });
    }
}
