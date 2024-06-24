/**
 * GameBoard.java
 * Main container for battleship game
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import javax.swing.*;

import java.awt.*;

public class GameBoard extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private GameGrid placementGrid;
    private GameGrid grid1;
    private GameGrid grid2;
    private StatusPanel placementStatus1;
    private StatusPanel placementStatus2;
    private StatusPanel battleStatus;
    private EndScreen endScreen;
    private ShipPanel shipPanel;
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem exit;
    private JMenuItem back;
    
    // Initializes main board JFrame
    public GameBoard() {
        super("Battleship");
        generateBoard();
    }
    
    // Initialize frame and all components
    public void generateBoard() {
        setSize(1280, 720);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize ship placement panel
        placementGrid = new GameGrid(3, true);

        // Initialize game grids
        grid1 = new GameGrid(1, false);
        grid2 = new GameGrid(2, false);

        // Initialize Status Panels
        battleStatus = new StatusPanel(false, 0);
        placementStatus1 = new StatusPanel(true, 1);
        placementStatus2 = new StatusPanel(true, 2);
        shipPanel = new ShipPanel();
        
        // Initialize menu items
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        exit = new JMenuItem("Exit");
        back = new JMenuItem("Menu");
        
        // Add action listeners to menu items
        exit.addActionListener(e -> exit(false));
        back.addActionListener(e -> exit(true));
        
        // Add menu items to menu bar
        gameMenu.add(back);
        gameMenu.add(exit);
        menuBar.add(gameMenu);
        
        // Set menu bar
        setJMenuBar(menuBar);
        
        setVisible(true);
    }
    
    // Set image background
    public void setBackgroundImage(String imagePath) {
        ImagePanel imagePanel = new ImagePanel(imagePath);
        setContentPane(imagePanel);
        setLayout(new BorderLayout());
        revalidate();
        repaint();
    }
    
    // Method to show ship placement panel
    public void showShipPlacement(int player) {
        getContentPane().removeAll(); // Remove all components from the frame
        setBackgroundImage("images/backgrounds/blueprint.png");
        add(placementGrid, BorderLayout.WEST);
        add(shipPanel, BorderLayout.CENTER);
        if (player == 1) add(placementStatus1, BorderLayout.NORTH);
        else if (player == 2) add(placementStatus2, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    // Method to start the game with game grids
    public void showBattleFrame() {
        getContentPane().removeAll(); // Remove all components from the frame
        setBackgroundImage("images/backgrounds/ocean.png");
        add(grid1, BorderLayout.WEST);
        add(grid2, BorderLayout.EAST);
        add(battleStatus, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
    
    // Method to show end screen
    public void showEndScreen(String winner) {
        getContentPane().removeAll();
        endScreen = new EndScreen(winner);
        add(endScreen);
        revalidate();
        repaint();
    }
    
    // Calls method to change button text
    public void setButtonText(int gridNumber, int x, int y, String newText) {
        switch (gridNumber) {
            case 1:
                grid1.setButtonText(x, y, newText);
                break;
            case 2:
                grid2.setButtonText(x, y, newText);
                break;
            case 3:
                placementGrid.setButtonText(x, y, newText);
                break;
        }
    }
    
    // Calls method to change button enabled
    public void setButtonEnabled(int gridNumber, int x, int y, boolean choice) {
        switch (gridNumber) {
            case 1:
                grid1.setButtonEnabled(x, y, choice);
                break;
            case 2:
                grid2.setButtonEnabled(x, y, choice);
                break;
            case 3:
                placementGrid.setButtonEnabled(x, y, choice);
                break;
        }
    }
    
    // Calls method to set button color
	public void setButtonColor(int gridNumber, int x, int y, Color color) {
		switch (gridNumber) {
		case 1:
			grid1.setButtonColor(x, y, color);
			break;
		case 2:
			grid2.setButtonColor(x, y, color);
			break;
		case 3:
			placementGrid.setButtonColor(x, y, color);
			break;
		}
	}
    
	// Calls method to set button background
	public void setButtonBackground(int gridNumber, int x, int y, String path) {
		switch (gridNumber) {
		case 1:
			grid1.setButtonBackground(x, y, path);
			break;
		case 2:
			grid2.setButtonBackground(x, y, path);
			break;
		case 3:
			placementGrid.setButtonBackground(x, y, path);
			break;
		}
	}
	
	// Calls method to change button border color
	public void setButtonBorderColor(int gridNumber, int x, int y, Color color) {
		switch (gridNumber) {
		case 1:
			grid1.setButtonBorderColor(x, y, color);
			break;
		case 2:
			grid2.setButtonBorderColor(x, y, color);
			break;
		case 3:
			placementGrid.setButtonBorderColor(x, y, color);
			break;
		}
	}
	
	// Method to Reset/Regenerates the placement grid
    public void resetPlacementGrid() {
        System.out.println("Reset Placement Grid");
        getContentPane().removeAll();
        placementGrid = new GameGrid(3, true);
    }
    
    // Method to  Refresh frame and status panels
    public void refreshFrame() {
        System.out.println("Refreshed Frame");
        placementStatus1.updateStatus();
        placementStatus2.updateStatus();
        battleStatus.updateStatus();
        revalidate();
        repaint();
    }
    
    // Method to exit the game or go to menu
    public void exit(boolean toMenu) {
    	dispose();
    	
    	// Goes to menu
    	if (toMenu) {
    		SwingUtilities.invokeLater(() -> new Menu());
    	}
    }
}
