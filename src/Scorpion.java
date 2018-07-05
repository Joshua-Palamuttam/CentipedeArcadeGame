import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 
 * This class represents a scorpion monster in the game
 *
 * @author josebaf. Created May 20, 2016.
 */
public class Scorpion extends Monster {

	public Scorpion(GamePanel main, int x, int y) {
		super(main, x, y);
		this.main = main;
		this.picture = new Image("images\\Centipede_scorpion.png");
		Random rand = new Random();
		if (rand.nextFloat() > 0.5) {
			this.x = 1;
			this.point = new Point(-100, (int) (rand.nextFloat() * 600));
		} else {
			this.x = -1;
			this.point = new Point(1000, (int) (rand.nextFloat() * 600));
		}

		this.picture.setBackground(Color.black);
		this.rectangle = new Rectangle();
		this.rectangle.setSize(this.size);
	}

	/**
	 * 
	 * Moves the Scorpion like an actual Scorpion
	 *
	 */
	public void move() {
		this.point.translate(this.x, 0);
		this.picture.setLocation(this.point);
		this.rectangle.setLocation(this.point);
	}

	/**
	 * Checks if the Scorpion, hits a mushroom, and changes the mushroom to
	 * poison
	 */
	public void checkHitMushroom() {
		for (int i = 0; i < this.main.mushrooms.size(); i++) {
			if (this.rectangle.intersects(this.main.mushrooms.get(i).getSize())
					&& !(this.main.mushrooms.get(i) instanceof PoisonMushroom)) {
				Point spawnPoint = this.main.mushrooms.get(i).getPoint();
				this.main.mushrooms.get(i).kill();
				this.main.mushrooms.remove(i);
				this.main.mushrooms.add(new PoisonMushroom(spawnPoint.x, spawnPoint.y));
				this.main.drawMushrooms();
			}
		}
	}
}
