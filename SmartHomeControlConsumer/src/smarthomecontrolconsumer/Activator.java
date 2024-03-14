package smarthomecontrolconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import thermistorcontrolproducer.ThermistorControlService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import java.util.Scanner;

public class Activator implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
        startMenu();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
    }

    private void startMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Smart Home Control Consumer Menu:");
            System.out.println("1. Regular Home Control");
            System.out.println("2. Lite Home Control");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    startRegularHomeControl();
                    break;
                case 2:
                    startLiteHomeControl();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void startRegularHomeControl() {
        // Get the bundle context of the homecontrol_regular_consumer bundle
        BundleContext homeControlRegularBundleContext = getContext().getBundle(0).getBundleContext();

        try {
            // Get the service reference for ThermistorControlService from homecontrol_regular_consumer bundle
            ThermistorControlService thermistorControlService = (ThermistorControlService) homeControlRegularBundleContext
                    .getService(homeControlRegularBundleContext.getServiceReference(ThermistorControlService.class.getName()));

            // Check if the service reference is not null
            if (thermistorControlService != null) {
               

                // Unget the service reference
                homeControlRegularBundleContext.ungetService(homeControlRegularBundleContext
                        .getServiceReference(ThermistorControlService.class.getName()));
            } else {
                System.out.println("ThermistorControlService not found in homecontrol_regular_consumer bundle.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startLiteHomeControl() {
        // Code to start lite home control bundle
    }
}
