import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * 
 * This class represent the mushrooms in the game
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class Mushroom {
	
	public final Dimension DEFAULT_SIZE = new Dimension(30, 30);
	
	protected Point point;
	public Rectangle Mushroom;
	public Image picture;
	public int damagepoints = 0;
	
	protected boolean isPoisoned = false;
	public boolean isDead = false;
	public boolean flipped = true;
	
	public JPanel panel;

	public Mushroom(int x, int y) {
		this.point = new Point(x, y);
		this.Mushroom = new Rectangle(this.point, this.DEFAULT_SIZE);
		this.picture = new Image("images\\Centipede_mushroom_damage" + this.damagepoints + ".png");
	}

	/**
	 * 
	 * This method draws the mushroom in the game panel.
	 *
	 * @param panel
	 */
	public void draw(JPanel panel) {
		this.panel = panel;
		this.panel.add(this.picture);
		this.picture.setSize(this.DEFAULT_SIZE);
		this.picture.setLocation(this.point);
		this.Mushroom.setLocation(this.point);
		this.picture.setBackground(null);
	}

	/**
	 * 
	 * Returns the rectangle box of the current mushroom
	 *
	 * @return
	 */
	public Rectangle getSize() {
		return this.Mushroom;
	}

	/**
	 * 
	 * This method updates the damage points for the mushroom
	 * and its picture as well given the remainder amount of 
	 * damage points.
	 *
	 */
	public void damage() {
		this.picture.setVisible(false);
		this.damagepoints++;

		if (this.damagepoints == 4) {

			this.isDead = true;
			this.picture.setVisible(false);
			return;
		}
		this.picture = new Image("images\\Centipede_mushroom_damage" + this.damagepoints + ".png");

		this.draw(this.panel);
		this.picture.setVisible(true);
	}

	/**
	 * 
	 * When the mushroom dies, this method removes it from the game.
	 *
	 */
	public void kill() {
		this.panel.remove(this.picture);
	}

	/**
	 * 
	 * Returns whether this mushroom is dead.
	 *
	 * @return isDead
	 */
	public Boolean isDead() {
		return this.isDead;
	}

	/**
	 * 
	 * Returns whether this mushroom is poisoned.
	 *
	 * @return isPoisoned
	 */
	public boolean getPoisoned() {
		return this.isPoisoned;
	}

	/**
	 * 
	 * Returns the center point of this mushroom
	 *
	 * @return point
	 */
	public Point getPoint() {
		return this.point;
	}

	/**
	 * 
	 * Returns whether this mushroom is flipped
	 *
	 * @return flipped
	 */
	public boolean isFlipped() {
		return this.flipped;
	}

}
