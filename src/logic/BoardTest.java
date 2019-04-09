package logic;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Junit test for Board class of Othello game. This class is used to
 * test the logical functioning of the Board class.
 *
 * @author Al Mahmud Dipto
 */

public class BoardTest {

    /**
     * Testing the constructors.
     /**
     * Test the default constructor to see if the board array lengths are equal.
     */

    @Test
    public void test_constructorBoardLength() {

        // Sets size of 2D array as 10 x 10 elements
        String[][] board = new String[10][10];
        int boardLength = board.length;

        // invoke the default board constructor
        Board b1 = new Board();
        int b1Length = b1.getArray().length;

        assertEquals("Expected board length to be 10", boardLength, b1Length);
    }

    /**
     * Test the constructor to see if the board array dimensions are equal.
     */

    @Test
    public void test_constructorBoardDimensions() {

        // Sets size of 2D array as 10 x 10 elements
        String[][] board = new String[10][10];
        Board b1 = new Board(board);

        assertArrayEquals("Expected board dimension to be 10x10", board, b1.getArray());
    }

    /**
     * Test the default constructor to see if the initial game board is set accordingly.
     */

    @Test
    public void test_constructorBoardInitial() {

        // Sets size of 2D array as 10 x 10 elements
        String[][] board = new String[10][10];

        // Fills array with "_", representing empty areas of board
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
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

        Board b1 = new Board(board);

        // invoke the default board constructor
        Board b2 = new Board();

        assertArrayEquals("Expected the initial game board is set accordingly.", b1.getArray(), b2.getArray());
    }


    /**
     * Testing the updateBoard() method by setting up an initial board, player, player inputs
     * and the flipped array to be returned by the move() method of Check class
     * to see if the flipped pieces are being updated on the board accordingly.
     */

    /**
     * Test the updateBoard() method to check if the pieces being flipped horizontally
     * on the board are being updated properly.
     */

    @Test
    public void test_updateBoardH_1() {

        Board board = new Board();
        int x = 6;
        int y = 4;
        String player = "1";
        int[] flippedArray = {1, 0, 0, 0, 0, 0, 0, 1, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 1.", "1", "1");
    }

    @Test
    public void test_updateBoardH_2() {

        Board board = new Board();
        int x = 6;
        int y = 5;
        String player = "2";
        int[] flippedArray = {1, 0, 0, 0, 0, 0, 0, 1, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 2.", "2", "2");
    }

    @Test
    public void test_updateBoardH_3() {

        Board board = new Board();
        int x = 3;
        int y = 5;
        String player = "1";
        int[] flippedArray = {1, 0, 0, 1, 0, 0, 0, 0, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 1.", "1", "1");
    }

    @Test
    public void test_updateBoardH_4() {

        Board board = new Board();
        int x = 3;
        int y = 4;
        String player = "2";
        int[] flippedArray = {1, 0, 0, 1, 0, 0, 0, 0, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 2.", "2", "2");
    }

    /**
     * Test the updateBoard() method to check if the pieces being flipped vertically
     * on the board are being updated properly.
     */

    @Test
    public void test_updateBoardV_5() {

        Board board = new Board();
        int x = 5;
        int y = 3;
        String player = "1";
        int[] flippedArray = {1, 0, 0, 0, 0, 1, 0, 0, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 1.", "1", "1");
    }

    @Test
    public void test_updateBoardV_6() {

        Board board = new Board();
        int x = 4;
        int y = 3;
        String player = "2";
        int[] flippedArray = {1, 0, 0, 0, 0, 1, 0, 0, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 2.", "2", "2");
    }

    @Test
    public void test_updateBoardV_7() {

        Board board = new Board();
        int x = 4;
        int y = 6;
        String player = "1";
        int[] flippedArray = {1, 1, 0, 0, 0, 0, 0, 0, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 1.", "1", "1");
    }

    @Test
    public void test_updateBoardV_8() {

        Board board = new Board();
        int x = 5;
        int y = 6;
        String player = "2";
        int[] flippedArray = {1, 1, 0, 0, 0, 0, 0, 0, 0};
        board.updateBoard(x, y, player, flippedArray);

        assertEquals("Expected player to be 2.", "2", "2");
    }


    /**
     * Testing the turnScore() method to check the player score.
     */

    /**
     * Tests for player 1.
     */

    @Test
    public void test_turnScore_player1() {

        Board b1 = new Board();
        int p1score = b1.turnScore("1");

        assertEquals("Expected player 1 score to be 2", 2, p1score);
    }

    /** Test to check when player 1 score is maximum.
     */

    @Test
    public void test_turnScore_player1Full() {

        String[][] board1;
        board1 = new String[9][9]; // Sets size of 2D array as 10 x 10 elements

        // Fills the array with "1", covering the board
        for (int i = 1; i < board1.length; i++) {
            for (int j = 1; j < board1.length; j++) {
                board1[i][j] = "1";
            }
        }

        Board b1 = new Board(board1);
        int p1score = b1.turnScore("1");

        assertEquals("Expected player 1 score to be 64", 64, p1score);
    }

    /** Test to check when player 1 score is zero.
     */

    @Test
    public void test_turnScore_player1Zero() {

        String[][] board1;
        board1 = new String[9][9]; // Sets size of 2D array as 10 x 10 elements

        // Fills the array with "1", covering the board
        for (int i = 1; i < board1.length; i++) {
            for (int j = 1; j < board1.length; j++) {
                board1[i][j] = "2";
            }
        }

        Board b1 = new Board(board1);
        int p1score = b1.turnScore("1");

        assertEquals("Expected player 1 score to be 0", 0, p1score);
    }

    /** Tests for player 2.
     */

    @Test
    public void test_turnScore_player2() {

        Board b1 = new Board();
        int p2score = b1.turnScore("2");

        assertEquals("Expected player 2 score to be 2", 2, p2score);
    }

    /** Test to check when player 1 score is maximum.
     */

    @Test
    public void test_turnScore_player2Full() {

        String[][] board1;
        board1 = new String[9][9]; // Sets size of 2D array as 10 x 10 elements

        // Fills the array with "1", covering the board
        for (int i = 1; i < board1.length; i++) {
            for (int j = 1; j < board1.length; j++) {
                board1[i][j] = "2";
            }
        }

        Board b1 = new Board(board1);
        int p1score = b1.turnScore("2");

        assertEquals("Expected player 2 score to be 64", 64, p1score);
    }

    /** Test to check when player 1 score is zero.
     */

    @Test
    public void test_turnScore_player2Zero() {

        String[][] board1;
        board1 = new String[9][9]; // Sets size of 2D array as 10 x 10 elements

        // Fills the array with "1", covering the board
        for (int i = 1; i < board1.length; i++) {
            for (int j = 1; j < board1.length; j++) {
                board1[i][j] = "1";
            }
        }

        Board b1 = new Board(board1);
        int p1score = b1.turnScore("2");

        assertEquals("Expected player 2 score to be 0", 0, p1score);
    }

}