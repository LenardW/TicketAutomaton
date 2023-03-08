package de.leuphana.cosa.documentsystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;
import de.leuphana.cosa.documentsystem.structure.Document;

class DocumentSystemTest {

	private static DocumentCommandService documentSystem;
	private static Document document;
	private static Manageable manageable;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		documentSystem = new DocumentSystemImpl();
		document = new Document("New document");
		manageable = new Manageable() {

			@Override
			public String getStartLocation() {
				return "Hamburg";
			}

			@Override
			public String getEndLocation() {
				return "Berlin";
			}

			@Override
			public String getPriceGroup() {
				return "Normal-Tarif";
			}

			@Override
			public float getPrice() {
				return 14.00f;
			}

			@Override
			public float getDistance() {
				return 200.00f;
			}

		
		};
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		documentSystem = null;
		document = null;
	}

	@Order(1)
	@Test
	public void canTicketBeCreated() {
		Document document = documentSystem.createTicket(manageable);
		System.out.println(document.getText());
		Assertions.assertNotNull(document);
	}
	
	@Order(2)
	@Test
	public void canDocumentBeAdded() {
		Assertions.assertTrue(documentSystem.addDocument(document));	
	}
}
