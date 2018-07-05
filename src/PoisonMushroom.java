import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * This class represents a poisoned mushroom in the game
 * 
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class PoisonMushroom extends Mushroom {

	public PoisonMushroom(int x, int y) {
		super(x, y);
		this.isPoisoned = true;
		this.damagepoints = 0;
		this.point = new Point(x, y);
		this.Mushroom = new Rectangle(this.point, this.DEFAULT_SIZE);
		this.picture = new Image("images\\Centipede_poisonshroom_damage" + this.damagepoints + ".png");
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
		
		this.picture = new Image("images\\Centipede_poisonshroom_damage" + this.damagepoints + ".png");
		this.picture.setVisible(true);
		this.draw(this.panel);
	}
}
