package com.demo.instamojo.apis;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Handler;

import com.demo.instamojo.apis.response.CreateLinkResponse;
import com.demo.instamojo.apis.response.LinksResponse;
import com.demo.instamojo.apis.response.ListAuthTokensResponse;
import com.demo.instamojo.apis.response.PaymentDetailResponse;
import com.demo.instamojo.apis.response.PaymentsResponse;
import com.demo.instamojo.apis.response.Response;
import com.demo.instamojo.apis.response.URLDetailResponse;
import com.demo.instamojo.apis.response.UploadURLResponse;
import com.demo.instamojo.http.HttpFactory;
import com.demo.instamojo.http.HttpResponseCallback;
import com.demo.instamojo.model.AuthToken;
import com.demo.instamojo.model.CreateLink;
import com.demo.instamojo.utils.Utilities;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Instamojo {

	private static final String AUTHENTICATE = "authenticate";
	private static final String DELETE_AUTH_TOKEN = "deleteAuthToken";
	private static final String LIST_AUTH_TOKENS = "listAuthTokens";
	private static final String LIST_URLS = "listUrls";
	private static final String CREATE_URL = "createUrl";
	private static final String GET_URL_DETAIL = "getUrlDetail";
	private static final String GET_PAYMENT_LIST = "paymentList";
	private static final String GET_PAYMENT_DETAIL = "getPaymentDetail";
	private static final String GET_FILE_UPLOAD_URL = "getFileUploadURL";
	
	private Handler mHandler;
	private Context mContext;
	
	private class AuthTokenResponse {
		
		@SerializedName("auth_token")
		public AuthToken authToken;
		public String token;
		public boolean success;
	}
	
	private List<NameValuePair> getParams(CreateLink link) {
		
		List<NameValuePair> params = new ArrayList<>();
		
		params.add(new BasicNameValuePair("title", link.title));
		params.add(new BasicNameValuePair("description", link.description));
		params.add(new BasicNameValuePair("currency", link.currency));
		params.add(new BasicNameValuePair("base_price", Integer.toString(link.basePrice)));
		
		return params;
	}
	
	public Instamojo(Context context) {
		mContext = context;
		mHandler = new Handler(context.getMainLooper());
	}
	
	public void login(String username, String password, HttpResponseCallback callback) {
		
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair(IMConstants.IM_USERNAME, username));
		params.add(new BasicNameValuePair(IMConstants.IM_PASSWORD, password));

		BackgroundThread thread = new BackgroundThread(AUTHENTICATE, params, callback);
		thread.start();
	}
	
	public boolean isUserLoggedIn() {
		
		if(Utilities.getAuthToken(mContext) != null)
			return true;
		return false;
	}
	
	public void logout() {
		Utilities.saveAuthToken(mContext, null);
	}
	
	public void deleteAuthToken(String authTokeToDelete, HttpResponseCallback callback) {
		
		NameValuePair param = new BasicNameValuePair("authTokenToDelete", authTokeToDelete);
		List<NameValuePair>  params = new ArrayList<>();
		params.add(param);
		
		BackgroundThread thread = new BackgroundThread(DELETE_AUTH_TOKEN, params, callback);
		thread.start();
	}
	
	public void listAuthTokens(HttpResponseCallback callback) {

		BackgroundThread thread = new BackgroundThread(LIST_AUTH_TOKENS, null, callback);
		thread.start();
	}
	
	public void getListOfURLs(HttpResponseCallback callback) {
		
		BackgroundThread thread = new BackgroundThread(LIST_URLS, null, callback);
		thread.start();
	}
	
	public void createURL(CreateLink link, HttpResponseCallback callback) {

		List<NameValuePair> params = getParams(link);
		
		BackgroundThread thread = new BackgroundThread(CREATE_URL, params, callback);
		thread.start();
	}
	
	public void getURLDetail(String identifier, HttpResponseCallback callback) {

		NameValuePair param = new BasicNameValuePair("identifier", identifier);
		List<NameValuePair>  params = new ArrayList<>();
		params.add(param);

		BackgroundThread thread = new BackgroundThread(GET_URL_DETAIL, params, callback);
		thread.start();
	}
	
	public void getPaymentsList(HttpResponseCallback callback) {
		
		BackgroundThread thread = new BackgroundThread(GET_PAYMENT_LIST, null, callback);
		thread.start();
	}
	
	public void getPaymentDetail(String identifier, HttpResponseCallback callback) {
		
		NameValuePair param = new BasicNameValuePair("identifier", identifier);
		List<NameValuePair>  params = new ArrayList<>();
		params.add(param);

		BackgroundThread thread = new BackgroundThread(GET_PAYMENT_DETAIL, params, callback);
		thread.start();
	}
	
	public void getFileUploadURL(HttpResponseCallback callback) {
		
		BackgroundThread thread = new BackgroundThread(GET_FILE_UPLOAD_URL, null, callback);
		thread.start();
	}
	
	private class BackgroundThread extends Thread {

		private String request;
		private List<NameValuePair> params;
		private HttpResponseCallback callback;
		private Response resp;
		private Gson gson;
		
		public BackgroundThread(String request, List<NameValuePair> params, HttpResponseCallback callback) {

			resp = null;
			
			this.request = request;
			this.params = params;
			this.callback = callback;
			
			gson = new Gson();
		}
		
		@Override
		public void run() {

			try {
				switch(request) {

				case AUTHENTICATE : {
					String response = HttpFactory.postRequest(IMConstants.AUTH_URL, params, "");
					AuthTokenResponse res = gson.fromJson(response, AuthTokenResponse.class);
					if(res.success)
						Utilities.saveAuthToken(mContext, res.token);
					resp = new Response();
					resp.success = res.success;
					break;
				}
					
				case LIST_AUTH_TOKENS : {
					
					String response = HttpFactory.getRequest(IMConstants.LIST_AUTH_TOKENS_URL, Utilities.getAuthToken(mContext));
					resp = gson.fromJson(response, ListAuthTokensResponse.class);
					break;
				}
				
				case DELETE_AUTH_TOKEN : {
					
					URL url = new URL(new URL(IMConstants.DEL_AUTH_TOKEN_URL), params.get(0).getValue());
					int response = HttpFactory.deleteRequest(url.toString(), Utilities.getAuthToken(mContext));
					
					resp = new Response();
					resp.responseCode = response;
					
					break;
				}
				
				case LIST_URLS : {

					String response = HttpFactory.getRequest(IMConstants.LIST_LINKS_URL, Utilities.getAuthToken(mContext));
					resp = gson.fromJson(response, LinksResponse.class);
					break;
				}
				
				case CREATE_URL : {
					
					String response = HttpFactory.postRequest(IMConstants.CREATE_LINK_URL, params, Utilities.getAuthToken(mContext));
					resp = gson.fromJson(response, CreateLinkResponse.class);
					break;
				}
				
				case GET_URL_DETAIL : {
					
					URL url = new URL(new URL(IMConstants.LINK_DETAIL_URL), params.get(0).getValue());
					String response = HttpFactory.getRequest(url.toString(), Utilities.getAuthToken(mContext));
					resp = gson.fromJson(response, URLDetailResponse.class);
					break;
				}
				
				case GET_PAYMENT_LIST : {

					String response = HttpFactory.getRequest(IMConstants.LIST_PAYMENTS_URL, Utilities.getAuthToken(mContext));
					resp = gson.fromJson(response, PaymentsResponse.class);					
					break;
				}
				
				case GET_PAYMENT_DETAIL : {
					
					URL url = new URL(new URL(IMConstants.PAYMENT_DETAIL_URL), params.get(0).getValue());
					String response = HttpFactory.getRequest(url.toString(), Utilities.getAuthToken(mContext));
					resp = gson.fromJson(response, PaymentDetailResponse.class);
					break;
				}
				
				case GET_FILE_UPLOAD_URL : {
					
					String response = HttpFactory.getRequest(IMConstants.UPLOAD_FILE_URL, Utilities.getAuthToken(mContext));
					resp = gson.fromJson(response, UploadURLResponse.class);
					break;
				}
				
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			}

			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					callback.onHttpResponse(resp);	
				}
			});
		}
	}
	
}
	