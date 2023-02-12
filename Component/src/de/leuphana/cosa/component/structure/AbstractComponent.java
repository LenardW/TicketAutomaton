package de.leuphana.cosa.component.structure;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
	

//@Component(immediate = true)
public abstract class AbstractComponent {
	
	
//	@Reference(service = EventAdmin.class)
	EventAdmin eventAdmin;
    
	
//	private String eventTopic;
//	private Map<String, ?> properties;
//	private EventAdmin thisEventAdmin;
	
	
	
	
//	public AbstractComponent() {
//		//eventAdmin = new EventAdmin();
//	}
	
	public abstract String getComponentName();
	public abstract String getCommandServiceName();
	public abstract String getEventServiceName();
	public abstract String getCommandServicePath();
	public abstract String getEventServicePath();
	
//	@Activate
//	public void activate(ComponentContext context) {
//		thisEventAdmin = eventAdmin;
////		Event event = new Event(eventTopic, properties);
////		eventAdmin.postEvent(event);
//	}
	
	
	public void post(String eventTopic, Map<String, ?> properties) {
		BundleContext context =FrameworkUtil.getBundle(AbstractComponent.class).getBundleContext();
		ServiceReference ref = context.getServiceReference(EventAdmin.class.getName());
	    if (ref != null)
	    {
	        EventAdmin eventAdmin = (EventAdmin) context.getService(ref);
	    }
		
		System.out.println("TEST");
		eventAdmin.postEvent(new Event(eventTopic, properties));
	}
	
	protected void register(Object object) {
		//eventBus.register(object);
	}
	
	protected void unregister(Object object) {
		//eventBus.unregister(object);
	}
}