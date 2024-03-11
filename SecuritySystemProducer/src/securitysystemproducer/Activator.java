package securitysystemproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting Security System Service...");

        SecuritySystemService securitySystemService = new SecuritySystemServiceImpl();
        serviceRegistration = bundleContext.registerService(SecuritySystemService.class.getName(), securitySystemService, null);

        System.out.println("Security System Service started.");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopping Security System Service...");

        serviceRegistration.unregister();

        System.out.println("Security System Service stopped.");
    }

}
