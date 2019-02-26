import java.util.*;

public class Othello {
    private static Scanner coord = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = new Board(); // creates a new Board object
        String player = "0";// sets player to 0, this means there are no players at this time
        Player player_1 = new Player();
        Player player_2 = new Player();

        /**
         * Use math.random to decide which players turn it is if the number generated is
         * <= 0.5 then it is black's turn, else it is white's turn
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
         * gameOver checks whether board is full of pieces if the board is full the game
         * ends and the winner's are announced otherwise the game keeps going
         */
        while (board.gameOver() == false) {
            if (player.equals("1")) {
                board.printBoard();
                System.out.println("\n" + "It is player " + player + "'s turn");
                System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                int x = coord.nextInt();
                System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                int y = coord.nextInt();

                int[] flipped = board.move(x, y, player);

                while ((x < 1 || x > 8) || (y < 1 || y > 8) || (flipped[0] == 0)) {
                    player = "1";
                    System.out.println("Invalid move, try again");
                    System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                    x = coord.nextInt();
                    System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                    y = coord.nextInt();
                    flipped = board.move(x, y, player);
                }
                if (flipped[0] != 0) {
                    board.updateBoard(x, y, player, flipped);
                    playerOneScore = board.turnScore(player);
                    player_1.setScore(playerOneScore);
                    System.out.println("\n" + "Player 1's score is " + playerOneScore);
                    System.out.println("Player 2's score is " + playerTwoScore);
                }
                player = "2";
            }

            else if (player.equals("2")) {
                board.printBoard();
                System.out.println("\n" + "It is player " + player + "'s turn");
                System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                int x = coord.nextInt();
                System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                int y = coord.nextInt();

                int[] flipped = board.move(x, y, player);

                while ((x < 1 || x > 8) || (y < 1 || y > 8) || (flipped[0] == 0)) {
                    player = "2";
                    System.out.println("Invalid move, try again");
                    System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
                    x = coord.nextInt();
                    System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
                    y = coord.nextInt();
                    flipped = board.move(x, y, player);
                }
                if (flipped[0] != 0) {
                    board.updateBoard(x, y, player, flipped);
                    playerTwoScore = board.turnScore(player);
                    player_2.setScore(playerTwoScore);
                    System.out.println("\n" + "Player 1's score is " + playerOneScore);
                    System.out.println("Player 2's score is " + playerTwoScore);
                }
                player = "1";
            }
        }
        if (board.gameOver() == true) {
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