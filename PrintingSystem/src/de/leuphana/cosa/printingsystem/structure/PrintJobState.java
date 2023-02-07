package de.leuphana.cosa.printingsystem.structure;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public abstract class PrintJobState {
	private Logger logger;
	
	public PrintJobState() {
		logger = LoggerFactory.getLogger(this.getClass());
		
		// System.out.println("Print job status - " + this.getClass().getSimpleName());
		logger.info("Print job status - " + this.getClass().getSimpleName());
	}
	
	protected abstract PrintJobState changePrintJobState(PrintJobAction printJobAction);
}
