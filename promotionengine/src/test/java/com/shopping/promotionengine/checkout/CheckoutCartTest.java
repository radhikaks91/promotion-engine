package com.shopping.promotionengine.checkout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shopping.promotionengine.models.ComboBasedPromotion;
import com.shopping.promotionengine.models.Product;
import com.shopping.promotionengine.models.UnitBasedPromotion;

import junit.framework.TestCase;

/**
 * Test class to verify the checkout process
 * 
 * @author radhikaks91
 *
 */
public class CheckoutCartTest extends TestCase {

	private CheckoutCart checkoutCart = new CheckoutCart();
	private List<UnitBasedPromotion> unitBasedPromotions = new ArrayList<>();
	private List<ComboBasedPromotion> comboBasedPromotions = new ArrayList<>();
	private List<Product> allProducts = new ArrayList<>();

	@Override
	protected void setUp() {
		buildComboBasedPromotions();
		buildUnitBasedPromotions();
		buildProducts();
	}

	public void testScenarioA() {
		assertEquals(100.0, checkoutCart.priceCart(Arrays.asList("A", "B", "C"), allProducts, unitBasedPromotions,
				comboBasedPromotions));
	}

	public void testScenarioB() {
		assertEquals(370.0, checkoutCart.priceCart(Arrays.asList("A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C"),
				allProducts, unitBasedPromotions, comboBasedPromotions));
	}

	public void testScenarioC() {
		assertEquals(280.0, checkoutCart.priceCart(Arrays.asList("A", "A", "C", "D", "A", "B", "B", "B", "B", "B"),
				allProducts, unitBasedPromotions, comboBasedPromotions));
	}

	private void buildUnitBasedPromotions() {
		unitBasedPromotions.add(new UnitBasedPromotion("A", 3, 130));
		unitBasedPromotions.add(new UnitBasedPromotion("B", 2, 45));
	}

	private void buildComboBasedPromotions() {
		comboBasedPromotions.add(new ComboBasedPromotion("CD", 30));
	}

	private void buildProducts() {
		allProducts.add(new Product("A", "Product A", 50));
		allProducts.add(new Product("B", "Product B", 30));
		allProducts.add(new Product("C", "Product C", 20));
		allProducts.add(new Product("D", "Product D", 15));
	}
}
