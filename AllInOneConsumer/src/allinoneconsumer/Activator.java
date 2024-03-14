package allinoneconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import securitysystemproducer.SecuritySystemService;
import smartappcontrolproducer.SmartAppControlSystem;
import thermistorcontrolproducer.Device;
import thermistorcontrolproducer.ThermistorControlService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lightcontrolproducer.LightControlService;

public class Activator implements BundleActivator {

	private LightControlService lightControlService;
	private SecuritySystemService securitySystemService;
	private ThermistorControlService thermistorControlService;
	private SmartAppControlSystem smartAppControlSystem;
	private Scanner scanner;

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting All in One Service Consumer...");

		ServiceReference<LightControlService> lightControlServiceReference = context
				.getServiceReference(LightControlService.class);
		lightControlService = context.getService(lightControlServiceReference);

		ServiceReference<SecuritySystemService> securitySystemServiceReference = context
				.getServiceReference(SecuritySystemService.class);
		securitySystemService = context.getService(securitySystemServiceReference);

		// Get the service reference for ThermistorControlService
		ServiceReference<ThermistorControlService> thermistorControlServiceRef = context
				.getServiceReference(ThermistorControlService.class);
		if (thermistorControlServiceRef != null) {
			thermistorControlService = context.getService(thermistorControlServiceRef);
		} else {
			System.err.println("ThermistorControlService is unavailable.");
			return;
		}

		// Get the service reference for SmartAppControlSystem
		ServiceReference<SmartAppControlSystem> smartAppControlSystemRef = context
				.getServiceReference(SmartAppControlSystem.class);
		if (smartAppControlSystemRef != null) {
			smartAppControlSystem = context.getService(smartAppControlSystemRef);
		} else {
			System.err.println("SmartAppControlSystem is unavailable.");
			return;
		}

