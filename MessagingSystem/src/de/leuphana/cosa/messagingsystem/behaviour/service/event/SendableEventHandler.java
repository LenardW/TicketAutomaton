package de.leuphana.cosa.messagingsystem.behaviour.service.event;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.leuphana.cosa.messagingsystem.behaviour.service.MessageType;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.Sendable;

public class SendableEventHandler implements EventHandler{
	
	private MessagingCommandService messagingCommandService;
	
	public SendableEventHandler(MessagingCommandService messagingCommandService){
		this.messagingCommandService = messagingCommandService;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getTopic().equals("de/leuphana/cosa/documentSystem/documentCreated")) {
			Sendable sendable = new Sendable () {

				@Override
				public String getContent() {
					return (String) event.getProperty("text");
				}

				@Override
				public MessageType getMessageType() {
					return MessageType.EMAIL;
				}

				@Override
				public String getSender() {
					return "automat.funktioniertnicht@immer.de";
				}

				@Override
				public String getReceiver() {
					return "kunde.kauft@etwas.de";
				}

				@Override
				public String getTitle() {
					return (String) event.getProperty("title");
				}
				
			};
			
			messagingCommandService.sendMessage(sendable);
			
			
			
		}
		
	}
	

}
