package de.leuphana.cosa.messagingsystem.behaviour.service;

public interface Sendable {
	String getContent();
	
	String getTitle();

	MessageType getMessageType();

	String getSender();

	String getReceiver();
}
