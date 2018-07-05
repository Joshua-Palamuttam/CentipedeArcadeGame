import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * This class represents the spaceship the player uses in the game
 *
 * @author josebaf. Created May 20, 2016.
 */
public class Spaceship {

	public final Dimension size = new Dimension(30, 30);
	public Rectangle spaceship_rect;
	public Image picture;
	public Point point;

	public int x;
	public int y;
	public JPanel panel;

	public int health = 100;
	public ArrayList<Gun> bullets = new ArrayList<>();
	public ArrayList<Mushroom> mushrooms;

	public Spaceship(JPanel panel) {
		this.panel = panel;
		this.x = 375;
		this.y = 845;

		this.point = new Point(375, 845);
		this.spaceship_rect = new Rectangle(this.point, this.size);
		this.picture = new Image("images\\Centipede_blaster.png");
	}

	public Spaceship(JPanel panel, ArrayList<Mushroom> mushrooms) {
		this(panel);
		this.mushrooms = mushrooms;
	}

	/**
	 * 
	 * Draws the spaceship in the game panel
	 *
	 * @param panel
	 */
	public void draw(JPanel panel) {
		this.panel = panel;
		this.panel.add(this.picture);
		this.picture.setSize(this.size);
		this.picture.setLocation(this.point);
		this.spaceship_rect.setLocation(this.point);
		this.picture.setBackground(Color.black);

	}

	/**
	 * 
	 * Removes this spaceship from the game when this dies.
	 *
	 */
	public void kill() {
		this.panel.remove(this.picture);
	}

	/**
	 * 
	 * Moves the spaceship to the left
	 *
	 */
	public void moveLeft() {

		if (this.spaceship_rect.x >= 0) {
			if (this.x > 0)
				this.x -= 4;
		}
		this.panel.add(this.picture);
		this.point.move(this.x, this.y);
		this.spaceship_rect.setLocation(this.point);
		this.picture.setLocation(this.point);
	}

	/**
	 * 
	 * Moves the spaceship to the right
	 *
	 */
	public void moveRight() {
		if (this.spaceship_rect.x <= 950) {
			if (this.x < this.panel.getWidth() - this.picture.getWidth())
				this.x += 4;
		}
		this.panel.add(this.picture);
		this.point.move(this.x, this.y);
		this.spaceship_rect.setLocation(this.point);
		this.picture.setLocation(this.point);

	}

	/**
	 * 
	 * Moves the spaceship up
	 *
	 */
	public void moveUp() {

		if (this.spaceship_rect.y >= 700) {
			this.y -= 4;
		}

		this.panel.add(this.picture);
		this.point.move(this.x, this.y);
		this.spaceship_rect.setLocation(this.point);
		this.picture.setLocation(this.point);

	}

	/**
	 * 
	 * Moves the spaceship down
	 *
	 */
	public void moveDown() {

		if (this.spaceship_rect.y <= 910) {
			if (this.y < this.panel.getHeight() - this.picture.getHeight())
				this.y += 4;
		}
		this.panel.add(this.picture);
		this.point.move(this.x, this.y);
		this.spaceship_rect.setLocation(this.point);
		this.picture.setLocation(this.point);

	}

	/**
	 * 
	 * Resets the spaceship in the game
	 *
	 */
	public void reset() {
		this.x = 375;
		this.y = 845;
		this.point.move(375, 845);
		this.spaceship_rect.setLocation(this.point);
		this.picture.setLocation(this.point);
		this.picture.setVisible(true);
		this.moveUp();
	}

	/**
	 * 
	 * Implements the shooting action of the spaceship given the selected gun
	 *
	 * @param gunNum
	 */
	public void Shoot(Integer gunNum) {

		if (gunNum == 1) {
			if (this.bullets.size() == 0) {
				Gun bullet = new Gun(this.x + 23, this.y, this.panel);
				bullet.draw(this.panel);
				bullet.move();
				this.bullets.add(bullet);
			}
		}

		if (gunNum == 2) {
			if (this.bullets.size() == 0) {
				Gun bullet = new Gun(this.x + 75, this.y, this.panel);
				bullet.draw(this.panel);
				bullet.move();
				this.bullets.add(bullet);

				Gun bullet2 = new Gun(this.x + 23, this.y, this.panel);
				bullet2.draw(this.panel);
				bullet2.move();
				this.bullets.add(bullet2);

				Gun bullet3 = new Gun(this.x - 29, this.y, this.panel);
				bullet3.draw(this.panel);
				bullet3.move();
				this.bullets.add(bullet3);
			}
		}

		if (gunNum == 3) {

			if (this.bullets.size() == 0) {
				Gun bullet = new Gun(this.x + 23, this.y - 40, this.panel);
				bullet.draw(this.panel);
				bullet.move();
				this.bullets.add(bullet);

				Gun bullet2 = new Gun(this.x + 23, this.y + 30, this.panel);
				bullet2.draw(this.panel);
				bullet2.move();
				this.bullets.add(bullet2);
			}
		}

	}

}
