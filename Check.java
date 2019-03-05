public class Check {
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
    * @param x x coordinate of point to check
    * @param y y coordinate of point to check
    * @param player player who imputs a moves
    * @param gameboard board to check
    * @return flipped An array of 9 integer elements. The first element is total
    *         number of pieces that will be flipped, the second is the number of
    *         pieces flipped in the north direction from the piece at the x,y
    *         coordinate, the next elements keep track of the number of pieces
    *         flipped following the north direction (clockwise).
    **/

    public static int[] move(int x, int y, String player, Board gameboard) {

    board = gameboard.getArray();


    // Resets the flipped array everytime the player tries to make a move
    for (int e = 0; e < 9; e++) {
      flipped[e] = 0;
    }

    // Determines the number of the other player
    if (player.equals("1")) {
      otherPlayer = "2";
    } else if (player.equals("2")) {
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


  public static boolean AnyMovesLeft(String player, Board gameBoard){
    board = gameBoard.getArray();
    boolean moves_left = false;
    for (int i = 0; i < 9; i++){
      for (int j = 0; j < 9; j++){
        if (moves_left == false){
          if (board[j][i].equals("_"))
          {
            int[] flipped = Check.move(i + 1, j + 1, player, gameBoard);
            // place piece of the player
            if (flipped[0] != 0)
            {
              moves_left = true;
              break;
            }
          }
        }
      }
    }
    return moves_left;
  }


}
