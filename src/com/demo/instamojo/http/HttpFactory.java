package com.demo.instamojo.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;

import com.demo.instamojo.apis.IMConstants;

/*
 * HTTP method request.
 */
public class HttpFactory {

	private HttpFactory() {
	}

	public static String getRequest(String uri, String authToken) throws IOException {
		
		URL url = new URL(uri);
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		
		urlConnection.setRequestMethod(HttpConstants.HTTP_GET);
		urlConnection.setRequestProperty(IMConstants.X_API_KEY, IMConstants.CLIENT_API_KEY);
		urlConnection.setRequestProperty(IMConstants.X_AUTH_TOKEN, authToken);

		urlConnection.connect();
		String response = getResponse(urlConnection.getInputStream());
		urlConnection.disconnect();
		
		return response;
	}
	
	public static String postRequest(String uri, List<NameValuePair> params, String authToken) throws IOException {
		
		URL url = new URL(uri);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(HttpConstants.HTTP_POST);
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty(IMConstants.X_API_KEY, IMConstants.CLIENT_API_KEY);
		urlConnection.setRequestProperty(IMConstants.X_AUTH_TOKEN, authToken);
		
		writeDataToStream(urlConnection.getOutputStream(), params);
				
		urlConnection.connect();
		String response = getResponse(urlConnection.getInputStream());
		urlConnection.disconnect();
		
		return response;
	}
	
	public static int deleteRequest(String uri, String authToken) throws IOException {
		
		URL url = new URL(uri);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		
		urlConnection.setRequestMethod(HttpConstants.HTTP_DELETE);
		urlConnection.setRequestProperty(IMConstants.X_API_KEY, IMConstants.CLIENT_API_KEY);
		urlConnection.setRequestProperty(IMConstants.X_AUTH_TOKEN, authToken);
		
		urlConnection.connect();
		int responseCode = urlConnection.getResponseCode();
		urlConnection.disconnect();
		
		return responseCode;
	}
	
	public static String uploadFileToURL(String uri, String filePath, String authToken) throws IOException {
	
		URL url = new URL(uri);
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(HttpConstants.HTTP_POST);
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty(IMConstants.X_API_KEY, IMConstants.CLIENT_API_KEY);
		urlConnection.setRequestProperty(IMConstants.X_AUTH_TOKEN, authToken);

		FileInputStream fis = new FileInputStream(new File(filePath));
		
		OutputStream os = urlConnection.getOutputStream();

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
		writer.write("fileUpload=");
		writer.flush();
		
		int buffer;
		while((buffer = fis.read()) != -1)
			os.write(buffer);
		
		os.flush();
		os.close();
		fis.close();
	
		urlConnection.connect();
		String response = getResponse(urlConnection.getInputStream());
		urlConnection.disconnect();
	
		return response;
	}
	
	private static void writeDataToStream(OutputStream os, List<NameValuePair> params) throws IOException {

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
		
		writer.write(getQuery(params));
		writer.flush();
		writer.close();
		os.close();
	}
	
	private static String getResponse(InputStream in) throws IOException {

		StringBuffer response = new StringBuffer();
		String line;
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while((line = reader.readLine()) != null)
			response = response.append(line);
		
		in.close();

		return response.toString();
	}
	
	private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {

	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (NameValuePair pair : params)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	    }

	    return result.toString();
	}
	
}
