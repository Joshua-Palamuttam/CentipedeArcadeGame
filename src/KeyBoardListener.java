import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * This class implements the key listener for the game 
 * and handles each case.
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class KeyBoardListener implements KeyListener {
	// Fields
	public Spaceship spaceship;
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public boolean shoot = false;
	public boolean pause = false; //false
	public boolean help = false;
	public int gunNum = 1;
	public GamePanel viewer;
	public JPanel panel;

	// constructor
	public KeyBoardListener(Spaceship spaceship, JPanel panel, GamePanel viewer) {
		this.viewer = viewer;
		this.panel = panel;
		this.spaceship = spaceship;
	}

	/**
	 * This method takes an event, identifies which key was pressed
	 * and handle it accordingly.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.left = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.down = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.shoot = true;
		}
		//switch guns
		if (e.getKeyCode() == KeyEvent.VK_1) {
			this.gunNum = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			this.gunNum = 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_3) {
			this.gunNum = 3;
		}
		// Switch Levels
		else if (e.getKeyCode() == KeyEvent.VK_U) {
			this.viewer.changeLevel("up");

		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			this.viewer.changeLevel("down");
		}
		//pause
		if (e.getKeyCode() == KeyEvent.VK_P) {
			this.pause = !this.pause;
			if (this.pause == true) {
				this.viewer.thread.suspend();
			}
			else{
				this.viewer.thread.resume();
			}
		}
		
		//help window
		if (e.getKeyCode() == KeyEvent.VK_H) {
			this.keyPressed(KeyEvent.VK_P);
			JOptionPane.showMessageDialog(new JFrame(),
					"<html> <br> Before you start the game, here are the instrcutions: <br>For Weapon 1 press : 1 <br>For Weapon 2 press : 2 <br>For Weapon 3 press : 3 <br> Fire Gun with Space Bar "
							+ "Bar <br> Move around with Arrow keys <br> Move Up and Down levels with U & D "
							+ "<br> Pause and Unpause with P " + "<br> <br> ARE YOU READY TO BEGIN ?" + "</html>" );
			this.keyPressed(KeyEvent.VK_P);
		}
	}

	/**
	 *  This method takes an event, identifies whether a key was released
	 * 	and handle it accordingly.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.left = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.up = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.up = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.shoot = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// ignored

	}

	/**
	 * 
	 * This method implements the movement of the spaceship 
	 *
	 */
	public void Update() {

		if (this.right == true) {
			this.spaceship.moveRight();
			for (int i = 0; i < this.viewer.mushrooms.size(); i++) {
				if (this.spaceship.spaceship_rect.intersects(this.viewer.mushrooms.get(i).getSize())) {
					this.spaceship.moveLeft();
				}
			}
		}

		if (this.left == true) {
			this.spaceship.moveLeft();
			for (int i = 0; i < this.viewer.mushrooms.size(); i++) {
				if (this.spaceship.spaceship_rect.intersects(this.viewer.mushrooms.get(i).getSize())) {
					this.spaceship.moveRight();
				}
			}
		}

		if (this.up == true) {
			this.spaceship.moveUp();
			for (int i = 0; i < this.viewer.mushrooms.size(); i++) {
				if (this.spaceship.spaceship_rect.intersects(this.viewer.mushrooms.get(i).getSize())) {
					this.spaceship.moveDown();
				}
			}
		}

		if (this.down == true) {
			this.spaceship.moveDown();
			for (int i = 0; i < this.viewer.mushrooms.size(); i++) {
				if (this.spaceship.spaceship_rect.intersects(this.viewer.mushrooms.get(i).getSize())) {
					this.spaceship.moveUp();
				}
			}
		}

		if (this.shoot == true) {
			this.spaceship.Shoot(this.gunNum);
			
		}

	}

	/**
	 * 
	 * The listener for the pause game function
	 *
	 * @param vkP
	 */
	public void keyPressed(int vkP) {
		if (vkP == KeyEvent.VK_P){
			this.pause = !this.pause;
			if (this.pause == true) {
				this.viewer.thread.suspend();
			}
			else{
				this.viewer.thread.resume();
			}
		}
		
	}

}
