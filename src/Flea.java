import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 
 * This class represents the flea monster.
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class Flea extends Monster {

	public Flea(GamePanel main, int x, int y) {
		super(main, x, y);
		this.main = main;
		
		this.picture = new Image("images\\Centipede_flea.png"); 
		Random rand = new Random();
		
		this.x = 1;
		
		this.point = new Point((int) (rand.nextFloat()*800),0);
		
		this.size = new Dimension(30,30);
		
		this.picture.setBackground(Color.black);
		this.rectangle = new Rectangle();
		this.rectangle.setSize(this.size);
	}
	
	/**
	 * 
	 * This method implements the movement of the flea
	 *
	 */
	public void move() {
		this.point.translate(0, 1);
		this.picture.setLocation(this.point);
		this.rectangle.setLocation(this.point);
		Random rand = new Random();
		if (rand.nextFloat() < 0.008) {
			int x = this.point.x;
			int y = this.point.y;
			int xdiff = x%50;
			int ydiff = y%50;
			if (xdiff>25) {
				x = x + (50-xdiff);
			}
			if (xdiff<25) { 
				x = x - xdiff;
			}
			if (ydiff>25) {
				y = y + (50-ydiff);
			}
			if (ydiff<=25) {
				y = y - ydiff;
			}
			this.main.mushrooms.add(new Mushroom(x,y));
			this.main.drawMushrooms();
		}
		
	}
}

