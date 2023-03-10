package de.leuphana.routesystem;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.routesystem.behaviour.RouteSystemImpl;
import de.leuphana.routesystem.behaviour.service.event.StartingEventHandler;


public class RouteSystemActivator implements BundleActivator{

	
	private Logger logger;
	private static BundleContext context;
	
	private RouteSystemImpl routeSystemImpl;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		RouteSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("RouteSystem started");
		routeSystemImpl = new RouteSystemImpl();
		
		String[] topics = new String[] {"de/leuphana/cosa/TicketAutomation/startTicketSelling"};
		Dictionary<String, Object> eventHandlerProps = new Hashtable<>();
		eventHandlerProps.put(EventConstants.EVENT_TOPIC, topics);
		
		context.registerService(EventHandler.class.getName(), new StartingEventHandler(routeSystemImpl), eventHandlerProps);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		RouteSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("RouteSystem stopped");
	}
}
