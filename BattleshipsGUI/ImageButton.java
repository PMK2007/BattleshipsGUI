/**
 * ImageButton.java
 * Customizable background JButton
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
    
	private static final long serialVersionUID = 1L;
	
	private Image backgroundImage;

    // Constructor
    public ImageButton() {
        setPreferredSize(new Dimension(30, 30));
    }

    // Method to set the background image for the button
    public void setBackgroundImage(String imagePath) {
        try {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
            repaint(); // Trigger a repaint to show the new background image
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Override the paintComponent method to draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image to fill the button
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
