package com.shopping.promotionengine.promotions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.utils.ProductsUtil;

public class ComboBasedPromotionRule extends PromotionRule {

	@Override
	public double computePrice(List<String> cartItems, List<Product> allProducts, List<?> promotions) {
		Map<String, Integer> productCountMap = ProductsUtil.createProductToCountMap(cartItems);
		AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
		promotions.forEach(promotion -> {
			ComboBasedPromotion comboPromotion = (ComboBasedPromotion) promotion;
			String comboId = comboPromotion.getComboId();
			List<String> skuIds = Arrays.asList(comboId.split(""));
			Product firstProduct = ProductsUtil.getProduct(allProducts, skuIds.get(0));
			Product secondProduct = ProductsUtil.getProduct(allProducts, skuIds.get(1));
			int unitsEligibleForPromotion = getUnitsEligibleForPromotion(productCountMap, skuIds);
			int unitsWithFixedPriceForFirstProduct = productCountMap.get(skuIds.get(0)) == null ? 0
					: (productCountMap.get(skuIds.get(0)) - unitsEligibleForPromotion);
			int unitsWithFixedPriceForSecondProduct = productCountMap.get(skuIds.get(1)) == null ? 0
					: (productCountMap.get(skuIds.get(1)) - unitsEligibleForPromotion);

			double currentPrice = totalPrice.get() + (unitsEligibleForPromotion * comboPromotion.getOfferPrice())
					+ (unitsWithFixedPriceForFirstProduct * firstProduct.getUnitPrice())
					+ (unitsWithFixedPriceForSecondProduct * secondProduct.getUnitPrice());
			totalPrice.set(currentPrice);
		});
		return totalPrice.get();
	}

	private int getUnitsEligibleForPromotion(Map<String, Integer> productCountMap, List<String> skuIds) {
		if (productCountMap.get(skuIds.get(0)) == null || productCountMap.get(skuIds.get(1)) == null) {
			return 0;
		} else {
			return Math.min(productCountMap.get(skuIds.get(0)), productCountMap.get(skuIds.get(1)));
		}
	}
}
