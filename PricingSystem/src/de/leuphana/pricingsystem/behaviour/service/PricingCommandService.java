package de.leuphana.pricingsystem.behaviour.service;

import de.leuphana.pricingsystem.structure.PriceCalculation;

public interface PricingCommandService {
	PriceCalculation calculatePrice(Priceable priceable);
}
