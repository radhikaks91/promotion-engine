package com.shopping.promotionengine.models;

import java.util.List;

/**
 * Model class for holding items in a cart
 *
 * @author radhikaks91
 *
 */
public class Cart {

	private List<String> skuIds;

	public List<String> getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(List<String> skuIds) {
		this.skuIds = skuIds;
	}
}
