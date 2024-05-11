import javax.swing.*;

public class Main {
	private Monitor monitor = new Monitor();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createAndShowGUI());	
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("SAM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Monitor monitor = new Monitor();
		frame.setContentPane(monitor);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
