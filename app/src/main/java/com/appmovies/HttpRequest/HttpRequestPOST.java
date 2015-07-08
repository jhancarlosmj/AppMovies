package com.appmovies.HttpRequest;

import android.os.AsyncTask;
import android.util.Log;

import com.appmovies.models.Movie;
import com.appmovies.util.Configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpRequestPOST extends AsyncTask<Void, Void, Object> {

	private String pathVariable;
	private RestCallback restCallback;
	private Object param;

	public RestCallback getRestCallback() {
		return restCallback;
	}

	public void setRestCallback(RestCallback restCallback) {
		this.restCallback = restCallback;
	}

	public void setPathVariable(String pathVariable) {
		this.pathVariable = pathVariable;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	@Override
	public Object doInBackground(Void... params) {
		try {
			String ip = "http://" + Configuration.ipWebService;
			String url = ip + pathVariable;

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			ResponseEntity<Movie> response = restTemplate.postForEntity(url, this.param, Movie
					.class);

			Log.i("HttpRequestPOST", "doInBackground " + response.getStatusCode());
			Log.i("HttpRequestPOST ", "response.getBody()= " + response.getBody());

			return response.getBody();

		} catch (Exception e) {
			Log.e("HttpRequestPOST ", "Error: " + e.getMessage(), e);
			return e;
		}
	}

	@Override
	protected void onPostExecute(Object result) {

		if (restCallback != null) {
			restCallback.onPostExecute(result);
		} else {
			Log.e("restCallBack: ", "restCallback==null");
		}
	}
}
