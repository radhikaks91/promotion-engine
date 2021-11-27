package com.shopping.promotionengine.models;

public class Product {

	private String skuId;
	private String name;
	private Double unitPrice;
	
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
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Product(String skuId, String name, Double unitPrice) {
		super();
		this.skuId = skuId;
		this.name = name;
		this.unitPrice = unitPrice;
	}	
}
