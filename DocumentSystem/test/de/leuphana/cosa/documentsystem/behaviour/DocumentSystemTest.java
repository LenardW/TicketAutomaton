package de.leuphana.cosa.documentsystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.structure.Document;

class DocumentSystemTest {

	private static DocumentCommandService documentSystem;
	private static Document document;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		documentSystem = new DocumentSystemImpl();
		document = new Document("New document");
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		documentSystem = null;
		document = null;
	}

	@Order(1)
	@Test
	public void canDocumentBeCreated() {
		Assertions.assertNotNull(documentSystem.createDocument("New document was "));
	}
	
	@Order(2)
	@Test
	public void canDocumentBeAdded() {
		Assertions.assertTrue(documentSystem.addDocument(document));	
	}
}
