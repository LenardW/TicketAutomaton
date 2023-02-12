package de.leuphana.cosa.messagingsystem.behaviour.service.event;

import java.util.EventListener;

public interface SendableEventListener extends EventListener {
	void onMessageDelivered(SendableEvent sendableEvent);
//	void onMessageNotDelivered(SendableEvent sendableEvent);
}
