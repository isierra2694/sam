import javax.swing.*;


public class Main {
	private Monitor monitor = new Monitor();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createAndShowGUI());		
	}
	
	private static void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
	   	catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
    		e.printStackTrace();
		}
    	catch (IllegalAccessException e) {
    		e.printStackTrace();
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("SAM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Monitor monitor = new Monitor();
		Aquarium aquarium = new Aquarium();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, aquarium, monitor);
		splitPane.setResizeWeight(0.5);
		frame.add(splitPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
