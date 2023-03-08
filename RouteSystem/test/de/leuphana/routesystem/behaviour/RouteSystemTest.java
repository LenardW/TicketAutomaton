package de.leuphana.routesystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.routesystem.behaviour.RouteSystemImpl;
import de.leuphana.routesystem.behaviour.service.RouteCommandService;

class RouteSystemTest {

	private static RouteCommandService routeSystemService;
	
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
