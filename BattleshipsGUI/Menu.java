/**
 * Menu.java
 * Game Menu
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel buttonPanel;
    private JPanel helpPanel;
    private JPanel centerPanel;
    private JLabel titleLabel;
    private JTextArea helpText;
    private JScrollPane scroll;
    private JButton start;
    private JButton help;
    private JButton exit;
    
    private final String txt = "Ship Placement:"
    		+ "\nClick Rotate to select ship orientation"
    		+ "\nClick on a valid tile on grid to place ship"
    		+ "\nNotes: Ships are 3 tiles long"
    		+ "\n\nBattling:"
    		+ "\nClick on opponent's grid to attack"
    		+ "\nTile will show either a hit or miss icon"
    		+ "\nMiss = Blue, Hit = Orange"
    		+ "\nAlternate turns until one runs out of ship tiles"
    		+ "\n\nMade By: MK Pham";

    // Constructor
    public Menu() {
        super("Battleship");
        initializeFrame();
        createTitleLabel();
        createButtonPanel();
        createButtons();
        createHelpPanel();
        addComponentsToFrame();
    }

    // Method to initialize the frame properties
    private void initializeFrame() {
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to create and add the title label to the frame
    private void createTitleLabel() {
        titleLabel = new JLabel("Battleship");
        titleLabel.setBackground(Color.BLUE);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // Method to create the button panel
    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(Color.BLUE);
    }
    
    // Method to create the help panel
    private void createHelpPanel() {
        helpPanel = new JPanel();
        helpPanel.setLayout(new BorderLayout());
        helpPanel.setBackground(Color.BLUE);
        helpPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        helpText = new JTextArea();
        helpText.setText(txt);
        helpText.setEditable(false);
        helpText.setBackground(Color.BLUE);
        helpText.setForeground(Color.WHITE);
        helpText.setFont(new Font("", Font.PLAIN, 15));
        
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        
        scroll = new JScrollPane(helpText);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hides Scroll Bar
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
        
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> showMainMenu());

        helpPanel.add(scroll, BorderLayout.CENTER);
        helpPanel.add(returnButton, BorderLayout.SOUTH);
    }


    
    // Method to create buttons and set their styles
    private void createButtons() {
        start = createStyledButton("Start Game");
        help = createStyledButton("Help");
        exit = createStyledButton("Exit");

        Dimension buttonSize = new Dimension(200, 50);
        start.setPreferredSize(buttonSize);
        help.setPreferredSize(buttonSize);
        exit.setPreferredSize(buttonSize);
        
        start.addActionListener(e -> start());
        help.addActionListener(e -> showHelp());
        exit.addActionListener(e -> exit());

        buttonPanel.add(start);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(help);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(exit);
    }

    // Method to create a styled button with a specified text
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        button.setFont(buttonFont);
        return button;
    }

    // Method to add components to the frame and display it
    private void addComponentsToFrame() {
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(Color.BLUE);
        centerPanel.add(buttonPanel);
        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Button Action Listeners
	public void start() {
		System.out.println("Start Game");
		GameController.init();
		dispose();
	}
	
	// Show the help panel
	public void showHelp() {
		System.out.println("Opened Help Menu");
	    titleLabel.setText("Help");
	    getContentPane().removeAll();
	    add(titleLabel, BorderLayout.NORTH);
	    add(helpPanel, BorderLayout.CENTER);
	    repaint();
	    revalidate();
	}

	// Hide the help panel
	public void showMainMenu() {
		System.out.println("Opened Main Menu");
	    titleLabel.setText("Battleship");
	    getContentPane().removeAll();
	    add(titleLabel, BorderLayout.NORTH);
	    add(centerPanel, BorderLayout.CENTER);
	    repaint();
	    revalidate();
	}


	// Exits
	public void exit() {
		System.out.println("Exiting");
		dispose();
	}
    
    
}
