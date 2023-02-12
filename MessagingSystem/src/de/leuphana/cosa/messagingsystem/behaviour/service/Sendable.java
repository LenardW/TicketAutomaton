package de.leuphana.cosa.messagingsystem.behaviour.service;

public interface Sendable {
	String getContent();

	MessageType getMessageType();

	String getSender();

	String getReceiver();
}
