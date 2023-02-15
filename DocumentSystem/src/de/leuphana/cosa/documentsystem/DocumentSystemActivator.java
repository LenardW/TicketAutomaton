package de.leuphana.cosa.documentsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentSystemActivator implements BundleActivator{

	private Logger logger;
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		DocumentSystemActivator.context = bundleContext;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("DocumentSystemActivator started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		DocumentSystemActivator.context = null;
		logger = LoggerFactory.getLogger(this.getClass());
		logger.info("DocumentSystemActivator stopped");
	}
}
