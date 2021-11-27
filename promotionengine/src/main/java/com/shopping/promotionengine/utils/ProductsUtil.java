package com.shopping.promotionengine.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
