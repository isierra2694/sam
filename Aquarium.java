import javax.swing.*;
import java.awt.*;

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
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 100);
	}
}
