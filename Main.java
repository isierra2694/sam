import javax.swing.*;
import java.nio.file.*;
import java.io.IOException;

public class Main {
	private static final String APP_NAME = "SAM";	

	public static void main(String[] args) {
		Logger.createLogger();
		Logger.logText("Starting application (" + APP_NAME + ") on OS " + System.getProperty("os.name"));
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
		JFrame frame = new JFrame(APP_NAME);
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
	
	// getDataDirectory()
	// Gets the data directory for SAM.  Cross-platform.
	// Returns: Path (the data directory)
	public static Path getDataDirectory() {
		try {		
			Path dataPath;
			// Get the os name
			String os = (System.getProperty("os.name")).toLowerCase();
			// Windows
			if (os.contains("win")) {
				dataPath = Paths.get(System.getenv("AppData"));
			}
			// macOS
			else if (os.contains("mac")) {
				dataPath = Paths.get(System.getProperty("user.home"), "Library", "Application Support");
			}
			// *nix
			else {
				dataPath = Paths.get(System.getProperty("user.home"), ".config");
			}
			
			Path appDataPath = dataPath.resolve(APP_NAME);
			// Create data path if it doesn't exist
			if (!Files.exists(appDataPath)) {
				Files.createDirectories(appDataPath);
			}

			return appDataPath;
		}
		catch (IOException e) {
			e.printStackTrace();

			return null;
		}
	}	
}
