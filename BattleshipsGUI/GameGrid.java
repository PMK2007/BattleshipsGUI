/**
 * GameGrid.java
 * Grid of JButtons for battleship tiles and grid
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGrid extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Variables to track placement mode, grid number, and button array
    private boolean isPlacement;
    private int gridNumber;
    private ImageButton[][] buttons;

    // Constructor
    public GameGrid(int gridNumber, boolean isPlacement) {
        super(new GridLayout(GameController.numCols, GameController.numRows));
        this.isPlacement = isPlacement;
        this.gridNumber = gridNumber;

        setBackground(Color.GRAY);
        setOpaque(false);
        setVisible(true);
        setPreferredSize(new Dimension(600, 300));

        buttons = new ImageButton[10][10];

        // Create buttons and add action listeners
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int ii = i;
                final int jj = j;

                buttons[i][j] = new ImageButton();
                if (isPlacement)
                    buttons[i][j].setText(i + ", " + j);
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(ii, jj);
                    }
                });

                // Customize button appearance
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setBorderPainted(true);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                buttons[i][j].setFont(new Font("", Font.BOLD, 10));
                buttons[i][j].setForeground(Color.white);

                add(buttons[i][j]);
            }
        }
    }

    // Method called when a button is clicked
    private void buttonClicked(int x, int y) {
        System.out.println("Grid " + gridNumber + " clicked at: " + x + ", " + y);

        // Check game status and perform actions accordingly
        if (!isPlacement && GameController.gameStatus.equals("battle")) {
            GameController.shotFired(gridNumber, x, y);
        }

        if (isPlacement && GameController.gameStatus.equals("placement")) {
            GameController.onShipPlaced(x, y);
        }
    }

    // Method to change text of a button given coordinates
    public void setButtonText(int x, int y, String newText) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            buttons[x][y].setText(newText);
        }
    }

    // Method to enable/disable a button given coordinates
    public void setButtonEnabled(int x, int y, boolean choice) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            buttons[x][y].setFocusable(choice);
            buttons[x][y].setEnabled(choice);
        }
    }

    // Method to set the background color of a button given coordinates
    public void setButtonColor(int x, int y, Color color) {
        buttons[x][y].setBackground(color);
        buttons[x][y].setContentAreaFilled(true);
    }

    // Method to set the border color of a button given coordinates
    public void setButtonBorderColor(int x, int y, Color color) {
        buttons[x][y].setBorder(BorderFactory.createLineBorder(color, 3));
    }

    // Method to set the background image for a specific button
    public void setButtonBackground(int x, int y, String imagePath) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            buttons[x][y].setBackgroundImage(imagePath);
        }
    }
}
