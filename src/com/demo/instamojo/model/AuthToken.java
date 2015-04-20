package com.demo.instamojo.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class AuthToken {
	
	@SerializedName("auth_token")
	public String token;
	
	@SerializedName("created_on")
	public String createdOn;
}