package securitysystemconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import lightcontrolproducer.LightControlService;
import securitysystemproducer.SecuritySystemService;

public class Activator implements BundleActivator {
	
	private SecuritySystemService securitySystemService;
	private Scanner scanner;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting Security System Consumer...");
		
		ServiceReference securitySystemServiceReference = bundleContext
				.getServiceReference(SecuritySystemService.class.getName());
		securitySystemService = (SecuritySystemService) bundleContext.getService(securitySystemServiceReference);
		
		
		scanner = new Scanner(System.in);
		runConsumer();

	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping Security System Consumer...");
	}
	
	
	private void runConsumer() {
		System.out.println("Welcome to Home Control and Security System");

		while (true) {
			System.out.println("\nOptions:");
			System.out.println("1. Security System Control");
			System.out.println("2. Exit");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

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
		System.out.println("\nSecurity System Control:");
		System.out.println("1. Arm security system");
		System.out.println("2. Disarm security system");
		System.out.println("3. Get security system status");
		System.out.println("4. Add security camera");
		System.out.println("5. Add a Door");
		System.out.println("6. Open a Door");
		System.out.println("7. Close a Door");
		System.out.println("8. Door Status");

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
			System.out.print("Enter Door ID: ");
			String doorId = scanner.next();
			System.out.print("Enter Camera ID: ");
			String cameraId1 = scanner.next();
			securitySystemService.addDoor(doorId, cameraId1);
			break;
		case 6:
			System.out.println("Enter Camera ID to open the door");
			String cameraId2 = scanner.next();
			securitySystemService.openDoorByCameraId(cameraId2);
			break;
		case 7:
			System.out.println("Enter Camera ID to close the door");
			String cameraId3 = scanner.next();
			securitySystemService.closeDoorByCameraId(cameraId3);
			break;
		case 8:
			System.out.println("Enter Door ID to check Status");
			String doorId1 = scanner.next();
			securitySystemService.getDoorStatus(doorId1);
			break;
		default:
			System.out.println("Invalid choice");
		}
	}

	


}
