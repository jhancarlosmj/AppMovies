package com.appmovies.HttpRequest;

import com.appmovies.models.Movie;

public class HttpRequestGETMovies extends HttpRequestGET {

	@Override
	protected Class getResultType() {
		return Movie[].class;
	}
}
