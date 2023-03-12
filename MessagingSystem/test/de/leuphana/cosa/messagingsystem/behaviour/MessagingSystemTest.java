package de.leuphana.cosa.messagingsystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.leuphana.cosa.messagingsystem.behaviour.service.MessageType;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.Sendable;

class MessagingSystemTest {

	private static MessagingCommandService messagingSystem;
	private static Sendable sendable;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		messagingSystem = new MessagingSystemImpl();
		
		sendable = new Sendable() {
			
			@Override
			public String getSender() {
				return "test.name@leuphana.de";
			}
			
			@Override
			public String getReceiver() {
				return "rainer.zufall@web.de";
			}
			
			@Override
			public MessageType getMessageType() {
				return MessageType.EMAIL;
			}
			
			@Override
			public String getContent() {
				return "This is content content!";
			}

			@Override
			public String getTitle() {
				return "This is a title";
			}
		};
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		messagingSystem = null;
	}

	@Test
	void canMessageBeSentViaEmail() {
		Assertions.assertTrue(messagingSystem.sendMessage(sendable).isDeliverySuccessful());
	}

}
