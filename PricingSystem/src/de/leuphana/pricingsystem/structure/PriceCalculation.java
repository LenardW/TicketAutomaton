package de.leuphana.pricingsystem.structure;

public class PriceCalculation {

	public PriceCalculation(String priceGroup,float price, float distance) {
		this.priceGroup = priceGroup;
		this.price = price;
		this.distance = distance;
	}

	private String priceGroup;
	
	private float price;
	
	private float distance;
	
	public float getDistance() {
		return distance;
	}
	
	public String getPriceGroup() {
		return priceGroup;
	}

	public float getPrice() {
		return price;
	}

}
