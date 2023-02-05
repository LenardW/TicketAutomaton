package de.leuphana.cosa.componentservicebus.behaviour;

import de.leuphana.cosa.component.structure.Component;

public interface ComponentServiceBusService {

	void registerComponent(Component component);

	void configureComponentConnections();

	boolean sellTicket(String start, String end);

}