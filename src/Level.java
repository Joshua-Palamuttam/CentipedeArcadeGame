import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This class represents a level in the game
 *
 * @author josebaf.
 *         Created May 19, 2016.
 */
public class Level {

	private final static int LEVEL_QUANTITY = 4;
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	private final Shape BACKGROUND;

	private int levelNumber = 1;
	private Scanner input;
	private File gameFile;
	
	private ArrayList<Mushroom> mushrooms;

	public Level() {
		this.BACKGROUND = new Rectangle2D.Double(0, 0, this.WIDTH, this.HEIGHT);
		this.readGameFile("src\\Level" + this.levelNumber + ".txt");
	}

	/**
	 * 
	 * This method reads a new level text file given a 
	 * change asked by the user
	 *
	 * @param change
	 */
	public void changeLevel(String change) {
		if (change.equals("up")) {
			this.levelNumber = (this.levelNumber % LEVEL_QUANTITY) + 1;
			this.readGameFile("src\\Level" + this.levelNumber + ".txt");
		} else if (change.equals("down")) {
			this.levelNumber = (this.levelNumber % LEVEL_QUANTITY) - 1;
			this.readGameFile("src\\Level" + this.levelNumber + ".txt");
		}

	}

	/**
	 * 
	 * This method takes a level text file name and 
	 * reads it to create a level in the game
	 *
	 * @param fileName
	 */
	public void readGameFile(String fileName) {
		this.gameFile = new File(fileName);
		double row = 0;
		this.mushrooms = new ArrayList<>();

		try {
			this.input = new Scanner(this.gameFile);
			while (this.input.hasNextLine()) {
				String temp = this.input.nextLine();
				for (int i = 0; i < temp.length(); i++) {
					if (temp.charAt(i) == 'x') {
						Point2D newPoint = new Point2D.Double((i * 10) * 2 + 10, (row * 10) * 2 + 10);
						Mushroom m = new Mushroom((int) newPoint.getX(), (int) newPoint.getY());
						this.mushrooms.add(m);
					}
				}
				row++;

			}
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
}
