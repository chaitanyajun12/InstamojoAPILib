package com.demo.instamojo.model;

import com.google.gson.annotations.SerializedName;

public class Payment {

	@SerializedName("payment_id")
	public String paymentId;
	
	public int quantity;
	
	public String status;
	
	@SerializedName("link_slug")
	public String linkSlug;

	@SerializedName("link_title")
	public String linkTitle;

	@SerializedName("buyer_name")
	public String buyerName;

	@SerializedName("buyer_phone")
	public String buyerPhone;

	@SerializedName("buyer_email")
	public String buyerEmail;

	public String currency;

	@SerializedName("unit_price")
	public int unitPrice;

	public int amount;

	public double fees;

	@SerializedName("shipping_address")
	public String shippingAddress;
	
	@SerializedName("shipping_city")
	public String shippingCity;

	@SerializedName("shipping_state")
	public String shippingState;

	@SerializedName("shipping_zip")
	public String shippingZip;

	@SerializedName("shipping_country")
	public String shippingCountry;

	@SerializedName("discount_code")
	public String discountCode;

	@SerializedName("discount_amount_off")
	public String discountAmountOff;

	public String[] variants;

	@SerializedName("custom_fields")
	public String customFields;

	@SerializedName("affiliate_id")
	public String affiliateId;

	@SerializedName("affiliate_commission")
	public int affiliateCommision;

	@SerializedName("created_at")
	public String createdAt;
	
}
