import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aquarium extends JPanel {
	private static final int UPDATE_INTERVAL = 5;

	private JLabel titleLabel;
	private JPanel buttonsPanel;
	
	private List<Sensor> sensors;

	public Aquarium() {
		sensors = new ArrayList<Sensor>();
		setLayout(new BorderLayout());
		initComponents();
		initRefresher();
	}

	private void initComponents() {
		titleLabel = new JLabel("Aquarium");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(titleLabel, BorderLayout.NORTH);
		
		add(new AquariumPicture(), BorderLayout.CENTER);	
		
		buttonsPanel = new JPanel(new FlowLayout());
		buttonsPanel.add(createButton("Temperature", "C", 24, 2, 24, 24, true));
		buttonsPanel.add(createButton("ph", "pH", 7.5, 0.5, 7.5, 7.5, true));
		buttonsPanel.add(createButton("Water Fullness", "%", 0.95, 0.02, 0.95, 0.95, false));
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	private void initSensors() {
		try (BufferedReader reader = new BufferedReader(new FileReader("sensors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Sensor newSensor = Sensor.fromString(line);
				sensors.add(newSensor);
            }
        } 
		catch (IOException e) {
			Logger.logError("An error occurred while reading the aquarium file");
            e.printStackTrace();
        }
	}

	public void initRefresher() {
		Timer timer = new Timer(UPDATE_INTERVAL * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loop over each Sensor and call update()
				for (Sensor sensor : sensors) {
					if (sensor.getUserPreferredVal() <= sensor.getData() || !sensor.getIsAutomated()) {
						sensor.update(false);
					}
					else {
						if (sensor.getIsAutomated()) sensor.update(true);
						//else sensor.alert();
					}
					writeSensorDataToFile(sensor);
				}
			}
		});
		
		timer.start();
	}

	private JButton createButton(String type, String dataType, double data, double delta, double defaultVal, double userPreferredVal, boolean isAutomated) {
		JButton newButton = new JButton("Add " + type);
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createSensor(type, dataType, data, delta, defaultVal, userPreferredVal, isAutomated);
				buttonsPanel.remove(newButton);
				buttonsPanel.revalidate();
				buttonsPanel.repaint();
			}
		});

		return newButton;
	}

	private void createSensor(String type, String dataType, double data, double delta, double defaultVal, double userPreferredVal, boolean isAutomated) {
		// add image to aquarium whene we create a sensor?
		Sensor newSensor = new Sensor(type, dataType, data, delta, defaultVal, userPreferredVal, isAutomated);
		Monitor.registerSensor(newSensor);
		sensors.add(newSensor);
	}

	public void writeSensorDataToFile(Sensor sensor) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("sensors.txt"))) {
			writer.write(sensor.toString());
			writer.newLine();
		}
		catch (IOException e) {
			Logger.logError("An error occured while writing the aquarium file");
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 100);
	}
}
