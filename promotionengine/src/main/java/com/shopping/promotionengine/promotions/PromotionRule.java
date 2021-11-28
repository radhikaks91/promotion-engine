package com.shopping.promotionengine.promotions;

import java.util.List;

import com.shopping.promotionengine.models.Product;

/**
 * Abstract class to implement base functionalities common to all types of
 * promotions
 * 
 * @author radhikaks91
 *
 */
public abstract class PromotionRule {

	/**
	 * Computes and return the total cart price after applying all active
	 * promotions. This method needs to be implemented separately for each type of
	 * promotion available.
	 *
	 * @param cartItems
	 *            list of SKU codes in cart
	 * @param allProducts
	 *            list of available products
	 * @param promotions
	 *            list of active promotions
	 * @return total cart price
	 */
	public abstract double computePrice(List<String> cartItems, List<Product> allProducts, List<?> promotions);
}
