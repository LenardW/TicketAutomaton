package de.leuphana.cosa.printingsystem;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.cosa.printingsystem.behaviour.PrintingSystemImpl;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEventHadler;

public class PrintingSystemActivator implements BundleActivator{
	
	private Logger logger;
	private static BundleContext context;
	
	private PrintingSystemImpl printingSystemImpl;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		PrintingSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PrintingSystem started");
		printingSystemImpl = new PrintingSystemImpl();
		
		String[] topics = new String[] {"de/leuphana/cosa/documentSystem/documentCreated"};
		Dictionary<String, Object> eventHandlerProps = new Hashtable<>();
		eventHandlerProps.put(EventConstants.EVENT_TOPIC, topics);
		
		context.registerService(EventHandler.class.getName(), new PrintableEventHadler(printingSystemImpl), eventHandlerProps);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		PrintingSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PrintingSystem stopped");
	}

}
