package com.shopping.promotionengine.models;

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
	public Product(String skuId, String name, double unitPrice) {
		super();
		this.skuId = skuId;
		this.name = name;
		this.unitPrice = unitPrice;
	}	
}
