package text;

import logic.AI;
import logic.Board;
import logic.Check;
import logic.Player;

/**
 * This is the driver class for the text based version of Othello. It
 * orchestrates the different classes in the background to simulate the game
 * being played by the player and divides each action into a method.
 * 
 * 
 * @author Himika Dastidar, Vivian Huynh
 * @version 2.0
 */
public class OthelloHelper {

    /**
     * Calculates the score of each player after every turn of the game
     * 
     * @param one   The player who places black pieces on the board
     * @param two   The player who places white pieces on the board
     * @param board The current state of the board @ return score array with the
     *              score for both players
     */
    public int[] calculate_Score(Player one, Player two, Board board) {
        int[] score = new int[2];
        score[0] = board.turnScore(one.getNumber());
        score[1] = board.turnScore(two.getNumber());
        one.setScore(score[0]);
        two.setScore(score[1]);
        return score;
    }

    /**
     * Randomly determines which player goes first. The person who is assigned as
     * player 1 goes first, and uses the "1" pieces on the board.
     *
     * @param name1 The first name entered into the game.
     * @param name2 The second name entered into the game.
     * @return players A 2D array of type players, the elements being player 1 and
     *         player 2 respectively.
     */
    public Player[] initial_turn(String name1, String name2, String mode) {
        double initial_turn = Math.random();
        Player[] players = new Player[2];
        if (mode == "human") {
            if (initial_turn <= 0.5) {
                // black goes first
                Player one = new Player("1", "Black", name1);
                Player two = new Player("2", "White", name2);
                players[0] = one;
                players[1] = two;
            } else {
                Player one = new Player("1", "Black", name2);
                Player two = new Player("2", "White", name1);
                players[0] = one;
                players[1] = two;
            }
        } else {
            if (initial_turn <= 0.5) {
                // black goes first
                Player one = new Player("1", "Black", name1);
                AI two = new AI("2", "White");
                players[0] = one;
                players[1] = two;
            } else {
                AI one = new AI("1", "Black");
                Player two = new Player("2", "White", name1);
                players[0] = one;
                players[1] = two;
            }
        }
        return players;
    }

    /**
     * Initializes the text based game to provide a greeting to the user and
     * provides the user the choice to choose the mode of the game
     */
    public void greeting() {
        System.out.println("\n" + "WELCOME TO OTHELLO!");
        System.out.println("Enter p to play against another player");
        System.out.println("Enter c to play against a computer");
        System.out.println("Enter q to end game");
    }

    /**
     * This method manages the end state of the game, once the decision is made the
     * game is over, this method shows each players score and determines who the
     * winner is and shuts down the game.
     * 
     * @param one   Player who places black pieces on the board
     * @param two   Player who places white pieces on the board
     * @param board Current state of the board
     */
    public void finishGame(Player one, Player two, Board board) {
        board.printBoard();
        if (one.getScore() > two.getScore()) {
            System.out.println(one.getName() + " wins! Final scores are-- " + one.getName() + " : " + one.getScore()
                    + " " + two.getName() + ": " + two.getScore());
        } else if (one.getScore() < two.getScore()) {
            System.out.println(two.getName() + " wins! Final scores are--" + one.getName() + " : " + one.getScore()
                    + " " + two.getName() + ": " + two.getScore());
        } else {
            System.out.println("It's a draw! Final scores are-- " + one.getName() + ": " + one.getScore() + " "
                    + two.getName() + " : " + two.getScore());
        }
        System.exit(0);
    }

    /**
     * Simulates the player taking a turn, takes in player input, sanitizes it, and
     * updates the board
     * 
     * @param currentPlayer The player who's turn it is
     * @param otherPlayer   The player who's waiting their turn
     * @param board         The current state of the board
     * @param x_y           takes the integer array containing the user input
     *                      coordinates
     */
    public void playerTurn(Player currentPlayer, Player otherPlayer, Board board, int[] x_y) {

        int[] flipped = Check.move(x_y[0], x_y[1], currentPlayer, board); // Checks if the x_y array contains a valid
                                                                          // move

        // if the move is not valid, reprompts the user to try again
        while (flipped[0] == 0) {
            System.out.println("No pieces can be flipped. Try again.");
            x_y = currentPlayer.getInput(board);
            int x = x_y[0];
            int y = x_y[1];
            flipped = Check.move(x, y, currentPlayer, board);
        }

        // if the move is valid, updates the board
        board.updateBoard(x_y[0], x_y[1], currentPlayer.getNumber(), flipped);

        // calculates the scores of each player
        int currentPlayerScore = board.turnScore(currentPlayer.getNumber());
        int otherPlayerScore = board.turnScore(otherPlayer.getNumber());

        currentPlayer.setScore(currentPlayerScore); // sets the score
        otherPlayer.setScore(otherPlayerScore); // sets the score
        System.out.println(currentPlayer.getName() + "'s score is: " + currentPlayer.getScore());
        System.out.println(otherPlayer.getName() + "'s score is: " + otherPlayer.getScore());

        board.isFull(); // checks whether the board is full

    }

