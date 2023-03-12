package de.leuphana.routesystem.behaviour.service.event;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.leuphana.routesystem.behaviour.service.RouteCommandService;

public class StartingEventHandler implements EventHandler{
	
	private RouteCommandService routeCommandService;
	
	public StartingEventHandler(RouteCommandService routeCommandService) {
		this.routeCommandService = routeCommandService;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getTopic().equals("de/leuphana/cosa/ticketAutomaton/startTicketSelling")) {
			routeCommandService.chooseRoute();
		}
	}

}
