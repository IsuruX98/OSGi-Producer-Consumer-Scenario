package thermistorcontrolproducer;

import java.util.Map;

public interface ThermistorControlService {

    // Method to add a new component
    void addComponent(String componentName, String deviceType, String location);

    // Method to remove a component
    void removeComponent(String componentName);

    // Method to change the status of a component
    void changeComponentStatus(String componentName, boolean status);

    // Method to change the mode of a component
    void changeComponentMode(String componentName, String mode);

    // Method to get the current temperature
    double getCurrentTemperature();

    // Method to automatically adjust component mode based on temperature
    void autoAdjustMode();
    
    void disableAllDevices(); 

    // Method to manually change the temperature of a component
    void changeComponentTemperature(String componentName, double temperature);
    

    // Method to retrieve information about all components
    Map<String, Device> getAllComponentsInfo();
}
