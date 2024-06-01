import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Monitor extends JPanel {
	private static final int REFRESH_INTERVAL = 5;
	
	private static List<SensorReceiver> sensorReceivers;
	
	private static JPanel sensorGrid;
	private JLabel titleLabel;
	
	// Monitor()
	// Monitor displays all data points.
	public Monitor() {
		sensorReceivers = new ArrayList<SensorReceiver>();
		
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

		add(sensorGrid, BorderLayout.CENTER);
	}
	
	// initRefresher()
	// Starts the main refresh loop.  Will refresh every sensor after REFRESH_INTERVAL.
	private void initRefresher() {
		Timer timer = new Timer(REFRESH_INTERVAL * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loop over every active SensorReceiver and call readSensor()
				for (SensorReceiver sensorReceiver : sensorReceivers) {
					sensorReceiver.readSensor();
				}
			}
		});
		timer.start();
	}

	public static void registerSensor(Sensor sensor) {
		SensorReceiver newReceiver = new SensorReceiver(sensor);
		sensorReceivers.add(newReceiver);
		sensorGrid.add(newReceiver);
		
		sensorGrid.revalidate();
		sensorGrid.repaint();
		Logger.logText("Registered new sensor of type " + sensor.getType());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 100);
	}
}
