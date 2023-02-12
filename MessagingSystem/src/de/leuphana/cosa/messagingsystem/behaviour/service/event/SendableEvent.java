package de.leuphana.cosa.messagingsystem.behaviour.service.event;

import java.util.EventObject;

import de.leuphana.cosa.messagingsystem.behaviour.service.DeliveryReport;

public class SendableEvent extends EventObject {
	private DeliveryReport deliveryReport;

	public SendableEvent(DeliveryReport deliveryReport) {
		super(deliveryReport);
		this.deliveryReport = deliveryReport;
	}

	public DeliveryReport getDeliveryReport() {
		return deliveryReport;
	}

}