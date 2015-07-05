package com.jhancarlos.appmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.models.Movie;
import com.jhancarlos.appmovies.util.MovieFactoryJson;


/**
 * Created by DAM on 21/5/15.
 */
public class MovieDetailFragment extends Fragment {


    public static final String KEY_CURRENT_MOVIE = "KEY_CURRENT_MOVIE";
    private Movie currentMovie;
    private ImageView mCaratulaDetailMovie;
    private TextView mNameDetailMovie;
    private TextView mDirectorDetailMovie;
    private TextView mYearDetailMovie;
    private TextView mCountryDetailMovie;
    private Toolbar toolbar;


    public MovieDetailFragment() {
    }

    public static MovieDetailFragment newInstance() {

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle extraArguments = new Bundle();
        movieDetailFragment.setArguments(extraArguments);
        return movieDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentMovieId = getActivity().getIntent().getExtras().getInt(KEY_CURRENT_MOVIE);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);


        currentMovie = MovieFactoryJson.data.get(currentMovieId);

        getActivity().setTitle(currentMovie.getName());

        Log.d("Current Movie",""+currentMovie);
        Log.d("Year Movie",""+currentMovie.getYear());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detailmovie, container, false);


        mNameDetailMovie = (TextView) root.findViewById(R.id.movie_detail_title);
        mNameDetailMovie.setText(currentMovie.getName());

        mDirectorDetailMovie = (TextView) root.findViewById(R.id.movie_detail_director);
        mDirectorDetailMovie.setText(currentMovie.getDirector());

        mYearDetailMovie = (TextView) root.findViewById(R.id.movie_detail_year);
        mYearDetailMovie.setText(""+currentMovie.getYear());

        mCountryDetailMovie = (TextView) root.findViewById(R.id.movie_detail_country);
        mCountryDetailMovie.setText(currentMovie.getCountry());

        return root;
    }

}
