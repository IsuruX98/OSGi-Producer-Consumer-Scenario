package lightcontrolproducer;

import java.util.List;
import java.util.Map;

public interface LightControlService {
	void turnOnLight(String lightId);
    void turnOffLight(String lightId);
    String getLightStatus();
    void dimLight(String lightId, int brightness);
    void changeLightColor(String lightId, String color);
    void activatePreset(String presetName);
    void addPreset(String presetName, Map<String, Object> preset);
    void addLight(String lightId, boolean status, int brightness, String color); // New method to add lights
    List<String> getAvailablePresets();
    Map<String, String> getAllLights();
}
