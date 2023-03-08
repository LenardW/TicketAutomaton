package de.leuphana.cosa.pricingsystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.pricingsystem.behaviour.PricingSystemImpl;
import de.leuphana.pricingsystem.behaviour.service.Priceable;
import de.leuphana.pricingsystem.behaviour.service.PricingCommandService;

class PricingSystemTest {
	
	private static PricingCommandService pricingSystemService;
	private static Priceable priceable;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pricingSystemService = new PricingSystemImpl();
		priceable = new Priceable() {

			@Override
			public int getRouteLength() {
				return 300;
			}

			@Override
			public String getStartLocation() {
				return "Lueneburg";
			}

			@Override
			public String getEndLocation() {
				return "Hamburg";
			}
		};
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		Assertions.assertNotNull(pricingSystemService.calculatePrice(priceable));
	}

}
