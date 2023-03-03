package de.leuphana.routesystem.behaviour;

import java.util.HashMap;
import java.util.Scanner;

import de.leuphana.routesystem.behaviour.service.RouteSystemCommandService;
import de.leuphana.routesystem.structure.Route;

public class RouteSystemImpl implements RouteSystemCommandService {

	private HashMap<Integer, String> location = new HashMap<Integer, String>();


	@Override
	public boolean chooseLocations() {
		location.put(1, "Lueneburg");
		location.put(2, "Berlin");
		location.put(3, "Hamburg");
		location.put(4, "Hannover");
		location.put(5, "Muenchen");
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
		Scanner scanner1 = new Scanner(System.in);
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
//		System.out.println(routeLength);
		Route route = new Route(startLocation, endLocation, routeLength);

		
		// TODO Event Admin with Route to adapter

		if (endLocation.isEmpty()) {
			return false;
		}
		return true;
	}

}
