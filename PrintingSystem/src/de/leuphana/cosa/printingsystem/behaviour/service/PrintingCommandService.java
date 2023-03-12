package de.leuphana.cosa.printingsystem.behaviour.service;

import java.util.Set;

import de.leuphana.cosa.printingsystem.behaviour.service.exceptions.UnsupportedPrintFormatException;

public interface PrintingCommandService  {

	// Signature
	PrintReport printDocument(Printable printable, PrintConfiguration printConfiguration) throws UnsupportedPrintFormatException, Exception;
	
	Set<String> getSupportedPrintFormats();

}