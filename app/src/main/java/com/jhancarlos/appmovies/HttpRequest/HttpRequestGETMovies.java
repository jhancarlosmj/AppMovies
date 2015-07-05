package com.jhancarlos.appmovies.HttpRequest;

import com.jhancarlos.appmovies.models.Movie;

public class HttpRequestGETMovies extends HttpRequestGET {

	@Override
	protected Class getResultType() {
		return Movie[].class;
	}
}
