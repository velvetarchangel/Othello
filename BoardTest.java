import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Junit test for Board class of Othello game. This class is used to
 * test the logical functioning of the Board class.
 *
 * @author Al Mahmud Dipto
 */

public class BoardTest {
  // test the constructors

  /**
   * Test the default constructor to see if the board array lengths are equal.
   */
  @Test
    public void test_constructorBoardLength() {
      String[][] board = new String[10][10];
      int boardLength = board.length;
      Board b1 = new Board();
      int b1Length = b1.getArray().length;
      assertEquals("Expected board length to be 10", boardLength, b1Length);
    }

  /**
   * Test the constructor to see if the board array dimensions are equal.
   */
  @Test
    public void test_constructorBoardDimensions() {
      String[][] board = new String[10][10];
      Board b1 = new Board(board);
      assertArrayEquals("Expected board dimension to be 10x10", board, b1.getArray());
    }

  /**
   * Test the turnScore method to check the player score.
   */
  @Test
    public void test_turnScore_player1() {
      Board b1 = new Board();
      int p1score = b1.turnScore("1");
      assertEquals("Expected player 1 score to be 2", 2, p1score);
    }
  @Test
    public void test_turnScore_player2() {
      Board b1 = new Board();
      int p2score = b1.turnScore("2");
      assertEquals("Expected player 2 score to be 2", 2, p2score);
    }
}
