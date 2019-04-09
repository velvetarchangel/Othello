package logic;

import java.util.*;
import java.io.*;

/**
 * Board class for Othello game. This class contains attributes of the 2D board
 * array, and methods that print out the board, and keeps track of moves and
 * scores.
 *
 * @author Himika Dastidar, Jayoo Hwang, Vivian Huynh
 * @version 1.0 (February 27, 2019)
 */
public class Board implements Serializable {

    // Instance variables
    private String[][] board;
    private int n; // counts how many pieces are flipped in a direction
    private int[] flipped = new int[9];
    private String otherPlayer = "0";

    /**
     * This default constructor sets the size of the 2D array and the initial pieces
     * of the board.
     */
    public Board() {

        this.board = new String[10][10]; // Sets size of 2D array as 10 x 10 elements

        // Fills array with "_", representing empty areas of board
        for (int i = 1; i < this.board.length; i++) {
            for (int j = 1; j < this.board.length; j++) {
                board[i][j] = "_";
            }
        }

        // Fills borders of board with 3's to help check that pieces cannot be placed on
        // the edges
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                board[0][j] = "3"; // top
                board[9][j] = "3"; // bottom
                board[i][0] = "3"; // left
                board[i][9] = "3"; // right
            }
        }
        // Sets the four initial game pieces in the centre of the board to start the
        // game
        board[4][4] = "1";
        board[4][5] = "2";
        board[5][4] = "2";
        board[5][5] = "1";

    }


    /**
     *Constructor that creates a new Board using @param aBoard
     *of String array type. This helps with forcing creation
     * of boards for testing*/
    public Board(String[][] aBoard){
        this.board = aBoard;
    }

    // Methods

    /**
     * Prints the board with numbered borders to the console so the user can see the
     * 2D array clearly, and choose which coordinates to enter.
     */
    public void printBoard() {
        System.out.println("   1 2 3 4 5 6 7 8  ");
        for (int i = 1; i <= 8; i++) {
            if ((i < 1) || (i > 8)) {
                System.out.print("  |"); // leaves out the 0 and 9 on the left column of numbers
            } else {
                System.out.print(i + " |"); // prints out number column on left side
            }
            for (int j = 1; j <= 8; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.print(" " + "\n");
        }
    }

    /** Retreives the contents of the board array
     @return board*/
    public String[][] getArray() {
        return this.board;
    }

    /**sets the board so that player can start a saved game from a file
     @return board from the binary file SavedGames.data*/
    public static String[][] loadBoard() throws IOException {
        String[][] temp_board = new String[10][10];
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("SavedGames.data"));
            temp_board = (String[][])input.readObject();
            input.close();
        }
        catch (ClassNotFoundException e) {

        }
        catch (IOException ioe) {

        }
        return temp_board;
    }


    public void setBoard(String[][] aBoard){
        this.board = aBoard;
    }

    /**Saves the state of the game @param none, @return none*/
    public void saveBoard() throws IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("SavedGames.data"));
        output.writeObject(board);
        output.close();
    }


    /**
     * Updates the board and score by using the user's input
     *
     * @param x      The x coordinate that the user inputs
     * @param y      The y coordinate that the user inputs
     * @param player The player who's turn it is (Player 1 or 2)
     * @param array  The array returned by the move() method, which tells us the
     *               number of pieces flipped in total and in each direction
     */
    public void updateBoard(int x, int y, String player, int[] array) {
        int north = array[1]; //checks if there are any pieces that can be flipped in the north direction
        // if thats true, then flips those pieces
        if (north != 0) {
            for (int i = 0; i <= north; i++) {
                board[y - i][x] = player;
            }
        }

        int northeast = array[2]; //checks if there are any pieces that can be flipped in the north east direction
        // if thats true, flips those pieces
        if (northeast != 0) {
            for (int i = 0; i <= northeast; i++) {
                board[y - i][x + i] = player;
            }
        }

        int east = array[3]; //checks if there are any pieces that can be flipped in the east direction
        // if thats true, flips those pieces
        if (east != 0) {
            for (int i = 0; i <= east; i++) {
                board[y][x + i] = player;
            }
        }

        int southeast = array[4];//checks if there are any pieces that can be flipped in the south east direction
        // if thats true, flips those pieces
        if (southeast != 0) {
            for (int i = 0; i <= southeast; i++) {
                board[y + i][x + i] = player;
            }
        }

        int south = array[5];//checks if there are any pieces that can be flipped in the south direction
        // if thats true, flips those pieces
        if (south != 0) {
            for (int i = 0; i <= south; i++) {
                board[y + i][x] = player;
            }
        }

        int southwest = array[6]; //checks if there are any pieces that can be flipped in the south west direction
        // if thats true, flips those pieces
        if (southwest != 0) {
            for (int i = 0; i <= southwest; i++) {
                board[y + i][x - i] = player;
            }
        }

        int west = array[7]; //checks if there are any pieces that can be flipped in the west direction
        // if thats true, flips those pieces
        if (west != 0) {
            for (int i = 0; i <= west; i++) {
                board[y][x - i] = player;
            }
        }

        int northwest = array[8]; //checks if there are any pieces that can be flipped in the north west direction
        // if thats true, flips those pieces
        if (northwest != 0) {
            for (int i = 0; i <= northwest; i++) {
                board[y - i][x - i] = player;
            }
        }

    }

    /**
     * Calculates the score of the player
     *
     * @param player The player who's score will be updated
     * @return turnScore The player's score (number of pieces)
     */
    public int turnScore(String player) {
        int turnScore = 0;
        if (player.equals("1")) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    if (board[i][j].equals("1"))
                        turnScore++;
                }
            }
        } else if (player.equals("2")) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    if (board[i][j].equals("2"))
                        turnScore++;
                }
            }
        }
        return turnScore;
    }

    /**
     * Checks whether the board is full
     *
     * @return full True if the board is filled, false if the board is not filled
     */
    public boolean isFull() {
        int countTotal = 0;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (board[i][j].equals("1") || board[i][j].equals("2")) {
                    countTotal++;
                }
            }
        }
        if (countTotal == 64) {
            return true;
        }
        return false;
    }

}

