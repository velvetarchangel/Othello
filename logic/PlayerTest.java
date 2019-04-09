package logic;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


/**
 * Junit test for Player class of Othello game. This class is used to
 * test the logical functioning of the Player class.
 *
 * @author Al Mahmud Dipto
 */

class PlayerTest {
  // test the constructors

  /**java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore <name of test class>
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

           /**tests Getter and setter for the class */
    @Test
    public void testGetter_and_Setter()
    {
        Player p = new Player("1", "White", "Othello", 0);
        p.setScore(20);
        p.setColour("Black");
        p.setName("Dracula");
        p.setNumber("2");
        assertEquals("Player colour should be Black", "Black", p.getColour());
        assertEquals("Player score should be 20", 20, p.getScore());
        assertEquals("Player name should be Dracula", "Dracula", p.getName());
        assertEquals("Player should be 2", "2", p.getNumber());
    }

         
    /**tests the input sanitization method in the player class*/
   
    /** Tests whether the user input is within range, is valid and whether it is a permissible move*/
    @Test 
    public void test_input_sanitization(){
        Player p = new Player();
        String input = "p";
        String oor_too_high = "9";
        String oor_too_low = "0";
        String in_range_top = "8";
        String in_range_bottom = "1";
        boolean answer = p.isInt(input);
        boolean too_high_Test = p.withinRange(oor_too_high);
        boolean too_low_Test = p.withinRange(oor_too_low);
        boolean ir_top = p.withinRange(in_range_top);
        boolean ir_bottom = p.withinRange(in_range_bottom);
        
        assertEquals("Player input should return false", false, answer);
        assertEquals("Player input out of range", false, too_high_Test);
        assertEquals("Player input out of range", false, too_low_Test);
        assertEquals("Player input in range", true, ir_top);
        assertEquals("Player input in range", true, ir_bottom);
}
       
       /**Tests whether the input consists of a move that is logically 
       * valid in the game*/
                    
       // Tests the flipsPiece method with a valid move
       @Test
       public void test_flipsPiece_invalid_move(){
           Player p = new Player();
           Board b = new Board();
           
           int[] invalid_move = {3,4};
           boolean i_move = p.flipsPieces(invalid_move, b);
           assertEquals("Pieces cannot be flipped", false, i_move);
       }
        
       // Test the flipsPiece method with an invalid move
       @Test
       public void test_flipsPiece_valid_move(){
           Player p = new Player();
           Board b = new Board();
           int[] valid_move = {3,5};
           boolean v_move = p.flipsPieces(valid_move, b);
           assertEquals("Pieces can be flipped", true , v_move);
                        
       }
}


