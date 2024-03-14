package smartappcontrolproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	
	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting Smart App Control Service...");
		
		SmartAppControlSystem smartAppControlSystem = new SmartAppControlSystemImpl();
		serviceRegistration = bundleContext.registerService(SmartAppControlSystem.class.getName(), smartAppControlSystem, null);
		
		System.out.println("Smart App Control Service started.");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping Smart App Control Service...");

        serviceRegistration.unregister();

        System.out.println("Smart App Control Service stopped.");
	}

}
