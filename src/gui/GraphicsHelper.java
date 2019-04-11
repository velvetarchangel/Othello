package gui;

import logic.AI;
import logic.Board;
import logic.Check;
import logic.Player;

/**
 * Handles the game logic for the GUI
 * 
 * @author Jayoo Hwang
 * @version 1.0 (April 8, 2019)
 */
public class GraphicsHelper {

  /**
   * Processes the player inputs, changes the board and whose turn it is
   * accordingly
   * 
   * @param x     Column on the board where player makes their move
   * @param y     Row on the board where player makes their move
   * @param board The board object that the GUI references
   * @param turn  Which players turn it is in the game
   * @param ai    Whether or not the current game is against the computer
   */
  public void takeInput(int x, int y, Board board, String turn, boolean ai) {
    Player p1 = new Player("1", "Black");
    Player p2 = new Player("2", "White");

    // Checks if the game is over
    if (board.isFull() == false && (Check.AnyMovesLeft(p1, board) || Check.AnyMovesLeft(p2, board))) {

      // If the game is vs another player
      if (ai == false) {
        if (turn.equals("1")) { // move made by player one
          if (Check.AnyMovesLeft(p1, board)) {
            int[] flipped = Check.move(x, y, p1, board);
            if (flipped[0] != 0) {
              // if the move is valid update the board variable and turn variable in the GUI
              board.updateBoard(x, y, "1", flipped);
              OthelloGraphics.setTurn("2");
            }
          } else { // if player one has no moves it is player twos turn
            OthelloGraphics.setTurn("2");
          }

        } else if (turn.equals("2")) { // move made by player two
          if (Check.AnyMovesLeft(p2, board)) {
            int[] flipped = Check.move(x, y, p2, board);
            if (flipped[0] != 0) {
              // if the move is valid update the board variable and turn variable in the GUI
              board.updateBoard(x, y, "2", flipped);
              OthelloGraphics.setTurn("1");
            }
          } else { // if player two has no moves it is player ones turn
            OthelloGraphics.setTurn("1");
          }
        }
      }

      // if the game is vs an ai
      else {
        int[] flipped = Check.move(x, y, p1, board);
        if (flipped[0] != 0) {
          // if players move is valid update the board, then ai makes move if it has a
          // valid move
          board.updateBoard(x, y, "1", flipped);
          if (Check.AnyMovesLeft(p2, board)) {
            int[] x_y = AI.chooseMove(p2, board);
            flipped = Check.move(x_y[0], x_y[1], p2, board);
            board.updateBoard(x_y[0], x_y[1], "2", flipped);
          }

          // allow AI to continuosly make moves as long as the player doesn't have any
          // valid moves
          while (!Check.AnyMovesLeft(p1, board) && Check.AnyMovesLeft(p2, board)) {
            int[] x_y = AI.chooseMove(p2, board);
            flipped = Check.move(x_y[0], x_y[1], p2, board);
            board.updateBoard(x_y[0], x_y[1], "2", flipped);
          }
        }

      }

    }

  }

}
