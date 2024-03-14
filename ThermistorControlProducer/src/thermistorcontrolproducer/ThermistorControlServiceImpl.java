package thermistorcontrolproducer;

import java.util.HashMap;
import java.util.Map;

public class ThermistorControlServiceImpl implements ThermistorControlService {

    private Map<String, Device> devices; // Map to store device information

    public ThermistorControlServiceImpl() {
        this.devices = new HashMap<>();
    }

    @Override
    public void addComponent(String componentName, String deviceType, String location) {
        Device device = new Device(componentName, deviceType, location);
        devices.put(componentName, device);
    }

    @Override
    public void removeComponent(String componentName) {
        devices.remove(componentName);
    }

    @Override
    public void changeComponentStatus(String componentName, boolean status) {
        if (devices.containsKey(componentName)) {
            devices.get(componentName).setStatus(status);
        }
    }

    @Override
    public void changeComponentMode(String componentName, String mode) {
        if (devices.containsKey(componentName)) {
            devices.get(componentName).setMode(mode);
        }
    }

    @Override
    public double getCurrentTemperature() {
        // This is a placeholder method. In a real system, this would retrieve the temperature from sensors.
        // For demonstration purposes, let's return a random temperature between 20.0 and 30.0.
        return 20.0 + Math.random() * 10.0;
    }
    @Override
    public void disableAllDevices() {
        for (Device device : devices.values()) {
            device.setStatus(false);
        }
    }


    @Override
    public void autoAdjustMode() {
        double currentTemperature = getCurrentTemperature();
        for (Device device : devices.values()) {
            String mode = device.getMode();
            // Adjust mode based on temperature
            if (currentTemperature > 28.0) {
                if (!mode.equals("power-saving")) {
                    device.setMode("power-saving");
                }
            } else if (currentTemperature < 22.0) {
                if (!mode.equals("comfortable")) {
                    device.setMode("comfortable");
                }
            } else {
                if (!mode.equals("regular")) {
                    device.setMode("regular");
                }
            }
        }
    }

    @Override
    public void changeComponentTemperature(String componentName, double temperature) {
        if (devices.containsKey(componentName)) {
            devices.get(componentName).setTemperature(temperature);
        }
    }

    @Override
    public Map<String, Device> getAllComponentsInfo() {
        return devices;
    }
}