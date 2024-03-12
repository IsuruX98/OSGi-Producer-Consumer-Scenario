package models;

public class Door {
    private String doorId;
    private boolean isDoorOpen;
    private Camera doorCamera;


    public Door(String doorId, Camera doorCamera) {
        this.doorId = doorId;
        this.isDoorOpen = false;
        this.setDoorCamera(doorCamera);
    }


    public void open() {
        if (!isDoorOpen) {
            System.out.println("Opening the door with ID: " + doorId);
            isDoorOpen = true;
        } else {
            System.out.println("The door with ID " + doorId + " is already open.");
        }
    }


    public void close() {
        if (isDoorOpen) {
            System.out.println("Closing the door with ID: " + doorId);
            isDoorOpen = false;
        } else {
            System.out.println("The door with ID " + doorId + " is already closed.");
        }
    }

    public void checkStatus() {
        System.out.println("The door with ID " + doorId + " is " + (isDoorOpen ? "open" : "closed"));
    }


    public String getDoorId() {
        return doorId;
    }


    public boolean isDoorOpen() {
        return isDoorOpen;
    }


	public Camera getDoorCamera() {
		return doorCamera;
	}


	public void setDoorCamera(Camera doorCamera) {
		this.doorCamera = doorCamera;
	}
		
}
