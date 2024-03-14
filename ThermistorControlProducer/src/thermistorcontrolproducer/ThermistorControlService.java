package thermistorcontrolproducer;



import java.util.Map;

public interface ThermistorControlService {

    void addComponent(String componentName, String deviceType, String location);

    void removeComponent(String componentName);

    void changeComponentStatus(String componentName, boolean status);

    void changeComponentMode(String componentName, String mode);

    double getCurrentTemperature();

    void autoAdjustMode();
    
    void disableAllDevices(); 

    void changeComponentTemperature(String componentName, double temperature);

    Map<String, Device> getAllComponentsInfo();
}