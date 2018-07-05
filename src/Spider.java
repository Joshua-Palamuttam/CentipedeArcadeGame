import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * This class represents a spider monster in the game
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class Spider extends Monster {

	public Spider(GamePanel main, int x, int y) {
		super(main, x, y);
		this.main = main;
		this.picture = new Image("images\\Centipede_spider.png");
		this.picture.setBackground(Color.black);
		this.rectangle = new Rectangle();
		this.rectangle.setSize(this.size);
		this.point = new Point(x,y);
	}

}
