package de.leuphana.cosa.documentsystem.structure;

import java.time.LocalDate;

public abstract class TicketDocumentTemplate extends Document{
	
	private String startLocation;
	private String endLocation;
	private float distance;
	private float price;
	private LocalDate date;

	public TicketDocumentTemplate(String startLocation, String endLocation, float distance, float price) {
		super("Ticket von: "+startLocation+" nach: "+endLocation);
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.distance = distance;
		this.price = price;		
		this.date= LocalDate.now();
		
	}
	public LocalDate getDate() {
		return date;
	}
	
	public String getStartLocation() {
		return startLocation;
	}
	public String getEndLocation() {
		return endLocation;
	}
	public float getDistance() {
		return distance;
	}
	public float getPrice() {
		return price;
	}
	

}
