import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class SensorReceiver extends JPanel {
	private static final int INTERVAL_SECONDS = 5;

	private JLabel titleLabel;
	private JLabel dataLabel;

	private Path dataPath;

	private Timer timer;
	
	// SensorReceiver(<name of sensor>, <sensor.txt name>)
	// SensorReceiver reads sensor files and displays them in a Sensor on the Monitor.
	public SensorReceiver(String name, String data) {
		setLayout(new FlowLayout());
		initComponents(name, data);
		initReader();
		
		Logger.logText("Created new sensor of name " + name);
	}
	
	// readSensorFile()
	// Reads the sensor.txt file associated with this sensor and displays that
	// in the sensor's data label.
	public void readSensorFile() {
		try {
			String content = new String(Files.readAllBytes(dataPath));
			dataLabel.setText(content);
		}
		catch (IOException err) {
			Logger.logError("Error reading sensor file");
		}
	}
	
	// initReader()
	// Called when we load the sensor for the first time so we can display initial data
	// in the sensor's data label.
	private void initReader() {
		readSensorFile();
	}

	private void initComponents(String name, String data) {
		titleLabel = new JLabel(name);
		dataLabel = new JLabel();

		dataPath = Main.getDataDirectory().resolve(data);

		add(titleLabel);
		add(dataLabel);
	}

}
