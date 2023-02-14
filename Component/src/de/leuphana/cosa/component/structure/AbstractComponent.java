package de.leuphana.cosa.component.structure;

public abstract class AbstractComponent {
	
	
	public abstract String getComponentName();
	public abstract String getCommandServiceName();
	public abstract String getEventServiceName();
	public abstract String getCommandServicePath();
	public abstract String getEventServicePath();
	
	protected void register(Object object) {
		//eventBus.register(object);
	}
	
	protected void unregister(Object object) {
		//eventBus.unregister(object);
	}
}