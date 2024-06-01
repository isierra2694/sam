import java.util.Random;

public class Sensor {
    private String type;
    private double data;
    private double delta;
    private double defaultVal;

    public Sensor(String type, double data, double delta, double defaultVal) {
        this.type = type;
        this.data = data;
        this.delta = delta;
        this.defaultVal = defaultVal;
    }

    public String getType() {
        return type;
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

	// update()
	// Updates the data variable with a random value based on delta.
	public void update() {
		Random rand = new Random();
		double randDouble = rand.nextDouble();

		double x = delta * randDouble;
		data -= x;
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
        return new Sensor(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
    }

    // toString()
    // Converts the Sensor to a string so it can be written in the aquarium file.
	public String toString() {
        return type + "," + Double.toString(data) + "," + Double.toString(delta) + "," + Double.toString(defaultVal);
    }
}
