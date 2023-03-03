package de.leuphana.cosa.messagingsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagingSystemActivator implements BundleActivator{
	
	private Logger logger;
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		MessagingSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("MessagingSystem started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		MessagingSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("MessagingSystem stopped");
	}

}