package de.leuphana.cosa.componentservicebus.structure.adapter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.google.common.eventbus.Subscribe;

import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;
import de.leuphana.cosa.documentsystem.behaviour.service.event.ManageableEvent;
import de.leuphana.cosa.documentsystem.behaviour.service.event.ManageableEventListener;
//import de.leuphana.cosa.documentsystem.behaviour.service.event.ManageableEventService;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEventService;

@Component(property = EventConstants.EVENT_TOPIC+ "=" + "de/leuphana/cosa/manageableEvent/DocumentCreated")
public class ManageableToPrintableAdapter implements ManageableEventListener {
	
	//@Reference
	private PrintingCommandService printingCommandService;
	private Logger logger;
	
	public ManageableToPrintableAdapter() {
//		manageableEventService.addManageableEventListener(this);
//		this.printingCommandService = printingCommandService;
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
	public void handleEvent(Event event) {
		Manageable manageable = (Manageable) (((ManageableEvent) event).getDocument());
		logger.info(" Recevied event with topic: {}", event.getTopic());
		Printable printable = new Printable() {
			
			@Override
			public String getTitle() {
				return manageable.getTitle();
			}
			
			@Override
			public String getContent() {
				return manageable.getContent();
			}
		};
		
		PrintConfiguration printConfiguration = new PrintConfiguration() {
			String printFormat;

			@Override
			public void setPrintFormat(String printFormat) {
				this.printFormat = printFormat;
			}

			@Override
			public String getPrintFormat() {
				return printFormat;
			}
		};
		
		printConfiguration.setPrintFormat("A4");
		
		
		printingCommandService.printDocument(printable, printConfiguration);
	}

	}

//	@Subscribe
//	@Override
//	public void onManageableCreated(ManageableEvent manageableEvent) {
//		Manageable manageable = manageableEvent.getDocument();
//		
//		Printable printable = new Printable() {
//			
//			@Override
//			public String getTitle() {
//				return manageable.getTitle();
//			}
//			
//			@Override
//			public String getContent() {
//				return manageable.getContent();
//			}
//		};
//		
//		PrintConfiguration printConfiguration = new PrintConfiguration() {
//			String printFormat;
//
//			@Override
//			public void setPrintFormat(String printFormat) {
//				this.printFormat = printFormat;
//			}
//
//			@Override
//			public String getPrintFormat() {
//				return printFormat;
//			}
//		};
//		
//		printConfiguration.setPrintFormat("A4");
//		
//		
//		printingCommandService.printDocument(printable, printConfiguration);
//	}
//
//}
