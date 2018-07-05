import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {

	/**
	 * @param args
	 *            ignored
 	 */
	public static void main(String[] args) {
		// Starts the game by creating the SetupFrame
		SetupFrame frame = new SetupFrame();
		play();
	}

	/**
	 * 
	 * Plays the sountrack in the background
	 *
	 */
	public static void play() {
		try {
			File file = new File("Images/song.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			Thread.sleep(clip.getMicrosecondLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
