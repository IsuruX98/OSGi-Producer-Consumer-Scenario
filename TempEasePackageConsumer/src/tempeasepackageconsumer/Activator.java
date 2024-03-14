package tempeasepackageconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import smartappcontrolproducer.SmartAppControlSystem;
import thermistorcontrolproducer.Device;
import thermistorcontrolproducer.ThermistorControlService;
import java.util.Map;
import java.util.Scanner;

public class Activator implements BundleActivator {

    private ThermistorControlService thermistorControlService;
    private SmartAppControlSystem smartAppControlSystem;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        // Get the service reference for ThermistorControlService
        ServiceReference<ThermistorControlService> thermistorControlServiceRef = bundleContext.getServiceReference(ThermistorControlService.class);
        if (thermistorControlServiceRef != null) {
            thermistorControlService = bundleContext.getService(thermistorControlServiceRef);
        } else {
            System.err.println("ThermistorControlService is unavailable.");
            return;
        }

        // Get the service reference for SmartAppControlSystem
        ServiceReference<SmartAppControlSystem> smartAppControlSystemRef = bundleContext.getServiceReference(SmartAppControlSystem.class);
        if (smartAppControlSystemRef != null) {
            smartAppControlSystem = bundleContext.getService(smartAppControlSystemRef);
        } else {
            System.err.println("SmartAppControlSystem is unavailable.");
            return;
        }

        // Start the command-line interface
        startCLI();
    }


    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopping Home Control Consumer...");
        bundleContext.ungetService(bundleContext.getServiceReference(ThermistorControlService.class));
        bundleContext.ungetService(bundleContext.getServiceReference(SmartAppControlSystem.class));
    }

    private void startCLI() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("");
            System.out.println("Home Control Regular Package");
            System.out.println("1. Thermistor Control");
            System.out.println("2. Smart App Control");
            System.out.println("3. Exit");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    startThermistorControlCLI();
                    break;
                case 2:
                    startSmartAppControlCLI();
                    break;
                case 3:
                    // Prompt for exit confirmation
                    System.out.print("Are you sure you want to exit? (yes/no): ");
                    String confirmExit = scanner.nextLine();
                    if (confirmExit.equalsIgnoreCase("yes")) {
                        exit = true;
                        System.out.println("Exiting Home Control Consumer...");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
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
                System.out.print("Status: " );
                if(selectedDeviceInfo.isStatus()) {
                	  System.out.println("On" );
                }
                else {
                	 System.out.println("Off" );
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