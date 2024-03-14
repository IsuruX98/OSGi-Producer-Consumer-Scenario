package thermistorcontrolproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;



public class Activator implements BundleActivator {
	
	 private ServiceRegistration<ThermistorControlService> serviceRegistration;

	    @Override
	    public void start(BundleContext bundleContext) {
	        // Create an instance of the ThermistorControlServiceImpl
	        ThermistorControlService thermistorControlService = new ThermistorControlServiceImpl();
	        
	        // Register the service with the bundle context
	        serviceRegistration = bundleContext.registerService(
	                ThermistorControlService.class, thermistorControlService, null);

	        System.out.println("Thermistor Control Producer started.");
	    }

	    @Override
	    public void stop(BundleContext bundleContext) {
	        // Unregister the service
	        serviceRegistration.unregister();
	        System.out.println("Thermistor Control Producer stopped.");
	    }

}
