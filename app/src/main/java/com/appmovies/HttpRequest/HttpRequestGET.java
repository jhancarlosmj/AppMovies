package com.appmovies.HttpRequest;

import android.os.AsyncTask;
import android.util.Log;

import com.appmovies.util.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/* Is not possible to use java generics because we need to specify .class Result type to Spring RestTemplate API.
	More info: http://stackoverflow.com/questions/2390662/java-how-do-i-get-a-class-literal-from-a-generic-type
*/

public abstract class HttpRequestGET extends AsyncTask<Void, Void, Object> {

	private String pathVariable;
	private Map<String, Object> mapParams;
	private RestCallback restCallback;
	private String ip;
	private String url;

	public RestCallback getRestCallback() {
		return restCallback;
	}

	public void setRestCallback(RestCallback restCallback) {
		this.restCallback = restCallback;
	}

	public void setPathVariable(String pathVariable) {
		this.pathVariable = pathVariable;
	}

	public void setParams(Map<String, Object> mapParams) {
		this.mapParams = mapParams;
	}


	@Override
	protected Object doInBackground(Void... params) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			ip = "http://" + Configuration.ipWebService;
			url = ip + pathVariable;
			Object result;

			if (mapParams != null) {
				url = url + addParams();
				Log.i("URL enviada", url);


				ResponseEntity<Object> response = restTemplate.getForEntity(url, getResultType(),
						mapParams);

				checkHttpStatus(response);

				result = response.getBody();

			} else {
				Log.i("URL", "enviada sin params:" + url);

				ResponseEntity<Object> response = restTemplate.getForEntity(url, getResultType());

				checkHttpStatus(response);

				result = response.getBody();
			}

			return result;

		} catch (Exception e) {
			Log.e("Error GET webService", e.getMessage(), e);
			return e;
		}
	}

	private void checkHttpStatus(ResponseEntity<Object> response) {
		if (response.getStatusCode() == HttpStatus.OK) {
			Log.i("HttpRequestGET", "doInBackground response.getStatusCode()==OK");
		} else {
			Log.e("HttpRequestGET ", "ERROR doInBackground response.getStatusCode()" +
					response.getStatusCode());
		}
	}

	protected abstract Class getResultType();

	private String addParams() {
		String requestParams = null;
		boolean initialize = false;
		int i = 0;

		for (String key : mapParams.keySet()) {
			if (initialize == false) {
				initialize = true;
				requestParams = "?";
			}
			requestParams += key + "={" + key + "}";
			if (i < mapParams.size() - 1) requestParams += "&";
			i++;
		}

		return requestParams;
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
