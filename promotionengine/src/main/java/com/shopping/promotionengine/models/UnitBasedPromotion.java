package com.shopping.promotionengine.models;

public class UnitBasedPromotion {

	private String skuId;
	private int numberOfUnits;
	private double offerPrice;
	
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public int getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public UnitBasedPromotion(String skuId, int numberOfUnits, double offerPrice) {
		super();
		this.skuId = skuId;
		this.numberOfUnits = numberOfUnits;
		this.offerPrice = offerPrice;
	}
}
