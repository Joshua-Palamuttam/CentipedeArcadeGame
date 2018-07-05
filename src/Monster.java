import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * 
 * This class represents the monsters in general in the game
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class Monster {

	public Dimension size = new Dimension(70, 38);
	public Rectangle rectangle;
	public Image picture;
	public Point point = new Point(200, 500);

	public int x = 1;
	public int y = 1;

	public boolean dead = false;

	public GamePanel main;
	public JPanel panel;

	public Monster(GamePanel main, int x, int y) {
		this.main = main;
		this.picture = new Image("images\\Centipede_spider.png");
		this.picture.setBackground(Color.black);
		this.rectangle = new Rectangle();
		this.rectangle.setSize(this.size);
		this.point = new Point(x, y);
	}

	/**
	 * 
	 * Draws the Monster image, and corresponding rectangle.
	 *
	 */

	public void draw() {
		this.main.game_panel.add(this.picture);
		this.picture.setLocation(this.point);
		this.picture.setSize(this.size);
		this.rectangle.setLocation(this.point);
		this.picture.setVisible(true);
	}

	/**
	 * 
	 * Base movement of monster as spider movement
	 *
	 */
	public void move() {
		if (this.point.x <= 0) {
			this.x = this.x * -1;
			this.point.translate(this.x, 0);
		}
		if (this.point.x >= 750) {
			this.x = this.x * -1;
			this.point.translate(this.x, 0);
		}

		if (this.point.y >= 850) {
			this.y = this.y * -1;
			this.point.translate(this.y, 0);
		}
		if (this.point.y <= 0) {
			this.y = this.y * -1;
			this.point.translate(this.y, 0);
		}
		this.point.translate(this.x, this.y);
		this.picture.setLocation(this.point);
		this.rectangle.setLocation(this.point);
	}

	/**
	 * 
	 * Sets the visibility of the Monster to false, essentially killing the
	 * Monster
	 *
	 */

	public void kill() {
		this.picture.setVisible(false);
	}

	/**
	 * 
	 * Gets the rectangle of the Monster
	 *
	 * @return
	 */

	public Rectangle getRect() {
		return this.rectangle;
	}

	/**
	 * 
	 * Returns whether this monster is dead.
	 *
	 * @return
	 */
	public boolean isDead() {
		return this.dead;
	}

	/**
	 * 
	 * Checks if monster collides with bullet
	 *
	 * @param bullet
	 */

	public void checkHitBullet(ArrayList<Gun> bullet) {
		for (int i = 0; i < this.main.spaceship.bullets.size(); i++) {
			if (bullet.get(i).bullet_rec.intersects(this.rectangle)) {
				this.main.spiders.remove(this);
				this.main.spaceship.bullets.remove(i);
			}
		}

	}

	/**
	 * 
	 * Returns the center point of this monster.
	 *
	 * @return point
	 */
	public Point getPoint() {
		return this.point;
	}

	/**
	 * 
	 * Checks if monster collides with Mushrooms
	 *
	 */

	public void checkHitMushroom() {
		for (int i = 0; i < this.main.mushrooms.size(); i++) {
			if (this.rectangle.intersects(this.main.mushrooms.get(i).getSize())) {

				Random value = new Random();
				if (value.nextFloat() > 0.5) {
					this.x *= -1;
					this.y *= -1;
				} else {
					this.main.mushrooms.get(i).picture.setVisible(false); // getPicture();
					this.main.mushrooms.remove(i);
				}
				break;
			}

		}
	}

}
