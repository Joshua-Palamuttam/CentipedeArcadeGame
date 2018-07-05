import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * This class represents the game by itseft.
 * It is responsible to start it and control it.
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class GamePanel {

	private Scanner input;
	private File gameFile;

	public Player player;
	private String Name;
	private int score;
	private int level = 1;

	private JFrame frame;
	private JLabel label1;
	private JLabel label2;
	public JPanel menu_panel;
	public JPanel game_panel;
	public JFrame frame22;
	public JFrame Game_Frame;

	public Spaceship spaceship;
	public ArrayList<Mushroom> mushrooms;
	public ArrayList<Spider> spiders = new ArrayList<>();
	private ArrayList<Scorpion> scorpions = new ArrayList<>();
	private ArrayList<Flea> fleas = new ArrayList<>();
	private ArrayList<Centipede> Centipedes;

	public KeyBoardListener listener;
	public Thread thread;

	public GamePanel(Player player) {
		this.game_panel = new JPanel();
		this.spaceship = new Spaceship(this.game_panel);
		this.Centipedes = new ArrayList<>();
		this.player = player;
		this.label2 = new JLabel();
		this.label1 = new JLabel();
		this.score = 0;
		this.frame22 = new JFrame();
	}

	/**
	 * 
	 * Draws the frame of the game with all buttons and labels
	 *
	 */
	public void DrawWindow() {
		this.Game_Frame = new JFrame();
		this.Game_Frame.setSize(800, 1000);

		this.Game_Frame.setLayout(null);
		this.Game_Frame.setResizable(false);
		this.frame = this.Game_Frame;

		this.menu_panel = new JPanel();
		this.menu_panel.setSize(new Dimension(800, 100));
		this.menu_panel.setBackground(Color.GRAY);

		updateLabel(this.label1, "Score ", this.score);
		updateLabel(this.label2, "Lives ", this.player.lives);

		JLabel label3 = new JLabel("Name: " + this.player.name);
		JLabel label4 = new JLabel("Press Space to start  game");

		GridLayout grid = new GridLayout(1, 8);
		this.menu_panel.setLayout(grid);
	

		this.game_panel = new JPanel();
		this.game_panel.setSize(new Dimension(800, 900));
		this.game_panel.setBackground(Color.BLACK);
		this.game_panel.setLayout(null);

		/*
		 * Creates the helpButton and adds a listener that shows 
		 * a Message Dialog if the button is pressed
		 * 
		 */
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.keyPressed(KeyEvent.VK_P);
				JOptionPane.showMessageDialog(frame22,
						"<html> <br> Before you start the game, here are the instrcutions: <br>For Weapon 1 press : 1 <br>For Weapon 2 press : 2 <br>For Weapon 3 press : 3 <br> Fire Gun with Space Bar "
								+ "Bar <br> Move around with Arrow keys <br> Move Up and Down levels with U & D "
								+ "<br> Pause and Unpause with P " + "<br> <br> ARE YOU READY TO BEGIN ?" + "</html>" );
				

				((JButton) e.getSource()).setFocusable(false);
				listener.keyPressed(KeyEvent.VK_P);
			}
		}); 

		this.label1.setFont(new Font("Arial", Font.BOLD, 20));
		this.label2.setFont(new Font("Arial", Font.BOLD, 22));
		label3.setFont(new Font("Arial", Font.BOLD, 19));
		label3.setFont(new Font("Arial", Font.BOLD, 19));

		this.menu_panel.add(this.label1);
		this.menu_panel.add(this.label2);
		this.menu_panel.add(label3);
		this.menu_panel.add(label4);
		this.menu_panel.add(helpButton);

		this.frame.add(this.menu_panel);
		this.menu_panel.setLocation(new Point(0, 900));
		this.frame.add(this.game_panel);
		this.game_panel.setLocation(new Point(0, 0));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	/**
	 * 
	 * Updates a label using a prefix text and a value.
	 *
	 * @param label			the label to be updated
	 * @param prefixText	the prefix text to be shown
	 * @param value			the new value
	 */
	private static void updateLabel(JLabel label, String prefixText, int value) {
		label.setText(prefixText + ": " + value);
	}

	/**
	 * 
	 * Checks if the player still have lives to continue playing.
	 *
	 */
	public void checkLives() {
		updateLabel(this.label2, "Lives", this.player.lives);
		if (this.player.lives == 0) {

			System.out.println("Player Lost");
			this.spaceship.kill();
			System.exit(0);
		}
	}
	
	/**
	 *
	 * This method takes a text file with a binary description of the position
	 * of the mushrooms and then constructs the mushrooms for the level.
	 *
	 * @param fileName	the text file of a level with a binary description of the position
	 * of the mushrooms
	 */
	public void readGameFile(String fileName) {
		// Load the textFile and convert the data to the ArrayList of mushroom
		// positions
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

	/**
	 * 
	 * This method changes the level on the frame given the key pressed.
	 * (This method is called by the KeyBoardListener)
	 *
	 * @param up
	 */
	public void changeLevel(String up) {
		if (up == "up") {
			this.checkLevel();
			this.level++;
			this.checkLevel();
			this.game_panel.removeAll();
			this.readGameFile("src\\Level" + this.level + ".txt");
			this.drawMushrooms();
			this.drawSpaceship();
			this.Centipedes.clear();
			this.addCentipede();
			this.drawMonsters();
			this.game_panel.repaint();
			this.frame.repaint();

		} else if (up == "down") {
			this.checkLevel();
			this.level--;
			this.checkLevel();
			this.game_panel.removeAll();
			this.readGameFile("src\\Level" + this.level + ".txt");
			this.drawMushrooms();
			this.drawSpaceship();
			this.Centipedes.clear();
			this.addCentipede();
			this.drawMonsters();
			this.game_panel.repaint();
			this.frame.repaint();

		}

	}

	/**
	 * 
	 * Draws the mushrooms on the game frame.
	 *
	 */
	public void drawMushrooms() {
		for (int i = 0; i < this.mushrooms.size(); i++) {
			this.mushrooms.get(i).draw(this.game_panel);
		}
	}

	/**
	 * 
	 * Draws the spaceship on the game frame.
	 *
	 */
	public void drawSpaceship() {
		this.spaceship.reset();
		this.spaceship.draw(this.game_panel);
	}

	/**
	 * 
	 * Resets the Monsters of the game and adds the centipede
	 *
	 */
	public void resetMonsters() {
		this.Centipedes.clear();
		this.spiders.clear();
		this.scorpions.clear();
		this.fleas.clear();
		this.addCentipede();
	}

	/**
	 * 
	 * Draws all the monsters on the game frame.
	 *
	 */
	public void drawMonsters() {
		for (int i = 0; i < this.Centipedes.size(); i++) {
			this.Centipedes.get(i).draw_head(this.game_panel);
		}
		for (int j = 0; j < this.spiders.size(); j++) {
			this.spiders.get(j).draw();
		}
		for (int j = 0; j < this.scorpions.size(); j++) {
			this.scorpions.get(j).draw();
		}
		for (int j = 0; j < this.fleas.size(); j++) {
			this.fleas.get(j).draw();
		}
	}

	/**
	 * 
	 * Adds a centipede to the game.
	 *
	 */
	public void addCentipede() {
		for (int k = 0; k < 10; k++) {
			this.Centipedes.add(new Centipede(this, 1 + (k * 50), -5, this.mushrooms));
		}
	}

	/**
	 * 
	 * This method is responsible to set the game up.
	 * It calls the readGameFile method to construct the mushrooms for the current level,
	 * constructs the frame and the KeyBoardListener.
	 * Then it starts the thread.
	 *
	 */
	public void startGame() {
		this.DrawWindow();
		this.readGameFile("src\\Level" + this.level + ".txt");
		this.addCentipede();
		this.listener = new KeyBoardListener(this.spaceship, this.game_panel, this);
		this.frame.addKeyListener(this.listener);
		this.drawMushrooms();
		this.drawSpaceship();
		this.drawMonsters();
		Animator newAnimate = new Animator(this, this.listener);
		this.thread = new Thread(newAnimate);
		this.thread.start();
		this.listener.keyPressed(KeyEvent.VK_H);
	}

	/**
	 * 
	 * This method set the next level on the screen if the player wins the
	 * current level. If the player won the last level, the player wins the game.
	 *
	 */
	public void checkLevel() {
		if (this.level > 4 && this.player.lives > 0) {
			System.out.println("Player won");
			System.exit(0);
		}
		if (this.level > 4) {
			this.level = 4;
		}
		if (this.level < 1) {
			this.level = 1;
		}
	}

	/**
	 * 
	 * This method is responsible for the movement of the monsters on the game frame
	 * and for handling the collision between bullets and monsters.
	 *
	 */
	public void updateGame() {
		this.checkLevel();
		
		Random rand2 = new Random();
		Random rand = new Random();
		if (rand2.nextFloat() < 0.00018) {
			this.scorpions.add(new Scorpion(this, 0, 0));
			this.drawMonsters();
		}
		int xx = (int) rand2.nextInt(900);
		int yy = (int) rand2.nextInt(700);
		if (rand.nextFloat() < 0.00023) {
			this.spiders.add(new Spider(this, xx, yy));
			this.drawMonsters();
		}
		if (rand.nextFloat() < 0.002) {
			if (this.mushrooms.size() < 30) {
				this.fleas.add(new Flea(this, 0, 0));
				this.drawMonsters();
			}
		}

		// bullet movement
		for (int i = 0; i < this.spaceship.bullets.size(); i++) {
			this.spaceship.bullets.get(i).draw(game_panel);
			this.spaceship.bullets.get(i).move();
			// play2();

			if (this.spaceship.bullets.get(i).y < 0) {
				this.spaceship.bullets.get(i).kill();

				this.spaceship.bullets.remove(i);
				break;

			}

			// when bullets hit the mushroom
			for (int k = 0; k < this.mushrooms.size(); k++) {

				if (this.mushrooms.get(k).getSize().intersects(this.spaceship.bullets.get(i).bullet_rec)) {
					this.score += 30;
					this.mushrooms.get(k).damage();
					this.spaceship.bullets.get(i).kill();
					this.spaceship.bullets.remove(i);
					this.spaceship.picture.setVisible(true);

					if (this.mushrooms.get(k).isDead()) {
						this.score += 10;
						this.mushrooms.get(k).kill();
						this.mushrooms.remove(k);
						updateLabel(this.label1, "Score", this.score);
					}

					break;
				}

			}
		}
		// Centipede Movement
		for (int m = 0; m < this.Centipedes.size(); m++) {

			this.Centipedes.get(m).move();
			this.Centipedes.get(m).checkhitMushrooom();

			if (this.Centipedes.get(m).getSize().intersects(this.spaceship.spaceship_rect)) {
				this.player.lives--;
				this.checkLives();
				this.game_panel.removeAll();
				this.drawMushrooms();
				this.spiders.removeAll(this.spiders);
				this.Centipedes.removeAll(this.Centipedes);
				this.fleas.removeAll(this.fleas);
				this.scorpions.removeAll(this.scorpions);
				this.addCentipede();
				this.drawMonsters();

				this.spaceship.reset();
				this.game_panel.repaint();
				this.frame.repaint();
				break;
			}
		}
		// when bullets hit the Centipede
		for (int i = 0; i < this.spaceship.bullets.size(); i++) {
			for (int c = 0; c < this.Centipedes.size(); c++) {
				if (this.Centipedes.get(c).getSize().intersects(this.spaceship.bullets.get(i).bullet_rec)) {
					this.score += 50;
					updateLabel(this.label1, "Score", this.score);
					Point Death = new Point();
					Death = this.Centipedes.get(c).getPoint();
					this.Centipedes.get(c).kill();
					this.Centipedes.remove(c);
					Mushroom new_mush = new Mushroom(Death.x, Death.y);
					this.mushrooms.add(new_mush);
					this.drawMushrooms();
				}
			}
		}
		if (this.Centipedes.size() == 0 && this.player.lives > 0) {
			this.changeLevel("up");
		}
		// spider interaction and movement
		for (int m = 0; m < this.spiders.size(); m++) {

			this.spiders.get(m).move();
			this.spiders.get(m).checkHitMushroom();

			if (this.spiders.get(m).rectangle.intersects(this.spaceship.spaceship_rect)) {

				this.player.lives--;
				this.checkLives();
				this.game_panel.removeAll();
				this.drawMushrooms();
				this.spiders.removeAll(this.spiders);
				this.Centipedes.removeAll(this.Centipedes);
				this.fleas.removeAll(this.fleas);
				this.scorpions.removeAll(this.scorpions);
				this.addCentipede();
				this.drawMonsters();

				this.spaceship.reset();
				this.game_panel.repaint();
				this.frame.repaint();
				break;
			}

			for (int i = 0; i < this.spaceship.bullets.size(); i++) {
				if (this.spaceship.bullets.get(i).bullet_rec.intersects(this.spiders.get(m).rectangle)) {
					this.score += 600;
					updateLabel(this.label1, "Score", this.score);
					this.spiders.get(m).kill();
					this.spiders.remove(m);
					this.spaceship.bullets.get(i).kill();
					this.spaceship.bullets.remove(i);
					break;

				}
			}
		}

		// scorpion movement
		for (int p = 0; p < this.scorpions.size(); p++) {
			this.scorpions.get(p).move();
			this.scorpions.get(p).checkHitMushroom();

			for (int i = 0; i < this.spaceship.bullets.size(); i++) {
				if (this.scorpions.get(p).rectangle.intersects(this.spaceship.bullets.get(i).bullet_rec)) {
					this.score += 1000;
					updateLabel(this.label1, "Score", this.score);
					this.scorpions.get(p).kill();
					this.scorpions.remove(p);
					this.spaceship.bullets.get(i).kill();
					this.spaceship.bullets.remove(i);
					break;
				}
			}

		}
		// flea movement
		for (int i = 0; i < this.fleas.size(); i++) {
			this.fleas.get(i).move();

			if (this.fleas.get(i).rectangle.intersects(this.spaceship.spaceship_rect)) {

				this.player.lives--;
				this.checkLives();
				this.game_panel.removeAll();
				this.drawMushrooms();
				this.spiders.removeAll(this.spiders);
				this.Centipedes.removeAll(this.Centipedes);
				this.fleas.removeAll(this.fleas);
				this.scorpions.removeAll(this.scorpions);
				this.addCentipede();
				this.drawMonsters();

				this.spaceship.reset();
				this.game_panel.repaint();
				this.frame.repaint();
				break;

			}

			for (int k = 0; k < this.spaceship.bullets.size(); k++) {
				if (this.fleas.get(i).rectangle.intersects(this.spaceship.bullets.get(k).bullet_rec)) {
					this.score += 50;
					updateLabel(this.label1, "Score", this.score);
					this.fleas.get(i).kill();
					this.fleas.remove(i);
					this.spaceship.bullets.get(i).kill();
					this.spaceship.bullets.remove(i);
					this.drawMonsters();
					break;
				}
			}

		}

		this.game_panel.repaint();
		this.frame.repaint();
	}

	/**
	 *
	 * @return this frame
	 */
	public JFrame getFrame() {
		return this.frame;
	}
}
