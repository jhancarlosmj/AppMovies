package com.jhancarlos.appmovies.HttpRequest;

import com.jhancarlos.appmovies.models.Movie;

/**
 * Created by Warren on 31/03/2015.
 */
public interface AddMovieCallback {
	void onAddedMovieResult(Movie movie);
}