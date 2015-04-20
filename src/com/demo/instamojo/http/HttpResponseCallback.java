package com.demo.instamojo.http;

import com.demo.instamojo.apis.response.Response;

/*
 * Callback which gets called after fetching response.
 */
public interface HttpResponseCallback {

	public void onHttpResponse(Response response);
}
