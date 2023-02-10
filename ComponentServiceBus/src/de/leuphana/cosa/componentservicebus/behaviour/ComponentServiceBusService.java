package de.leuphana.cosa.componentservicebus.behaviour;

import de.leuphana.cosa.component.structure.AbstractComponent;

public interface ComponentServiceBusService {

	void registerComponent(AbstractComponent component);

	void configureComponentConnections();

	boolean sellTicket(String start, String end);

}