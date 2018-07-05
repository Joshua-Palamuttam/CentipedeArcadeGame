/**
 * 
 * This class implements the threading for the game
 * to control the state of the program
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class Animator implements Runnable {

	public GamePanel Game;
	public KeyBoardListener listener;

	public Animator(GamePanel game, KeyBoardListener listener) {
		this.Game = game;
		this.listener = listener;
	}

	/**
	 * 	Threading implementation
	 */
	@Override
	public void run() {
		try {
			while (true) {
				this.listener.Update();
				this.Game.updateGame();
				Thread.sleep(10);
			} 
		} catch (InterruptedException exception) {
			System.out.println("The Thread was caught");
		}
	}
}
