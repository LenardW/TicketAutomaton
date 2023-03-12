package de.leuphana.cosa.documentsystem;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.cosa.documentsystem.behaviour.DocumentSystemImpl;
import de.leuphana.cosa.documentsystem.behaviour.service.event.ManageableEventHandler;

public class DocumentSystemActivator implements BundleActivator{

	private Logger logger;
	private static BundleContext context;
	
	private DocumentSystemImpl documentSystemImpl;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		DocumentSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("DocumentSystemActivator started");
		documentSystemImpl = new DocumentSystemImpl();
		
		String[] topics = new String[] {"de/leuphana/cosa/pricingSystem/priceCalculated"};
		Dictionary<String, Object> eventHandlerProps = new Hashtable<>();
		eventHandlerProps.put(EventConstants.EVENT_TOPIC, topics);
		
		context.registerService(EventHandler.class.getName(), new ManageableEventHandler(documentSystemImpl), eventHandlerProps);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		DocumentSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("DocumentSystemActivator stopped");
	}
}
