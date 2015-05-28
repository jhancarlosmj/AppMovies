package com.jhancarlos.appmovies.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.NestedActivityResultFragment;
import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.models.Movie;
import com.jhancarlos.appmovies.util.MovieFactoryJson;


/**
 * Created by DAM on 21/5/15.
 */
public class MovieAddFragment extends NestedActivityResultFragment {

    private TextView mNameMovie;
    private TextView mDirectorMovie;
    private TextView mYearMovie;
    private TextView mCountryMovie;


    public MovieAddFragment() {
    }

    public static MovieAddFragment newInstance() {

        MovieAddFragment movieAddFragment = new MovieAddFragment();
        Bundle extraArguments = new Bundle();
        movieAddFragment.setArguments(extraArguments);
        return movieAddFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addmovie, container, false);

        mNameMovie = (TextView) root.findViewById(R.id.name_movie);
        mDirectorMovie = (TextView) root.findViewById(R.id.director_movie);
        mYearMovie = (TextView) root.findViewById(R.id.year_movie);
        mCountryMovie = (TextView) root.findViewById(R.id.country_movie);

        return root;
    }
    public void addMovie(){
        Movie movie = new Movie();
        movie.setName((String) mNameMovie.getText());
        movie.setDirector((String) mDirectorMovie.getText());
        movie.setYear(Integer.parseInt((String) mYearMovie.getText()));
        movie.setCountry((String) mCountryMovie.getText());

        MovieFactoryJson.data.add(movie);

        getActivity().finish();
    }


}
