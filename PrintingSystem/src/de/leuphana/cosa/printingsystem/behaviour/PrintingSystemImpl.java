package de.leuphana.cosa.printingsystem.behaviour;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.cosa.component.structure.AbstractComponent;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintReport;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEvent;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEventListener;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEventService;
import de.leuphana.cosa.printingsystem.structure.PrintFormat;
import de.leuphana.cosa.printingsystem.structure.PrintJob;
import de.leuphana.cosa.printingsystem.structure.PrintJobQueue;
import de.leuphana.cosa.printingsystem.structure.Printer;

@Component(immediate = true)
public class PrintingSystemImpl extends AbstractComponent implements PrintingCommandService, PrintableEventService {
	// Interfaces
	// Collection (Sammlung von Objekten)(Was?) ==> Set[keine doppelten Objekte],
	// List[kann auch doppelten Objekte enthalten], Map[organisiert nach
	// Schl�ssel/Wert-Prinzip], Queue[Reihenfolge der Objekte spielt eine Rolle]
	// (Wie? Unterschied: NFR's)

	// Was? / Interface
//	private Map<DocumentFormat, Printer> printers;
	private PrintJobQueue printJobQueue;
	
//	private Set<PrintableEventListener> printableEventListeners;

	private static EventAdmin eventAdmin;
	private Map<String, PrintReport> eventProperties;
	private String eventTopic;
	
	private Logger logger;
	
	public PrintingSystemImpl() {
		
		eventProperties = new HashMap<String, PrintReport>();
		
//		printableEventListeners = new HashSet<PrintableEventListener>();

		// Wie? / konkrete Klasse
//		printers = new HashMap<DocumentFormat, Printer>();
//		printers.put();
//		printers.put(DocumentFormat.A5, new Printer(DocumentFormat.A5));

		printJobQueue = PrintJobQueue.getInstance();
		printJobQueue.addPrinter(new Printer(PrintFormat.A4));
		printJobQueue.addPrinter(new Printer(PrintFormat.A3));
		
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public PrintReport printDocument(Printable printable, PrintConfiguration printConfiguration) {
		// TODO sp�ter in der Queue pr�fen, ob DocumentFormat korrekt ist
		// TODO pr�fen, �ber Queue, ob PrintJob auch gedruckt wurde (Exceptions)
		PrintJob printJob = new PrintJob(printable, printConfiguration);
		// Action
		printJobQueue.addPrintJob(printJob);

		PrintReport printReport = new PrintReport();
		printReport.setConfirmationText(printable.getContent());
		printReport.setPrintDate(LocalDate.now());
		printReport.setPrintSuccessful(true);
		
		
		eventTopic = "de/leuphana/cosa/printableEvent";
		eventProperties.put("printReport", printReport);
		
		// TODO Refactor into seperate method
		PrintableEvent printableEvent = new PrintableEvent(eventTopic, eventProperties);

//		for (PrintableEventListener printableEventListener : printableEventListeners) {
//			printableEventListener.onPrintableExcuted(printableEvent);
//		}
		
		
		eventAdmin.postEvent(printableEvent);
		logger.info("printableEvent occoured");
		
		return printReport;
	}
	
	@Reference(name = "EventAdmin", policy = ReferencePolicy.DYNAMIC, cardinality = 
			ReferenceCardinality.MANDATORY, bind = "setEventAdmin",unbind = "unsetEventAdmin")
    public void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }
	
	public void unsetEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = null;
    }
	

	@Override
	public Set<String> getSupportedPrintFormats() {
		// transformation
//		Set<String> supportedPrintFormatAsString = new HashSet<String>();
//		
//		for (PrintFormat printFormat : PrintFormat.values()) {
//			supportedPrintFormatAsString.add(printFormat.toString());
//		}

//		return supportedPrintFormatAsString;

		// Pipe&Filter
		// transformation
		return Arrays.stream(PrintFormat.values()).map(Enum::name).collect(Collectors.toSet());
	}

	@Override
	public String getCommandServiceName() {
		return PrintingCommandService.class.getName();
	}

	@Override
	public String getEventServiceName() {
		return PrintableEventService.class.getName();
	}

	@Override
	public String getCommandServicePath() {
		return PrintingCommandService.class.getPackageName();
	}

	@Override
	public String getEventServicePath() {
		return PrintableEventService.class.getPackageName();
	}

	@Override
	public String getComponentName() {
		return "PrintingSystem";
	}

	@Override
	public void addPrintableEventListener(PrintableEventListener printableEventListener) {
//		printableEventListeners.add(printableEventListener);
		
		super.register(printableEventListener);
	}

	@Override
	public void removePrintableEventListener(PrintableEventListener printableEventListener) {
		// printableEventListeners.remove(printableEventListener);
		
		super.unregister(printableEventListener);
	}
	
//	void registerEventAdmin(EventAdmin admin) {
//        this.eventAdmin = admin;
//
//    }
//
//    void unregisterEventAdmin(EventAdmin admin) {
//        this.eventAdmin = null;
//
//    }

}
