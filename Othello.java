import java.util.Scanner;

/**
 * Main class for Othello game which is required to run the application
 *
 * @author Himika Dastidar, Jayoo Hwang, Vivian Hyunh
 * @version 1.2 (March 11, 2019)
 */

public class Othello {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        do {

            // welcome screen which allows user to choose whether 
            // they want to play with AI or another human player
            System.out.println("WELCOME TO OTHELLO!");
            System.out.println("Enter p to play against another player");
            System.out.println("Enter c to play against a computer");
            System.out.println("Enter q to end game");

            String choice = kb.nextLine();

            // Exit if user enters a q
            if (choice.equals("q")) {
                break;
            }

            Board board = new Board(); // creates a new Board object

            if (choice.equals("p")) {
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
                        System.out.println("It's a draw! " + "Player 1's score: " + player_1.getScore()
                                + "Player 2's score: " + player_2.getScore());
                    }
                }

                else {
                    while (board.gameOver() == false) {
                        // Continues game if board is not full
                        if (player.equals("1") && Check.AnyMovesLeft("1", board)) { // Player 1's turn
                            board.printBoard();
                            System.out.println("\n" + "It is player " + player + "'s turn");
                            x_yCoordinate = Othello.getUserInput();
                            x = x_yCoordinate[0];
                            y = x_yCoordinate[1];

                            while ((x < 1 || x > 8) || (y < 1 || y > 8)) { // Reprompts user if integers are out of
                                                                           // range
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

                            while ((x < 1 || x > 8) || (y < 1 || y > 8)) { // Reprompts user if integers are out of
                                                                           // range
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
            } else if (choice.equals("c")) {
                System.out.println("will play w AI");

                String player = "0";// sets player to 0, this means there are no players at this time
                Player player_1 = new Player();
                AI player_2 = new AI();
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
                        System.out.println("Computer wins! Final score is: " + player_2.getScore());
                    } else if (player_2.getScore() == player_2.getScore()) {
                        System.out.println("It's a draw! " + "Computer's score: " + player_1.getScore()
                                + "Computer's score: " + player_2.getScore());
                    }
                } else {
                    while (board.gameOver() == false) {
                        // Continues game if board is not full
                        if (player.equals("1") && Check.AnyMovesLeft("1", board)) { // Player 1's turn
                            board.printBoard();
                            System.out.println("\n" + "It is player " + player + "'s turn");
                            x_yCoordinate = Othello.getUserInput();
                            x = x_yCoordinate[0];
                            y = x_yCoordinate[1];

                            while ((x < 1 || x > 8) || (y < 1 || y > 8)) { // Reprompts user if integers are out of
                                                                           // range
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
                                System.out.println("Computer's score is " + playerTwoScore);
                            }

                            player = "2"; // Switch to computer's turn
                        }

                        else if (player.equals("2") && Check.AnyMovesLeft("2", board)) { // Computer's turn
                            board.printBoard();
                            System.out.println("\n" + "It is the computer's turn");
                            int[] xy1 = AI.firstMove();
                            int x1 = xy1[0];
                            int y1 = xy1[1];
                            int[] xy2 = AI.secondMove();
                            int x2 = xy2[0];
                            int y2 = xy2[1];

                            System.out.println("one " + x1 + y1);
                            System.out.println("two " + x2 + y2);


                            // checks if the first move is valid and how many points it will generate
                            int[] flipped1 = Check.move(x1, y1, player, board); 
                            while (flipped1[0] == 0) 
                            {
                                player = "2";
                                xy1 = AI.firstMove();
                                x1 = xy1[0];
                                y1 = xy1[1];
                                System.out.println("one " + x1 + y1);
                                flipped1 = Check.move(x1, y1, player, board);
                            }

                            // checks if the first move is valid and how many points it will generate
                            int[] flipped2 = Check.move(x2, y2, player, board);
                            while (flipped2[0] == 0) 
                            {
                                player = "2";
                                xy2 = AI.secondMove();
                                x2 = xy1[0];
                                y2 = xy1[1];
                                System.out.println("two " + x2 + y2);
                                flipped1 = Check.move(x1, y1, player, board);
                            }

                            // if the first move generates more points or if they are both equal choose move 1
                            if (flipped1[0] > flipped2[0] || flipped1[0] == flipped2[0]) {
                                board.updateBoard(x1, y1, player, flipped1);
                                board.printBoard();
                                playerTwoScore = board.turnScore(player);
                                playerOneScore = board.turnScore("1");
                                player_2.setScore(playerTwoScore);
                                player_1.setScore(playerOneScore);
                                System.out.println("\n" + "Player 1's score is " + playerOneScore);
                                System.out.println("Computer's score is " + playerTwoScore);
                            
                                // if the second move generates more points choose this
                            } else if (flipped2[0] > flipped1[0]) {
                                board.updateBoard(x2, y2, player, flipped1);
                                board.printBoard();
                                playerTwoScore = board.turnScore(player);
                                playerOneScore = board.turnScore("1");
                                player_2.setScore(playerTwoScore);
                                player_1.setScore(playerOneScore);
                                System.out.println("\n" + "Player 1's score is " + playerOneScore);
                                System.out.println("Computer's score is " + playerTwoScore);
                            } 
                            player = "1"; // Switch to player 1's turn
                        }
                    }
                }
            }
        } while (true);

    }


    /** Prompts the user to put in valid moves,
     * if the moves are not valid, then repromps the user
     * @return x_y which is an array containing the x, y
     * coordinate
     */
    public static int[] getUserInput() {
        Scanner coord = new Scanner(System.in);
        int[] x_y = new int[2];
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
}
