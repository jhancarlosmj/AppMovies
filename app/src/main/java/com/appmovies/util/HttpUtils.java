package com.appmovies.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtils {
	public static boolean isNetworkAvailable(Context appContext) {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

		boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

		return isConnected;
	}
}
