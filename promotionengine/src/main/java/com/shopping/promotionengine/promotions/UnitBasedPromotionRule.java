package com.shopping.promotionengine.promotions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;
import com.shopping.promotionengine.utils.ProductsUtil;

public class UnitBasedPromotionRule extends PromotionRule {

	@Override
	public double computePrice(List<String> cartItems, List<Product> allProducts, List<?> promotions) {
		Map<String, Integer> productCountMap = ProductsUtil.createProductToCountMap(cartItems);
		AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
		productCountMap.forEach((skuId, count) -> {
			UnitBasedPromotion promotion = getPromotionDetail(promotions, skuId);
			Product currentProduct = ProductsUtil.getProduct(allProducts, skuId);
			if (promotion != null) {
				int unitsEligibleForPromotion = count / promotion.getNumberOfUnits();
				int unitsWithFixedPrice = count % promotion.getNumberOfUnits();
				double currentPrice = totalPrice.get() + (unitsEligibleForPromotion * promotion.getOfferPrice())
						+ (unitsWithFixedPrice * currentProduct.getUnitPrice());
				totalPrice.set(currentPrice);
			}
		});
		return totalPrice.get();
	}

	private UnitBasedPromotion getPromotionDetail(List<?> promotions, String skuId) {
		return promotions.stream().map(promotion -> (UnitBasedPromotion) promotion)
				.filter(promotion -> promotion.getSkuId().equals(skuId)).findFirst().orElse(null);
	}

}
