import java.util.Scanner;

public class Othello{

   public static int [] getUserInput(){
       Scanner coord = new Scanner(System.in);
       int[] x_y = new int [2];
       System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
       while (!coord.hasNextInt()) {
           coord.next();
           System.out.println("Please enter an integer.");
       }
       int x = coord.nextInt();
       x_y[0] = x;
       System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
               // Reprompt if not an int
               while (!coord.hasNextInt()) {
                   coord.next();
                   System.out.println("Please enter an integer.");
               }
       int y = coord.nextInt();
       x_y[1] = y;

       return x_y;
   }


   public static void main(String[] args){
/**
* Main class for Othello game which is required to run the application
*
* @author T08 Group 5 (CPSC 233)
* @version 1.0 (February 27, 2019)
*/
       Board board = new Board(); // creates a new Board object
       String player = "0";// sets player to 0, this means there are no players at this time
       Player player_1 = new Player();
       Player player_2 = new Player();
       int[] x_yCoordinate;
       int x, y;

       /**
        * Uses math.random to decide which players turn it i. If the number generated
        * is <= 0.5 then it is black's turn, else it is white's turn
        */


       double initial_turn = Math.random();
       if (initial_turn <= 0.5) {
           player = "1";
           player_1.setColour("Black");
           player_2.setColour("White");
       } else {
           player = "2";
           player_2.setColour("Black");
           player_1.setColour("White");
       }

       int playerOneScore = player_1.getScore();
       int playerTwoScore = player_2.getScore();


       /**
        * Checks whether the board is full of pieces. If the board is full, the game
        * end, and the winners are announce. Otherwise the game continues.
        */

        if (board.gameOver() == true) { // Once board is filled, end game and print out result
            board.printBoard();
            if (player_1.getScore() > player_2.getScore()) {
                System.out.println("Player 1 wins! Final score is: " + player_1.getScore());
            } else if (player_2.getScore() < player_2.getScore()) {
                System.out.println("Player 2 wins! Final score is: " + player_2.getScore());
            } else if (player_2.getScore() == player_2.getScore()) {
                System.out.println("It's a draw! " + "Player 1's score: " + player_1.getScore() + "Player 2's score: "
                        + player_2.getScore());
            }
        }

        else{
            while (board.gameOver() == false) {
            // Continues game if board is not full
            if (player.equals("1") && Check.AnyMovesLeft("1", board)) { // Player 1's turn
                board.printBoard();
                System.out.println("\n" + "It is player " + player + "'s turn");
                x_yCoordinate = Othello.getUserInput();
                x = x_yCoordinate[0];
                y = x_yCoordinate[1];

                while ((x < 1 || x > 8) || (y < 1 || y > 8)) { // Reprompts user if integers are out of range
                    player = "1";
                    System.out.println("Invalid move, try again");
                    x_yCoordinate = Othello.getUserInput();
                    x = x_yCoordinate[0];
                    y = x_yCoordinate[1];
                }

                int[] flipped = Check.move(x, y, player, board);

                while ((flipped[0] == 0)) { // Reprompts user if no pieces can be flipped
                    player = "1";
                    System.out.println("Invalid move, try again");
                    x_yCoordinate = Othello.getUserInput();
                    x = x_yCoordinate[0];
                    y = x_yCoordinate[1];
                }

                if (flipped[0] != 0) { // If pieces can be flipped, update board and scores
                    board.updateBoard(x, y, player, flipped);
                    board.printBoard();
                    playerOneScore = board.turnScore("1");
                    playerTwoScore = board.turnScore("2");
                    player_1.setScore(playerOneScore);
                    player_2.setScore(playerTwoScore);
                    System.out.println("\n" + "Player 1's score is " + playerOneScore);
                    System.out.println("Player 2's score is " + playerTwoScore);
                }

                player = "2"; // Switch to player 2's turn
            }

            else if (player.equals("2") && Check.AnyMovesLeft("2", board)) { // Player 2's turn
                board.printBoard();
                System.out.println("\n" + "It is player " + player + "'s turn");
                x_yCoordinate = Othello.getUserInput();
                x = x_yCoordinate[0];
                y = x_yCoordinate[1];

                while ((x < 1 || x > 8) || (y < 1 || y > 8)) { // Reprompts user if integers are out of range
                    player = "2";
                    System.out.println("Invalid move, try again");
                    x_yCoordinate = Othello.getUserInput();
                    x = x_yCoordinate[0];
                    y = x_yCoordinate[1];
                }

                int[] flipped = Check.move(x, y, player, board);

                while ((flipped[0] == 0)) { // Reprompts users if no pieces can be flipped
                    player = "2";
                    System.out.println("Invalid move, try again");
                    x_yCoordinate = Othello.getUserInput();
                    x = x_yCoordinate[0];
                    y = x_yCoordinate[1];
                    flipped = Check.move(x, y, player, board);
                }

                if (flipped[0] != 0) { // If pieces can be flipped, update the board and scores
                    board.updateBoard(x, y, player, flipped);
                    board.printBoard();
                    playerTwoScore = board.turnScore(player);
                    playerOneScore = board.turnScore("1");
                    player_2.setScore(playerTwoScore);
                    player_1.setScore(playerOneScore);
                    System.out.println("\n" + "Player 1's score is " + playerOneScore);
                    System.out.println("Player 2's score is " + playerTwoScore);
                }
                player = "1"; // Switch to player 1's turn
            }
        }
    }
}
}
