package com.shopping.promotionengine.promotions;

import java.util.List;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;

public class ComboBasedPromotionRule extends PromotionRule {

	@Override
	double computePrice(List<String> cartItems, List<Product> allProducts, List<UnitBasedPromotion> unitBasedPromotions,
			List<ComboBasedPromotion> comboBasedPromotions) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
