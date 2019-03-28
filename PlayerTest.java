import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Junit test for Player class of Othello game. This class is used to
 * test the logical functioning of the Player class.
 *
 * @author Al Mahmud Dipto
 */

public class PlayerTest {
  // test the constructors

  /**
   * Test the default constructor to see if the player's colour and score
   * has been set initially.
   */
  @Test
    public void test_constructorDefault() {
      Player con = new Player();
      assertEquals("Expected initial player number to be 1", "1", con.getNumber());
      assertEquals("Expected initial player token colour to be Black", "Black", con.getColour());
      assertEquals("Expected initial player score to be 0", 0, con.getScore());
    }

  /**
   * Test the constructor to see if the player's number and colour
   * has been set.
   */
  @Test
    public void test_constructorWithNumberAndColour() {
      Player con = new Player("1", "Black");
      String num = con.getNumber();
      String col = con.getColour();

      assertEquals("Expected player number to be 1", "1", con.getNumber());
      assertTrue("Player number should either be 1 or 2", num.equals("1") || num.equals("2"));
      assertEquals("Expected player token colour to be Black", "Black", con.getColour());
      assertTrue("Player colour should either be Black or White", col.equals("Black") || col.equals("White"));
    }
    

    /**
     * Test the constructor to see if the player's number, colour and name
     * has been set.
     */
    @Test
      public void test_constructorWithNumberColourAndName() {
        Player con = new Player("1", "Black", "Joestar");
        String num = con.getNumber();
        String col = con.getColour();

        assertEquals("Expected player number to be 1", "1", con.getNumber());
        assertTrue("Player number should either be 1 or 2", num.equals("1") || num.equals("2"));
        assertEquals("Expected player token colour to be Black", "Black", con.getColour());
        assertTrue("Player colour should either be Black or White", col.equals("Black") || col.equals("White"));
        assertEquals("Expected player name to be Joestar", "Joestar", con.getName());
      }

      /**
       * Test the constructor to see if the player's number, colour and score
       * has been set.
       */
      @Test
        public void test_constructorWithNumberColourAndScore() {
          Player con = new Player("1", "Black", 69);
          String num = con.getNumber();
          String col = con.getColour();
          int score = con.getScore();

          assertEquals("Expected player number to be 1", "1", con.getNumber());
          assertTrue("Player number should either be 1 or 2", num.equals("1") || num.equals("2"));
          assertEquals("Expected player token colour to be Black", "Black", con.getColour());
          assertTrue("Player colour should either be Black or White", col.equals("Black") || col.equals("White"));
          assertEquals("Expected player score to be 69", 69, con.getScore());
          assertTrue("Player score should be 0 or higher", score >= 0);
        }

        /**
         * Test the constructor to see if the player's number, colour, name
         * and score has been set.
         */
        @Test
          public void test_constructorWithNumberColourNameAndScore() {
            Player con = new Player("1", "Black", "Joestar", 69);
            String num = con.getNumber();
            String col = con.getColour();
            int score = con.getScore();

            assertEquals("Expected player number to be 1", "1", con.getNumber());
            assertTrue("Player number should either be 1 or 2", num.equals("1") || num.equals("2"));
            assertEquals("Expected player token colour to be Black", "Black", con.getColour());
            assertTrue("Player colour should either be Black or White", col.equals("Black") || col.equals("White"));
            assertEquals("Expected player name to be Joestar", "Joestar", con.getName());
            assertEquals("Expected player score to be 69", 69, con.getScore());
            assertTrue("Player score should be 0 or higher", score >= 0);
          }

          /**
           * Test the copy constructor.
           */
          @Test
            public void test_constructorCopy() {
              Player con = new Player("1", "Black", "Joestar", 69);
              Player copy = new Player(con);

              assertEquals("Expected player number to be 1", "1", copy.getNumber());
              assertEquals("Expected player token colour to be Black", "Black", copy.getColour());
              assertEquals("Expected player name to be Joestar", "Joestar", copy.getName());
              assertEquals("Expected player score to be 69", 69, copy.getScore());
            }
}
