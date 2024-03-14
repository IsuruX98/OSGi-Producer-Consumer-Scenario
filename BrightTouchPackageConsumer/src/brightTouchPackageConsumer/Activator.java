package brightTouchPackageConsumer;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import smartappcontrolproducer.SmartAppControlSystem;

public class Activator implements BundleActivator {

	private SmartAppControlSystem smartAppControlSystem;
	private Scanner scanner;
	
	public void start(BundleContext context) throws Exception {
		 System.out.println("Starting Smart App Control Consumer...");

	        ServiceReference<SmartAppControlSystem> serviceReference = context.getServiceReference(SmartAppControlSystem.class);
	        smartAppControlSystem = context.getService(serviceReference);

	        scanner = new Scanner(System.in);
	        runConsumer();
	}

	public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart App Control Consumer...");
    }
	
	private void runConsumer() {
        System.out.println("Welcome to Smart App Control System");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Turn on/off an appliance");
            System.out.println("2. Get appliance status");
            System.out.println("3. Schedule appliance operation");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    turnOnOffAppliance();
                    break;
                case 2:
                    getApplianceStatus();
                    break;
//                case 3:
//                    adjustApplianceSettings();
//                    break;
                case 4:
                    scheduleApplianceOperation();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void turnOnOffAppliance() {
    	System.out.println("Appliance ID are Washing Machine , Refrigerators ,humidifier ");
        System.out.print("Enter appliance ID: ");
        String applianceId = scanner.next();
        System.out.print("Enter 'on' to turn on or 'off' to turn off: ");
        String action = scanner.next();
        if (action.equalsIgnoreCase("on")) {
            smartAppControlSystem.turnOnAppliance(applianceId);
        } else if (action.equalsIgnoreCase("off")) {
            smartAppControlSystem.turnOffAppliance(applianceId);
        } else {
            System.out.println("Invalid action");
        }
    }

    private void getApplianceStatus() {
    	System.out.println("Appliance ID are Washing Machine , Refrigerators ,humidifier ");
        System.out.print("Enter appliance ID: ");
        String applianceId = scanner.next();
        String status = smartAppControlSystem.getApplianceStatus(applianceId);
        System.out.println("Appliance status: " + status);
    }

//    private void adjustApplianceSettings() {
//        System.out.print("Enter appliance ID: ");
//        String applianceId = scanner.next();
//        
//        // Assuming settings are provided as key-value pairs, e.g., {"setting1": value1, "setting2": value2}
//        Map<String, Integer> settings = new HashMap<>();
//        settings.put("setting1", 100); // Replace value1 with the actual value
//        settings.put("setting2", 50); // Replace value2 with the actual value
//        
//        smartAppControlSystem.adjustApplianceSettings(applianceId, settings);
//        System.out.println("Appliance settings adjusted.");
//    }

    private void scheduleApplianceOperation() {
    	System.out.println("Appliance ID are Washing Machine , Refrigerators ,humidifier ");
        System.out.print("Enter appliance ID: ");
        String applianceId = scanner.next();
        
        System.out.print("Enter hour (0-23): ");
        String hour = scanner.next();
        
//        // Assuming boolean parameter isOn indicates whether the appliance should be turned on or off
//        boolean isOn = true; // Change to false if the appliance should be turned off
        
        smartAppControlSystem.scheduleApplianceOperation(applianceId, hour);
        System.out.println("Appliance operation scheduled.");
    }


}
