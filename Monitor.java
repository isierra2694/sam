import javax.swing.*;
import java.awt.*;

public class Monitor extends JPanel {
	private JLabel titleLabel;

	public Monitor() {
		setLayout(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		titleLabel = new JLabel("Monitor");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(titleLabel, BorderLayout.NORTH);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 100);
	}
}
