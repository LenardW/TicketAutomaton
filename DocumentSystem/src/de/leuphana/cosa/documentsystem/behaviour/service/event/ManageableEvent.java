package de.leuphana.cosa.documentsystem.behaviour.service.event;

import java.util.Map;

import org.osgi.service.event.Event;

import de.leuphana.cosa.documentsystem.structure.Document;

public class ManageableEvent extends Event {
	private Document document;

	public ManageableEvent(String topic, Map<String, Document> properties) {
		super(topic, properties);
		this.document = properties.get("document");
	}

	public Document getDocument() {
		return document;
	}

}