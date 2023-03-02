package de.leuphana.cosa.documentsystem.behaviour.service.event;

//import java.util.EventListener;

import org.osgi.service.event.EventHandler;

public interface ManageableEventListener extends EventHandler {
	void onManageableCreated(ManageableEvent manageableEvent);
//	void onManageableDeleted(ManageableEvent manageableEvent);
//	void onManageableAdded(ManageableEvent manageableEvent);
//	void onManageableUpdated(ManageableEvent manageableEvent);
//	void onMangeableRequested(ManageableEvent manageableEvent);
}
