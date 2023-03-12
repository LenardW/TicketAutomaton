package de.leuphana.cosa.messagingsystem;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.cosa.messagingsystem.behaviour.MessagingSystemImpl;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.SendableEventHandler;

public class MessagingSystemActivator implements BundleActivator{
	
	private Logger logger;
	private static BundleContext context;
	
	private MessagingSystemImpl messagingSystemImpl;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		MessagingSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("MessagingSystem started");
		messagingSystemImpl = new MessagingSystemImpl();
		
		String[] topics = new String[] {"de/leuphana/cosa/documentSystem/documentCreated"};
		Dictionary<String, Object> eventHandlerProps = new Hashtable<>();
		eventHandlerProps.put(EventConstants.EVENT_TOPIC, topics);
		
		context.registerService(EventHandler.class.getName(), new SendableEventHandler(messagingSystemImpl), eventHandlerProps);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		MessagingSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("MessagingSystem stopped");
	}

}