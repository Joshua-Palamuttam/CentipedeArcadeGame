import javax.swing.JComponent;

/**
 * 
 * This class represents the Game Component that gathers the level
 * and the spaceship and sets a thread for them.
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class GameComponent extends JComponent {
	
	private static final int FRAMES_PER_SECOND = 30;
	private static final long REPAINT_INTERVAL_MS = 1000 / FRAMES_PER_SECOND;

	private Level level;
	private Spaceship spaceship;
	
	public GameComponent(Level level, Spaceship spaceship) {
		this.level = level;
		this.spaceship = spaceship;
		
		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(REPAINT_INTERVAL_MS);
						repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repainter).start();
	}

}
