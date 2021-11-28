package com.shopping.promotionengine.models;

/**
 * Model class for holding details of promotions like offer price for 'n' items
 * of a product
 *
 * @author radhikaks91
 *
 */
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

	/**
	 * Constructor to initialize promotion like offer price of 'n' items of a
	 * product
	 *
	 * @param skuId
	 *            unique SKU code of a product
	 * @param numberOfUnits
	 *            number of units for which offer is applied
	 * @param offerPrice
	 *            price applied for the promotion
	 */
	public UnitBasedPromotion(String skuId, int numberOfUnits, double offerPrice) {
		super();
		this.skuId = skuId;
		this.numberOfUnits = numberOfUnits;
		this.offerPrice = offerPrice;
	}
}
