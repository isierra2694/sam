import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	public static void createLogger() {
		// Create the log.txt file if it doesn't exist
		try {
			File logTxt = new File(Main.getDataDirectory().resolve("log.txt").toString());
			if (!logTxt.createNewFile()) {
				logTxt.delete();
				logTxt.createNewFile();
			}
			logText("Starting logging");
		}
		catch (IOException e) {
			System.out.println("An error occurred while creating the logger.");
			e.printStackTrace();
		}
	}
	
	// logText(String message)
	// Logs an INFO line to the log file with the supplied message.
	public static void logText(String message) {
		log("INFO | " + message);
	}
	
	// logWarning(String message)
	// Logs a WARN line to the log file with the supplied message.
	public static void logWarning(String message) {
		log("WARN | " + message);
	}
	
	// logError(String message)
	// Logs an ERROR line to the log file with the supplied message.
	public static void logError(String message) {
		log("ERROR | " + message);
	}

	// logError(String message, String trace)
	// Logs an ERROR line to the log file with the supplied message.
	// Also logs the stack trace after the logged error.
	public static void logError(String message, String trace) {
		log("ERROR | " + message);
		log("[begin stack trace]");
		log(trace);
	}
	
	// log(String message)
	// Only to be used in the Logger class.
	// Logs the supplied message to the log file.  Actually writes the message to the log file.
	private static void log(String message) {
		try {
			FileWriter writer = new FileWriter(Main.getDataDirectory().resolve("log.txt").toString(), true);
			writer.write(new java.util.Date() + " | " + message + "\n");
			writer.close();
		}
		catch (IOException e) {
			System.out.println("An error occured while logging.");
			e.printStackTrace();
		}
	}
}
