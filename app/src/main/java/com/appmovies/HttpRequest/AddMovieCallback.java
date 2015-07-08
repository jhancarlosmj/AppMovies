package com.appmovies.HttpRequest;

import com.appmovies.models.Movie;

public interface AddMovieCallback {
	void onAddedMovieResult(Movie movie);
}
