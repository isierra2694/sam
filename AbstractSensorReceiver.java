import javax.swing.*;
import java.awt.*;

public abstract class SensorReceiver {
	private Sensor sensor;
	private JLabel sensorDataLabel;
	private JLabel sensorTitleLabel;

	public SensorReceiver() {
		setLayout(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		sensorTitleLabel = new JLabel();
	}
}
