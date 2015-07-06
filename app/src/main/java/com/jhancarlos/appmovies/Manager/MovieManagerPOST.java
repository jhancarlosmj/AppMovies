package com.jhancarlos.appmovies.Manager;


import android.content.Context;
import android.util.Log;

import com.jhancarlos.appmovies.HttpRequest.AddMovieCallback;
import com.jhancarlos.appmovies.HttpRequest.HttpRequestPOST;
import com.jhancarlos.appmovies.HttpRequest.RestCallback;
import com.jhancarlos.appmovies.models.Movie;
import com.jhancarlos.appmovies.util.HttpUtils;

public class MovieManagerPOST implements RestCallback {
	public static MovieManagerPOST movieManagerPOST;
	private AddMovieCallback addMovieCallback;
	private Context appContext;
	private Movie movie;

	private MovieManagerPOST() {
	}

	public AddMovieCallback getAddMovieCallback() {
		return addMovieCallback;
	}

	public void setAddMovieCallback(AddMovieCallback addMovieCallback) {
		this.addMovieCallback = addMovieCallback;
	}

	public static synchronized MovieManagerPOST getInstance() {
		if (movieManagerPOST == null) {
			movieManagerPOST = new MovieManagerPOST();
		}

		return movieManagerPOST;
	}

	public synchronized void setAppContext(Context appContext) {
		this.appContext = appContext;
	}

	public synchronized void postMovieToServer(Movie movieToPost) {
		if (HttpUtils.isNetworkAvailable(appContext)) {
			Log.i("MovieManagerPOST ", " postToServer starting");

			HttpRequestPOST httpRequestPOST = new HttpRequestPOST();
			httpRequestPOST.setPathVariable("/movies");
			httpRequestPOST.setParam(movieToPost);
			httpRequestPOST.setRestCallback(this);
			httpRequestPOST.execute();

		} else {
			Log.d("MovieManagerPOST ", " unable postToServer, no Internet connection. " +
					movie);
		}


	}

	@Override
	public synchronized void onPreExecute() {

	}

	@Override
	public synchronized void onPostExecute(Object o) {
		if (o instanceof Movie) {
			movie = (Movie) o;
			addMovieCallback.onAddedMovieResult(movie);
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