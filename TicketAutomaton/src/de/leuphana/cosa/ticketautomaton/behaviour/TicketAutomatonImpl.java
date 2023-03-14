package de.leuphana.cosa.ticketautomaton.behaviour;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component(immediate = true)
public class TicketAutomatonImpl implements BundleActivator{
	
	private Logger logger;
	
	private static BundleContext context;
	
	private ServiceReference eventAdminRef;
	private EventAdmin eventAdmin;
	private Map<String, Object> eventProperties;
	private String eventTopic;

	static BundleContext getContext() {
		return context;
	}
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		TicketAutomatonImpl.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("TicketAutomaton started");
		eventAdminRef = context.getServiceReference(EventAdmin.class.getName());
		if (eventAdminRef != null) {
			eventAdmin = (EventAdmin) context.getService(eventAdminRef);
		}
		
		eventProperties = new HashMap<String, Object>();
		eventTopic = "de/leuphana/cosa/ticketAutomaton/startTicketSelling";
		eventAdmin.postEvent(new Event(eventTopic,eventProperties));
		
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
