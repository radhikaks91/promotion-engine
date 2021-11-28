package com.shopping.promotionengine.promotions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;
import com.shopping.promotionengine.utils.ProductsUtil;

/**
 * This class is responsible for the computation of price by applying the
 * promotions applicable for 'n' items of individual product
 * 
 * @author radhikaks91
 *
 */
public class UnitBasedPromotionRule extends PromotionRule {

	@Override
	public double computePrice(List<String> cartItems, List<Product> allProducts, List<?> promotions) {
		Map<String, Integer> productCountMap = ProductsUtil.createProductToCountMap(cartItems);
		AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
		productCountMap.forEach((skuId, count) -> {
			UnitBasedPromotion promotion = getPromotionDetail(promotions, skuId);
			Product currentProduct = ProductsUtil.getProduct(allProducts, skuId);
			if (promotion != null) {
				// count of SKU code in cart for which promotion is applicable
				int unitsEligibleForPromotion = count / promotion.getNumberOfUnits();

				// count of SKU code in cart for which no promotions can be applied
				int unitsWithFixedPrice = count % promotion.getNumberOfUnits();

				// update total price by adding the price of current product for which promotion
				// is applied and the fixed price product for which no promotions can be applied
				double currentPrice = totalPrice.get() + (unitsEligibleForPromotion * promotion.getOfferPrice())
						+ (unitsWithFixedPrice * currentProduct.getUnitPrice());
				totalPrice.set(currentPrice);
			}
		});
		return totalPrice.get();
	}

	/**
	 * Returns the promotion available for given SKU code
	 * 
	 * @param promotions
	 *            list of all promotions applicable for 'n' items of individual
	 *            product
	 * @param skuId
	 *            unique SKU code for a product
	 * @return
	 */
	private UnitBasedPromotion getPromotionDetail(List<?> promotions, String skuId) {
		return promotions.stream().map(promotion -> (UnitBasedPromotion) promotion)
				.filter(promotion -> promotion.getSkuId().equals(skuId)).findFirst().orElse(null);
	}

}
