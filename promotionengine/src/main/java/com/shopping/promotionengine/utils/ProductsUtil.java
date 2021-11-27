package com.shopping.promotionengine.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;

public final class ProductsUtil {

	private ProductsUtil() {
		// TODO Auto-generated constructor stub
	}

	public static Map<String, Integer> createProductToCountMap(List<String> cartItems) {
		Map<String, Integer> productCountMap = new HashMap<>();
		for (String item : cartItems) {
			Integer currentCount = productCountMap.get(item);
			productCountMap.put(item, (currentCount == null) ? 1 : currentCount + 1);
		}
		return productCountMap;
	}

	public static Product getProduct(List<Product> allProducts, String skuId) {
		return allProducts.stream().filter(product -> product.getSkuId().equals(skuId)).findFirst().orElse(null);
	}

	public static double computePriceForNonPromotionItems(List<String> cartItems, List<Product> allProducts,
			List<UnitBasedPromotion> unitBasedPromotions, List<ComboBasedPromotion> comboBasedPromotions) {
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
