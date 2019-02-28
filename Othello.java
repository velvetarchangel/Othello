import java.util.*;

/**
 * Main class for Othello game which is required to run the application
 * 
 * @author T08 Group 5 (CPSC 233)
 * @version 1.0 (February 27, 2019)
 */
public class Othello {

    private static Scanner coord = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(); // creates a new Board object
        String player = "0";// sets player to 0, this means there are no players at this time
        Player player_1 = new Player();
        Player player_2 = new Player();

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
        while (board.gameOver() == false) { // Continues game if board is not full

            if (player.equals("1")) { // Player 1's turn

                board.printBoard();
                System.out.println("\n" + "It is player " + player + "'s turn");
                System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                // Reprompt if not an int
                while (!coord.hasNextInt()) {
                    coord.next();
                    System.out.println("Please enter an integer.");
                }
                int x = coord.nextInt();
                System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                // Reprompt if not an int
                while (!coord.hasNextInt()) {
                    coord.next();
                    System.out.println("Please enter an integer.");
                }
                int y = coord.nextInt();

                while ((x < 1 || x > 8) || (y < 1 || y > 8)) { // Reprompts user if integers are out of range
                    player = "1";
                    System.out.println("Invalid move, try again");
                    System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    x = coord.nextInt();
                    System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    y = coord.nextInt();
                }

                int[] flipped = board.move(x, y, player);

                while ((flipped[0] == 0)) { // Reprompts user if no pieces can be flipped
                    player = "1";
                    System.out.println("Invalid move, try again");
                    System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    x = coord.nextInt();
                    System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    y = coord.nextInt();
                    flipped = board.move(x, y, player);
                }

                if (flipped[0] != 0) { // If pieces can be flipped, update board and scores
                    board.updateBoard(x, y, player, flipped);
                    playerOneScore = board.turnScore("1");
                    playerTwoScore = board.turnScore("2");
                    player_1.setScore(playerOneScore);
                    player_2.setScore(playerTwoScore);
                    System.out.println("\n" + "Player 1's score is " + playerOneScore);
                    System.out.println("Player 2's score is " + playerTwoScore);
                }

                player = "2"; // Switch to player 2's turn

            }

            else if (player.equals("2")) { // Player 2's turn

                board.printBoard();
                System.out.println("\n" + "It is player " + player + "'s turn");
                System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                // Reprompt if not an int
                while (!coord.hasNextInt()) {
                    coord.next();
                    System.out.println("Please enter an integer.");
                }
                int x = coord.nextInt();
                System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                // Reprompt if not an int
                while (!coord.hasNextInt()) {
                    coord.next();
                    System.out.println("Please enter an integer.");
                }
                int y = coord.nextInt();

                while ((x < 1 || x > 8) || (y < 1 || y > 8)) { // Reprompts user if integers are out of range
                    player = "2";
                    System.out.println("Invalid move, try again");
                    System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    x = coord.nextInt();
                    System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    y = coord.nextInt();
                }

                int[] flipped = board.move(x, y, player);

                while ((flipped[0] == 0)) { // Reprompts users if no pieces can be flipped
                    player = "2";
                    System.out.println("Invalid move, try again");
                    System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    x = coord.nextInt();
                    System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                    // Reprompt if not an int
                    while (!coord.hasNextInt()) {
                        coord.next();
                        System.out.println("Please enter an integer.");
                    }
                    y = coord.nextInt();
                    flipped = board.move(x, y, player);
                }

                if (flipped[0] != 0) { // If pieces can be flipped, update the board and scores
                    board.updateBoard(x, y, player, flipped);
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

    }

}
