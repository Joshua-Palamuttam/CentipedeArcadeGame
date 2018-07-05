import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * This class represents the Game Frame
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class GameFrame extends JFrame {

	public GameFrame() {
		setTitle("Centipede Game");

		add(quitButtonComponent(), BorderLayout.PAGE_END);
		setResizable(false);
		
		pack();
	}
	
	/**
	 * 
	 * This method creates a quit button for the game frame
	 *
	 * @return quitButton
	 */
	private JComponent quitButtonComponent() {
		JButton quitButton = new JButton("Quit");

		ActionListener quitter = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		};
		
		quitButton.addActionListener(quitter);
		return quitButton;
	}
}
