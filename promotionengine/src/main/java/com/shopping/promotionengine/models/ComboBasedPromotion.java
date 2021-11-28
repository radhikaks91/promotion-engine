package com.shopping.promotionengine.models;

/**
 * Model class for holding details of promotions like offer price for
 * combination of different products
 *
 * @author radhikaks91
 *
 */
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

	/**
	 * Constructor to initialize combo promotions
	 * 
	 * @param comboId
	 *            combination of SKU codes applicable for this promotion
	 * @param offerPrice
	 *            offer price for the promotion
	 */
	public ComboBasedPromotion(String comboId, double offerPrice) {
		super();
		this.comboId = comboId;
		this.offerPrice = offerPrice;
	}

}
