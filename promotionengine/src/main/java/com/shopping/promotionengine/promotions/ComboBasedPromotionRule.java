package com.shopping.promotionengine.promotions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.utils.ProductsUtil;

/**
 * This class is responsible for the computation of price by applying the
 * promotions applicable for combination of different products
 * 
 * @author radhikaks91
 *
 */
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
			// number of units for which combo promotion can be applied
			int unitsEligibleForPromotion = getUnitsEligibleForPromotion(productCountMap, skuIds);
			// number of units of product one left for fixed price after applying combo
			// promotion
			int unitsWithFixedPriceForFirstProduct = productCountMap.get(skuIds.get(0)) == null ? 0
					: (productCountMap.get(skuIds.get(0)) - unitsEligibleForPromotion);
			// number of units of product two left for fixed price after applying combo
			// promotion
			int unitsWithFixedPriceForSecondProduct = productCountMap.get(skuIds.get(1)) == null ? 0
					: (productCountMap.get(skuIds.get(1)) - unitsEligibleForPromotion);
			// update the total price by adding the total offer price applicable and the
			// total fixed price of left over products
			double currentPrice = totalPrice.get() + (unitsEligibleForPromotion * comboPromotion.getOfferPrice())
					+ (unitsWithFixedPriceForFirstProduct * firstProduct.getUnitPrice())
					+ (unitsWithFixedPriceForSecondProduct * secondProduct.getUnitPrice());
			totalPrice.set(currentPrice);
		});
		return totalPrice.get();
	}

	/**
	 * Returns the number of items in cart for which combination promotions are
	 * applicable
	 *
	 * @param productCountMap
	 *            a mapping of each product SKU code to the number of units added in
	 *            cart
	 * @param skuIds
	 *            list of SKU codes
	 * @return number of items
	 */
	private int getUnitsEligibleForPromotion(Map<String, Integer> productCountMap, List<String> skuIds) {
		if (productCountMap.get(skuIds.get(0)) == null || productCountMap.get(skuIds.get(1)) == null) {
			return 0;
		} else {
			return Math.min(productCountMap.get(skuIds.get(0)), productCountMap.get(skuIds.get(1)));
		}
	}
}
