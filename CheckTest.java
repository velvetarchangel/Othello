import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Junit test for Check class of Othello game. This class is used to
 * test the logical functioning of the Check class.
 *
 * @author Al Mahmud Dipto
 */

public class CheckTest {
  // test the move method

  /**
   * Test the move method to check for the right number of pieces fliped.
   */
  @Test
    public void test_move_1() {
      Board box = new Board();
      Player boi = new Player("1", "Black");
      int x = 6;
      int y = 4;
      int[] flipper = Check.move(x, y, boi, box);
      int[] flippedArray = {1, 0, 0, 0, 0, 0, 0, 1, 0};
      assertArrayEquals("Expected 1 of the pieces to be flipped", flippedArray, flipper);
    }

  @Test
    public void test_move_2() {
      Board box = new Board();
      Player boi = new Player("2", "White");
      int x = 4;
      int y = 3;
      int[] flipper = Check.move(x, y, boi, box);
      int[] flippedArray = {1, 0, 0, 0, 0, 1, 0, 0, 0};
      assertArrayEquals("Expected 1 of the pieces to be flipped", flippedArray, flipper);
    }

/**
  @Test
    public void test_move_3() {
      Board box = new Board();
      Player boi = new Player("1", "Black");
      int x = 3;
      int y = 4;
      int[] oldFlippedArray = {1, 0, 0, 0, 0, 1, 0, 0, 0};
      box.updateBoard(4, y, "1", oldFlippedArray);
      int[] flipper = Check.move(x, y, boi, box);
      int[] flippedArray = {1, 0, 0, 1, 0, 0, 0, 0, 0};
      assertArrayEquals("Expected 1 of the pieces to be flipped", flippedArray, flipper);
    }*/
}
