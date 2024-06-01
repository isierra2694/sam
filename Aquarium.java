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
		buttonsPanel.add(createButton("Temperature", 24, 2, 24));
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	private void initSensors() {
		try (BufferedReader reader = new BufferedReader(new FileReader("sensors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Sensor newSensor = Sensor.fromString(line);
				sensors.add(newSensor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void initRefresher() {
		Timer timer = new Timer(UPDATE_INTERVAL * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loop over each Sensor and call update()
				for (Sensor sensor : sensors) {
					sensor.update();
					writeSensorDataToFile(sensor);
				}
			}
		});
		
		timer.start();
	}

	private JButton createButton(String type, double data, double delta, double defaultVal) {
		JButton newButton = new JButton("Add " + type);
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createSensor(type, data, delta, defaultVal);
				buttonsPanel.remove(newButton);
				buttonsPanel.revalidate();
				buttonsPanel.repaint();
			}
		});

		return newButton;
	}

	private void createSensor(String type, double data, double delta, double defaultVal) {
		// add image to aquarium whene we create a sensor?
		Sensor newSensor = new Sensor(type, data, delta, defaultVal);
		Monitor.registerSensor(newSensor);
		sensors.add(newSensor);
	}

	public void writeSensorDataToFile(Sensor sensor) {

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 100);
	}
}
