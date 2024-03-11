package homecontrol_lite_consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import securitysystemproducer.SecuritySystemService;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lightcontrolproducer.LightControlService;

public class Activator implements BundleActivator {

	private LightControlService lightControlService;
	private SecuritySystemService securitySystemService;
	private Scanner scanner;

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Home Control and Security System Consumer...");

		ServiceReference lightControlServiceReference = context
				.getServiceReference(LightControlService.class.getName());
		lightControlService = (LightControlService) context.getService(lightControlServiceReference);

		ServiceReference securitySystemServiceReference = context
				.getServiceReference(SecuritySystemService.class.getName());
		securitySystemService = (SecuritySystemService) context.getService(securitySystemServiceReference);

		scanner = new Scanner(System.in);
		runConsumer();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Home Control and Security System Consumer...");
	}

	private void runConsumer() {
		System.out.println("Welcome to Home Control and Security System");

		while (true) {
			System.out.println("\nOptions:");
			System.out.println("1. Light Control");
			System.out.println("2. Security System Control");
			System.out.println("3. Preset Management");
			System.out.println("4. Schedule Management");
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
				runPresetManagement();
				break;
			case 4:
				runScheduleManagement();
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
		default:
			System.out.println("Invalid choice");
		}
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

	private void runSecuritySystemOptions() {
		System.out.println("\nSecurity System Control:");
		System.out.println("1. Arm security system");
		System.out.println("2. Disarm security system");
		System.out.println("3. Get security system status");
		System.out.println("4. Add security camera");

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
		default:
			System.out.println("Invalid choice");
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

	private void runScheduleManagement() {
		System.out.println("\nSchedule Management:");
		System.out.println("1. Schedule arming");
		System.out.println("2. Schedule disarming");

		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			scheduleArming();
			break;
		case 2:
			scheduleDisarming();
			break;
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

}
