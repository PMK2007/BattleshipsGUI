/**
 * ShipPanel.java
 * Displays ship orientation and allows rotation
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import java.awt.*;
import javax.swing.*;

public class ShipPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JPanel shipsPanel;
    JPanel buttonPanel;
    
    JLabel shipUp;
    JLabel shipDown;
    JLabel shipRight;
    JLabel shipLeft;
    
    // Constructor
    public ShipPanel() {
        super(new BorderLayout());
        setOpaque(false);
        
        // Create ship labels for different orientations
        shipUp = new JLabel(new ImageIcon("images/ships/up.png"));
        shipDown = new JLabel(new ImageIcon("images/ships/down.png"));
        shipRight = new JLabel(new ImageIcon("images/ships/right.png"));
        shipLeft = new JLabel(new ImageIcon("images/ships/left.png"));
        
        // Panel to hold ship images
        shipsPanel = new JPanel(new BorderLayout());
        shipsPanel.setOpaque(false);
        
        // Create button panel with the "Rotate" button
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        
        // Create rotate button
        JButton rotate = new JButton("Rotate");
        
        // Add button and set default orientation
        buttonPanel.add(rotate);
        GameController.placementOrientation = "up";
        
        // Add default ship orientation label (up)
        shipsPanel.add(shipUp, BorderLayout.CENTER);
        
        // Add action listener to button
        rotate.addActionListener(e -> rotate());
        
        // Add ship images panel to the top and button panel to the bottom
        add(shipsPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // Method to handle rotation of ship orientation
    public void rotate() {
        if (GameController.placementOrientation.equals("up")) {
            GameController.placementOrientation = "right";
            shipsPanel.removeAll();
            shipsPanel.add(shipRight);
            repaint();
            revalidate();
        }
        else if (GameController.placementOrientation.equals("right")) {
            GameController.placementOrientation = "down";
            shipsPanel.removeAll();
            shipsPanel.add(shipDown);
            repaint();
            revalidate();
        }
        else if (GameController.placementOrientation.equals("down")) {
            GameController.placementOrientation = "left";
            shipsPanel.removeAll();
            shipsPanel.add(shipLeft);
            repaint();
            revalidate();
        }
        else if (GameController.placementOrientation.equals("left")) {
            GameController.placementOrientation = "up";
            shipsPanel.removeAll();
            shipsPanel.add(shipUp);
            repaint();
            revalidate();
        }
        
        System.out.println("Rotated, Current Orientation: " + GameController.placementOrientation);
    }
}
