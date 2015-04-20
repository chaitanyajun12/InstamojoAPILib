package com.demo.instamojo.model;

import com.google.gson.annotations.SerializedName;

public class CreateLink {

	public String title;
	public String description;
	public String currency;
	
	@SerializedName("base_price")
	public int basePrice;
	
	public int quantity;
    
    @SerializedName("start_date")
    public String startDate;
    
    @SerializedName("end_date")
    public String endDate;

    public String venue;

    public String timeZone;

    public String note;

    @SerializedName("redirect_url")
    public String redirectUrl;
    
    @SerializedName("webHookUrl")
    public String webHookUrl;

    @SerializedName("file_upload_json")
    public String fileUploadJson;
    
    @SerializedName("cover_image_json")
    public String coverImageJson;
    
    @SerializedName("enable_pwyw")
    public String enablePwyw;
    
    @SerializedName("enable_sign")
    public String enableSign;
}	
