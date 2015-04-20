package com.demo.instamojo.apis.response;

import com.google.gson.annotations.SerializedName;

/*
 * @author Krishna Chaitanya M
 * Upload URL response
 */
public class UploadURLResponse extends Response {

	@SerializedName("upload_url")
	public String uploadURL;
}
