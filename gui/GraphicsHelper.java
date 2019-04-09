package gui;
import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.AI;
import logic.Board;
import logic.Check;
import logic.Player;

import java.io.IOException;
import java.lang.Math;
import java.util.*;





public class GraphicsHelper{

    /**public void drawVictoryScreen(){
      Pane victoryPane = new Pane();
      Scene victoryScene = new Scene(victoryPane, 500, 200);
      Label winnerMessage = new Label();
      if (player_1.getScore() > player_2.getScore()) {
        Label winner = new Label("Player 1 wins!");
      } else if (player_1.getScore() < player_2.getScore()) {
        Label winner = new Label("Player 2 wins!");
      }
      winnerMessage.setFont(Font.font("Arial", 20));
      winnerMessage.setLayoutX(100);
      winnerMessage.setLayoutY(100);
      victoryPane.getChildren().add(winnerMessage);

      Stage victoryStage = new Stage();
      victoryStage.setScene(victoryScene);
      victoryStage.show();
    } */



    public void startGame(){

    }


    public void updateScore(int Score){

    }


    public void initializePlayer(){

    }

    public void endGame(){

    }

    public void UpdateBoard(Board b){

    }

    /*
    public void playerTurn(Player currentPlayer, Board b, int[] x_y){
        int [] flipped = Check.move(x_y[0], x_y[1], currentPlayer, b);
        while (flipped[0] == 0){
            int x = g.get_xCoord();
            int y = g.get_yCoord();
            int[] x_y = {x,y};
            flipped = Check.move(x, y, currentPlayer, b);
        }
        b.UpdateBoard();
        g.drawBoard();
    }*/


    /*
    public void player_v_player(Board b, Player one, Player two){
        setTurn(1);

        Player currentPlayer = setCurrentPlayer(one);
        Player otherPlayer = setOtherPlayer(two);

        while (!b.isFull() && ((Check.AnyMovesLeft(player_1, b)) || Check.AnyMovesLeft(player_2, b))){
            if (this.turn == 1){
                if(Check.AnyMovesLeft(currentPlayer, b)){
                    int x_coord = g.get_xCoord(); //gets the x coordinate from graphics
                    int y_coord = g.get_yCoord(); // gets the y coordinate from graphics
                }

                setTurn(2);
            }

            else if(this.turn == 2){
                // do stuff

                setTurn(1);
            }

        }

        g.drawVictoryScreen();

        int currentPlayerScore = b.turnScore(currentPlayer.getNumber()); //gets the score for the other Player
        int otherPlayerScore = b.turnScore(otherPlayer.getNumber()); //gets the score for the other Player
    }*/

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

    /**public void updateScores(Player p1, Player p2, Board board){
        int playerOneScore = board.turnScore(p1.getNumber());
        int playerTwoScore = board.turnScore(p2.getNumber());
        p1.setScore(playerOneScore);
        p2.setScore(playerTwoScore);
    } */


}
