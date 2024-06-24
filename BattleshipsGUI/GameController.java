/**
 * GameController.java
 * Main logic of battleship game
 * @author MK Pham
 * Computing Science 20
 * Copyright 2024, Centennial High School. All rights reserved.
 */
import java.awt.Color;

public class GameController {
    public static GameBoard board;

    // Player names and game settings
    public static String playerOneName = "Player 1";
    public static String playerTwoName = "Player 2";
    public static int numCols = 10;
    public static int numRows = 10;
    public static int shipCount = 5;

    // Game state variables
    public static String gameStatus = "";
    public static String placementOrientation = "";
    public static int counter;
    public static int currentPlayer;
    public static int playerOneShips = 0;
    public static int playerTwoShips = 0;
    public static boolean playerOneGrid[][];
    public static boolean playerTwoGrid[][];

    // Initialize the game
    public static void init() {
        board = new GameBoard();
        counter = 0;
        currentPlayer = 0;
        gameStatus = "";
        playerOneShips = 0;
        playerTwoShips = 0;
        playerOneGrid = new boolean[numCols][numRows];
        playerTwoGrid = new boolean[numCols][numRows];

        placeShip();
    }

    // Start ship placement for Player 1
    public static void placeShip() {
        System.out.println("Player 1 Placement Start");
        board.showShipPlacement(1);
        currentPlayer = 1;
        gameStatus = "placement";
    }

    // Process ship placement for the current player
    public static void onShipPlaced(int x, int y) {
        // Validate ship placement
        boolean valid = true;
        int[] coordsX = new int[3];
        int[] coordsY = new int[3];

        // Calculate ship coordinates based on orientation
        for (int i = 0; i < 3; i++) {
            if (placementOrientation.equals("up")) {
                coordsX[i] = x + -i;
                coordsY[i] = y;
            } else if (placementOrientation.equals("down")) {
                coordsX[i] = x + i;
                coordsY[i] = y;
            } else if (placementOrientation.equals("right")) {
                coordsX[i] = x;
                coordsY[i] = y + i;
            } else if (placementOrientation.equals("left")) {
                coordsX[i] = x;
                coordsY[i] = y + -i;
            }
        }

        // Check for overlapping or out-of-bounds placement
        for (int i = 0; i < coordsX.length; i++) {
            if (coordsX[i] > 9 || coordsX[i] < 0 || coordsY[i] > 9 || coordsY[i] < 0)
                valid = false;
            else if (currentPlayer == 1 && checkTile(1, coordsX[i], coordsY[i]))
                valid = false;
            else if (currentPlayer == 2 && checkTile(2, coordsX[i], coordsY[i]))
                valid = false;
        }

        if (!valid) {
            System.out.println("Overlapping or Out of Bounds");
            return;
        }

        // Update the game board based on successful ship placement
        if (currentPlayer == 1) {
            for (int i = 0; i < 3; i++) {
                playerOneGrid[coordsX[i]][coordsY[i]] = true;
                board.setButtonText(3, coordsX[i], coordsY[i], "");
                board.setButtonColor(3, coordsX[i], coordsY[i], Color.GREEN);
                board.setButtonEnabled(3, coordsX[i], coordsY[i], false);
                System.out.println("Coords " + (i + 1) + ": " + coordsX[i] + ", " + coordsY[i]);
            }
            playerOneShips++;
        } else {
            for (int i = 0; i < 3; i++) {
                playerTwoGrid[coordsX[i]][coordsY[i]] = true;
                board.setButtonText(3, coordsX[i], coordsY[i], "");
                board.setButtonColor(3, coordsX[i], coordsY[i], Color.GREEN);
                board.setButtonEnabled(3, coordsX[i], coordsY[i], false);
                System.out.println("Coords " + (i + 1) + ": " + coordsX[i] + ", " + coordsY[i]);
            }
            playerTwoShips++;
        }

        // Refresh the game board and proceed to the next player's turn
        board.refreshFrame();
        if (playerOneShips >= 5 && currentPlayer == 1) {
            board.resetPlacementGrid();
            currentPlayer = 2;
            board.showShipPlacement(2);
            board.refreshFrame();
        }
        if (playerTwoShips >= 5) {
            System.out.println("Placement Ends, Game Begins");
            playerOneShips *= 3;
            playerTwoShips *= 3;
            battle();
        }
    }

    // Start the battle phase
    public static void battle() {
        board.showBattleFrame();
        gameStatus = "battle";
        currentPlayer = 1;
        board.refreshFrame();
    }

    // Process a shot fired on the specified grid and coordinates
    public static void shotFired(int gridNumber, int x, int y) {
        if (gridNumber == 1) {
            if (currentPlayer == 2) {
                if (checkTile(1, x, y)) {
                    System.out.println("Hit At: " + x + ", " + y);
                    playerOneShips--;
                    board.setButtonEnabled(1, x, y, false);
                    board.setButtonBackground(1, x, y, "images/tiles/hit.png");
                    board.setButtonBorderColor(1, x, y, Color.ORANGE);
                    currentPlayer = 1;
                } else {
                    System.out.println("Miss At: " + x + ", " + y);
                    board.setButtonEnabled(1, x, y, false);
                    board.setButtonBackground(1, x, y, "images/tiles/miss.png");
                    board.setButtonBorderColor(1, x, y, Color.BLUE);
                    currentPlayer = 1;
                }
            } else System.out.println("Incorrect Turn");
        }

        if (gridNumber == 2) {
            if (currentPlayer == 1) {
                if (checkTile(2, x, y)) {
                    System.out.println("Hit At: " + x + ", " + y);
                    playerTwoShips--;
                    board.setButtonEnabled(2, x, y, false);
                    board.setButtonBackground(2, x, y, "images/tiles/hit.png");
                    board.setButtonBorderColor(2, x, y, Color.ORANGE);
                    currentPlayer = 2;
                } else {
                    System.out.println("Miss At: " + x + ", " + y);
                    board.setButtonEnabled(2, x, y, false);
                    board.setButtonBackground(2, x, y, "images/tiles/miss.png");
                    board.setButtonBorderColor(2, x, y, Color.BLUE);
                    currentPlayer = 2;
                }
            } else System.out.println("Incorrect Turn");
        }

        // Check for the end of the game based on remaining ships
        if (playerOneShips <= 0) {
            gameEnds(2);
        }
        if (playerTwoShips <= 0) {
            gameEnds(1);
        }

        // Refresh the game board
        board.refreshFrame();
    }

    // Display the winner and end the game
    public static void gameEnds(int winner) {
        if (winner == 1) {
            System.out.println("Player 1 Won");
            board.showEndScreen(playerOneName);
        }
        if (winner == 2) {
            System.out.println("Player 2 Won");
            board.showEndScreen(playerTwoName);
        }
    }

    // Check if a tile on a specific grid is occupied by a ship
    public static boolean checkTile(int grid, int x, int y) {
        switch (grid) {
            case 1:
                return playerOneGrid[x][y];
            case 2:
                return playerTwoGrid[x][y];
        }
        return false;
    }

    // Exit the game
    public static void exit() {
        board.dispose();
    }
}
