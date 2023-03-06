package de.leuphana.cosa.documentsystem.behaviour;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.Event;
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
public class DocumentSystemImpl implements DocumentCommandService{
	// Java Collection classes
	// Interface (Was? - 1): List, Set, Map, Queue
	// Realisierung: (Wie? - N): ArrayList, LinkedList / HashMap, TreeMap
	private Map<Integer, Document> documents;
	
//	private Set<ManageableEventListener> manageableEventListeners;
	
	private Logger logger;
	
	private static EventAdmin eventAdmin;


	public DocumentSystemImpl() {
//		manageableEventListeners = new HashSet<ManageableEventListener>();
		
		// Was? / Interface = Wie? / Realisierung
		documents = new HashMap<Integer, Document>();
		logger = LoggerFactory.getLogger(this.getClass());
		

	}

	@Override
	public Boolean addDocument(Document document) {
		documents.put(document.getId(), document);

		logger.info("Document with title " + document.getTitel() + " added!");

		return documents.containsKey(document.getId());
	}

	@Override
	public Document createDocument(String title) {
		
		Document document = new Document(title);
		logger.info("Document : " + title + " created!");
		
		postDocumentEvent(document);

		return document;
	}
	
	public void postDocumentEvent(Document document) {
		Map<String, Object> eventProperties = new HashMap<>();
		eventProperties.put("title", document.getTitel());
		eventProperties.put("text", document.getText());
		String eventTopic = "de/leuphana/cosa/documentSystem/documentCreated";
		eventAdmin.postEvent(new Event(eventTopic, eventProperties));//send synchron /post asynchron was ist sinnvoller?
		logger.info("DocumentCreatedEvent occoured");
		
	}
	
	@Reference(name = "EventAdmin", policy = ReferencePolicy.DYNAMIC, cardinality = 
			ReferenceCardinality.MANDATORY, bind = "setEventAdmin",unbind = "resetEventAdmin")
    public void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }
	
	public void resetEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = null;
    }


	
}
