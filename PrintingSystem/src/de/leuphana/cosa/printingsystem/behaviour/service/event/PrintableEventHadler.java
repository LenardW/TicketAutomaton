package de.leuphana.cosa.printingsystem.behaviour.service.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;

public class PrintableEventHadler implements EventHandler{
	
	private PrintingCommandService printingCommandService;
		
	public PrintableEventHadler(PrintingCommandService printingCommandService) {
		this.printingCommandService = printingCommandService;
	}

	
	@Override
	public void handleEvent(Event event) {
	
		if (event.getTopic().equals("de/leuphana/cosa/document/documentCreated")) {	
			Printable printable = new Printable() {
				
				@Override
				public String getTitle() {
					return (String) event.getProperty("title");
				}
				
				@Override
				public String getContent() {
					return (String) event.getProperty("text");
				}
			};
			
			PrintConfiguration printConfiguration = new PrintConfiguration() {
				String printFormat;

				@Override
				public void setPrintFormat(String printFormat) {
					this.printFormat = printFormat;
				}

				@Override
				public String getPrintFormat() {
					return printFormat;
				}
			};
			
			List<String> printFormats = new ArrayList<>(printingCommandService.getSupportedPrintFormats());
			System.out.println("Druckformat wählen");
			printFormats.forEach((v) -> {
				System.out.println(printFormats.indexOf(v) + ": " + v);
			});
			Scanner scanner = new Scanner(System.in);
			Integer dataInput = scanner.nextInt();

			printConfiguration.setPrintFormat(printFormats.get(dataInput));
			
		}
	}

}
