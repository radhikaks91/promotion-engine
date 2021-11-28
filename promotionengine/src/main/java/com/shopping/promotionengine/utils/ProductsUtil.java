package com.shopping.promotionengine.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;

/**
 * Utility class which helps in processing products and items in cart
 *
 * @author radhikaks91
 *
 */
public final class ProductsUtil {

	private ProductsUtil() {
		// restrict instantiation of this class
	}

	/**
	 * Returns a mapping of SKU code to the number of units added in cart
	 *
	 * @param cartItems
	 *            list of SKU codes in cart
	 * @return SKU code to number of units in cart mapping
	 */
	public static Map<String, Integer> createProductToCountMap(List<String> cartItems) {
		Map<String, Integer> productCountMap = new HashMap<>();
		for (String item : cartItems) {
			Integer currentCount = productCountMap.get(item);
			productCountMap.put(item, (currentCount == null) ? 1 : currentCount + 1);
		}
		return productCountMap;
	}

	/**
	 * Returns the product having the given SKU code
	 * 
	 * @param allProducts
	 *            list of all available products
	 * @param skuId
	 *            unique SKU code for a product
	 * @return product with given SKU code
	 */
	public static Product getProduct(List<Product> allProducts, String skuId) {
		return allProducts.stream().filter(product -> product.getSkuId().equals(skuId)).findFirst().orElse(null);
	}

	/**
	 * Returns the cart price for the products which are not included in any of the
	 * promotions active
	 *
	 * @param cartItems
	 *            list of SKU codes in cart
	 * @param allProducts
	 *            list of all available products
	 * @param unitBasedPromotions
	 *            list of unit base promotions
	 * @param comboBasedPromotions
	 *            list of combo promotions
	 * @return cart price for items not eligible for any promotions
	 */
	public static double computePriceForNonPromotionItems(List<String> cartItems, List<Product> allProducts,
			List<UnitBasedPromotion> unitBasedPromotions, List<ComboBasedPromotion> comboBasedPromotions) {
		// creates a long id by concatenating all the SKU codes for which promotions are
		// applicable
		String allSkuIdsOnPromotion = unitBasedPromotions.stream().map(UnitBasedPromotion::getSkuId)
				.collect(Collectors.joining(""))
				+ comboBasedPromotions.stream().map(ComboBasedPromotion::getComboId).collect(Collectors.joining(""));
		AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
		cartItems.forEach(item -> {
			if (!allSkuIdsOnPromotion.contains(item)) {
				Product product = getProduct(allProducts, item);
				double currentPrice = totalPrice.get() + product.getUnitPrice();
				totalPrice.set(currentPrice);
			}
		});
		return totalPrice.get();
	}
}
