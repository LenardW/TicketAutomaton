package de.leuphana.pricingsystem.behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.pricingsystem.behaviour.service.Priceable;
import de.leuphana.pricingsystem.behaviour.service.PricingCommandService;
import de.leuphana.pricingsystem.structure.PriceCalculation;

@Component(immediate = true)
public class PricingSystemImpl implements PricingCommandService{

	private static EventAdmin eventAdmin;
	
	private BundleContext context;
	
	private Logger logger;
	
	public PricingSystemImpl() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
	public PriceCalculation calculatePrice(Priceable priceable) {
		
		float distance = priceable.getRouteLength()*1.45f;
		System.out.println("Bitte wählen Sie die Preis Gruppe aus, in dem Sie die entsprechende Zahl eingeben");
		System.out.println("1: Normal-Tarif");
		System.out.println("2: Günstiger Reisen-Tarif");
		System.out.println("3: Schnäppchen-Tarif");
		Scanner scanner = new Scanner(System.in);
		Integer dataInput = scanner.nextInt();
		float normalPrice = distance*0.03f;
		float price = 0;
		String priceGroup;
		switch (dataInput) {
		case 1: {
			price = normalPrice;
			priceGroup ="Normal-Tarif";
			break;
		}
		case 2: {
			price = normalPrice * 0.75f;
			priceGroup ="Guenstiger Reisen-Tarif";
			break;
		}
		case 3: {
			price = normalPrice * 0.50f;
			priceGroup ="Schnaeppchen-Tarif";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + dataInput);
		}
		
		PriceCalculation priceCalculation = new PriceCalculation(priceGroup, price, distance);
		System.out.println("Das Ticket kostet: "+price+"€");
		//TODO price round to second place
		postPricingEvent(priceCalculation, priceable);
		return priceCalculation;
	}
	
	public void postPricingEvent(PriceCalculation priceCalculation, Priceable priceable) {
		Map<String, Object> eventProperties = new HashMap<>();
		eventProperties.put("priceGroup", priceCalculation.getPriceGroup());
		eventProperties.put("price", priceCalculation.getPrice());
		eventProperties.put("distance", priceCalculation.getDistance());
		eventProperties.put("startLocation", priceable.getStartLocation());
		eventProperties.put("endLocation", priceable.getEndLocation());
		String eventTopic = "de/leuphana/cosa/priceSystem/priceCalculated";
		eventAdmin.postEvent(new Event(eventTopic, eventProperties));
		logger.info("PriceCalculatedEvent occoured");
	}
	
	@Reference
    public void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }
	
//	public void resetEventAdmin(EventAdmin eventAdmin) {
//        this.eventAdmin = null;
//    }
	
//	@Reference
//	public void setBundleContext(BundleContext context) {
//		this.context = context;
//	}
//	public void resetBundleContext(BundleContext context) {
//		this.context = null;
//	}
	
	
	
}
