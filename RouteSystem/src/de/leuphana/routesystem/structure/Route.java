package de.leuphana.routesystem.structure;

public class Route {

	public Route(String startLocation, String endLocation, int routeLength) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.routeLengh = routeLength;
	}

	private String startLocation;
	
	private String endLocation;
	
	private int routeLengh;
	
	public String getStartLocation() {
		return startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public int getRoutLengh() {
		return routeLengh;
	}



	
	
	
}
