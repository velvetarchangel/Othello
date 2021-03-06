package logic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit test for Check class of Othello game. This class is used to
 * test the logical functioning of the Check class.
 *
 * @author Al Mahmud Dipto
 */

public class CheckTest {

    /**
     * Testing the move() method by creating an intial game board, setting the
     * player inputs and comparing it with the array to be returned.
     */

    /**
     * Test the move() method to check for the right number of pieces flipped
     * horizontally.
     */

    @Test
    public void test_moveH_1() {

        Board board = new Board();// initializes the board
        Player player = new Player("1", "Black");// initializes the player object
        //picking appropriate coordinate
        int x = 6;
        int y = 4;
        int[] flipper = Check.move(x, y, player, board);// checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 0, 0, 0, 0, 0, 0, 1, 0};

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }

    /*Places the piece at 6,5 and expect 1 piece black piece on the board to be flipped*/
    @Test
    public void test_moveH_2() {

        Board board = new Board();// initializes the board
        Player player = new Player("2", "white");// initializes the player object
        //picking appropriate coordinate
        int x = 6;
        int y = 5;
        int[] flipper = Check.move(x, y, player, board);// checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 0, 0, 0, 0, 0, 0, 1, 0};

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }

    
    /*Places the piece at 6,5 and expect 1 piece white piece on the board to be flipped*/
    @Test
    public void test_moveH_3() {

        Board board = new Board(); // initializes the board
        Player player = new Player("1", "Black"); // initializes the player object
        //picking appropriate coordinate
        int x = 3;
        int y = 5;
        int[] flipper = Check.move(x, y, player, board); // checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 0, 0, 1, 0, 0, 0, 0, 0}; // expected flipped array

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }

    
    /*Places the piece at 6,5 and expect 1 piece black piece on the board to be flipped*/
    @Test
    public void test_moveH_4() {

        Board board = new Board();// initializes the board
        Player player = new Player("2", "White");// initializes the player object
        //picking appropriate coordinate
        int x = 3;
        int y = 4;
        int[] flipper = Check.move(x, y, player, board); // checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 0, 0, 1, 0, 0, 0, 0, 0};// expected flipped array

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }

    /**
     * Test the move() method to check for the right number of pieces fliped
     * vertically.
     */

    @Test
    public void test_moveV_5() {

        Board board = new Board();// initializes the board
        Player player = new Player("1", "Black");// initializes the player object
        //picking appropriate coordinate
        int x = 5;
        int y = 3;
        int[] flipper = Check.move(x, y, player, board);// checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 0, 0, 0, 0, 1, 0, 0, 0};// expected flipped array

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }

    @Test
    public void test_moveV_6() {

        Board board = new Board();// initializes the board
        Player player = new Player("2", "White");// initializes the player object
        //picking appropriate coordinate
        int x = 4;
        int y = 3;
        int[] flipper = Check.move(x, y, player, board);// checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 0, 0, 0, 0, 1, 0, 0, 0};// expected flipped array

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }

    @Test
    public void test_moveV_7() {

        Board board = new Board();// initializes the board
        Player player = new Player("1", "Black");// initializes the player object
        //picking appropriate coordinate
        int x = 4;
        int y = 6;
        int[] flipper = Check.move(x, y, player, board);// checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 1, 0, 0, 0, 0, 0, 0, 0};// expected flipped array

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }

    @Test
    public void test_moveV_8() {

        Board board = new Board();// initializes the board
        Player player = new Player("2", "White");// initializes the player object
        //picking appropriate coordinate
        int x = 5;
        int y = 6;
        int[] flipper = Check.move(x, y, player, board);// checks to see if the move is valid and generates the flipped array
        int[] flippedArray = {1, 1, 0, 0, 0, 0, 0, 0, 0};// expected flipped array

        assertArrayEquals("Expected 1 of the pieces to be flipped.", flippedArray, flipper);
    }


    /**
     * Test the AnyMovesLeft() method to check if there are any valid moves left by
     * setting up the initial game board and a player.
     */

    /**
     * Checking when there are valid moves left.
     */
    /** Checks if black player has any moves left in the original state of the board
    * should return true*/
    @Test
    public void test_anyMovesLeft_black() {

        Board board = new Board();// initializes the board
        Player player = new Player("1", "Black");// initializes the player object
        boolean detective = Check.AnyMovesLeft(player, board); 

        assertEquals("Expected there are moves left for player 1.", true, detective);
    }

    /** Checks if white player has any moves left in the original state of the board
    * should return true*/
    @Test
    public void test_anyMovesLeft_white() {

        Board board = new Board();// initializes the board
        Player player = new Player("2", "White");// initializes the player object
        boolean detective = Check.AnyMovesLeft(player, board);

        assertEquals("Expected there are moves left for player 2.", true, detective);
    }


    /** Checks if black player has any moves left in the original state of the board, 
    * fills the board up the board with 1's and checks if the payer has any moves left
    * should return false*/
    @Test
    public void test_anyMovesLeft_forWhite() { // Checking if any valid moves are left for White.

        String[][] board1;
        board1 = new String[10][10]; // Sets size of 2D array as 10 x 10 elements

        // Fills the array with "1", covering the board
        for (int i = 1; i < board1.length; i++) {
            for (int j = 1; j < board1.length; j++) {
                board1[i][j] = "1";
            }
        }

        Board board = new Board(board1);
        Player player = new Player("2", "White");
        boolean detective = Check.AnyMovesLeft(player, board);

        assertEquals("Expected there are no moves left for player 2.", false, detective);
    }

    /** Checks if black player has any moves left in the original state of the board, 
    * fills the board up the board with 2's and checks if the payer has any moves left
    * should return false*/
    @Test
    public void test_anyMovesLeft_forBlack() { // Checking if any valid moves are left for Black.

        String[][] board1;
        board1 = new String[10][10]; // Sets size of 2D array as 10 x 10 elements

        // Fills the array with "2", covering the board
        for (int i = 1; i < board1.length; i++) {
            for (int j = 1; j < board1.length; j++) {
                board1[i][j] = "2";
            }
        }

        Board board = new Board(board1);
        Player player = new Player("1", "Black");
        boolean detective = Check.AnyMovesLeft(player, board);

        assertEquals("Expected there are no more moves left for player 1.", false, detective);
    }

}
