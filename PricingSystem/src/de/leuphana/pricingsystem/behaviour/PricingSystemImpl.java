package de.leuphana.pricingsystem.behaviour;

import java.util.Scanner;

import de.leuphana.pricingsystem.behaviour.service.Priceable;
import de.leuphana.pricingsystem.behaviour.service.PricingCommandService;
import de.leuphana.pricingsystem.structure.PriceCalculation;

public class PricingSystemImpl implements PricingCommandService{

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
			priceGroup ="Günstiger Reisen-Tarif";
			break;
		}
		case 3: {
			price = normalPrice * 0.50f;
			priceGroup ="Schnäppchen-Tarif";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + dataInput);
		}
		
		PriceCalculation priceCalculation = new PriceCalculation(priceGroup, price, distance);
		
		
		
		
		return priceCalculation;
	}

}