		scanner = new Scanner(System.in);
		runConsumer();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping All in One Service Consumer...");
	}

	private void runConsumer() {
		System.out.println("Welcome to All in One Service System");

		while (true) {
			System.out.println("\nOptions:");
			System.out.println("1. Light Control");
			System.out.println("2. Security System Control");
			System.out.println("3. Thermistor Control");
			System.out.println("4. Smart App Control");
			System.out.println("5. Exit");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				runLightControlOptions();
				break;
			case 2:
				runSecuritySystemOptions();
				break;
			case 3:
				startThermistorControlCLI();
				break;
			case 4:
				startSmartAppControlCLI();
				break;
			case 5:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	private void runLightControlOptions() {
        System.out.println("\nLight Control Options:");
        System.out.println("1. Turn on/off a light");
        System.out.println("2. Dim light");
        System.out.println("3. Change light color");
        System.out.println("4. Get Light Status");
        System.out.println("5. Preset Management");
        System.out.println("6. Add a Light");
        System.out.println("7. Back to Main Menu");

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
            case 7:
                return;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void addLight() {
        Map<String, String> allLights = lightControlService.getAllLights();
        if (allLights.isEmpty()) {
            System.out.println("No lights available.");
            return;
        }

        System.out.println("\nAvailable lights:");
        for (Map.Entry<String, String> entry : allLights.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // Consume newline character
        scanner.nextLine();

        System.out.print("\nEnter light Name (ID): ");
        String lightId = scanner.nextLine();
        if (allLights.containsKey(lightId)) {
            System.out.println("Light ID already exists.");
            return;
        }

        System.out.print("Enter initial status (on/off): ");
        String statusInput;
        while (true) {
            statusInput = scanner.nextLine().toLowerCase();
            if (statusInput.equals("on") || statusInput.equals("off")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'on' or 'off'.");
            }
        }
        boolean status = statusInput.equals("on");

        int brightness;
        do {
            System.out.print("Enter initial brightness (1-100): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            brightness = scanner.nextInt();
            if (brightness < 1 || brightness > 100) {
                System.out.println("Brightness must be between 1 and 100.");
            }
        } while (brightness < 1 || brightness > 100);

        scanner.nextLine(); // consume newline character
        System.out.print("Enter initial color: ");
        String color;
        while (true) {
            color = scanner.nextLine();
            if (color.matches(".*\\d+.*")) {
                System.out.println("Color cannot contain numbers. Please enter a valid color.");
            } else {
                break;
            }
        }

        lightControlService.addLight(lightId, status, brightness, color);
    }


    private void getLightStatus() {
        Map<String, String> lightStatuses = lightControlService.getAllLights();
        if (lightStatuses.isEmpty()) {
            System.out.println("No lights available.");
            return;
        }
        System.out.println("\nLight Statuses:");
        for (Map.Entry<String, String> entry : lightStatuses.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private void turnOnOffLight() {
        Map<String, String> allLights = lightControlService.getAllLights();
        if (allLights.isEmpty()) {
            System.out.println("No lights available.");
            return;
        }

        System.out.println("\nAvailable lights:");
        for (Map.Entry<String, String> entry : allLights.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
     // Consume newline character
        scanner.nextLine();

        System.out.print("\nEnter light ID: ");
        String lightId = scanner.nextLine();
        
        if (!allLights.containsKey(lightId)) {
            System.out.println("Invalid light ID.");
            return;
        }

        System.out.print("Enter 'on' to turn on or 'off' to turn off: ");
        String action = scanner.next().toLowerCase(); // Convert action to lowercase to handle 'ON' or 'OFF'
        if (!action.equals("on") && !action.equals("off")) {
            System.out.println("Invalid action. Please enter 'on' or 'off'.");
            return;
        }

        if (action.equals("on")) {
            lightControlService.turnOnLight(lightId);
        } else if (action.equals("off")) {
            lightControlService.turnOffLight(lightId);
        }
    }

    private void dimLight() {
        Map<String, String> allLights = lightControlService.getAllLights();
        if (allLights.isEmpty()) {
            System.out.println("No lights available.");
            return;
        }

        System.out.println("\nAvailable lights:");
        for (Map.Entry<String, String> entry : allLights.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        // Consume newline character
        scanner.nextLine();

        System.out.print("\nEnter light ID: ");
        String dimLightId = scanner.nextLine();
        if (!allLights.containsKey(dimLightId)) {
            System.out.println("Invalid light ID.");
            return;
        }

        int brightness;
        do {
            System.out.print("Enter brightness (1-100): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            brightness = scanner.nextInt();
            if (brightness < 1 || brightness > 100) {
                System.out.println("Brightness must be between 1 and 100.");
            }
        } while (brightness < 1 || brightness > 100);

        lightControlService.dimLight(dimLightId, brightness);
    }

    private void changeLightColor() {
        Map<String, String> allLights = lightControlService.getAllLights();
        if (allLights.isEmpty()) {
            System.out.println("No lights available.");
            return;
        }

        System.out.println("\nAvailable lights:");
        for (Map.Entry<String, String> entry : allLights.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
     // Consume newline character
        scanner.nextLine();

        System.out.print("\nEnter light ID: ");
        String changeColorLightId = scanner.nextLine();
        if (!allLights.containsKey(changeColorLightId)) {
            System.out.println("Invalid light ID.");
            return;
        }
        
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        if (color.matches(".*\\d+.*")) {
            System.out.println("Color cannot contain numbers. Please enter a valid color.");
            return;
        }

        lightControlService.changeLightColor(changeColorLightId, color);
    }
    
    private void runPresetManagement() {
        System.out.println("\nPreset Management:");
        System.out.println("1. Create preset");
        System.out.println("2. Activate preset");
        System.out.println("3. Back");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createPreset();
                break;
            case 2:
                activatePreset();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void createPreset() {
        // Display available presets
        List<String> availablePresets = lightControlService.getAvailablePresets();
        if (availablePresets.isEmpty()) {
            System.out.println("No presets available.");
            return;
        }

        System.out.println("\nAvailable presets:");
        for (int i = 0; i < availablePresets.size(); i++) {
            System.out.println((i + 1) + ". " + availablePresets.get(i));
        }

        // Consume newline character
        scanner.nextLine();

        System.out.print("\nEnter new preset name: ");
        String presetName = scanner.nextLine();

        // Check if preset already exists
        if (availablePresets.contains(presetName)) {
            System.out.println("Preset '" + presetName + "' already exists.");
            return;
        }

        Map<String, String> allLights = lightControlService.getAllLights();
        if (allLights.isEmpty()) {
            System.out.println("No lights available.");
            return;
        }

        System.out.println("\nAvailable lights:");
        for (Map.Entry<String, String> entry : allLights.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        // Prompt the user for light settings
        System.out.print("\nEnter new light name (ID): ");
        String lightId = scanner.next();
        
        System.out.print("Enter 'on' to turn on or 'off' to turn off: ");
        String statusInput = scanner.next();
        
        boolean status = statusInput.equalsIgnoreCase("on");
        int brightness;
        do {
            System.out.print("Enter initial brightness (1-100): ");
            brightness = scanner.nextInt();
        } while (brightness < 1 || brightness > 100);
        System.out.print("Enter color: ");
        scanner.nextLine(); // Consume newline character
        String color = scanner.nextLine();

        // Create preset map
        Map<String, Object> preset = Map.of("lightId", lightId, "status", status, "brightness", brightness, "color",
                color);

        // Add preset
        lightControlService.addPreset(presetName, preset);

        // Print status message
        System.out.println("Preset '" + presetName + "' created successfully.");
    }

    private void activatePreset() {
        // Display available presets
        List<String> availablePresets = lightControlService.getAvailablePresets();
        if (availablePresets.isEmpty()) {
            System.out.println("No presets available.");
            return;
        }

        System.out.println("\nAvailable presets:");
        for (int i = 0; i < availablePresets.size(); i++) {
            System.out.println((i + 1) + ". " + availablePresets.get(i));
        }

        System.out.print("\nEnter preset number to activate: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume invalid input
        }
        int presetNumber = scanner.nextInt();

        if (presetNumber >= 1 && presetNumber <= availablePresets.size()) {
            String presetName = availablePresets.get(presetNumber - 1);
            lightControlService.activatePreset(presetName);
        } else {
            System.out.println("Invalid preset number.");
        }
    }

    private void runSecuritySystemOptions() {
        System.out.println("\nSecurity System Control:");
        System.out.println("1. Arm security system");
        System.out.println("2. Disarm security system");
        System.out.println("3. Get security system status");
        System.out.println("4. Add security camera");
        System.out.println("5. Schedule Management");
        System.out.println("6. Add a Door");
        System.out.println("7. Open a Door");
        System.out.println("8. Close a Door");
        System.out.println("9. Door Status");
        System.out.println("10. Back to Main menu");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                securitySystemService.armSecuritySystem();
                System.out.println("Security system is armed.");
                break;
            case 2:
                securitySystemService.disarmSecuritySystem();
                System.out.println("Security system is disarmed.");
                break;
            case 3:
                String securityStatus = securitySystemService.getSecurityStatus();
                System.out.println("Security system status: " + securityStatus);
                break;
            case 4:
                System.out.print("Enter camera ID: ");
                String cameraId = scanner.next();
                securitySystemService.addSecurityCamera(cameraId);
                System.out.println("Camera " + cameraId + " added.");
                break;
            case 5:
                runScheduleManagement();
                break;
            case 6:
                System.out.print("Enter Door ID: ");
                String doorId = scanner.next();
                System.out.print("Enter Camera ID: ");
                String cameraId1 = scanner.next();
                securitySystemService.addDoor(doorId, cameraId1);
                break;
            case 7:
                System.out.println("Enter Camera ID to open the door");
                String cameraId2 = scanner.next();
                securitySystemService.openDoorByCameraId(cameraId2);
                break;
            case 8:
                System.out.println("Enter Camera ID to close the door");
                String cameraId3 = scanner.next();
                securitySystemService.closeDoorByCameraId(cameraId3);
                break;
            case 9:
                System.out.println("Enter Door ID to check Status");
                String doorId1 = scanner.next();
                securitySystemService.getDoorStatus(doorId1);
                break;
            case 10:
                return;
            default:
                System.out.println("Invalid choice");
        }
    }

    

    private void runScheduleManagement() {
        System.out.println("\nSchedule Management:");
        System.out.println("1. Schedule arming");
        System.out.println("2. Schedule disarming");
        System.out.println("3. Back");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                scheduleArming();
                break;
            case 2:
                scheduleDisarming();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void scheduleArming() {
        System.out.print("Enter the hour for arming (0-23): ");
        int hour = scanner.nextInt();
        System.out.print("Enter the minute for arming (0-59): ");
        int minute = scanner.nextInt();
        securitySystemService.scheduleArming(hour, minute);
        System.out.println("Arming scheduled for " + hour + ":" + minute);
    }

    private void scheduleDisarming() {
        System.out.print("Enter the hour for disarming (0-23): ");
        int hour = scanner.nextInt();
        System.out.print("Enter the minute for disarming (0-59): ");
        int minute = scanner.nextInt();
        securitySystemService.scheduleDisarming(hour, minute);
        System.out.println("Disarming scheduled for " + hour + ":" + minute);
    }

	private void startThermistorControlCLI() {
		Scanner scanner = new Scanner(System.in);

		boolean exit = false;
		while (!exit) {
			System.out.println("");
			System.out.println("Thermistor Control");
			System.out.println("1. Add Device");
			System.out.println("2. Remove Device");
			System.out.println("3. Change Device Status");
			System.out.println("4. Change Device Mode");
			System.out.println("5. View All Devices Info");
			System.out.println("6. Disable All Devices");
			System.out.println("7. Back to Main Menu");
			System.out.println("");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				addThermistorDevice(scanner);
				break;
			case 2:
				removeThermistorDevice(scanner);
				break;
			case 3:
				changeThermistorDeviceStatus(scanner);
				break;
			case 4:
				changeThermistorDeviceMode(scanner);
				break;
			case 5:
				viewAllThermistorDeviceInfo();
				break;
			case 6:
				thermistorControlService.disableAllDevices();
				System.out.println("Devices disabled!");
				break;
			case 7:
				return; // Back to main menu
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

		scanner.close();
	}

	private void addThermistorDevice(Scanner scanner) {
		System.out.print("Enter Device name: ");
		String componentName = scanner.nextLine();
		System.out.print("Enter device type (AC/Heater): ");
		String deviceType = scanner.nextLine();
		System.out.print("Enter device location: ");
		String location = scanner.nextLine();
		thermistorControlService.addComponent(componentName, deviceType, location);
		System.out.println("Device added successfully!");
	}

	private void removeThermistorDevice(Scanner scanner) {
		System.out.println("Available Devices:");
		Map<String, Device> allComponentsInfo = thermistorControlService.getAllComponentsInfo();
		String selectedDevice = showDeviceListAndSelect(allComponentsInfo, scanner);
		if (selectedDevice != null) {
			thermistorControlService.removeComponent(selectedDevice);
			System.out.println("Component removed successfully!");
		}
	}

	private void changeThermistorDeviceStatus(Scanner scanner) {
		System.out.println("Available Devices:");
		Map<String, Device> allComponentsInfo = thermistorControlService.getAllComponentsInfo();
		String selectedDevice = showDeviceListAndSelect(allComponentsInfo, scanner);
		if (selectedDevice != null) {
			System.out.print("Enter 1 for On or 0 for Off: ");
			int statusInput = scanner.nextInt();
			boolean status = (statusInput == 1);
			thermistorControlService.changeComponentStatus(selectedDevice, status);
			System.out.println(selectedDevice + " Component status changed successfully!");
		}
	}

	private void changeThermistorDeviceMode(Scanner scanner) {
		System.out.println("Available Devices:");
		Map<String, Device> allComponentsInfo = thermistorControlService.getAllComponentsInfo();
		String selectedDevice = showDeviceListAndSelect(allComponentsInfo, scanner);
		if (selectedDevice != null) {
			System.out.println("Select mode:");
			System.out.println("1. powersaving");
			System.out.println("2. comfortable");
			System.out.println("3. regular");
			System.out.print("Enter the number corresponding to the mode: ");
			int modeNumber = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			String mode;
			switch (modeNumber) {
			case 1:
				mode = "powersaving";
				break;
			case 2:
				mode = "comfortable";
				break;
			case 3:
				mode = "regular";
				break;
			default:
				System.out.println("Invalid mode number. Mode set to regular by default.");
				mode = "regular";
				break;
			}

			thermistorControlService.changeComponentMode(selectedDevice, mode);
			System.out.println("Device mode set to " + mode);
		}
	}

	void viewAllThermistorDeviceInfo() {
		System.out.println("All Components Information:");

		// Check if thermistorControlService is available
		if (thermistorControlService == null) {
			System.out.println("ThermistorControlService is unavailable.");
			return;
		}

		Map<String, Device> allComponentsInfo = thermistorControlService.getAllComponentsInfo();

		if (allComponentsInfo == null || allComponentsInfo.isEmpty()) {
			System.out.println("No devices available.");
		} else {
			String selectedDevice = showDeviceListAndSelect(allComponentsInfo, new Scanner(System.in));
			if (selectedDevice != null) {
				Device selectedDeviceInfo = allComponentsInfo.get(selectedDevice);
				System.out.println("Selected Device Details:");
				System.out.println("Component Name: " + selectedDevice);
				System.out.println("Device Type: " + selectedDeviceInfo.getDeviceType());
				System.out.println("Location: " + selectedDeviceInfo.getLocation());
				System.out.print("Status: ");
				if (selectedDeviceInfo.isStatus()) {
					System.out.println("On");
				} else {
					System.out.println("Off");
				}
				System.out.println("Mode: " + selectedDeviceInfo.getMode());
			}
		}
	}

	private void startSmartAppControlCLI() {
		Scanner scanner = new Scanner(System.in);

		boolean exit = false;
		while (!exit) {
			System.out.println("");
			System.out.println("Smart App Control");
			System.out.println("1. Turn On Appliance");
			System.out.println("2. Turn Off Appliance");
			System.out.println("3. Check Appliance Status");
			System.out.println("4. Adjust Appliance Settings");
			System.out.println("5. Schedule Appliance Operation");
			System.out.println("6. Back to main menu");
			System.out.println("");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				turnOnAppliance(scanner);
				break;
			case 2:
				turnOffAppliance(scanner);
				break;
			case 3:
				checkApplianceStatus(scanner);
				break;
			case 4:
				adjustApplianceSettings(scanner);
				break;
			case 5:
				scheduleApplianceOperation(scanner);
				break;
			case 6:
				return; // Back to main menu
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

		scanner.close();
	}

	private void turnOnAppliance(Scanner scanner) {
		System.out.print("Enter Appliance ID to turn on: ");
		String applianceId = scanner.nextLine();
		smartAppControlSystem.turnOnAppliance(applianceId);
	}

	private void turnOffAppliance(Scanner scanner) {
		System.out.print("Enter Appliance ID to turn off: ");
		String applianceId = scanner.nextLine();
		smartAppControlSystem.turnOffAppliance(applianceId);
	}

	private void checkApplianceStatus(Scanner scanner) {
		System.out.print("Enter Appliance ID to check status: ");
		String applianceId = scanner.nextLine();
		System.out.println(smartAppControlSystem.getApplianceStatus(applianceId));
	}

	private void adjustApplianceSettings(Scanner scanner) {
		// Logic to adjust appliance settings can be implemented here
		System.out.println("Functionality to adjust appliance settings not implemented in this version.");
	}

	private void scheduleApplianceOperation(Scanner scanner) {
		// Logic to schedule appliance operation can be implemented here
		System.out.println("Functionality to schedule appliance operation not implemented in this version.");
	}

	private String showDeviceListAndSelect(Map<String, Device> deviceMap, Scanner scanner) {
		System.out.println("Available Devices:");
		int i = 1;
		for (Map.Entry<String, Device> entry : deviceMap.entrySet()) {
			String componentName = entry.getKey();
			Device device = entry.getValue();
			System.out.println(i + ". " + componentName + " (" + device.getLocation() + ")");
			i++;
		}
		System.out.print("Enter the number corresponding to the device you want to select: ");
		int deviceNumber = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		if (deviceNumber < 1 || deviceNumber > deviceMap.size()) {
			System.out.println("Invalid device number. Please enter a valid number.");
			return null;
		}

		// Get the selected device name
		return (String) deviceMap.keySet().toArray()[deviceNumber - 1];
	}

}
