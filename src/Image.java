import java.awt.Graphics;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * Represent the Buffered Images in the game.
 *
 * @author Joshua Palamuttam Created May 05, 2016.
 */
@SuppressWarnings("serial")
public class Image extends JPanel {

	public BufferedImage image;

	public Image(String imagePath) {
		try {
			this.image = ImageIO.read(new File(imagePath));
		} catch (IOException x) {
			System.out.println("File path invalid" + imagePath);
		}

	}

	/**
	 * 
	 * Sets a new BufferedImage for the current Image object
	 *
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * This method paints the current image on the Graphics object
	 */
	@Override
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		g2.drawImage(this.image, 0, 0, null);
	}

}
