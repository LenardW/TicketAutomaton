package de.leuphana.cosa.componentservicebus.structure.adapter;

import com.google.common.eventbus.Subscribe;

import de.leuphana.cosa.messagingsystem.behaviour.service.MessageType;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.Sendable;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintReport;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEvent;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEventListener;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintableEventService;

public class PrintReportToSendableAdapter implements PrintableEventListener {

	private MessagingCommandService messagingCommandService;

	public PrintReportToSendableAdapter(PrintableEventService printableEventService, MessagingCommandService messagingCommandService) {
		printableEventService.addPrintableEventListener(this);
		this.messagingCommandService = messagingCommandService;
	}

	@Subscribe
	@Override
	public void onPrintableExcuted(PrintableEvent printableEvent) {
		PrintReport printReport = printableEvent.getPrintReport();
		
		Sendable sendable = new Sendable() {
			
			@Override
			public String getSender() {
				return "TicketAutomaton1@train.de";
			}
			
			@Override
			public String getReceiver() {
				return "TrainCentral@train.de";
			}
			
			@Override
			public MessageType getMessageType() {
				return MessageType.EMAIL;
			}
			
			@Override
			public String getContent() {
				return "Print date: " + printReport.getPrintDate() + " Print price: " + printReport.getPrice();
			}
		};
		
		messagingCommandService.sendMessage(sendable);
	}

}
