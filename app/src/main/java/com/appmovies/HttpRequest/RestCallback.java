package com.appmovies.HttpRequest;

public interface RestCallback {
	public void onPreExecute();

	public void onPostExecute(Object o);

	public String onInExecute();

	public void onCancelExecute();

}