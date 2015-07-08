package com.appmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.appmovies.HttpRequest.AddMovieCallback;
import com.appmovies.Manager.MovieManagerPOST;
import com.appmovies.R;
import com.appmovies.models.Genre;
import com.appmovies.models.Movie;

public class MovieAddFragment extends Fragment implements AddMovieCallback {

	private TextView mNameMovie;
	private TextView mDirectorMovie;
	private TextView mYearMovie;
	private TextView mCountryMovie;
	private Spinner mGenreSpinner;


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
		mGenreSpinner = (Spinner) root.findViewById(R.id.genre_spinner);

		return root;
	}

	public void addMovie() {
		Movie movie = new Movie();


		if (mNameMovie.getText().toString().equals("")) {
			movie.setName("Default Title");
		} else {
			movie.setName(mNameMovie.getText().toString());
		}


		if (mDirectorMovie.getText().toString().equals("")) {
			movie.setDirector("Default Director");
		} else {
			movie.setDirector(mDirectorMovie.getText().toString());
		}

		if (mYearMovie.getText().toString().trim().equals("")) {
			movie.setYear(1900);
		} else {
			movie.setYear(Integer.parseInt(mYearMovie.getText().toString().trim()));
		}

		if (mCountryMovie.getText().toString().equals("")) {
			movie.setCountry("Default Country");
		} else {
			movie.setCountry(mCountryMovie.getText().toString());
		}

		movie.setGenre(Genre.valueOf(mGenreSpinner.getSelectedItem().toString()));

		MovieManagerPOST.getInstance().setAppContext(getActivity().getApplicationContext());
		MovieManagerPOST.getInstance().setAddMovieCallback(this);
		MovieManagerPOST.getInstance().postMovieToServer(movie);

		Log.d("MovieAddFragment", "movieToAdd: " + movie);


	}


	@Override
	public void onAddedMovieResult(Movie movie) {
		Log.d("MovieAddFragment", "movieAdded: " + movie);
		getActivity().finish();
	}
}
