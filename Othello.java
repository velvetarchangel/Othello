import java.util.*;
import java.util.Arrays;



/**Version 1.0 
* @author Himika Dastidar
* this class runs the text based version of the game using other classes in the background
* the text based version is able to quit the game at any point, run a mode for player vs player and
* run a mode for player vs AI*/

public class Othello{

    public static void main(String[] args){
        Board board = new Board(); // creates a new board
        Scanner kb = new Scanner(System.in);
        Player[] players = new Player[2]; // creates a player array and initializes it
        OthelloHelper game = new OthelloHelper(); // initializes an OthelloHelper Object which 
            game.greeting(); // Prints welcome message and instructions using method
            String choice = kb.nextLine(); // allows the user to select the game mode
            switch(choice){
                case "q": // quits at any stage of the game
                    game.finishGame(players[0], players[1],board);
                    break;
                
                case "p": // initializes player vs player mode
                    System.out.println("Please enter a name:");
                    String name1 = kb.nextLine();
                    System.out.println("Please enter another name:");
                    String name2 = kb.nextLine();

                    players = game.initial_turn(name1, name2, "human"); // Decides which player goes first
                    System.out.println(players[0].getName() + " is Player 1.");
                    game.Player_v_Player(board, players);
                    break;
                
                case "c": // initializes the player vs AI mode of the game
                    System.out.println("Playing against computer.");
                    System.out.println("Please enter your name:");
                    String name3 = kb.nextLine();
                    String name4 = "Karel"; 
                    players = game.initial_turn(name3, name4, "computer"); // Decides which player goes first
                    System.out.println(players[0].getName() + " is Player 1.");
                    game.Player_v_AI(board, players);
                    break;
            }
    }

}
