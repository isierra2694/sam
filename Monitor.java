import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Monitor extends JPanel {
	private static final int REFRESH_INTERVAL = 5;
	
	private SensorReceiver[] sensors;
	
	private JPanel sensorGrid;
	private JLabel titleLabel;
	
	// Monitor()
	// Monitor displays all data points.
	public Monitor() {
		setLayout(new BorderLayout());
		initComponents();
		initRefresher();
	}
	
	private void initComponents() {
		titleLabel = new JLabel("Monitor");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(titleLabel, BorderLayout.NORTH);
		
		sensorGrid = new JPanel(new GridLayout(0,3));
		SensorReceiver testSensor = new SensorReceiver("test", "test.txt");
		SensorReceiver testSensor2 = new SensorReceiver("test2", "test2.txt");
		sensorGrid.add(testSensor);
		sensorGrid.add(testSensor2);

		add(sensorGrid, BorderLayout.CENTER);
	}
	
	// initRefresher()
	// Starts the main refresh loop.  Will refresh every sensor after REFRESH_INTERVAL.
	private void initRefresher() {
		Timer timer = new Timer(REFRESH_INTERVAL * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loop over every active sensor and call readSensorFile()
			}
		});
		timer.start();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 100);
	}
}
