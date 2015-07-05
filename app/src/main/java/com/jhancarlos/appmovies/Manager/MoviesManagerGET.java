package com.jhancarlos.appmovies.Manager;


import android.content.Context;

import com.jhancarlos.appmovies.HttpRequest.HttpRequestGETMovies;
import com.jhancarlos.appmovies.HttpRequest.MoviesCallback;
import com.jhancarlos.appmovies.HttpRequest.RestCallback;
import com.jhancarlos.appmovies.models.Movie;

public class MoviesManagerGET implements RestCallback {
	public static MoviesManagerGET moviesManagerGET;
	private MoviesCallback moviesCallback;
	private Context appContext;
	private Movie[] movies;

	private MoviesManagerGET() {
	}

	public MoviesCallback getMoviesCallback() {
		return moviesCallback;
	}

	public void setMoviesCallback(MoviesCallback moviesCallback) {
		this.moviesCallback = moviesCallback;
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
			moviesCallback.onGetMoviesResult(movies);
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