package com.demo.instamojo.apis;

public class IMConstants {

	public static final String CLIENT_API_KEY = "3f37e9d564937297e1143db4a3ce5586";
	public static final String AUTH_TOKEN = "fe2890fbe4e50051a5189596e1a1d062";
	
	
	public static final String X_API_KEY = "X-Api-Key";
	public static final String X_AUTH_TOKEN = "X-Auth-Token";
	
	public static final String IM_USERNAME = "username";
	public static final String IM_PASSWORD = "password";
	
	
	// Authentication - POST
	public static final String AUTH_URL = "https://www.instamojo.com/api/1.1/tokens/";

	// Delete Auth Token - DELETE
	public static final String DEL_AUTH_TOKEN_URL = "https://www.instamojo.com/api/1.1/tokens/";
		
	// Get List of Auth Tokens - GET
	public static final String LIST_AUTH_TOKENS_URL = "https://www.instamojo.com/api/1.1/tokens/";
	
	// List all Links - GET
	public static final String LIST_LINKS_URL = "https://www.instamojo.com/api/1.1/links/";
	
	// Create New Link - POST
	public static final String CREATE_LINK_URL = "https://www.instamojo.com/api/1.1/links/";
	
	// Uploading File and Cover Image - GET
	public static final String UPLOAD_FILE_URL = "https://www.instamojo.com/api/1.1/links/get_file_upload_url/";
	
	// Details of Link - GET
	public static final String LINK_DETAIL_URL = "https://www.instamojo.com/api/1.1/links/";
	
	// List all Payments - GET
	public static final String LIST_PAYMENTS_URL = "https://www.instamojo.com/api/1.1/payments/";
	
	// Details of Payment - GET
	public static final String PAYMENT_DETAIL_URL = "https://www.instamojo.com/api/1.1/payments/:payment_id/";
}
