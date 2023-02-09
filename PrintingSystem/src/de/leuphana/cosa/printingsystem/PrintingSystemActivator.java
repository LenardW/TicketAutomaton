package de.leuphana.cosa.printingsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class PrintingSystemActivator implements BundleActivator{
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		PrintingSystemActivator.context = bundleContext;
		System.out.println("PrintingSystem starting");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		PrintingSystemActivator.context = null;
	}

}
