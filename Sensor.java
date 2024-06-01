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

    // fromString(String sensorString)
    // Parses a line (sensor data) from the aquarium file and creates a new Sensor object.
    public static Sensor fromString(String sensorString) {
        String[] parts = sensorString.split(",");
        return new Sensor(parts[0], parts[1], parts[2], parts[3]);
    }

    // toString()
    // Converts the Sensor to a string so it can be written in the aquarium file.
    public static String toString() {
        return type + "," + data.toString() + "," + delta.toString() + "," + defaultVal.toString();
    }
}
