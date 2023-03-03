package de.leuphana.routesystem;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RouteSystemActivator {

	
	private Logger logger;
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		RouteSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("RouteSystem started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		RouteSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("RouteSystem stopped");
	}
}
