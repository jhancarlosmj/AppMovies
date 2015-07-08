package com.appmovies.Manager;


import android.content.Context;

import com.appmovies.HttpRequest.HttpRequestGETMovies;
import com.appmovies.HttpRequest.GetMoviesCallback;
import com.appmovies.HttpRequest.RestCallback;
import com.appmovies.models.Movie;

public class MoviesManagerGET implements RestCallback {
	public static MoviesManagerGET moviesManagerGET;
	private GetMoviesCallback getMoviesCallback;
	private Context appContext;
	private Movie[] movies;

	private MoviesManagerGET() {
	}

	public GetMoviesCallback getGetMoviesCallback() {
		return getMoviesCallback;
	}

	public void setGetMoviesCallback(GetMoviesCallback getMoviesCallback) {
		this.getMoviesCallback = getMoviesCallback;
	}

	public static synchronized MoviesManagerGET getInstance() {
		if (moviesManagerGET == null) {
			moviesManagerGET = new MoviesManagerGET();
		}

		return moviesManagerGET;
	}

	public synchronized void setAppContext(Context appContext) {
		this.appContext = appContext;
	}

	public synchronized void loadMoviesFromServer() {
		HttpRequestGETMovies httpRequestGETMovies = new HttpRequestGETMovies();
		httpRequestGETMovies.setPathVariable("/movies");
		httpRequestGETMovies.setRestCallback(this);
		httpRequestGETMovies.execute();
	}

	public synchronized Movie getMovie(Long id){
		for(Movie movie : movies){
			if(movie.getId().equals(id)){
				return movie;
			}
		}

		return null;
	}

	@Override
	public synchronized void onPreExecute() {

	}

	@Override
	public synchronized void onPostExecute(Object o) {
		if (o instanceof Movie[]) {
			movies = (Movie[]) o;
			getMoviesCallback.onGetMoviesResult(movies);
		}
	}

	@Override
	public synchronized String onInExecute() {
		return null;
	}

	@Override
	public synchronized void onCancelExecute() {

	}
}