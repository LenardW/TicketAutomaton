package de.leuphana.pricingsystem;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.pricingsystem.behaviour.PricingSystemImpl;
import de.leuphana.pricingsystem.behaviour.service.event.PriceableEventHandler;

public class PricingSystemActivator implements BundleActivator{

	private Logger logger;
	private static BundleContext context;
	
	private PricingSystemImpl pricingSystemImpl;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		PricingSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PricingSystem started");
		pricingSystemImpl = new PricingSystemImpl();
		
		String[] topics = new String[] {"de/leuphana/cosa/routeSystem/routeChoosen"};
		Dictionary<String, Object> eventHandlerProps = new Hashtable<>();
		eventHandlerProps.put(EventConstants.EVENT_TOPIC, topics);
		
		context.registerService(EventHandler.class.getName(), new PriceableEventHandler(pricingSystemImpl), eventHandlerProps);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		PricingSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PricingSystem stopped");
	}
}
