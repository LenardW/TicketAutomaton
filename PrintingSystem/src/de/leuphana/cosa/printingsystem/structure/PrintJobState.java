package de.leuphana.cosa.printingsystem.structure;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;



public abstract class PrintJobState {
	private Logger logger;
	private LoggerFactory loggerFactory; // check why this works??
	
	public PrintJobState() {
		Logger logger = loggerFactory.getLogger(this.getClass());
		
		// System.out.println("Print job status - " + this.getClass().getSimpleName());
		logger.info("Print job status - " + this.getClass().getSimpleName());
	}
	
	protected abstract PrintJobState changePrintJobState(PrintJobAction printJobAction);
}
