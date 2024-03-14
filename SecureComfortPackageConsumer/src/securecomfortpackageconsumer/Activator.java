package securecomfortpackageconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import securitysystemproducer.SecuritySystemService;
import thermistorcontrolproducer.ThermistorControlService;

public class Activator implements BundleActivator {
	
	private SecuritySystemService securitySystemService; 
	private ThermistorControlService thermistorService;
	private Scanner scanner;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting Security System Consumer...");
		
		ServiceReference securitySystemServiceReference = bundleContext
				.getServiceReference(SecuritySystemService.class.getName());
		securitySystemService = (SecuritySystemService) bundleContext.getService(securitySystemServiceReference);
		
		ServiceReference thermistorServiceReference = bundleContext
				.getServiceReference(ThermistorControlService.class.getName());
		thermistorService = (ThermistorControlService) bundleContext.getService(thermistorServiceReference);
		
		
		scanner = new Scanner(System.in);
		runConsumer();

	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping Security System Consumer...");
	}
	
	
	private void runConsumer() {
		System.out.println("Welcome to Security and Thermostat Control System");
		System.out.println("");

		while (true) {
			System.out.println("\nOptions:");
			System.out.println("1. Security System and Thermostat Control");
			System.out.println("2. Exit");

			System.out.print("Enter your choice: ");
		    int choice = scanner.nextInt();
		    System.out.print("");

			switch (choice) {

			case 1:
				runSecuritySystemOptions();
				break;
			case 2:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
	}
	

	private void runSecuritySystemOptions() {
		System.out.println("\nSecurity System and Thermostat Controls:");
		System.out.println("1. Arm security system");
		System.out.println("2. Disarm security system");
		System.out.println("3. Get security system status");
		System.out.println("4. Add security camera");
		System.out.println("5. Add a Door");
		System.out.println("6. Open a Door");
		System.out.println("7. Close a Door");
		System.out.println("8. Door Status");
		System.out.println("9, Add Thermal Components"); 
		System.out.println("10, Remove Thermal Components"); 
		System.out.println("11, Change Component status"); 
		System.out.println("12, Get Components Info"); 
		

		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			securitySystemService.armSecuritySystem();
			System.out.print("Security system is armed.");
			break;
		case 2:
			securitySystemService.disarmSecuritySystem();
			System.out.print("Security system is disarmed.");
			break;
		case 3:
			String securityStatus = securitySystemService.getSecurityStatus();
			System.out.print("Security system status: " + securityStatus);
			break;
		case 4:
			System.out.print("Enter camera ID: ");
		    String cameraId = scanner.next();
		    securitySystemService.addSecurityCamera(cameraId);
		    System.out.print("Camera " + cameraId + " added.");
		    break;
		case 5:
			System.out.print("Enter Door ID: ");
			String doorId = scanner.next();
			System.out.print("Enter Camera ID: ");
			String cameraId1 = scanner.next();
			securitySystemService.addDoor(doorId, cameraId1);
			break;
		case 6:
			System.out.print("Enter Camera ID to open the door");
			String cameraId2 = scanner.next();
			securitySystemService.openDoorByCameraId(cameraId2);
			break;
		case 7:
			System.out.print("Enter Camera ID to close the door");
			String cameraId3 = scanner.next();
			securitySystemService.closeDoorByCameraId(cameraId3);
			break;
		case 8:
			System.out.print("Enter Door ID to check Status");
			String doorId1 = scanner.next();
			securitySystemService.getDoorStatus(doorId1);
			break;
		case 9:
			System.out.println("Add Component Name:");
			String compoName = scanner.nextLine();

			// Consume the newline character
			scanner.nextLine();

			System.out.println("Add Device Type:");
			String deviceType = scanner.nextLine();

			System.out.println("Location:");
			String location = scanner.nextLine();

			thermistorService.addComponent(compoName, deviceType, location);

			
			break;
		case 10:
			System.out.print("Add Component Name to Delete:");
			String compoName1 = scanner.next();
			thermistorService.removeComponent(compoName1);
			break;
		case 11:
			System.out.print("Add Component Name:");
			String compoName2 = scanner.next();
			System.out.print("Add Status to change:");
			String status = scanner.next();
			thermistorService.changeComponentMode(compoName2, status);
			break;
		case 12:
			thermistorService.getAllComponentsInfo();
			
		default:
			System.out.println("Invalid choice");
		}
	}

	


}