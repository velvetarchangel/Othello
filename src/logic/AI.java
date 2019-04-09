package logic;


/**
 * This class codes for the AI, the AI works by randomly generating 2
 * coordinates on the board and checks if the moves are valid,
 * then the AI checks which of the moves would generate a higher score and
 * selects that particular coordinate on the board
 *
 * @author Vivian Huynh
 * @version 1.0
 */

public class AI extends Player
{

    // Instance variables
    int[] move;

    // Constructors
    public AI(String number, String colour) {
        setNumber(number);
        setColour(colour);
        this.name = "Computer";
    }

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


    //chooses which move will create a higher score
    public static int[] chooseMove(Player player, Board board)
    {

        int[] firstMove = AI.firstMove();
        int[] secondMove = AI.secondMove();
        // Randomizes firstMove until it flips pieces
        int[] flipped1 = Check.move(firstMove[0], firstMove[1], player, board);
        while (flipped1[0] == 0) {
            firstMove  = AI.firstMove();
            flipped1 = Check.move(firstMove[0], firstMove[1], player, board);
        }

        // Randomizes secondMove until it flips pieces
        int[] flipped2 = Check.move(secondMove[0], secondMove[1], player, board);
        while (flipped2[0] == 0) {
            secondMove  = AI.firstMove();
            flipped2 = Check.move(secondMove[0], secondMove[1], player, board);
        }

        // Returns the move that flips the most pieces
        if (flipped1[0] > flipped2[0]) {
            return firstMove;
        } else if (flipped1[0] < flipped2[0]) {
            return secondMove;
        } else {
            return firstMove;
        }
    }




}
