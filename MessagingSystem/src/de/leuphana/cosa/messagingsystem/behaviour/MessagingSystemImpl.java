package de.leuphana.cosa.messagingsystem.behaviour;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.EventAdmin;

import de.leuphana.cosa.component.structure.AbstractComponent;
import de.leuphana.cosa.messagingsystem.behaviour.service.DeliveryReport;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.Sendable;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.SendableEvent;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.SendableEventListener;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.SendableEventService;
import de.leuphana.cosa.messagingsystem.structure.message.Message;
import de.leuphana.cosa.messagingsystem.structure.messagetypefactory.InternalMessageType;
import de.leuphana.cosa.messagingsystem.structure.messagetypefactory.InternalMessagingTypeFactory;
import de.leuphana.cosa.messagingsystem.structure.messagingfactory.AbstractMessagingFactory;
import de.leuphana.cosa.messagingsystem.structure.messagingprotocol.MessagingProtocol;

@Component(immediate = true)
public class MessagingSystemImpl extends AbstractComponent implements MessagingCommandService, SendableEventService {
	private Logger logger;
	private static EventAdmin eventAdmin;
	private Map<String, DeliveryReport> eventProperties;
	private String eventTopic;

	public MessagingSystemImpl() {
		logger = LoggerFactory.getLogger(this.getClass());
		eventProperties = new HashMap<String, DeliveryReport>();
	}
	
	
	@Override
	public DeliveryReport sendMessage(Sendable sendable) {
		
		InternalMessageType internalMessageType = InternalMessagingTypeFactory.createInternalMessageType(sendable.getMessageType());
		
		AbstractMessagingFactory abstractMessagingFactory = AbstractMessagingFactory.getFactory(internalMessageType);

		Message message = abstractMessagingFactory.createMessage(sendable.getReceiver(), sendable.getSender(), sendable.getContent());

		MessagingProtocol messageProtocol = abstractMessagingFactory.createMessagingProtocol();
		messageProtocol.open();
		messageProtocol.transfer(message);
		messageProtocol.close();

		String deliveryConfirmationText = "Message: " + sendable.getContent() + " transported via " + sendable.getMessageType();
		logger.info(deliveryConfirmationText);
		
		DeliveryReport deliveryReport = new DeliveryReport();
		deliveryReport.setConfirmationText(deliveryConfirmationText);
		deliveryReport.setDeliveryDate(LocalDate.now());
		deliveryReport.setDeliverySuccessful(true);
		
		eventTopic = "de/leuphana/cosa/sendableEvent";
		eventProperties.put("deliveryReport", deliveryReport);
		
		SendableEvent sendableEvent = new SendableEvent(eventTopic, eventProperties);
		
		eventAdmin.postEvent(sendableEvent);
		logger.info("sendableEvent occoured");

		return deliveryReport;
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
	public String getCommandServiceName() {
		return MessagingCommandService.class.getName();
	}

	@Override
	public String getEventServiceName() {
		return SendableEventService.class.getName();
	}

	@Override
	public String getCommandServicePath() {
		return MessagingCommandService.class.getPackageName();
	}

	@Override
	public String getEventServicePath() {
		return SendableEventService.class.getPackageName();
	}

	@Override
	public String getComponentName() {
		return "MessagingSystem";
	}

	@Override
	public void addSendableEventListener(SendableEventListener sendableEventListener) {
		super.register(sendableEventListener);
	}

	@Override
	public void removeSendableEventListener(SendableEventListener sendableEventListener) {
		super.unregister(sendableEventListener);
	}
}
