import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * The SetupFrame for the game where the user types his name
 *
 * @author josebaf.
 *         Created May 20, 2016.
 */
public class SetupFrame extends JFrame {

	public SetupFrame() {
		setTitle("Centipede Game: Setup");

		JPanel content = new JPanel();

		JLabel label = new JLabel("Enter Player Name");
		content.add(label);

		final JTextField playerNameText = new JTextField();
		playerNameText.setPreferredSize(new Dimension(150, 30));
		content.add(playerNameText);

		// The startGameButton assigns the text to the PlayerName
		// and creates an instance of GamePanel, which runs the game
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String playerName = playerNameText.getText();
				Player player = new Player(playerName);
				GamePanel Game = new GamePanel(player);
				Game.startGame();
				dispose();
			}
		});

		add(content, BorderLayout.CENTER);
		add(startGameButton, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
