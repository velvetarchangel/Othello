/**This class codes for the AI, the AI works by randomly generating 2 
 * coordinates on the board and checks if the moves are valid, 
 * then the AI checks which of the moves would generate a higher score and
 * selects that particular coordinate on the board
 * 
 * @author Vivian Hyunh
 * @version 1.0
 */

public class AI extends Player 
{

  // Instance variables

  private int computerScore;

  // Methods

  // Generates the first move
  public static int[] firstMove() {
    int[] xy1 = {0,0};
    xy1[0] = (int)(Math.random() * 8 + 1);
    xy1[1] = (int)(Math.random() * 8 + 1);
    return xy1;
  }

  //generates the second move
  public static int[] secondMove() {
    int[] xy2 = {0,0};
    xy2[0] = (int)(Math.random() * 8 + 1);
    xy2[1] = (int)(Math.random() * 8 + 1);
    return xy2;
  }

  /*
  //chooses which move will create a higher score
  public static void chooseMove() 
  {


  }*/

}
