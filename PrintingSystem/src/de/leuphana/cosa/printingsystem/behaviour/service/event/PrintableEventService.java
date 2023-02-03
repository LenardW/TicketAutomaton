package de.leuphana.cosa.printingsystem.behaviour.service.event;

public interface PrintableEventService {
	void addPrintableEventListener(PrintableEventListener printableEventListener);
	void removePrintableEventListener(PrintableEventListener printableEventListener);
}