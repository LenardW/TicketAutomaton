package de.leuphana.cosa.documentsystem.behaviour.service.event;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;

public class ManageableEventHandler implements EventHandler{

private DocumentCommandService documentCommandService;
	
	public ManageableEventHandler(DocumentCommandService documentCommandService){
		this.documentCommandService = documentCommandService;
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event.getTopic().equals("de/leuphana/cosa/pricingSystem/priceCalculated")) {
			Manageable manageable = new Manageable() {

				@Override
				public String getStartLocation() {
					return (String) event.getProperty("startLocation");
				}

				@Override
				public String getEndLocation() {
					return (String) event.getProperty("endLocation");
				}

				@Override
				public String getPriceGroup() {
					return (String) event.getProperty("priceGroup");
				}

				@Override
				public float getPrice() {
					return (float) event.getProperty("price");
				}

				@Override
				public float getDistance() {
					return (float) event.getProperty("distance");
				}

			
			};
			documentCommandService.createTicket(manageable);
		}
	}
}
