package de.leuphana.routesystem.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.routesystem.behaviour.RouteSystemImpl;
import de.leuphana.routesystem.behaviour.service.RouteSystemCommandService;

class RouteSystemTest {

	private static RouteSystemCommandService routeSystemService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
		routeSystemService = new RouteSystemImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void canLocationsBeSelected() {
		Assertions.assertNotNull(routeSystemService.chooseRoute());
	}

}
