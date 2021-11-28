package com.shopping.promotionengine.models;

/**
 * Model class for holding the details of Product
 * 
 * @author radhikaks91
 *
 */
public class Product {

	private String skuId;
	private String name;
	private double unitPrice;

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * Constructor to initialize each details of the product
	 * 
	 * @param skuId
	 *            unique SKU code for the product
	 * @param name
	 *            product name
	 * @param unitPrice
	 *            price per unit
	 */
	public Product(String skuId, String name, double unitPrice) {
		super();
		this.skuId = skuId;
		this.name = name;
		this.unitPrice = unitPrice;
	}
}
