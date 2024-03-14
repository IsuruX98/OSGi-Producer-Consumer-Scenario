package brightTouchPackageConsumer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lightcontrolproducer.LightControlService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import smartappcontrolproducer.SmartAppControlSystem;

public class Activator implements BundleActivator {

	private SmartAppControlSystem smartAppControlSystem;
	private LightControlService lightControlService;
	private Scanner scanner;
	
	public void start(BundleContext context) throws Exception {
		 System.out.println("Starting Smart App Control Consumer... ");

	        ServiceReference<SmartAppControlSystem> serviceReference = context.getServiceReference(SmartAppControlSystem.class);
	        smartAppControlSystem = context.getService(serviceReference);

	        ServiceReference<LightControlService> serviceReferencee1 = context.getServiceReference(LightControlService.class);
	        lightControlService = context.getService(serviceReferencee1);
	        
	        scanner = new Scanner(System.in);
	        runConsumer();
	}

	public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart App Control Consumer...");
    }
	
	private void runConsumer() {
        System.out.println("Welcome to Smart App Control System");
        
        while(true) {
        	System.out.println("Welcome to the Bright Touch Package you can control lights and Smart Devices in here");
        	System.out.println("1.Light Control");
        	System.out.println("2.Smart Appliance Device control");
        	System.out.println("Enter your choice :");
        	int choice =  scanner.nextInt();
        	
        	switch(choice) {
        		case 1 :  runLight(); break;
        		case 2 :  runSmartApp();break;
        		case 3 : System.out.println("Exiting...");
        		default : System.out.println("Invalid Choice Try again");
        	}
        	
        }
        
    }
	
	private void runSmartApp() {
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
                case 3:
                    scheduleApplianceOperation();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
	}
	
	private void runLight() {
		System.out.println("\nLight Control Options:");
		System.out.println("1. Turn on/off a light");
		System.out.println("2. Dim light");
		System.out.println("3. Change light color");
		System.out.println("4. Get Light Status");
		System.out.println("5. Preset Management");
		System.out.println("6. Add a Light");

		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			turnOnOffLight();
			break;
		case 2:
			dimLight();
			break;
		case 3:
			changeLightColor();
			break;
		case 4:
			getLightStatus();
            break;
		case 5:
			runPresetManagement();
			break;
		case 6:
            addLight(); 
            break;
		default:
			System.out.println("Invalid choice");
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
    
    private void addLight() {
	    System.out.print("Enter light ID: ");
	    String lightId = scanner.next();
	    System.out.print("Enter initial status (true/false): ");
	    boolean status = scanner.nextBoolean();
	    System.out.print("Enter initial brightness (0-100): ");
	    int brightness = scanner.nextInt();
	    System.out.print("Enter initial color: ");
	    String color = scanner.next();

	    lightControlService.addLight(lightId, status, brightness, color);
	    System.out.println("Light " + lightId + " added.");
	}
    
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
    
    
    private void turnOnOffLight() {
	    Map<String, String> allLights = lightControlService.getAllLights();
	    if (allLights.isEmpty()) {
	        System.out.println("No lights available.");
	        return;
	    }
	    
	    System.out.println("Available lights:");
	    for (Map.Entry<String, String> entry : allLights.entrySet()) {
	        System.out.println(entry.getKey() + " - " + entry.getValue());
	    }
	    
	    System.out.print("Enter light ID: ");
	    String lightId = scanner.next();
	    System.out.print("Enter 'on' to turn on or 'off' to turn off: ");
	    String action = scanner.next();
	    if (action.equalsIgnoreCase("on")) {
	        lightControlService.turnOnLight(lightId);
	    } else if (action.equalsIgnoreCase("off")) {
	        lightControlService.turnOffLight(lightId);
	    } else {
	        System.out.println("Invalid action");
	    }
	}

	private void dimLight() {
	    Map<String, String> allLights = lightControlService.getAllLights();
	    if (allLights.isEmpty()) {
	        System.out.println("No lights available.");
	        return;
	    }
	    
	    System.out.println("Available lights:");
	    for (Map.Entry<String, String> entry : allLights.entrySet()) {
	        System.out.println(entry.getKey() + " - " + entry.getValue());
	    }
	    
	    System.out.print("Enter light ID: ");
	    String dimLightId = scanner.next();
	    System.out.print("Enter brightness (0-100): ");
	    int brightness = scanner.nextInt();
	    lightControlService.dimLight(dimLightId, brightness);
	}

	private void changeLightColor() {
	    Map<String, String> allLights = lightControlService.getAllLights();
	    if (allLights.isEmpty()) {
	        System.out.println("No lights available.");
	        return;
	    }
	    
	    System.out.println("Available lights:");
	    for (Map.Entry<String, String> entry : allLights.entrySet()) {
	        System.out.println(entry.getKey() + " - " + entry.getValue());
	    }
	    
	    System.out.print("Enter light ID: ");
	    String changeColorLightId = scanner.next();
	    System.out.print("Enter color: ");
	    String color = scanner.next();
	    lightControlService.changeLightColor(changeColorLightId, color);
	}
	
	private void getLightStatus() {
        Map<String, String> lightStatuses = lightControlService.getAllLights();
        if (lightStatuses.isEmpty()) {
            System.out.println("No lights available.");
            return;
        }
        System.out.println("Light Statuses:");
        for (Map.Entry<String, String> entry : lightStatuses.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
	
	private void runPresetManagement() {
		System.out.println("\nPreset Management:");
		System.out.println("1. Create preset");
		System.out.println("2. Activate preset");

		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			createPreset();
			break;
		case 2:
			activatePreset();
			break;
		default:
			System.out.println("Invalid choice");
		}
	}
	
	private void createPreset() {
		System.out.print("Enter preset name: ");
		String presetName = scanner.next();

		// Prompt the user for light settings
		System.out.print("Enter light ID: ");
		String lightId = scanner.next();
		System.out.print("Enter 'on' to turn on or 'off' to turn off: ");
		String statusInput = scanner.next();
		boolean status = statusInput.equalsIgnoreCase("on");
		System.out.print("Enter brightness (0-100): ");
		int brightness = scanner.nextInt();
		System.out.print("Enter color: ");
		String color = scanner.next();

		// Create preset map
		Map<String, Object> preset = Map.of("lightId", lightId, "status", status, "brightness", brightness, "color",
				color);

		// Add preset
		lightControlService.addPreset(presetName, preset);
	}

	private void activatePreset() {
		// Display available presets
		List<String> availablePresets = lightControlService.getAvailablePresets();
		if (availablePresets.isEmpty()) {
			System.out.println("No presets available.");
			return;
		}

		System.out.println("Available presets:");
		for (int i = 0; i < availablePresets.size(); i++) {
			System.out.println((i + 1) + ". " + availablePresets.get(i));
		}

		System.out.print("Enter preset number to activate: ");
		int presetNumber = scanner.nextInt();
		if (presetNumber >= 1 && presetNumber <= availablePresets.size()) {
			String presetName = availablePresets.get(presetNumber - 1);
			lightControlService.activatePreset(presetName);
		} else {
			System.out.println("Invalid preset number.");
		}
	}


}