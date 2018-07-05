import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * This class represents the centipede monster.
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class Centipede {
	
	public final Dimension size = new Dimension(30, 30);	
	public final int MOVE_Y = 25;
	public final int MOVE_X = 2;
	public Rectangle cent_rect;
	public Image picture;
	public Point point;
	
	public Boolean Centipede_Head;
	public Boolean up = false;
	public Boolean right = false;
	public Boolean poisoned = false;
	public Boolean dead = false;
	
	public int x = -1;
	public int y = 0;
	
	public JPanel panel;
	public GamePanel main;
	public ArrayList<Mushroom> Mushrooms;

	public Centipede(GamePanel main, int x, int y, ArrayList<Mushroom> mushrooms) {
		this.main = main;
		this.Mushrooms = mushrooms;
		this.picture = new Image("images\\Centipede_head.png");
		this.picture.setBackground(Color.BLACK);
		this.cent_rect = new Rectangle();
		this.cent_rect.setSize(this.size);
		this.point = new Point(x + 5, y + 5);
	}

	/**
	 * 
	 * This method daws the Centipede's head.
	 *
	 * @param panel the game panel that controls the current game
	 */
	public void draw_head(JPanel panel) {
		this.panel = panel;
		this.panel.add(this.picture);
		this.picture.setLocation(this.point);
		this.picture.setSize(new Dimension(30, 30));
		this.cent_rect.setLocation(this.point);
		this.picture.setVisible(true);

	}

	/**
	 * 
	 * This method daws the Centipede's body.
	 *
	 * @param panel the game panel that controls the current game
	 */
	public void draw_body(JPanel panel) {
		this.picture = new Image("images\\Centipede_body.png");
		this.picture.setBackground(Color.white);
		this.cent_rect = new Rectangle();
		this.cent_rect.setSize(this.size);
		this.point = new Point(x + 5, y + 5);
		this.panel = panel;
		this.panel.add(this.picture);
		this.picture.setLocation(this.point);
		this.picture.setSize(new Dimension(30, 30));
		this.cent_rect.setLocation(this.point);
		this.picture.setVisible(true);

	}

	/**
	 * 
	 * This method implements the movement of the centipede
	 *
	 */
	public void move() {
		// move_down
		if (this.up == true) {
			if (this.point.y <= 0) {
				this.up = false;
				this.y = this.y * -1;
				
			}
			if (this.poisoned == true) {
				if (this.point.y >= 850) {
					this.x = -MOVE_X;
					this.y = 0;
					this.poisoned = false;
					this.y = this.y * -1;
					this.up = true;
				}
			}
			this.point.translate(this.x, this.y);
		}

		else {
			if (this.point.y >= 850) {
				if (this.poisoned == true) {
					this.x = -MOVE_X;
					this.y = 0;
				}
				this.poisoned = false;
				this.y = this.y * -1;
				this.up = true;
			}
			this.point.translate(this.x, this.y);
		}
		// move_left
		if (this.point.x >= 800) {
			this.y = MOVE_Y;
			this.x = this.x * -1;

			if (this.up == true) {
				this.y = -MOVE_Y;
			}
			this.point.translate(this.x, this.y);
			this.y = 0;
		}

		// move_right
		if (this.point.x <= 0) {
			this.y = MOVE_Y;
			this.x = this.x * -1;

			if (this.up == true) {
				this.y = -MOVE_Y;
			}
			this.point.translate(this.x, this.y);
			this.y = 0;
		}

		// regularly move
		else {
			if(this.poisoned == true){
				this.x = 0;
			}
			this.point.translate(this.x, this.y);
		}
		this.picture.setLocation(this.point);
		this.cent_rect.setLocation(this.point);
	}

	/**
	 * 
	 * This method checks if centipede hit a mushroom
	 *
	 */
	public void checkhitMushrooom() {
		for (int i = 0; i < this.Mushrooms.size(); i++) {
			if (this.cent_rect.intersects(this.Mushrooms.get(i).getSize()) && this.poisoned == false) {
				if (this.up == true) {
					this.y = -MOVE_Y;
					this.x = this.x * -1;
				} else {
					this.y = MOVE_Y;
					this.x = this.x * -1;
				}
				this.point.translate(this.x, this.y);
				this.y = 0;
				if (this.Mushrooms.get(i).isPoisoned) {
					this.poisoned = true;
					this.y = 4;
				}
			}
		}
	}

	/**
	 * 
	 * This method is called when the centipede dies.
	 *
	 */
	public void kill() {
		this.picture.setVisible(false);
	}

	/**
	 * 
	 * Returns the rectangle box of the current centipede
	 *
	 * @return rectangle
	 */
	public Rectangle getSize() {
		return this.cent_rect;
	}
	
	/**
	 * 
	 * Returns the center point of the current centipede
	 *
	 * @return point
	 */
	public Point getPoint() {
		return this.point;
	}

	/**
	 * 
	 * Returns a boolean for wheter this centipede is dead.
	 *
	 * @return boolean
	 */
	public boolean isDead() {
		return this.isDead();
	}
}
