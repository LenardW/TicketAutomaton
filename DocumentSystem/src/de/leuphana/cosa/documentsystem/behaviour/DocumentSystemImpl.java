package de.leuphana.cosa.documentsystem.behaviour;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.leuphana.cosa.component.structure.AbstractComponent;
import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;
import de.leuphana.cosa.documentsystem.behaviour.service.event.ManageableEvent;
import de.leuphana.cosa.documentsystem.behaviour.service.event.ManageableEventListener;
import de.leuphana.cosa.documentsystem.behaviour.service.event.ManageableEventService;
import de.leuphana.cosa.documentsystem.structure.Document;

@Component(immediate = true)
public class DocumentSystemImpl extends AbstractComponent implements DocumentCommandService, ManageableEventService {
	// Java Collection classes
	// Interface (Was? - 1): List, Set, Map, Queue
	// Realisierung: (Wie? - N): ArrayList, LinkedList / HashMap, TreeMap
	private Map<Integer, Document> documents;
	
//	private Set<ManageableEventListener> manageableEventListeners;
	
	private Logger logger;
	
	private static EventAdmin eventAdmin;
	private Map<String, Document> eventProperties;
	private String eventTopic;

	public DocumentSystemImpl() {
//		manageableEventListeners = new HashSet<ManageableEventListener>();
		
		// Was? / Interface = Wie? / Realisierung
		documents = new HashMap<Integer, Document>();
		logger = LoggerFactory.getLogger(this.getClass());
		
		eventProperties = new HashMap<String, Document>();
	}

	@Override
	public Boolean addDocument(Document document) {
		documents.put(document.getId(), document);

		logger.info("Document with title " + document.getTitel() + " added!");

		return documents.containsKey(document.getId());
	}

	@Override
	public Document createDocument(String title) {
		Manageable manageable = new Manageable() {

			@Override
			public String getTitle() {
				return title;
			}

			@Override
			public String getContent() {
				return "";
			}
			
		};
		
		Document document = new Document(title);
		
		logger.info("Document : " + title + " created!");
		
		eventTopic = "de/leuphana/cosa/manageableEvent/DocumentCreated";
		eventProperties.put("document", document);
		
		// TODO Refactor into seperate method
		ManageableEvent manageableEvent = new ManageableEvent(eventTopic, eventProperties);
		
//		for (ManageableEventListener manageableEventListener : manageableEventListeners) {
//			manageableEventListener.onManageableCreated(manageableEvent);
//		}
		
		eventAdmin.postEvent(manageableEvent);
		logger.info("manageableEvent occoured");
		
		return document;
	}
	
	@Reference(name = "EventAdmin", policy = ReferencePolicy.DYNAMIC, cardinality = 
			ReferenceCardinality.MANDATORY, bind = "setEventAdmin",unbind = "resetEventAdmin")
    public void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }
	
	public void resetEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = null;
    }

	@Override
	public void addManageableEventListener(ManageableEventListener manageableEventListener) {
//		manageableEventListeners.add(manageableEventListener);
		
		super.register(manageableEventListener);
	}

	@Override
	public void removeManageableEventListener(ManageableEventListener manageableEventListener) {
//		manageableEventListeners.remove(manageableEventListener);
		
		super.unregister(manageableEventListener);
	}

	@Override
	public String getCommandServiceName() {
		return DocumentCommandService.class.getName();
	}

	@Override
	public String getEventServiceName() {
		return ManageableEventService.class.getName();
	}

	@Override
	public String getCommandServicePath() {
		return DocumentCommandService.class.getPackageName();
	}

	@Override
	public String getEventServicePath() {
		return ManageableEventService.class.getPackageName();
	}

	@Override
	public String getComponentName() {
		return "DocumentSystem";
	}
	
}
