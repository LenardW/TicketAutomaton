package de.leuphana.cosa.component.structure;

import org.osgi.service.event.Event;
//import com.google.common.eventbus.EventBus;
import org.osgi.service.event.EventAdmin;	

public abstract class Component {
	
	//private EventBus eventBus;
	private EventAdmin eventAdmin;
	
	public Component() {
		//eventBus = new EventBus();
		//eventAdmin = new EventAdmin();
	}
	
	public abstract String getComponentName();
	public abstract String getCommandServiceName();
	public abstract String getEventServiceName();
	public abstract String getCommandServicePath();
	public abstract String getEventServicePath();
	
	protected void post(Event event) {
		//eventAdmin.postEvent(event);
	}
	
	protected void register(Object object) {
		//eventBus.register(object);
	}
	
	protected void unregister(Object object) {
		//eventBus.unregister(object);
	}
}