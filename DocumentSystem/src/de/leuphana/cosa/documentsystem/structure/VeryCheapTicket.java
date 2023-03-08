package de.leuphana.cosa.documentsystem.structure;

public class VeryCheapTicket extends TicketDocumentTemplate{

	public VeryCheapTicket(String startLocation, String endLocation, float distance, float price) {
		super(startLocation, endLocation, distance, price);
	}
	@Override 
	public String getText() {
		String price = Float.toString(getPrice());
		String distance = Float.toString(getDistance());

		
		return "-----------------------------------------------\n"+
				"			Schnäppchen-Tarif			\n"+
				"Ticket von:  "+ getStartLocation()+"\n"+
				"		nach: "+ getEndLocation()+"\n"+
				"Entfernung:  "+ distance+" km \n"+
				"\n"+
				"Preis: 	  "+ price+" € \n"+
				"Datum:		  "+ getDate()+ "\n"+
				"-----------------------------------------------\n";
		
	}
	
}
