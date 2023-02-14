package de.leuphana.cosa.printingsystem.behaviour.service.event;

import java.util.Map;

import org.osgi.service.event.Event;

import de.leuphana.cosa.printingsystem.behaviour.service.PrintReport;

public class PrintableEvent extends Event {
	private PrintReport printReport;

	public PrintableEvent(String topic, Map<String, PrintReport> properties) {
		super(topic, properties);
		this.printReport = properties.get("printReport");
	}

	public PrintReport getPrintReport() {
		return printReport;
	}

}