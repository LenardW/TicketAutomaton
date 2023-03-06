package de.leuphana.pricingsystem.behaviour.service.event;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.leuphana.pricingsystem.behaviour.service.Priceable;
import de.leuphana.pricingsystem.behaviour.service.PricingCommandService;

public class PriceableEventHandler implements EventHandler{
	
	private PricingCommandService pricingCommandService;
	
	public PriceableEventHandler(PricingCommandService pricingCommandService){
		this.pricingCommandService = pricingCommandService;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getTopic().equals("de/leuphana/cosa/routeSystem/routeChoosen")) {
			Priceable priceable = new Priceable() {

				@Override
				public int getRouteLength() {
					return (int) event.getProperty("routeLength");
				}

				@Override
				public String getStartLocation() {
					return (String) event.getProperty("startLocation");
				}

				@Override
				public String getEndLocation() {
					return (String) event.getProperty("endLocation");
				}
			};
			
			pricingCommandService.calculatePrice(priceable);
			
		}
		
	}

}
