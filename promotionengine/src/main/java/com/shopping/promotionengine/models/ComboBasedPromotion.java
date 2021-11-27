package com.shopping.promotionengine.models;

public class ComboBasedPromotion {

	private String comboId;
	private double offerPrice;
	
	public String getComboId() {
		return comboId;
	}
	public void setComboId(String comboId) {
		this.comboId = comboId;
	}
	public double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public ComboBasedPromotion(String comboId, double offerPrice) {
		super();
		this.comboId = comboId;
		this.offerPrice = offerPrice;
	}
	
}
