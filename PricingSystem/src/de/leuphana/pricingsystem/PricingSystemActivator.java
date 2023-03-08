package de.leuphana.pricingsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PricingSystemActivator implements BundleActivator{

	private Logger logger;
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		PricingSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PricingSystem started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		PricingSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PricingSystem stopped");
	}
}
