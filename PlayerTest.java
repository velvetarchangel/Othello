import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**@Author Himika Dastidar, Al Mahmud Dipto
*Designed to test the player class thoroughly and all the methods in it
* tests the input sanitization in the Player class in the text based version 
* to make sure (1) the input is within the range of the board, (2) the input 
* actually indicates a legal move. Also test that the constructors in the class
* are working, the setters and getters are working */

public class PlayerTest {
    
    /**Tests the default constructor in the player class to make 
    * sure it returns player with parameters colour: "BLACK", player: "1", Score: 0, name: "" */
    @Test
    public void testDefaultConstructor()
    {
        Player p = new Player();
        assertEquals("Default player colour should be Black", "Black", p.getColour());
        assertEquals("Default player score should be 0", 0, p.getScore());
        assertEquals("Default player name should be an empty string", "", p.getName());
        assertEquals("Default player should be 1", "1", p.getPlayer());
    }

    /**Tests the constructor with @param String player, @param String colour*/
    @Test
    public void testConstructor_player_colour()
    {
        Player p = new Player("1", "White");
        assertEquals("Player colour should be White", "White", p.getColour());
        assertEquals("Default player score should be 0", 0, p.getScore());
        assertEquals("Default player name should be an empty string", "", p.getName());
        assertEquals("Player should be 1", "1", p.getPlayer());
    }

    /**Tests the constructor with @param String player, @param String colour*/
    @Test
    public void testConstructor_player_colour_name(){
        Player p = new Player("2", "White", "Bob");
        assertEquals("Player colour should be White", "White", p.getColour());
        assertEquals("Default player score should be 0", 0, p.getScore());
        assertEquals("Default player name should be an empty string", "Bob", p.getName());
        assertEquals("Player should be 2", "2", p.getPlayer());
    }

    /**Tests the constructor with @param String player, @param String colour and @param score*/
    @Test
    public void testConstructor_player_colour_score()
    {
        Player p = new Player("2", "White", 20);
        assertEquals("Player colour should be White", "White", p.getColour());
        assertEquals("Player score should be 20", 20, p.getScore());
        assertEquals("Default player name should be an empty string", "", p.getName());
        assertEquals("Expected player to be 2", "2", p.getPlayer());
    }

    /**Tests the constructor with @param String player, @param String colour, @param Name and @param score*/
    @Test
    public void testConstructor_player_colour_name_score(){
        Player p = new Player("2", "Black", "Mario", 30);
        assertEquals("Player colour should be Black", "Black", p.getColour());
        assertEquals("Player score should be 30", 30, p.getScore());
        assertEquals("Player name should be Mario", "Mario", p.getName());
        assertEquals("Player should be 2", "2", p.getPlayer());
    }

    
    /** Tests the copy constructor**/
    @Test
    public void testCopyConstructor()
    {
        Player p = new Player("1", "White","Othello",0);
        Player copy = new Player(p);
        assertEquals("Player colour should be White", "White", copy.getColour());
        assertEquals("Player score should be 0", 0, copy.getScore());
        assertEquals("Player name should be Othello", "Othello", copy.getName());
        assertEquals("Player should be 1", "1", copy.getPlayer());
    }

    /**tests Getter and setter for the class */
    @Test
    public void testGetter_and_Setter()
    {
        Player p = new Player("1", "White", "Othello, 0);
        p.setScore(20);
        p.setColour("Black);
        p.setName("Dracula");
        p.setPlayer("2");
        assertEquals("Player colour should be Black", "Black", p.getColour());
        assertEquals("Player score should be 20", 20, p.getScore());
        assertEquals("Player name should be Dracula", "Dracula", p.getName());
        assertEquals("Player should be 2", "2", p.getPlayer());
    }

         
    /**Tests the input sanitization method in the player class
    * (1) Tests whether the method isInt can properly decipher when the input is an integer
    * (2) Tests whether the method withinRange can decipher whether the input is within the board
    * (3) Tests whether the method isFlipped is */
   
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
        
        assertEquals("Player input should return false", "false", answer); // tests whether it can decipher between int and string
        assertEquals("Player input out of range", "false", too_high_Test); // test if it returns false when input is int, but not in the board
        assertEquals("Player input out of range", "false", too_low_Test); 
        assertEquals("Player input in range", "true", ir_top); // tests if it returns true when input is int and in the baord
        assertEquals("Player input in range", "true", ir_bottom);
}
       
       /**Tests whether the input consists of a move that is logically 
       * valid in the game
       */
                    
       // Tests the flipsPiece method with an invalid move
       @Test
       public void test_flipsPiece_invalid_move(){
           Player p = new Player();
           Board b = new Board();
           
           int[] invalid_move {3,4};
           boolean v_move = p.flipsPieces(valid_move, b, p);
           boolean i_move = p.flipsPieces(invalid_move, b, p);
           assertEquals("Pieces cannot be flipped", "false", invalid_move);
       }
        
       // Test the flipsPiece method with an valid move
       @Test
       public void test_flipsPiece_valid_move(){
           Player p = new Player();
           Board b = new Board();
           int[] valid_move = {3,5};
           boolean v_move = p.flipsPiece(valid_move, b,p);
           assertEquals("Pieces can be flipped", "true", v_move);
                        
       }
                    
