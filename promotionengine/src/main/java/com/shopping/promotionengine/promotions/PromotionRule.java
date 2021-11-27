package com.shopping.promotionengine.promotions;

import java.util.List;

import com.shopping.promotionengine.models.Product;

public abstract class PromotionRule {

	public abstract double computePrice(List<String> cartItems, List<Product> allProducts, List<?> promotions);
}
