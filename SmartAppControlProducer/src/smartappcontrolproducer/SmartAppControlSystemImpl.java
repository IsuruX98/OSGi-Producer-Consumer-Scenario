package smartappcontrolproducer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SmartAppControlSystemImpl implements SmartAppControlSystem {

    private Map<String, Boolean> applianceStatus;

    public SmartAppControlSystemImpl() {
        applianceStatus = new HashMap<>();
        
        // Initialize the Smart Apps
        applianceStatus.put("Washing Machine", false);
        applianceStatus.put("Refrigerators", false);
        applianceStatus.put("humidifier", false);       
        
    }

    public void turnOnAppliance(String applianceId) {
        // Implement the logic to turn on the specified appliance
        applianceStatus.put(applianceId, true);
        System.out.println("Turning on appliance with ID: " + applianceId);
    }

    public void turnOffAppliance(String applianceId) {
        // Implement the logic to turn off the specified appliance
        applianceStatus.put(applianceId, false);
        System.out.println("Turning off appliance with ID: " + applianceId);
    }

    public String getApplianceStatus(String applianceId) {
        // Implement the logic to retrieve the status of the specified appliance
        Boolean status = applianceStatus.get(applianceId);
        if (status != null) {
            return "Status of appliance with ID " + applianceId + ": " + (status ? "On" : "Off");
        } else {
            return "Appliance with ID " + applianceId + " not found.";
        }
    }

//    public void adjustApplianceSettings(String applianceId, Map<String, Integer> settings) {
//        // Implement the logic to adjust the settings of the specified appliance
//        System.out.println("Adjusting settings of appliance with ID: " + applianceId);
//        // Example: Extract settings from the map and apply them to the appliance
//        for (Map.Entry<String, Integer> entry : settings.entrySet()) {
//            String setting = entry.getKey();
//            Integer value = entry.getValue();
//            // Apply the setting to the appliance
//            System.out.println("Setting " + setting + " to " + value + " for appliance with ID: " + applianceId);
//        }
//    }

    
    public void scheduleApplianceOperation(String applianceId, String hour) {
        // Implement the logic to schedule an operation for the specified appliance
        System.out.println("Scheduling operation for appliance with ID: " + applianceId);
        System.out.println("Operation scheduled for " + hour + " hour for appliance with ID: " + applianceId);
       
    }

}