package logic;


/**
 * This class processes the validity of moves made by the player
 * 1. Checks whether a move is legal and valid
 * 2. Checks if the player has any moves left
 * @author Jayoo Hwang
 * @version 1.0
 */
public class Check {

    // Instance variables
    private static int[] flipped = new int[9];
    private static String otherPlayer;
    private static String[][] board;
    private static int n;


    /**
     * Checks each direction to see whether pieces can be flipped to the current
     * player's number according to the user's inputted coordinates. Lists the total
     * number of pieces flipped, and the number of pieces flipped in each direction
     * to an array.
     *
     * @param x         x coordinate of point to check
     * @param y         y coordinate of point to check
     * @param player    player who imputs a moves
     * @param gameboard board to check
     * @return flipped An array of 9 integer elements. The first element is total
     *         number of pieces that will be flipped, the second is the number of
     *         pieces flipped in the north direction from the piece at the x,y
     *         coordinate, the next elements keep track of the number of pieces
     *         flipped following the north direction (clockwise).
     **/

    public static int[] move(int x, int y, Player player, Board gameboard) {

        board = gameboard.getArray();

        // Resets the flipped array everytime the player tries to make a move
        for (int e = 0; e < 9; e++) {
            flipped[e] = 0;
        }

        // Determines the number of the other player
        if (player.number.equals("1")) {
            otherPlayer = "2";
        } else if (player.number.equals("2")) {
            otherPlayer = "1";
        }

        // If spot is empty, piece can be placed, so check the number of pieces that can
        // be flipped in each direction
        if (board[y][x].equals("_")) {
            n = 1;

            // Check north
            if (board[y - n][x].equals(otherPlayer)) {
                while (board[y - n][x].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y - n][x].equals("3")) {
                    n = 1;
                } else if (board[y - n][x].equals("_")) {
                    n = 1;
                } else {
                    flipped[1] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

            // Check northeast
            if (board[y - n][x + n].equals(otherPlayer)) {
                while (board[y - n][x + n].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y - n][x + n].equals("3")) {
                    n = 1;
                } else if (board[y - n][x + n].equals("_")) {
                    n = 1;
                } else {
                    flipped[2] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

            // Check east //
            if (board[y][x + n].equals(otherPlayer)) {
                while (board[y][x + n].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y][x + n].equals("3")) {
                    n = 1;
                } else if (board[y][x + n].equals("_")) {
                    n = 1;
                } else {
                    flipped[3] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

            // Check southeast
            if (board[y + n][x + n].equals(otherPlayer)) {
                while (board[y + n][x + n].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y + n][x + n].equals("3")) {
                    n = 1;
                } else if (board[y + n][x + n].equals("_")) {
                    n = 1;
                } else {
                    flipped[4] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

            // check south
            if (board[y + n][x].equals(otherPlayer)) {
                while (board[y + n][x].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y + n][x].equals("3")) {
                    n = 1;
                } else if (board[y + n][x].equals("_")) {
                    n = 1;
                } else {
                    flipped[5] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

            // Check southwest
            if (board[y + n][x - n].equals(otherPlayer)) {
                while (board[y + n][x - n].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y + n][x - n].equals("3")) {
                    n = 1;
                } else if (board[y + n][x - n].equals("_")) {
                    n = 1;
                } else {
                    flipped[6] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

            // check west
            if (board[y][x - n].equals(otherPlayer)) {
                while (board[y][x - n].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y][x - n].equals("3")) {
                    n = 1;
                } else if (board[y][x - n].equals("_")) {
                    n = 1;
                } else {
                    flipped[7] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

            // Check northwest
            if (board[y - n][x - n].equals(otherPlayer)) {
                while (board[y - n][x - n].equals(otherPlayer)) {
                    n += 1;
                }
                if (board[y - n][x - n].equals("3")) {
                    n = 1;
                } else if (board[y - n][x - n].equals("_")) {
                    n = 1;
                } else {
                    flipped[8] = n - 1;
                    flipped[0] += n - 1;
                    n = 1;
                }
            }

        }
        return flipped;
    }

    /**
     * Checks if the player who's turn it is has any moves left
     * goes through the board and places the player's piece in the board to see if
     * any of the opponent's pieces can be flipped at that position, stops the loop
     * if there is atleast 1 move for the player
     * @param player to check which piece to place and which pievce to flip
     * @param gameBoard to place the piece in an empty slot "_" and see if there
     * are any adjacent pieces of the opponent that can be flipped
     * @return moves_left which is a boolean
     **/
    public static boolean AnyMovesLeft(Player player, Board gameBoard) {
        board = gameBoard.getArray();
        boolean moves_left = false;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                //System.out.println("check " + Integer.toString(i) + " " + Integer.toString(j));
                if (moves_left == false) {
                    if (board[j][i].equals("_")) {
                        flipped = Check.move(i, j, player, gameBoard);
                        // delete
                        // place piece of the player
                        if (flipped[0] != 0) {

                            moves_left = true;
                            //break;
                        }
                    }
                }
            }
        }
        return moves_left;
    }

}

