package com.shopping.promotionengine.checkout;

import java.util.List;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;
import com.shopping.promotionengine.promotions.ComboBasedPromotionRule;
import com.shopping.promotionengine.promotions.UnitBasedPromotionRule;
import com.shopping.promotionengine.utils.ProductsUtil;

public class CheckoutCart {

	private UnitBasedPromotionRule unitBasedPromotionRule;
	private ComboBasedPromotionRule comboBasedPromotionRule;
	
	public double priceCart(List<String> cartItems, List<Product> allProducts,
			List<UnitBasedPromotion> unitBasedPromotions, List<ComboBasedPromotion> comboBasedPromotions) {
		return unitBasedPromotionRule.computePrice(cartItems, allProducts, unitBasedPromotions)
				+ comboBasedPromotionRule.computePrice(cartItems, allProducts, comboBasedPromotions)
				+ ProductsUtil.computePriceForNonPromotionItems(cartItems, allProducts, unitBasedPromotions, comboBasedPromotions);
	}

}
