package com.appmovies.HttpRequest;

import com.appmovies.models.Movie;

public interface GetMoviesCallback {
	void onGetMoviesResult(Movie[] movies);
}
