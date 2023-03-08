package de.leuphana.cosa.documentsystem.behaviour.service;

import de.leuphana.cosa.documentsystem.structure.Document;

public interface DocumentCommandService  {

	Document createDocument(String title);
	Boolean addDocument(Document document);
	Document createTicket(Manageable manageable);
	//TODO to implement
//	void updateDocument(Document document);
//	Document getDocument(Integer documentId) throws DocumentNotFoundException;

}