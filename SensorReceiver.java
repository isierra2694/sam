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

	private Sensor sensor;
	
	// SensorReceiver(<Sensor object>)
	// SensorReceiver takes data from a Sensor file and displays them in a Sensor on the Monitor.
	public SensorReceiver(Sensor sensor) {
		this.sensor = sensor;

		setLayout(new FlowLayout());
		initComponents();
		initReader();
		
		Logger.logText("Created new sensor of type " + sensor.getType());
	}
	
	// readSensor()
	// Gets data from sensor and sets content in data label.
	public void readSensor() {
		String content = sensor.getData();
		dataLabel.setText(content);
	}
	
	// initReader()
	// Called when we load the sensor for the first time so we can display initial data
	// in the sensor's data label.
	private void initReader() {
		readSensor();
	}

	private void initComponents() {
		titleLabel = new JLabel(sensor.getType());
		dataLabel = new JLabel();

		add(titleLabel);
		add(dataLabel);
	}

}
