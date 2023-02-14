package de.leuphana.cosa.printingsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PrintingSystemActivator implements BundleActivator{
	
	private Logger logger;
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		PrintingSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PrintingSystem started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		PrintingSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PrintingSystem stopped");
	}

}
