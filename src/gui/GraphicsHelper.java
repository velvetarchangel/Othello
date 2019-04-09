package gui;

import logic.AI;
import logic.Board;
import logic.Check;
import logic.Player;

public class GraphicsHelper{


    // this is for player_vs_player in OthelloGraphics
    public void takeInput(int x, int y, Board board, String turn, boolean ai) {
      Player p1 = new Player("1", "Black");
      Player p2 = new Player("2", "White");



      if (board.isFull() == false && (Check.AnyMovesLeft(p1, board) || Check.AnyMovesLeft(p2, board)) ) {

        if (ai == false) {
          if (turn.equals("1")) {
            if (Check.AnyMovesLeft(p1, board)) {
              int[] flipped = Check.move(x, y, p1, board);
              if (flipped[0] != 0) {
                board.updateBoard(x, y, "1", flipped);
                OthelloGraphics.setTurn("2");
              }
            } else {
              OthelloGraphics.setTurn("2");
            }

          }
          else if (turn.equals("2")) {
            if (Check.AnyMovesLeft(p2, board)) {
              int[] flipped = Check.move(x, y, p2, board);
              if (flipped[0] != 0) {
                board.updateBoard(x, y, "2", flipped);
                OthelloGraphics.setTurn("1");
              }
            } else {
              OthelloGraphics.setTurn("1");
            }
          }
        }

        else {
          int[] flipped = Check.move(x, y, p1, board);
          if (flipped[0] != 0) {
            board.updateBoard(x, y, "1", flipped);
            if (Check.AnyMovesLeft(p2, board)) {
              int[] x_y = AI.chooseMove(p2, board);
              flipped = Check.move(x_y[0], x_y[1], p2, board);
              board.updateBoard(x_y[0], x_y[1], "2", flipped);
            }

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
