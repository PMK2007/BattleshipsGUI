/**
 * EndScreen.java
 * Ending/Victory Screen for battleship game
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel titleLabel;
	private JLabel winnerLabel;
	private JButton playAgainButton;
	private JButton quitButton;

	// Constructor
	public EndScreen(String player) {
		super();
		setBackground(Color.BLUE);
		setLayout(new BorderLayout());

		// Create and format the title label
		titleLabel = new JLabel("Game Over");
		titleLabel.setFont(new Font("", Font.BOLD, 40));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Create and format the winner label
		winnerLabel = new JLabel("<html><center>Congratulations!<br>" + player + "</center></html>");
		winnerLabel.setFont(new Font("", Font.PLAIN, 30));
		winnerLabel.setForeground(Color.WHITE);
		winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Create and format buttons
		playAgainButton = new JButton("Menu");
		quitButton = new JButton("Quit");

		playAgainButton.setFont(new Font("", Font.BOLD, 20));
		quitButton.setFont(new Font("", Font.BOLD, 20));

		// Add action listener
		playAgainButton.addActionListener(e -> onClick(1));
		quitButton.addActionListener(e -> onClick(2));

		// Add components to the panel
		JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		centerPanel.setOpaque(false);
		centerPanel.add(titleLabel);
		centerPanel.add(winnerLabel);

		// Adds buttons to a panel
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setOpaque(false);
		buttonPanel.add(playAgainButton);
		buttonPanel.add(quitButton);

		// Add components to the main panel
		add(centerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	// Handles button click
	public void onClick(int choice) {
	    switch (choice) {
	        case 1:
	            // User clicked "Menu" button
	            // Perform necessary cleanup and exit the game
	            GameController.exit();
	            
	            // Open the menu in a new Swing thread
	            SwingUtilities.invokeLater(() -> new Menu());
	            break;
	        case 2:
	            // User clicked "Quit" button
	            // Perform necessary cleanup and exit the game
	            GameController.exit();
	            break;
	    }
	}

}
