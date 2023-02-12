package de.leuphana.cosa.messagingsystem.structure.communicationpartner;

public class EmailSender implements Sender {
	// später Role-Object-Pattern
	private String name;
	// TODO statt String später event. Address
	private String address;
	
	public EmailSender(String senderAddress) {
		this.address = senderAddress;
	}

}
