package de.leuphana.routesystem.behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.routesystem.behaviour.service.RouteCommandService;
import de.leuphana.routesystem.structure.Route;
@Component(immediate = true)
public class RouteSystemImpl implements RouteCommandService {

	private HashMap<Integer, String> location = new HashMap<Integer, String>();
	
	private static EventAdmin eventAdmin;
	
	private Logger logger;
	
	public RouteSystemImpl() {
		logger = LoggerFactory.getLogger(this.getClass());
		
		location.put(1, "Lueneburg");
		location.put(2, "Berlin");
		location.put(3, "Hamburg");
		location.put(4, "Hannover");
		location.put(5, "Muenchen");
	}


	@Override
	public Route chooseRoute() {
		System.out.println(
				"Bitte wählen Sie aus den nachfolgenden Orten einen Startort aus, in dem Sie die entsprechende Zahl eingeben");
		location.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		Scanner scanner = new Scanner(System.in);
		Integer dataInput = scanner.nextInt();
		String startLocation = location.get(dataInput);
		location.remove(dataInput);
		System.out.println(
				"Bitte wählen Sie aus den nachfolgenden Orten einen Zielort aus, in dem Sie die entsprechende Zahl eingeben");
		location.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		dataInput = scanner.nextInt();
		String endLocation = location.get(dataInput);
		int routeLength = 0;
		switch (startLocation) {
			case "Lueneburg": {
				switch (endLocation) {
				case "Berlin": {
					routeLength = 217;
					break;
				}
				case "Hamburg": {
					routeLength = 43;
					break;
				}
				case "Hannover": {
					routeLength = 107;
					break;
				}
				case "Muenchen": {
					routeLength = 574;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + endLocation);
				}
				break;
			}
			case "Berlin": {
				switch (endLocation) {
				case "Lueneburg": {
					routeLength = 217;
					break;
				}
				case "Hamburg": {
					routeLength = 255;
					break;
				}
				case "Hannover": {
					routeLength = 249;
					break;
				}
				case "Muenchen": {
					routeLength = 504;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + endLocation);
				}
				break;
			}
			case "Hamburg": {
				switch (endLocation) {
				case "Berlin": {
					routeLength = 255;
					break;
				}
				case "Lueneburg": {
					routeLength = 43;
					break;
				}
				case "Hannover": {
					routeLength = 132;
					break;
				}
				case "Muenchen": {
					routeLength = 612;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + endLocation);
				}
				break;
			}
			case "Hannover": {
				switch (endLocation) {
				case "Berlin": {
					routeLength = 249;
					break;
				}
				case "Hamburg": {
					routeLength = 132;
					break;
				}
				case "Lueneburg": {
					routeLength = 107;
					break;
				}
				case "Muenchen": {
					routeLength = 488;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + endLocation);
				}
				break;
			}
			case "Muenchen": {
				switch (endLocation) {
				case "Berlin": {
					routeLength = 504;
					break;
				}
				case "Hamburg": {
					routeLength = 612;
					break;
				}
				case "Hannover": {
					routeLength = 488;
					break;
				}
				case "Lueneburg": {
					routeLength = 574;
					break;
				}
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + endLocation);
				}

				break;
			}
		default:
			throw new IllegalArgumentException("Unexpected value: " + startLocation);
		}
		
		Route route = new Route(startLocation, endLocation, routeLength);
		postRouteEvent(route);

		return route;
	}
	public void postRouteEvent(Route route) {
		Map<String, Object> eventProperties = new HashMap<>();
		eventProperties.put("startLocation", route.getStartLocation());
		eventProperties.put("endLocation", route.getEndLocation());
		eventProperties.put("routeLength", route.getRouteLength());
		String eventTopic = "de/leuphana/cosa/routeSystem/routeChoosen";
		eventAdmin.postEvent(new Event(eventTopic, eventProperties));
		logger.info("RouteChoosenEvent occoured");
		
	}
	
	
	@Reference(name = "EventAdmin", policy = ReferencePolicy.DYNAMIC, cardinality = 
			ReferenceCardinality.MANDATORY, bind = "setEventAdmin",unbind = "resetEventAdmin")
    public void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }
	
	public void resetEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = null;
    }

}
