import java.util.Random;

public class Sensor {
    private String type;
    private String dataType;
	private double data;
    private double delta;
    private double defaultVal;
	private double userPreferredVal;
	private boolean isAutomated;

	private SensorReceiver receiver;

    public Sensor(String type, String dataType, double data, double delta, double defaultVal, double userPreferredVal, boolean isAutomated) {
        this.type = type;
		this.dataType = dataType;
        this.data = data;
        this.delta = delta;
        this.defaultVal = defaultVal;
		this.userPreferredVal = userPreferredVal;
		this.isAutomated = isAutomated;
    }

    public String getType() {
        return type;
    }
	
	public String getDataType() {
		return dataType;
	}

    public double getData() {
        return data;
    }

    public double getDelta() {
        return delta;
    }

    public double getDefaultVal() {
        return defaultVal;
    }

	public double getUserPreferredVal() {
		return userPreferredVal;
	}

	public boolean getIsAutomated() {
		return isAutomated;
	}

	public void setUserPreferredVal(double userPreferredVal) {
		this.userPreferredVal = userPreferredVal;
	}

	public void connectSensorReceiver(SensorReceiver receiver) {
		this.receiver = receiver;
	}

	// alert()
	// If this sensor isn't automated, send an alert to the sensor receiver to manually fix.
	public void alert() {
		receiver.alert();
	}

	// update()
	// Updates the data variable with a random value based on delta.
	public void update(boolean isIncrease) {
		Random rand = new Random();
		double randDouble = rand.nextDouble();

		double x = delta * randDouble;
		
		if (isIncrease) data += x;
		else data -= x; 
		
		Logger.logText(type + " sensor updated to " + Double.toString(data));
	}
	
	// reset()
	// Resets the data variable to the default value.
	public void reset() {
		data = defaultVal;
	}

    // fromString(String sensorString)
    // Parses a line (sensor data) from the aquarium file and creates a new Sensor object.
    public static Sensor fromString(String sensorString) {
        String[] parts = sensorString.split(",");
        return new Sensor(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Boolean.parseBoolean(parts[6]));
    }

    // toString()
    // Converts the Sensor to a string so it can be written in the aquarium file.
	public String toString() {
		return type + "," + dataType + "," + Double.toString(data) + "," + Double.toString(delta) + "," + Double.toString(defaultVal) + "," + Double.toString(userPreferredVal) + "," + Boolean.toString(isAutomated);
    }
}
