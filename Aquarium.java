import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Aquarium extends JPanel {
	private JLabel titleLabel;

	public Aquarium() {
		setLayout(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		titleLabel = new JLabel("Aquarium");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(titleLabel, BorderLayout.NORTH);
		
		add(new AquariumPicture(), BorderLayout.CENTER);
	}

	private void initSensors() {
		Sensor[] sensors;
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

	// Aquarium should write changes to the aquarium file every X seconds.

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 100);
	}
}
