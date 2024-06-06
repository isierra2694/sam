import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class SensorReceiver extends JPanel {
	private static final int INTERVAL_SECONDS = 5;

	private JLabel titleLabel;
	private JLabel dataLabel;
	private JButton optionsButton;

	private Sensor sensor;

	private LineGraphPanel graph;
	
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
		double data = sensor.getData();
		int scale = (int) Math.pow(10, 2);
		data = (double) Math.round(data * scale) / scale;
		if (sensor.getDataType() == "%") data *= 100;

		String content = Double.toString(data) + " "  + sensor.getDataType();
		dataLabel.setText(content);

		plot(data);
	}
	
	// alert()
	// Creates a button on the sensor receiver to simulate manually fixing the sensor.
	public void alert() {
			
	}

	private void plot(double data) {
		graph.addDataPoint(data);
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
		optionsButton = new JButton("Options");
		
		optionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createModal();
			}
		});

		add(titleLabel);
		add(dataLabel);
		add(optionsButton);
		
		graph = new LineGraphPanel();

		add(graph);
	}

	private void createModal() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame(sensor.getType() + " sensor options");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel("Options");
				JSpinner minValue = new JSpinner(new SpinnerNumberModel(sensor.getUserPreferredVal(), Integer.MIN_VALUE, Integer.MAX_VALUE, 1));
				JButton submitButton = new JButton("Submit");
				
				JPanel inputPanel = new JPanel();
				inputPanel.add(minValue);
				inputPanel.add(submitButton);

				panel.add(label, BorderLayout.NORTH);
				inputPanel.add(minValue, BorderLayout.CENTER);
				inputPanel.add(submitButton, BorderLayout.SOUTH);
				
				panel.add(inputPanel, BorderLayout.CENTER);
				frame.add(panel);
				
				frame.setPreferredSize(new Dimension(800, 600));

				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setResizable(false);
				
				submitButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int number = (Integer) minValue.getValue();
						sensor.setUserPreferredVal(number);
					}
				});
			}
		});
	}
}
