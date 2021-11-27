package com.shopping.promotionengine.promotions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;

public class UnitBasedPromotionRule  extends PromotionRule{

	@Override
	double computePrice(List<String> cartItems, List<Product> allProducts, List<UnitBasedPromotion> unitBasedPromotions,
			List<ComboBasedPromotion> comboBasedPromotions) {
		Map<String, Integer> productCountMap = new HashMap<>();
		return 0;
	}

	
}
