package com.demo.instamojo.model;

import com.demo.instamojo.apis.response.Response;
import com.google.gson.annotations.SerializedName;

public class Link {

	public String title;
	public String description;
	public String slug;
    public String shorturl;
    public String url;
    public String cover_image;
    public String currency;

    @SerializedName("base_price")
    public int basePrice;
    
    public int quantity;
    
    @SerializedName("quantity_sold")
    public int quantitySold;
    
    @SerializedName("requires_shipping")
    public boolean requiresShipping;
    
    @SerializedName("ships_within_days")
    public int shipWithinDays;
    
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

    public String status;
}