    /**
     * Simulates the player vs player mode of the game
     * 
     * @param board   takes in a board object to start the game
     * @param players is an array containing the information of the 2 players that
     *                are initialized by initial turn method
     **/
    public void Player_v_Player(Board board, Player[] players) {
        int turn = 1;
        int[] x_y = new int[2];
        // while board isn't full and atleast one of the players has a valid move left,
        // continue the game
        while (!board.isFull() && (Check.AnyMovesLeft(players[0], board) || Check.AnyMovesLeft(players[1], board))) {
            if (turn == 1) {
                if (Check.AnyMovesLeft(players[0], board)) { // checks if player 1 has any moves left
                    System.out.println(" ");
                    System.out.println("It's " + players[0].getName() + "'s turn.");
                    board.printBoard();
                    x_y = players[0].getInput(board); // gets player input
                    if (x_y != null) {
                        playerTurn(players[0], players[1], board, x_y);// simulates player turn
                    } else {
                        finishGame(players[0], players[1], board); // ends game if array is null
                    }
                    turn = 2; // switches player
                } else if (!Check.AnyMovesLeft(players[0], board)) { // if player 1 doesnt have any moves left switches
                                                                     // turns
                    System.out.println(players[0].getName() + " has no more valid moves.");
                    turn = 2;
                }
            } else if (turn == 2) {
                if (Check.AnyMovesLeft(players[1], board)) { // checks if player 2 has any moves left
                    System.out.println(" ");
                    System.out.println("It's " + players[1].getName() + "'s turn.");
                    board.printBoard();
                    x_y = players[1].getInput(board); // gets player input
                    if (x_y != null) {
                        playerTurn(players[1], players[0], board, x_y); // simulates player turn
                    } else {
                        finishGame(players[0], players[1], board); // ends game if array is null
                    }
                    turn = 1;
                } else if (!Check.AnyMovesLeft(players[1], board)) { // if player 2 doesnt have any moves left switches
                                                                     // turns
                    System.out.println(players[1].getName() + " has no more valid moves.");
                    turn = 1;
                }
            }
        }
        // If the board is full or neither player has any valid moves, end the game
        finishGame(players[0], players[1], board);
    }

    /**
     * Simulates the player vs AI mode of the game
     * 
     * @param board   takes in a board object to start the game
     * @param players is an array containing the information of the 2 players that
     *                are initialized by initial turn method
     **/
    public void Player_v_AI(Board board, Player[] players) {

        int turn = 1;
        int[] x_y = new int[2];

        while (!board.isFull() && (Check.AnyMovesLeft(players[0], board) || Check.AnyMovesLeft(players[1], board))) {
            // while board isn't full and atleast one of the players has a valid move left,
            // continue the game
            if (turn == 1) { // simulate the turn for player 1
                if (Check.AnyMovesLeft(players[0], board)) { // if player 1 doesnt have a turn, switch to player 2
                    System.out.println(" ");
                    System.out.println("It's " + players[0].getName() + "'s turn.");
                    board.printBoard();
                    if (!players[0].getName().equals("Computer")) { // gets input from the human user
                        x_y = players[1].getInput(board);
                    } else { // gets input from the computer
                        x_y = AI.chooseMove(players[0], board);
                        System.out.println("Computer makes move: " + x_y[0] + x_y[1]);

                    }
                    if (x_y != null) {
                        playerTurn(players[0], players[1], board, x_y);
                    } else {
                        finishGame(players[0], players[1], board);
                    }
                    turn = 2;
                } else if (!Check.AnyMovesLeft(players[0], board)) {
                    System.out.println(players[0].getName() + " has no more valid moves.");
                    turn = 2;
                }
            } else if (turn == 2) {
                if (Check.AnyMovesLeft(players[1], board)) {
                    System.out.println(" ");
                    System.out.println("It's " + players[1].getName() + "'s turn.");
                    board.printBoard();
                    if (!players[1].getName().equals("Computer")) { // gets input from the human user
                        x_y = players[1].getInput(board);
                    } else { // gets input from the computer
                        x_y = AI.chooseMove(players[1], board);
                        System.out.println("Computer makes move:" + x_y);
                    }
                    if (x_y != null) {
                        playerTurn(players[1], players[0], board, x_y);
                    } else {
                        finishGame(players[0], players[1], board);
                    }
                    turn = 1;
                } else if (!Check.AnyMovesLeft(players[1], board)) {
                    System.out.println(players[1].getName() + " has no more valid moves.");
                    turn = 1;
                }
            }
        }
        finishGame(players[0], players[1], board);
    }
}
