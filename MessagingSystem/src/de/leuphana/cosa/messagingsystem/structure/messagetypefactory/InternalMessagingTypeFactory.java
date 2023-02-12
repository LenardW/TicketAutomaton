package de.leuphana.cosa.messagingsystem.structure.messagetypefactory;

import de.leuphana.cosa.messagingsystem.behaviour.service.MessageType;

public class InternalMessagingTypeFactory {
	
	private InternalMessagingTypeFactory() {
	}

	public static InternalMessageType createInternalMessageType(MessageType messageType) {
		InternalMessageType internalMessageType;

		switch (messageType) {
		case EMAIL: {
			internalMessageType = InternalMessageType.EMAIL;
			break;
		}
		case SMS: {
			internalMessageType = InternalMessageType.SMS;
			break;
		}
		default:
			// besser mit MessageTypeNotAllowedException
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}

		return internalMessageType;
	}

}
