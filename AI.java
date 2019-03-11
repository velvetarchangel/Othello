
public class AI extends Player {

  // Instance variables

  private int computerScore;

  // Methods

  public static int[] firstMove() {
    int[] xy1 = {0,0};
    xy1[0] = (int)(Math.random() * 8 + 1);
    xy1[1] = (int)(Math.random() * 8 + 1);
    return xy1;
  }

  public static int[] secondMove() {
    int[] xy2 = {0,0};
    xy2[0] = (int)(Math.random() * 8 + 1);
    xy2[1] = (int)(Math.random() * 8 + 1);
    return xy2;
  }

  public static void chooseMove() {


  }

}
