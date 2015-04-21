package com.demo.instamojo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Utilities {

	private static final String AUTH_TOKEN_FILE = "authTokenFile";
	private static final String AUTH_TOKEN_PREF = "authToken";
	
	public static void saveAuthToken(Context context, String authToken) {
		
		SharedPreferences preferences = context.getSharedPreferences(AUTH_TOKEN_FILE, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();

		editor.putString(AUTH_TOKEN_PREF, authToken);
		editor.commit();
	}
	
	public static String getAuthToken(Context context) {

		SharedPreferences preferences = context.getSharedPreferences(AUTH_TOKEN_FILE, Context.MODE_PRIVATE);
		
		if(preferences.contains(AUTH_TOKEN_PREF)) {
			return preferences.getString(AUTH_TOKEN_PREF, null);
		}
		
		return null;
	}
}
