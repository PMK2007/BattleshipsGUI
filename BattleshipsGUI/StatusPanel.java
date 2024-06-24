/**
 * StatusPanel.java
 * Displays Game Status (Player Name, Ships Placed/Remaining)
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Text fields for player names and their ship counts
    private JTextField playerName1;
    private JTextField playerShips1;
    private JTextField playerName2;
    private JTextField playerShips2;
    
    private String txt = "";

    // Constructor for the StatusPanel
    public StatusPanel(boolean isPlacement, int player) {
        super();
        setOpaque(false);
        
        // Set Placement Status Text
        if (isPlacement) {
        	txt = "Ships: ";
        }
        else {
        	txt = "Tiles Remaining: ";	
        }

        // Initialize text fields with player names and ship counts
        playerName1 = createTextField(GameController.playerOneName);
        playerShips1 = createTextField(txt + GameController.playerOneShips);
        playerName2 = createTextField(GameController.playerTwoName);
        playerShips2 = createTextField(txt + GameController.playerTwoShips);

        // Customize appearance of text fields
        customizeTextField(playerName1, playerShips1);
        customizeTextField(playerName2, playerShips2);

        // Set up layout based on game state
        setupLayout(isPlacement, player);
    }

    // Create a text field with provided initial text
    private JTextField createTextField(String text) {
        return new JTextField(text);
    }

    // Customize appearance of text fields
    private void customizeTextField(JTextField nameField, JTextField shipsField) {
        Font font = new Font("", Font.BOLD, 50);
        nameField.setEditable(false);
        shipsField.setEditable(false);
        nameField.setBorder(null);
        shipsField.setBorder(null);
        nameField.setFont(font);
        shipsField.setFont(font);
        nameField.setOpaque(false);
        shipsField.setOpaque(false);
        
        nameField.setForeground(Color.WHITE);
        shipsField.setForeground(Color.WHITE);
    }

    // Set up the panel layout based on game state
    private void setupLayout(boolean isPlacement, int player) {
        setPreferredSize(new Dimension(1280, 200));

        if (!isPlacement) {
            // For gameplay display, use a 2x2 grid layout
            setLayout(new GridLayout(2, 2));
            add(playerName1);
            add(playerName2);
            add(playerShips1);
            add(playerShips2);
        } else {
            // For ship placement phase, use a single grid layout based on current player
            setLayout(new GridLayout(1, 1));

            switch (player) {
                case 1:
                    add(playerName1);
                    add(playerShips1);
                    break;
                case 2:
                    add(playerName2);
                    add(playerShips2);
                    break;
            }
        }
    }

    // Update the displayed player names and ship counts
    public void updateStatus() {
        playerName1.setText(GameController.playerOneName);
        playerShips1.setText(txt + GameController.playerOneShips);
        playerName2.setText(GameController.playerTwoName);
        playerShips2.setText(txt + GameController.playerTwoShips);
        repaint();
        revalidate();
    }
}
