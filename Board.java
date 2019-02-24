import java.util.*;

public class Board{

    private int [][] board;

    // variables for checking moves //
    private int n; //
    private int[] flipped = new int[9];
    private int otherPlayer = 0;


    public Board() {
        this.board = new int[10][10];
        for(int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                board[i][j] = 0;
            }
        }
        /** fills the top of the array with 3s (to help with edge cases)
        */
        for (int j = 0; j < 10; j++){
            int i = 0;
            board[i][j] = 3;
        }
        /** fills the bottom of the array with 3s (to check edge cases)
        */
        for (int j = 0; j < 10; j++){
            int i = 9;
            board[i][j] = 3;
        }
        /**fills the left hand side of the array with 3s
        */
        for (int i = 0; i < 10; i++){
            int j = 0;
            board[i][j] = 3;
        }

        /** fills the right hand side of the array with 3s
        */
        for (int i = 0; i < 10; i++){
            int j = 9;
            board[i][j] = 3;
        }
        /**this sets pieces in the middle of the board to set up the initial 
         * board
         */
        board[4][4] = 1;
        board[4][5] = 2;
        board[5][4] = 2;
        board[5][5] = 1;
    }

    public void printBoard(){
        System.out.println( "_ 0 1 2 3 4 5 6 7 8 0");

        /** fills the top of the array with 3s (to help with edge cases)
        */
        System.out.print("  ");
        for (int j = 0; j < 10; j++){
            int i = 0;
            System.out.print(board[i][j] + "|");
            }
        System.out.print("\n");

        /**fills the middle of the array and adds 3 to either
        side of the array */
        for(int i = 1; i <= 8; i++){
            System.out.print(i + " 3|");
            for (int j = 1; j <= 8; j++){
                System.out.print(board[i][j] + "|");
            }
            System.out.print("3"+ "\n");
        }

        /** fills the bottom of the array with 3s (to check edge cases)
        */
        System.out.print("  ");
        for (int j = 0; j < 10; j++){
            int i = 9;
            System.out.print(board[i][j] + "|");
        }
    }
    /** takes in user input @param x, @param y, and then checks for valid moves,
    * if the move is valid, the board updates accordingly */
    public void updateBoard(int x, int y, int player, int[] array){
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


    public int turnScore(int player){
        int turnScore = 0;
        if (player == 1){
            for(int i = 1; i <= 8; i++){
                for(int j = 1; j <= 8; j++){
                    if (board[i][j] == 1) turnScore++;
                    }
                }
            }
        else if (player == 2){
            for(int i = 1; i <= 8; i++){
                for(int j = 1; j <= 8; j++){
                    if (board[i][j] == 2) turnScore++;
                    }
            }
        }
        return turnScore;
    }

    public boolean gameOver(){
        boolean full = false;
        int countTotal = 0;
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                if (board[i][j] == 1 || board[i][j] == 2){
                    countTotal++;
                }
            }
        }
        if(countTotal == 64){
            full = true;
        }
        return full;
    }

    // checks if the spot on the board is a valid move and how many pieces will be flipped//

  /**
  @param x = x coordinate of point to check
  @param y = y coordinate of point to check
  @return flipped returns a nine integer array, first element is total number of pieces that would be flipped,
  the second is the number of pieces flipped in the north direction, third is pieces flipped northeast, continuing
  clockwise for all 8 directions.
  **/
  public int[] move(int x, int y, int player) {

    for (int e = 0; e < 9; e++) {
      flipped[e] = 0;
    }

    System.out.println("Flipped at 0:" + Arrays.toString(flipped));

    if (player == 1) {
      otherPlayer = 2;
    } else if (player == 2) {
      otherPlayer = 1;
    }


    if (board[y][x] == 0) {
      n = 1;

      // check north //
      if (board[y - n][x] == otherPlayer) {
        while (board[y - n][x] == otherPlayer) {
          n += 1;
        }
        if (board[y - n][x] == 3) {
          n = 1;
        }
        else if (board[y - n][x] == 0) {
          n = 1;
        }
        else {
          flipped[1] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }

      // check northeast //
      if (board[y - n][x + n] == otherPlayer) {
        while (board[y - n][x + n] == otherPlayer) {
          n += 1;
        }
        if (board[y - n][x + n] == 3) {
          n = 1;
        }
        else if (board[y - n][x + n] == 0) {
          n = 1;
        }
        else {
          flipped[2] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }

      // check east //
      if (board[y][x + n] == otherPlayer) {
        while (board[y][x + n] == otherPlayer) {
          n += 1;
        }
        if (board[y][x + n] == 3) {
          n = 1;
        }
        else if (board[y][x + n] == 0) {
          n = 1;
        }
        else {
          flipped[3] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }

      // check southeast //
      if (board[y + n][x + n] == otherPlayer) {
        while (board[y + n][x + n] == otherPlayer) {
          n += 1;
        }
        if (board[y + n][x + n] == 3) {
          n = 1;
        }
        else if (board[y + n][x + n] == 0) {
          n = 1;
        }
        else {
          flipped[4] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }


      // check south //
      if (board[y + n][x] == otherPlayer) {
        while (board[y + n][x] == otherPlayer) {
          n += 1;
        }
        if (board[y + n][x] == 3) {
          n = 1;
        }
        else if (board[y + n][x] == 0) {
          n = 1;
        }
        else {
          flipped[5] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }


      // check southwest //
      if (board[y + n][x - n] == otherPlayer) {
        while (board[y + n][x - n] == otherPlayer) {
          n += 1;
        }
        if (board[y + n][x - n] == otherPlayer) {
          n = 1;
        }
        else if (board[y + n][x - n] == 0) {
          n = 1;
        }
        else {
          flipped[6] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }


      // check west //
      if (board[y][x - n] == otherPlayer) {
        while (board[y][x - n] == otherPlayer) {
          n += 1;
        }
        if (board[y][x - n] == 3) {
          n = 1;
        }
        else if (board[y][x - n] == 0) {
          n = 1;
        }
        else {
          flipped[7] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }


      // check northwest //
      if (board[y - n][x - n] == otherPlayer) {
        while (board[y - n][x - n] == otherPlayer) {
          n += 1;
        }
        if (board[y - n][x - n] == 3) {
          n = 1;
        }
        else if (board[y - n][x - n] == 0) {
          n = 1;
        }
        else {
          flipped[8] = n - 1;
          flipped[0] += n - 1;
          n = 1;
        }
      }

}
return flipped;
    }
  }