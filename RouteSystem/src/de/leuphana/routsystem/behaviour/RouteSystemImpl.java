package de.leuphana.routsystem.behaviour;

import java.util.HashMap;
import java.util.Scanner;

public class RouteSystemImpl implements RouteSystemService {

	private HashMap<Integer, String> location = new HashMap<Integer, String>();
	
	
	@Override
	public boolean chooseLocations() {
		location.put(1, "Lüneburg");
		location.put(2, "Berlin");
		location.put(3, "Hamburg");
		location.put(4, "Hannover");
		location.put(5, "München");
		System.out.println("Bitte wählen Sie aus den nachfolgenden Orten einen Startort aus, in dem Sie die entsprechende Zahl eingeben");
		location.forEach((k,v) ->{
			System.out.println(k +": "+ v);
		});
		Scanner scanner = new Scanner(System.in);
		Integer dataInput = scanner.nextInt();
		String startLocation = location.get(dataInput);
		location.remove(dataInput);
		System.out.println("Bitte wählen Sie aus den nachfolgenden Orten einen Zielort aus, in dem Sie die entsprechende Zahl eingeben");
		location.forEach((k,v) ->{
			System.out.println(k +": "+ v);
		});
		Scanner scanner1 = new Scanner(System.in);
		dataInput = scanner.nextInt();
		String endLocation = location.get(dataInput);
		 
		if (endLocation.isEmpty()) {
			return false;
		}
		return true;
	}

	
}
