package de.leuphana.cosa.printingsystem.structure;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Printer {
	private PrintFormat printFormat;
	
	private static Logger logger;
    static FileHandler fh;

	public Printer(PrintFormat printFormat) {
		logger = Logger.getLogger(Printer.class.getName());
		this.printFormat = printFormat;
	}

	public PrintFormat getPrintFormat() {
		return printFormat;
	}

	public boolean executePrintJob(PrintJob printJob) throws Exception {
		fh = new FileHandler("Ticketkaufinformation.log");
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter); 
		
        logger.info(printJob.getPrintable().getContent());
//		System.out.println(printJob.getPrintable().getContent());
		printJob.changePrintJobState(PrintJobAction.PRINT);

		return true;
	}
}
