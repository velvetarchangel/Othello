
public class Board {

  // Instance variables
  private String[][] board;
  private int n; // counts how many pieces are flipped in a direction
  private int[] flipped = new int[9];
  private String otherPlayer = "0";

  /**
   * This is the default constructor and it creates a 2D array which represents
   * the board around the board it creates a border with 3's this is to checking
   * moves at the edges of the board
   */
  public Board() {
    this.board = new String[10][10];
    for (int i = 1; i < this.board.length; i++) {
      for (int j = 1; j < this.board.length; j++) {
        board[i][j] = "_";
      }
    }

    // Fills edges of board with 3's to help with edge cases
    for (int j = 0; j < 10; j++) {
      for (int i = 0; i < 10; i++) {
        board[0][j] = "3"; // top
        board[9][j] = "3"; // bottom
        board[i][0] = "3"; // left
        board[i][9] = "3"; // right
      }
    }
    /** this sets pieces in the middle of the board to set up the initial board */
    board[4][4] = "1";
    board[4][5] = "2";
    board[5][4] = "2";
    board[5][5] = "1";
  }

  /**
   * prints the board on to the terminal so that the user can visualize it prints
   * labels on the x and y axis so that it is easier for the user to see it
   */
  public void printBoard() {
    System.out.println("   1 2 3 4 5 6 7 8  ");
    for (int i = 1; i <= 8; i++) {
      if ((i < 1) || (i > 8)) {
        System.out.print("  |"); // leaves out the 0 and 9 on the left column of numbers
      } else {
        System.out.print(i + " |"); // prints out number column on left side
      }
      for (int j = 1; j <= 8; j++) {
        System.out.print(board[i][j] + "|");
      }
      System.out.print(" " + "\n");
    }
  }

  /**
   * takes in user input
   * 
   * @param x      = x coordinate that the user inputs
   * @param y      = y coordinate the user inputs
   * @param player = which player's turn it is
   * @param array  =
   */
  public void updateBoard(int x, int y, String player, int[] array) {
    int north = array[1];
    if (north != 0) {
      for (int i = 0; i <= north; i++) {
        board[y - i][x] = player;
      }
    }

    int northeast = array[2];
    if (northeast != 0) {
      for (int i = 0; i <= northeast; i++) {
        board[y - i][x + i] = player;
      }
    }

    int east = array[3];
    if (east != 0) {
      for (int i = 0; i <= east; i++) {
        board[y][x + i] = player;
      }
    }

    int southeast = array[4];
    if (southeast != 0) {
      for (int i = 0; i <= southeast; i++) {
        board[y + i][x + i] = player;
      }
    }

    int south = array[5];
    if (south != 0) {
      for (int i = 0; i <= south; i++) {
        board[y + i][x] = player;
      }
    }

    int southwest = array[6];
    if (southwest != 0) {
      for (int i = 0; i <= southwest; i++) {
        board[y + i][x - i] = player;
      }
    }

    int west = array[7];
    if (west != 0) {
      for (int i = 0; i <= west; i++) {
        board[y][x - i] = player;
      }
    }

    int northwest = array[8];
    if (northwest != 0) {
      for (int i = 0; i <= northwest; i++) {
        board[y - i][x - i] = player;
      }
    }
  }

  /**
   * calculates the score of the player whose move it is takes in
   * 
   * @param player and @return turnScore
   */
  public int turnScore(String player) {
    int turnScore = 0;
    if (player.equals("1")) {
      for (int i = 1; i <= 8; i++) {
        for (int j = 1; j <= 8; j++) {
          if (board[i][j].equals("1"))
            turnScore++;
        }
      }
    } else if (player.equals("2")) {
      for (int i = 1; i <= 8; i++) {
        for (int j = 1; j <= 8; j++) {
          if (board[i][j].equals("2"))
            turnScore++;
        }
      }
    }
    return turnScore;
  }

  /**
   * checks the state of the board and chcecks whether it is full
   * 
   * @return true if the board is full and ends the game
   */
  public boolean gameOver() {
    boolean full = false;
    int countTotal = 0;
    for (int i = 1; i <= 8; i++) {
      for (int j = 1; j <= 8; j++) {
        if (board[i][j].equals("1") || board[i][j].equals("2")) {
          countTotal++;
        }
      }
    }
    if (countTotal == 64) {
      full = true;
    }
    return full;
  }

  // checks if the spot on the board is a valid move and how many pieces will be
  // flipped//

  /**
   * @param x = x coordinate of point to check
   * @param y = y coordinate of point to check
   * @return flipped = a nine integer array, first element is total number of
   *         pieces that would be flipped, the second is the number of pieces
   *         flipped in the north direction, third is pieces flipped northeast,
   *         continuing clockwise for all 8 directions.
   **/
  public int[] move(int x, int y, String player) {

    // resets the flipped array everytime the player tries to make a move
    for (int e = 0; e < 9; e++) {
      flipped[e] = 0;
    }

    // determines the number of the other player
    if (player.equals("1")) {
      otherPlayer = "2";
    } else if (player.equals("2")) {
      otherPlayer = "1";
    }

    // checks if spot is empty, otherwise does nothing //
    if (board[y][x].equals("_")) {
      n = 1;

      // check north //
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

      // check northeast //
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

      // check east //
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

      // check southeast //
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

      // check south //
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

      // check southwest //
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

      // check west //
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

      // check northwest //
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
}