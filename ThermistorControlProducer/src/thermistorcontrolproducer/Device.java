package thermistorcontrolproducer;


public class Device {
    private String name;
    private String deviceType;
    private String mode;
    private boolean status;
    private double temperature;
    private String location;

    public Device(String name, String deviceType, String location) {
        this.name = name;
        this.deviceType = deviceType;
        this.mode = "regular";
        this.status = false;
        this.temperature = 0.0;
        this.location = location;
    }

	public void setStatus(boolean status2) {
			this.status=status2;
		
	}

	public void setMode(String mode2) {
		this.mode=mode2;
		
	}

	public String getMode() {
		return mode;
	}

	public void setTemperature(double temperature2) {
		this.temperature=temperature2;
		
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getLocation() {
		return this.location;
	}

	public boolean isStatus() {
		return this.status;
	}

	public String getTemperature() {
		return this.getTemperature();
	}

}
