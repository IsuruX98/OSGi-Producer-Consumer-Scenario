package smartappcontrolproducer;

import java.time.LocalDateTime;
import java.util.Map;

public interface SmartAppControlSystem {
	void turnOnAppliance(String applianceId);
    void turnOffAppliance(String applianceId);
    String getApplianceStatus(String applianceId);
//    void adjustApplianceSettings(String applianceId, Map<String, Integer> settings);
    void scheduleApplianceOperation(String applianceId, String hours);
}