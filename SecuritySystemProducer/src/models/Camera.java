package models;

public class Camera {
	 private String cameraId;
     private boolean isCameraOn;

     public Camera(String cameraId) {
         this.cameraId = cameraId;
         this.isCameraOn = false;
     }

     public String getCameraId() {
         return cameraId;
     }

     public void turnOn() {
         isCameraOn = true;
     }

     public void turnOff() {
         isCameraOn = false;
     }
}
