package com.shopping.promotionengine.checkout;

import java.util.Collections;
import java.util.List;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;
import com.shopping.promotionengine.promotions.ComboBasedPromotionRule;
import com.shopping.promotionengine.promotions.UnitBasedPromotionRule;
import com.shopping.promotionengine.utils.ProductsUtil;

/**
 * This class is responsible for handling the different steps included in
 * checkout process
 * 
 * @author radhikaks91
 *
 */
public class CheckoutCart {

	private UnitBasedPromotionRule unitBasedPromotionRule;
	private ComboBasedPromotionRule comboBasedPromotionRule;

	public CheckoutCart() {
		this.unitBasedPromotionRule = new UnitBasedPromotionRule();
		this.comboBasedPromotionRule = new ComboBasedPromotionRule();
	}

	/**
	 * Returns the total price to be paid for the items in the cart. The price is
	 * computed by applying all the promotions applicable for the items in cart.
	 *
	 * @param cartItems
	 *            list of skuIds in cart
	 * @param allProducts
	 *            list of all products available
	 * @param unitBasedPromotions
	 *            list of promotions applicable for each product
	 * @param comboBasedPromotions
	 *            list if promotions applicable for combination of products
	 * @return total price to be paid for the cart
	 */
	public double priceCart(List<String> cartItems, List<Product> allProducts,
			List<UnitBasedPromotion> unitBasedPromotions, List<ComboBasedPromotion> comboBasedPromotions) {
		Collections.sort(cartItems);
		return unitBasedPromotionRule.computePrice(cartItems, allProducts, unitBasedPromotions)
				+ comboBasedPromotionRule.computePrice(cartItems, allProducts, comboBasedPromotions)
				+ ProductsUtil.computePriceForNonPromotionItems(cartItems, allProducts, unitBasedPromotions,
						comboBasedPromotions);
	}

}
