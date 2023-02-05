package de.leuphana.cosa.componentservicebus.structure;

//import com.google.common.eventbus.EventBus;
import org.osgi.service.event.;	

public abstract class Component {
	
	private EventBus eventBus;
	
	public Component() {
		eventBus = new EventBus();
	}
	
	public abstract String getComponentName();
	public abstract String getCommandServiceName();
	public abstract String getEventServiceName();
	public abstract String getCommandServicePath();
	public abstract String getEventServicePath();
	
	protected void post(Object object) {
		eventBus.post(object);
	}
	
	protected void register(Object object) {
		eventBus.register(object);
	}
	
	protected void unregister(Object object) {
		eventBus.unregister(object);
	}
}