import static org.junit.Assert.*;
import org.junit.Test;


/**@Author Himika Dastidar
*Designed to test the player class thoroughly and all the methods in it*/

public class PlayerTest {

    
    /**Tests the default constructor in the player class*/
    @Test
    public void testDefaultConstructor()
    {
        Player p = new Player();
        assertEquals("Default player colour should be Black", "Black", p.getColour());
        assertEquals("Default player score should be 0", 0, p.getScore());
        assertEquals("Default player name should be an empty string", "", p.getName());
        assertEquals("Default player should be 1", "1", p.getNumber());
    }

    /**Tests the constructor with @param String player, @param String colour*/
    @Test
    public void testConstructor_player_colour()
    {
        Player p = new Player("1", "White");
        assertEquals("Player colour should be White", "White", p.getColour());
        assertEquals("Default player score should be 0", 0, p.getScore());
        assertEquals("Default player name should be an empty string", "", p.getName());
        assertEquals("Player should be 1", "1", p.getNumber());
    }

    /**Tests the constructor with @param String player, @param String colour*/
    @Test
    public void testConstructor_player_colour_name(){
        Player p = new Player("2", "White", "Bob");
        assertEquals("Player colour should be White", "White", p.getColour());
        assertEquals("Default player score should be 0", 0, p.getScore());
        assertEquals("Default player name should be an empty string", "Bob", p.getName());
        assertEquals("Player should be 2", "2", p.getNumber());
    }

    /**Tests the constructor with @param String player, @param String colour and @param score 
    */
    @Test
    public void testConstructor_player_colour_score()
    {
        Player p = new Player("2", "White", 20);
        assertEquals("Player colour should be White", "White", p.getColour());
        assertEquals("Player score should be 20", 20, p.getScore());
        assertEquals("Default player name should be an empty string", "", p.getName());
        assertEquals("Expected player to be 2", "2", p.getNumber());
    }

    /**Tests the constructor with @param String player, @param String colour, @param Name and @param score
    */
    @Test
    public void testConstructor_player_colour_name_score(){
        Player p = new Player("2", "Black", "Mario", 30);
        assertEquals("Player colour should be Black", "Black", p.getColour());
        assertEquals("Player score should be 30", 30, p.getScore());
        assertEquals("Player name should be Mario", "Mario", p.getName());
        assertEquals("Player should be 2", "2", p.getNumber());
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
        assertEquals("Player should be 1", "1", copy.getNumber());
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
        
        assertEquals("Player input should return false", "false", answer);
        assertEquals("Player input out of range", "false", too_high_Test);
        assertEquals("Player input out of range", "true", too_low_Test);
        assertEquals("Player input in range", "true", ir_top);
        assertEquals("Player input in range", "true", ir_bottom);
}
       
       /**Tests whether the input consists of a move that is logically 
       * valid in the game*/
                    
       // Tests the flipsPiece method with a valid move
       @Test
       public void test_flipsPiece_invalid_move(){
           Player p = new Player();
           Board b = new Board();
           
           int[] invalid_move =  {3,4};
           boolean i_move = p.flipsPieces(invalid_move, b);
           assertEquals("Pieces cannot be flipped", "false", invalid_move);
       }
        
       // Test the flipsPiece method with an invalid move
       @Test
       public void test_flipsPiece_valid_move(){
           Player p = new Player();
           Board b = new Board();
           int[] valid_move = {3,5};
           boolean v_move = p.flipsPieces(valid_move, b);
           assertEquals("Pieces can be flipped", "true", v_move);
                        
       }
                    
                    
     @Test
     public void test_getInput_check_4_valid_moves(){
         Player p = new Player();
         Board b = new Board();
         int[] move_1 = {5,3};
         int[] move_2 = {3,5};
         int[] move_3 = {4,6};
         int[] move_4 = {6,4};
         int[] x_y_1 = p.getInput(b);
         int[] x_y_2 = p.getInput(b);
         int[] x_y_3 = p.getInput(b);
         int[] x_y_4 = p.getInput(b);
         int x1 = x_y_1[0];
         int y1 = x_y_1[1];
         int x2 = x_y_2[0];
         int y2 = x_y_2[1];
         int x3 = x_y_3[0];
         int y3 = x_y_3[1];
         int x4 = x_y_4[0];
         int y4 = x_y_4[1];
         
         
         assertEquals("Checking for valid x coordinate", 5, x1);
         assertEquals("Checking for valid y coordinate", 3, y1);
         assertEquals("Checking for valid x coordinate", 3, x2);
         assertEquals("Checking for valid y coordinate", 5, y2);
         assertEquals("Checking for valid x coordinate", 4, x3);
         assertEquals("Checking for valid y coordinate", 6, y3);
         assertEquals("Checking for valid x coordinate", 6, x4);
         assertEquals("Checking for valid y coordinate", 4, y4);
         
     }
    }
