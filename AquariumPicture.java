import javax.swing.*;
import java.awt.*;

public class AquariumPicture extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Enable antialiasing
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Set the color to blue
        g2d.setColor(Color.BLUE);
       	
	   	int rectWidth = this.getWidth() - 100;	
        // Draw a rectangle (x, y, width, height)
        g2d.fillRect(50, 50, rectWidth, 300);
	}
}
