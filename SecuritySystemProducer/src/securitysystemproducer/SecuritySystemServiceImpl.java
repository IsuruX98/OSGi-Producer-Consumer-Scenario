package securitysystemproducer;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SecuritySystemServiceImpl implements SecuritySystemService {
	private boolean armed;
    private List<Camera> securityCameras;
    private boolean alarmActivated;
    private Map<String, Timer> scheduleTimers;

    public SecuritySystemServiceImpl() {
        armed = false;
        securityCameras = new ArrayList<>();
        alarmActivated = false;
        scheduleTimers = new HashMap<>();
        
     // Add default cameras
        addDefaultCameras();
    }
    
    private void addDefaultCameras() {
        addSecurityCamera("Camera1");
        addSecurityCamera("Camera2");
        addSecurityCamera("Camera3");
        addSecurityCamera("Camera4");
    }

    @Override
    public void armSecuritySystem() {
        if (!armed) {
            armed = true;
            System.out.println("Security system armed");
            // Turn on cameras
            for (Camera camera : securityCameras) {
                camera.turnOn();
                System.out.println("Camera " + camera.getId() + " turned on");
            }
        } else {
            System.out.println("Security system is already armed");
        }
    }

    @Override
    public void disarmSecuritySystem() {
        if (armed) {
            armed = false;
            System.out.println("Security system disarmed");
            // Turn off cameras
            for (Camera camera : securityCameras) {
                camera.turnOff();
                System.out.println("Camera " + camera.getId() + " turned off");
            }
        } else {
            System.out.println("Security system is already disarmed");
        }
    }

    @Override
    public String getSecurityStatus() {
        return armed ? "Armed" : "Disarmed";
    }

    @Override
    public void addSecurityCamera(String cameraId) {
        if (!cameraExists(cameraId)) {
            Camera camera = new Camera(cameraId);
            securityCameras.add(camera);
//            System.out.println("Security camera " + cameraId + " added");
        } else {
//            System.out.println("Security camera " + cameraId + " already exists");
        }
    }

    @Override
    public void removeSecurityCamera(String cameraId) {
        for (Camera camera : securityCameras) {
            if (camera.getId().equals(cameraId)) {
                securityCameras.remove(camera);
                System.out.println("Security camera " + cameraId + " removed");
                return;
            }
        }
        System.out.println("Security camera " + cameraId + " not found");
    }

    @Override
    public void activateAlarm() {
        if (armed) {
            alarmActivated = true;
            System.out.println("Alarm activated");
        } else {
            System.out.println("Cannot activate alarm while system is disarmed");
        }
    }

    @Override
    public void scheduleArming(int hour, int minute) {
        scheduleAction(hour, minute, true);
    }

    @Override
    public void scheduleDisarming(int hour, int minute) {
        scheduleAction(hour, minute, false);
    }

    private void scheduleAction(int hour, int minute, boolean arm) {
        Timer timer = new Timer();
        LocalTime time = LocalTime.of(hour, minute);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (arm) {
                    armSecuritySystem();
                } else {
                    disarmSecuritySystem();
                }
                scheduleTimers.remove(timer);
            }
        }, time.getHour() * 3600 * 1000 + time.getMinute() * 60 * 1000);

        scheduleTimers.put(timer.toString(), timer);
        System.out.println("Scheduled " + (arm ? "arming" : "disarming") + " at " + time);
    }

    private boolean cameraExists(String cameraId) {
        for (Camera camera : securityCameras) {
            if (camera.getId().equals(cameraId)) {
                return true;
            }
        }
        return false;
    }

    private class Camera {
        private String id;
        private boolean on;

        public Camera(String id) {
            this.id = id;
            this.on = false;
        }

        public String getId() {
            return id;
        }

        public void turnOn() {
            on = true;
        }

        public void turnOff() {
            on = false;
        }
    }
}
