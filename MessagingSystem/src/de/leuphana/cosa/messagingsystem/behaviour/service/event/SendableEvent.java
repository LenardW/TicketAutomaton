package de.leuphana.cosa.messagingsystem.behaviour.service.event;

import java.util.Map;

import org.osgi.service.event.Event;

import de.leuphana.cosa.messagingsystem.behaviour.service.DeliveryReport;

public class SendableEvent extends Event {
	private DeliveryReport deliveryReport;

	public SendableEvent(String topic, Map<String, DeliveryReport> properties) {
		super(topic, properties);
		this.deliveryReport = properties.get("deliveryReport");
	}

	public DeliveryReport getDeliveryReport() {
		return deliveryReport;
	}

}