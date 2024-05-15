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
		add(titleLabel, BorderLayout.NORTH);
		
		sensorGrid = new JPanel(new GridLayout(0,3));
		SensorReceiver testSensor = new SensorReceiver("test", "/home/indy/Repositories/sam/test.txt");
		SensorReceiver testSensor2 = new SensorReceiver("test2", "/home/indy/Repositories/sam/test.txt");
		sensorGrid.add(testSensor);
		sensorGrid.add(testSensor2);

		add(sensorGrid, BorderLayout.NORTH);
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
