import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * 
 * This class represents a gun in the game
 *
 * @author josebaf. Created May 20, 2016.
 */
public class Gun {
	public Point point;
	public Rectangle bullet_rec;
	public Image picture;
	public final Dimension size = new Dimension(4, 25);
	int y;
	int x;

	public Gun(int x, int y, JPanel panel) {
		this.x = x;
		this.y = y;
		this.point = new Point(x, y);
		this.bullet_rec = new Rectangle(this.point, this.size);
		this.picture = new Image("images\\1_gun.png");
		this.picture.setBounds(new Rectangle(this.bullet_rec));
	}

	/**
	 * 
	 * This method implements the movement for the bullets.
	 *
	 */
	public void move() {
		this.y -= 8;
		this.point.move(this.x, this.y);
		this.bullet_rec.setLocation(this.point);
		this.picture.setLocation(this.point);
	}

	/**
	 * 
	 * This method draws the bullet on game panel
	 *
	 * @param panel
	 */
	public void draw(JPanel panel) {
		panel.add(this.picture);
		this.bullet_rec.setLocation(this.point);
		this.picture.setSize(this.size);
		this.picture.setLocation(this.point);
		this.picture.setBackground(Color.BLACK);
	}

	/**
	 * 
	 * This method returns the rectangle box of the current bullet.
	 *
	 * @return
	 */
	public Rectangle getSize() {
		return this.bullet_rec;
	}

	/**
	 * 
	 * This clears the bullet from the screen
	 *
	 */
	public void kill() {
		this.picture.setVisible(false);
	}

}
