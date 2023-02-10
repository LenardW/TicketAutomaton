package de.leuphana.cosa.component.structure;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;	


public abstract class AbstractComponent {
	
	@Reference
	EventAdmin eventAdmin;
	
	
	public AbstractComponent() {
		//eventAdmin = new EventAdmin();
	}
	
	public abstract String getComponentName();
	public abstract String getCommandServiceName();
	public abstract String getEventServiceName();
	public abstract String getCommandServicePath();
	public abstract String getEventServicePath();
	
	@Activate
	protected void post(String eventTopic, Map<String, ?> properties) {
		eventAdmin.postEvent(new Event(eventTopic, properties));
	}
	
	protected void register(Object object) {
		//eventBus.register(object);
	}
	
	protected void unregister(Object object) {
		//eventBus.unregister(object);
	}
}