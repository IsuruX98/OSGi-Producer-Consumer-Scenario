package securitysystemproducer;

public interface SecuritySystemService {
	void armSecuritySystem();
    void disarmSecuritySystem();
    String getSecurityStatus();
    void addSecurityCamera(String cameraId);
    void removeSecurityCamera(String cameraId);
    void activateAlarm();
    void scheduleArming(int hour, int minute);
    void scheduleDisarming(int hour, int minute);
}
