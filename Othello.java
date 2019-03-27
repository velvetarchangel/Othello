import java.util.*;
import java.util.Arrays;

public class Othello{

    public static void main(String[] args){
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
    }

}