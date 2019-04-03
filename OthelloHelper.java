import java.util.*;

public class OthelloHelper {

    public static int[] calculate_Score(Player one, Player two, Board board) {
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

    public void greeting() {
        System.out.println("\n" + "WELCOME TO OTHELLO!");
        System.out.println("Enter p to play against another player");
        System.out.println("Enter c to play against a computer");
        System.out.println("Enter q to end game");
    }

    public static void finishGame(Player one, Player two, Board board) {
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

    public static void playerTurn(Player currentPlayer, Player otherPlayer, Board board, int[] x_y) {

        int[] flipped = Check.move(x_y[0], x_y[1], currentPlayer, board);

        while (flipped[0] == 0) {
            System.out.println("No pieces can be flipped. Try again.");
            x_y = currentPlayer.getInput(board);
            int x = x_y[0];
            int y = x_y[1];
            flipped = Check.move(x, y, currentPlayer, board);
        }

        board.updateBoard(x_y[0], x_y[1], currentPlayer.getNumber(), flipped);

        int currentPlayerScore = board.turnScore(currentPlayer.getNumber());
        int otherPlayerScore = board.turnScore(otherPlayer.getNumber());

        currentPlayer.setScore(currentPlayerScore);
        otherPlayer.setScore(otherPlayerScore);
        System.out.println(currentPlayer.getName() + "'s score is: " + currentPlayer.getScore());
        System.out.println(otherPlayer.getName() + "'s score is: " + otherPlayer.getScore());

        board.isFull();

    }

    public void Player_v_Player(Board board, Player[] players){
        int turn = 1;
        int[] x_y = new int[2];
                while (!board.isFull() && (Check.AnyMovesLeft(players[0], board) || Check.AnyMovesLeft(players[1], board))) { // while board isn't full
                    if (turn == 1) {
                        if (Check.AnyMovesLeft(players[0], board)) {
                            System.out.println("player 1 anymovesleft: " + Check.AnyMovesLeft(players[0], board)); // delete
                            System.out.println(" ");
                            System.out.println("It's " + players[0].getName() + "'s turn.");
                            board.printBoard();
                            x_y = players[0].getInput(board);
                            if (x_y != null) {
                                playerTurn(players[0], players[1], board, x_y);
                            } else {
                                finishGame(players[0], players[1], board);
                            }
                            turn = 2;
                        } else if (!Check.AnyMovesLeft(players[0], board)) {
                            System.out.println("player 1 anymovesleft: " + Check.AnyMovesLeft(players[0], board)); // delete
                            System.out.println(players[0].getName() + " has no more valid moves.");
                            turn = 2;
                        }
                    } else if (turn == 2) {
                        if (Check.AnyMovesLeft(players[1], board)) {
                            System.out.println("player 2 anymovesleft: " + Check.AnyMovesLeft(players[1], board)); // delete
                            System.out.println(" ");
                            System.out.println("It's " + players[1].getName() + "'s turn.");
                            board.printBoard();
                            x_y = players[1].getInput(board);
                            if (x_y != null) {
                                playerTurn(players[1], players[0], board, x_y);
                            } else {
                                finishGame(players[0], players[1], board);
                            }
                            turn = 1;
                        } else if (!Check.AnyMovesLeft(players[1], board)) {
                            System.out.println("player 2 anymovesleft: " + Check.AnyMovesLeft(players[1], board)); // delete
                            System.out.println(players[1].getName() + " has no more valid moves.");
                            turn = 1;
                        }
                    }
                }
                finishGame(players[0], players[1], board);

    }


    public void Player_v_AI(Board board, Player[] players){
        int turn = 1;
        int[] x_y = new int[2];

        while (!board.isFull() && (Check.AnyMovesLeft(players[0], board) || Check.AnyMovesLeft(players[1], board))) { // while board isn't full
            if (turn == 1) {
                if (Check.AnyMovesLeft(players[0], board)) {
                    System.out.println(" ");
                    System.out.println("It's " + players[0].getName() + "'s turn.");
                    board.printBoard();
                    //System.out.println("Name is:" + players[0].getName()); // delete
                    if (!players[0].getName().equals("Computer")) {
                        x_y = players[1].getInput(board);
                    } else {
                        x_y = AI.chooseMove(players[0], board);
                        System.out.println("Computer makes move: " + x_y[0]+ x_y[1]);

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
                        if (!players[1].getName().equals("Computer")) {
                            x_y = players[1].getInput(board);
                        } else {
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

    /*
    public static void main(String[] args) {
        Board board = new Board(); // creates a new board

        Scanner kb = new Scanner(System.in);
        Player[] players = new Player[2]; // creates a player array and initializes it

        OthelloHelper game = new OthelloHelper();

        game.greeting(); // Prints welcome message and instructions using method
        String choice = kb.nextLine();
        switch(choice){
            case "q":
                game.finishGame(players[0], players[1],board);
                break;
            case "p":
                System.out.println("Please enter a name:");
                String name1 = kb.nextLine();
                System.out.println("Please enter another name:");
                String name2 = kb.nextLine();

                players = game.initial_turn(name1, name2, "human"); // Decides which player goes first
                System.out.println(players[0].getName() + " is Player 1.");
                game.Player_v_Player(board, players);
                break;
            case "c":
                System.out.println("Playing against computer.");
                System.out.println("Please enter your name:");
                String name3 = kb.nextLine();
                String name4 = "Karel"; // not needed
                players = game.initial_turn(name3, name4, "computer"); // Decides which player goes first
                System.out.println(players[0].getName() + " is Player 1.");
                game.Player_v_AI(board, players);
                break;
        }
    }*/
}
