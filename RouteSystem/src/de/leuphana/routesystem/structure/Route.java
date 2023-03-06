package de.leuphana.routesystem.structure;

public class Route {

	public Route(String startLocation, String endLocation, int routeLength) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.routeLength = routeLength;
	}

	private String startLocation;
	
	private String endLocation;
	
	private int routeLength;
	
	public String getStartLocation() {
		return startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public int getRouteLength() {
		return routeLength;
	}



	
	
	
}
