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

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

/**
* <h>Othello</h>
* Creates a graphical user interface and implements
* the Board, Check, and Player classes to allow the user
* to play Othello.
* <p>
*
* @author Miguel Merin, Jayoo Hwang
* @version 2.1
* @since 2019-03-14
*/

public class OthelloGraphics extends Application{
  private Stage stage;
  private Board board = new Board();
  private Group graphicBoard = new Group();
  private Scene scene = new Scene(graphicBoard,1200,850,Color.BLACK);
  private static String player = "1";
  private Player player_1 = new Player("1","BLACK","Player 1");
  private Player player_2 = new Player("2","WHITE","Player 2");
  private int playerOneScore = board.turnScore("1");
  private int playerTwoScore = board.turnScore("2");
  private String versus = "menu";
  private double mouseX, mouseY;
  private int x, y;
  private TextField xCoord = new TextField("");
  private TextField yCoord = new TextField("");
  private TextField name1 = new TextField("");
  private TextField name2 = new TextField("");
  private TextField name3 = new TextField("");
  private GraphicsHelper helper = new GraphicsHelper();

  private EventHandler<ActionEvent> inputButtonHandler = new EventHandler<ActionEvent>(){

    /**
    * Handles the button click to input a piece at spot declared
    * @param buttonEvent
    */
    @Override
    public void handle(ActionEvent buttonEvent){
      System.out.println("button pressed");
      x = Integer.parseInt(xCoord.getText());
      y = Integer.parseInt(yCoord.getText());

      /**
       * Checks whether the board is full of pieces. If the board is full, the game
       * end, and the winners are announce. Otherwise the game continues.
       */

      if (board.isFull() == true || !(Check.AnyMovesLeft(player_1, board) || Check.AnyMovesLeft(player_2, board))) { // Once graphicBoard is filled, end game and print out result
          drawVictoryScreen(); // will generate the end of the screen
      }

      // if board is not filled continue the game
      else {
          if (board.isFull() == false) {
              clearScreen();
              // what to do if it is player 1's turn
              if (player.equals("1") && Check.AnyMovesLeft(player_1, board)) { // Player 1's turn
                  System.out.println("X: " + x + "    Y: " + y);
                  board.printBoard();
                  System.out.println("\n" + "It is player " + player + "'s turn");
                  turnBoard(player);
                  int[] flipped = Check.move(x, y, player_1, board);

                  // Reprompts user if no pieces can be flipped
                  if ((flipped[0] == 0))
                  {
                      player = "1";
                      System.out.println("Invalid move, try again");
                  }

                  // If pieces can be flipped, update board and scores
                  if (flipped[0] != 0)
                  {
                      board.updateBoard(x, y, player, flipped);
                      board.printBoard();
                      playerOneScore = board.turnScore("1");
                      playerTwoScore = board.turnScore("2");
                      player_1.setScore(playerOneScore);
                      player_2.setScore(playerTwoScore);
                      System.out.println("\n" + "Player 1's score is " + playerOneScore);
                      System.out.println("Player 2's score is " + playerTwoScore);
                      drawScore();
                      player = "2";
                  }
                  flipped = null;
                }

              // checks it if it is player 2's turn and runs through the game sequence
              else if (player.equals("2") && Check.AnyMovesLeft(player_2, board)) {
                  board.printBoard();
                  System.out.println("\n" + "It is player " + player + "'s turn");
                  //turnBoard(player);
                  int[] flipped = Check.move(x, y, player_2, board);

                  // Reprompts users if no pieces can be flipped
                  if ((flipped[0] == 0))
                  {
                      player = "2";
                      System.out.println("Invalid move, try again");
                      flipped = Check.move(x, y, player_2, board);
                  }

                  // If pieces can be flipped, update the board and scores
                  else if (flipped[0] != 0)
                  {
                      board.updateBoard(x, y, player, flipped);
                      board.printBoard();
                      playerTwoScore = board.turnScore(player);
                      playerOneScore = board.turnScore("1");
                      player_2.setScore(playerTwoScore);
                      player_1.setScore(playerOneScore);
                      drawScore();
                      player = "1";
                  }
                  flipped = null;
                }
          }
      }
      drawBoard();
    }
  };

  private EventHandler<ActionEvent> playerNameHandler = new EventHandler<ActionEvent>(){
    /**
    * Handles button to set the names for players
    * @param buttonEvent
    */
    @Override
    public void handle(ActionEvent buttonEvent){
      player_1.setName(name1.getText());
      player_2.setName(name2.getText());
    }
  };

  private EventHandler<ActionEvent> aiNameHandler = new EventHandler<ActionEvent>(){
    /**
    * Handles button to set the names for player against AI
    * @param buttonEvent
    */
    @Override
    public void handle(ActionEvent buttonEvent){
      player_1.setName(name3.getText());
      player_2.setName("Computer");
    }
  };

  private EventHandler<ActionEvent> exitGameHandler = new EventHandler<ActionEvent>(){
    /**
    * Handles button to exit game at the victory screen
    * @param buttonEvent
    */
    @Override
    public void handle(ActionEvent buttonEvent){
      System.exit(0);
    }
  };

  private EventHandler<ActionEvent> backToMenuHandler = new EventHandler<ActionEvent>(){
    /**
    * Handles button to reset game to menu in the victory screen
    * @param buttonEvent
    */
    @Override
    public void handle(ActionEvent buttonEvent){
      versus = "menu";
      //changeScenes();
      resetBoard();
    }
  };

  private EventHandler<MouseEvent> vsAIHandler = new EventHandler<MouseEvent>(){
    @Override
    public void handle(MouseEvent mouseEvent){
      mouseX = mouseEvent.getX();
      mouseY = mouseEvent.getY();
      player_2 = new AI("2", "WHITE");
      if ((mouseX > 900) && (mouseX < 1010) && (mouseY > 725) && (mouseY < 800)){
        System.exit(0);
      }
      if ((mouseX > 1040) && (mouseX < 1150) && (mouseY > 725) && (mouseY < 800)){
        resetBoard();
      }
      if ((mouseX > 900) && (mouseX < 1010) && (mouseY > 625) && (mouseY < 700)){
        //board.saveBoard();
        try{
        board.saveBoard();
        System.exit(0);
        }catch(IOException e){
          System.out.println(e.getMessage());
        }

        //System.out.println("save game here..");
      }

      if ((mouseX > 1040) && (mouseX < 1150) && (mouseY > 625) && (mouseY < 700)){
        try{
          String[][] temp_board = Board.loadBoard();
          board.setBoard(temp_board);
          //System.out.println(Arrays.deepToString(board.getArray()));
        }
        catch(IOException e){
          System.out.println("load game here..");
        }
        finally{
        graphicBoard.getChildren().clear();
        startup();
        }
      }

      if ((mouseX > 25) && (mouseX < 825) && (mouseY > 25) && (mouseY < 825)){
        x = (int)(Math.floor((mouseX - 25.0) / 100.0) + 1);
        y = (int)(Math.floor((mouseY - 25.0) / 100.0) + 1);
        int[] x_y = new int[2];

        if (board.isFull() == false || (Check.AnyMovesLeft(player_1, board) && Check.AnyMovesLeft(player_2, board))){
          helper.takeInput(x, y, board, "1", true);
        }
        else if (board.isFull() == true || (!Check.AnyMovesLeft(player_1, board) && !Check.AnyMovesLeft(player_2, board))) { // Once graphicBoard is filled, end game and print out result
          drawVictoryScreen(); // will generate the end of the screen
        }
        drawScore();
        drawBoard();
      }
    }
  };

  private EventHandler<MouseEvent> vsPlayerHandler = new EventHandler<MouseEvent>(){
    @Override
    /**
    * Handles mouse clicks, checks if space clicked is a valid move or a button and
    * updates the game based on action
    * @param mouseEvent
    */
    public void handle(MouseEvent mouseEvent){
      mouseX = mouseEvent.getX();
      mouseY = mouseEvent.getY();

      if ((mouseX > 900) && (mouseX < 1010) && (mouseY > 725) && (mouseY < 800)){
        System.exit(0);
      }
      if ((mouseX > 1040) && (mouseX < 1150) && (mouseY > 725) && (mouseY < 800)){
        resetBoard();
      }
      if ((mouseX > 900) && (mouseX < 1010) && (mouseY > 625) && (mouseY < 700)){
        //board.saveBoard();
        try{
        board.saveBoard();
        System.exit(0);
        }catch(IOException e){
          System.out.println(e.getMessage());
        }

        //System.out.println("save game here..");
      }
      if ((mouseX > 1040) && (mouseX < 1150) && (mouseY > 625) && (mouseY < 700)){
        try{
          String[][] temp_board = Board.loadBoard();
          board.setBoard(temp_board);
        }
        catch(IOException e){
          System.out.println("load game here..");
        }
        finally{
        graphicBoard.getChildren().clear();
        startup();

      }
      }
      if ((mouseX > 25) && (mouseX < 825) && (mouseY > 25) && (mouseY < 825)){
        x = (int)(Math.floor((mouseX - 25.0) / 100.0) + 1);
        y = (int)(Math.floor((mouseY - 25.0) / 100.0) + 1);

        /**
         * Checks whether the board is full of pieces. If the board is full, the game
         * end, and the winners are announce. Otherwise the game continues.
         */
        if (board.isFull() == true || (!Check.AnyMovesLeft(player_1, board) && !Check.AnyMovesLeft(player_2, board))) { // Once graphicBoard is filled, end game and print out result
            drawVictoryScreen(); // will generate the end of the screen
        }

        // if board is not filled continue the game
        else {
            helper.takeInput(x, y, board, player, false);
        }
        drawBoard();
        turnBoard(player);
        drawScore();
      }
    }
  };

  private EventHandler<MouseEvent> menuHandler = new EventHandler<MouseEvent>(){

    /**
    * Handles the mouse clicks in the menu screen of the game
    * @param MouseEvent
    */
    @Override
    public void handle(MouseEvent mouseEvent){
      double mouseX = mouseEvent.getX();
      double mouseY = mouseEvent.getY();
      // Exits the game if this area is clicked
      if ((mouseX > 400) && (mouseX < 800) && (mouseY > 600) && (mouseY < 700)){
        System.exit(0);
      }
      // Initializes the game if this area of the screeen is clicked
      if ((mouseX > 400) && (mouseX < 800) && (mouseY > 450) && (mouseY < 550)){
        versus = "vsScreen";
      }
      changeScenes();
    }
  };

  private EventHandler<MouseEvent> vsScreenHandler = new EventHandler<MouseEvent>(){

    /**
    * Handles the mouse clicks in the VS screen of the game
    * @param MouseEvent
    */
    @Override
    public void handle(MouseEvent mouseEvent){
      double mouseX = mouseEvent.getX();
      double mouseY = mouseEvent.getY();
      // Exits the game if this area is clicked
      if ((mouseX > 400) && (mouseX < 800) && (mouseY > 600) && (mouseY < 700)){
        player_2.setName("Computer");
        versus = "vsAI";
      }
      // Initializes the game if this area of the screeen is clicked
      if ((mouseX > 400) && (mouseX < 800) && (mouseY > 450) && (mouseY < 550)){
        versus = "vsPlayer";
      }
      changeScenes();
    }
  };

  /**
  * Groups together all the methods that draws the board game and its graphics
  * @return: graphicBoard the method returns a group that is then added to a stage which will show on the screen
  */
  private Parent startup(){
    initBackGround();
    initBoardGrid();
    initScoreBackground();
    initScoreButtons();
    initPlayerInfo();
    initInputBoard();
    drawBoard();
    turnBoard(player);
    drawScore();
    return graphicBoard;
  }

  /**
  * Groups together all the methods that draws the menu screen
  * @return: graphicBoard the method returns a group that is then added to a stage which will show on the screen
  */
  private Parent mainMenu(){
    initMenuBack();
    initMenuButtons();
    return graphicBoard;
  }

  /**
  * Groups together all the methods that draws the screen to select the VS.
  * @return: graphicBoard the method returns a group that is then added to a stage which will show on the screen
  */
  private Parent vsScreen(){
    initVsScreen();
    initVsScreenButtons();
    initPlayerNames();
    return graphicBoard;
  }

  //sets the turn
  public static void setTurn(String p) {
    player = p;
  }

  //redraws and updates the board and its pieces
  public void resetBoard(){
    board = new Board();
    player = "1";
    graphicBoard.getChildren().clear();
    changeScenes();
  }

  //draws all the pieces on the board
  public void drawBoard(){
    String[][] temp = board.getArray();
    for (int i = 0; i <= 8; i++){
      for (int j = 0; j <= 8; j++){
        if (temp[j][i].equals("1")){
          createBlackPiece((i*100)-25,(j*100)-25);
        }
        else if (temp[j][i].equals("2")){
          createWhitePiece((i*100)-25,(j*100)-25);
        }
      }
    }
    temp = null;
  }

  //draws the scores of both players in a box on the right hand side of the window
  public void drawScore(){
    Rectangle clear = new Rectangle(910,103,225,35);
    clear.setFill(Color.SNOW);
    Rectangle clear2 = new Rectangle(910,203,225,35);
    clear2.setFill(Color.SNOW);
    Text player1Score = new Text(925,125,String.valueOf(board.turnScore("1")));
    player1Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Text player2Score = new Text(925,225,String.valueOf(board.turnScore("2")));
    player2Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));

    graphicBoard.getChildren().addAll(clear,clear2,player1Score,player2Score);
  }

  //Displays text that declares the winner and the scores of the players
  public void drawVictoryScreen() {
    Pane victoryPane = new Pane();
    Scene victoryScene = new Scene(victoryPane, 500, 200);
    Label winnerMessage = new Label();
    if (board.turnScore("1") > board.turnScore("2")) {
      winnerMessage = new Label("Player 1 wins!");
    } else if (board.turnScore("1") < board.turnScore("2")) {
      winnerMessage = new Label("Player 2 wins!");
    }
    else {
      winnerMessage = new Label("");
    }
    winnerMessage.setFont(Font.font("Arial", 20));
    winnerMessage.setLayoutX(100);
    winnerMessage.setLayoutY(100);

    Button exitGame = new Button("Exit Game");
    exitGame.relocate(325,75);
    exitGame.setOnAction(exitGameHandler);

    Button backToMenu = new Button("Back to Main Menu");
    backToMenu.relocate(325,115);
    backToMenu.setOnAction(backToMenuHandler);

    victoryPane.getChildren().addAll(winnerMessage,exitGame,backToMenu);

    Stage victoryStage = new Stage();
    victoryStage.setScene(victoryScene);
    victoryStage.show();
  }

  //switches scenes and handlers from one screen to another
  public void changeScenes(){
    if (versus.equals("menu")){
      scene.setRoot(mainMenu());
      scene.setOnMouseClicked(menuHandler);
    }
    if (versus.equals("vsScreen")){
      scene.setRoot(vsScreen());
      scene.setOnMouseClicked(vsScreenHandler);
    }
    if (versus.equals("vsPlayer")){
      scene.setRoot(startup());
      scene.setOnMouseClicked(vsPlayerHandler);
    }
    if (versus.equals("vsAI")){
      scene.setRoot(startup());
      scene.setOnMouseClicked(vsAIHandler);
    }
  }

  /** Creates a part of the main menu scene which draws the images in the background and other graphic properties
    and adds the images to the graphicBoard group
  */
  public void initMenuBack(){
    Image feltTexture = new Image("images/feltboard.png");
    Image woodEdge = new Image("images/woodwalls.jpg");
    ImageView woodBack = new ImageView();
    ImageView feltBack = new ImageView();
    woodBack.setImage(woodEdge);
    woodBack.setFitWidth(1200);
    woodBack.setFitHeight(850);
    feltBack.setImage(feltTexture);
    feltBack.setX(25);
    feltBack.setY(25);
    feltBack.setFitWidth(1150);
    feltBack.setFitHeight(800);
    Path boardBorder = new Path();
    MoveTo bbstart = new MoveTo(25,25);
    LineTo bb1 = new LineTo(25,826);
    LineTo bb2 = new LineTo(1176,826);
    LineTo bb3 = new LineTo(1176,25);
    LineTo bb4 = new LineTo(25,25);
    boardBorder.setStrokeWidth(5);
    boardBorder.getElements().add(bbstart);
    boardBorder.getElements().addAll(bb1,bb2,bb3,bb4);
    Text title = new Text(240,350,"OTHELLO");
    title.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,175));
    Text title2 = new Text(560,400,"T-8  G-5");
    title2.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));

    graphicBoard.getChildren().addAll(woodBack,feltBack,boardBorder,title,title2);
  }

  //Draws the areas where the start and exit button will be handled if clicked and adds the images to the graphicBoard group
  public void initMenuButtons(){
    Image feltTexture2 = new Image("images/whitefelt.jpg");
    ImageView startFelt = new ImageView();
    startFelt.setImage(feltTexture2);
    startFelt.setX(400);
    startFelt.setY(450);
    startFelt.setFitWidth(400);
    startFelt.setFitHeight(100);
    Path startBorder = new Path();
    MoveTo sbstart = new MoveTo(400,450);
    LineTo sb1 = new LineTo(800,450);
    LineTo sb2 = new LineTo(800,550);
    LineTo sb3 = new LineTo(400,550);
    LineTo sb4 = new LineTo(400,450);
    startBorder.setStrokeWidth(5);
    startBorder.getElements().add(sbstart);
    startBorder.getElements().addAll(sb1,sb2,sb3,sb4);
    Text startText = new Text(470,520,"Start Game");
    startText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,50));
    ImageView exitarea = new ImageView();
    exitarea.setImage(feltTexture2);
    exitarea.setX(400);
    exitarea.setY(600);
    exitarea.setFitWidth(400);
    exitarea.setFitHeight(100);
    Path exitBorder = new Path();
    MoveTo ebstart = new MoveTo(400,600);
    LineTo eb1 = new LineTo(800,600);
    LineTo eb2 = new LineTo(800,700);
    LineTo eb3 = new LineTo(400,700);
    LineTo eb4 = new LineTo(400,600);
    exitBorder.setStrokeWidth(5);
    exitBorder.getElements().add(ebstart);
    exitBorder.getElements().addAll(eb1,eb2,eb3,eb4);
    Text exitText = new Text(480,665,"Exit Game");
    exitText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,50));

    graphicBoard.getChildren().addAll(startFelt,startBorder,startText,exitarea,exitBorder,exitText);
  }

  /** Creates a part of the Game Mode scene which draws the images in the background and other graphic properties
    and adds the images to the graphicBoard group
  */
  public void initVsScreen(){
    Image feltTexture = new Image("images/feltboard.png");
    Image woodEdge = new Image("images/woodwalls.jpg");
    ImageView woodBack = new ImageView();
    ImageView feltBack = new ImageView();
    woodBack.setImage(woodEdge);
    woodBack.setFitWidth(1200);
    woodBack.setFitHeight(850);
    feltBack.setImage(feltTexture);
    feltBack.setX(25);
    feltBack.setY(25);
    feltBack.setFitWidth(1150);
    feltBack.setFitHeight(800);
    Path boardBorder = new Path();
    MoveTo bbstart = new MoveTo(25,25);
    LineTo bb1 = new LineTo(25,826);
    LineTo bb2 = new LineTo(1176,826);
    LineTo bb3 = new LineTo(1176,25);
    LineTo bb4 = new LineTo(25,25);
    boardBorder.setStrokeWidth(5);
    boardBorder.getElements().add(bbstart);
    boardBorder.getElements().addAll(bb1,bb2,bb3,bb4);
    Text title = new Text(270,250,"Select Your");
    title.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,125));
    Text title2 = new Text(250,375,"Game Mode");
    title2.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,125));

    graphicBoard.getChildren().addAll(woodBack,feltBack,boardBorder,title,title2);
  }

  //Draws the areas where the 'vs Player' and 'vs AI' button will be handled if clicked and adds the images to the graphicBoard group
  public void initVsScreenButtons(){
    Image feltTexture2 = new Image("images/whitefelt.jpg");
    ImageView startFelt = new ImageView();
    startFelt.setImage(feltTexture2);
    startFelt.setX(400);
    startFelt.setY(450);
    startFelt.setFitWidth(400);
    startFelt.setFitHeight(100);
    Path startBorder = new Path();
    MoveTo sbstart = new MoveTo(400,450);
    LineTo sb1 = new LineTo(800,450);
    LineTo sb2 = new LineTo(800,550);
    LineTo sb3 = new LineTo(400,550);
    LineTo sb4 = new LineTo(400,450);
    startBorder.setStrokeWidth(5);
    startBorder.getElements().add(sbstart);
    startBorder.getElements().addAll(sb1,sb2,sb3,sb4);
    Text startText = new Text(455,520,"Player Vs Player");
    startText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,40));
    ImageView exitarea = new ImageView();
    exitarea.setImage(feltTexture2);
    exitarea.setX(400);
    exitarea.setY(600);
    exitarea.setFitWidth(400);
    exitarea.setFitHeight(100);
    Path exitBorder = new Path();
    MoveTo ebstart = new MoveTo(400,600);
    LineTo eb1 = new LineTo(800,600);
    LineTo eb2 = new LineTo(800,700);
    LineTo eb3 = new LineTo(400,700);
    LineTo eb4 = new LineTo(400,600);
    exitBorder.setStrokeWidth(5);
    exitBorder.getElements().add(ebstart);
    exitBorder.getElements().addAll(eb1,eb2,eb3,eb4);
    Text exitText = new Text(470,665,"Player Vs AI");
    exitText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,40));

    graphicBoard.getChildren().addAll(startFelt,startBorder,startText,exitarea,exitBorder,exitText);
  }

  //Draws textfields and a button to set names for players
  public void initPlayerNames(){
    Label p1name = new Label("Player 1:");
    p1name.setLayoutX(850);
    p1name.setLayoutY(450);
    NumberBinding nameCoord1 =
    p1name.layoutXProperty().add(p1name.widthProperty().add(10));
    name1.layoutXProperty().bind(nameCoord1);
    name1.selectAll();
    name1.layoutYProperty().bind(p1name.layoutYProperty());

    Label p2name = new Label("Player 2:");
    p2name.setLayoutX(850);
    p2name.setLayoutY(480);
    NumberBinding nameCoord2 =
    p2name.layoutXProperty().add(p2name.widthProperty().add(10));
    name2.layoutXProperty().bind(nameCoord2);
    name2.selectAll();
    name2.layoutYProperty().bind(p2name.layoutYProperty());

    Button setName = new Button("Set Names");
    setName.relocate(910,530);
    setName.setOnAction(playerNameHandler);

    Label pname = new Label("Player:");
    pname.setLayoutX(850);
    pname.setLayoutY(650);
    NumberBinding nameCoord3 =
    pname.layoutXProperty().add(pname.widthProperty().add(10));
    name3.layoutXProperty().bind(nameCoord3);
    name3.selectAll();
    name3.layoutYProperty().bind(pname.layoutYProperty());

    Button setpname = new Button("Set Name");
    setpname.relocate(910,700);
    setpname.setOnAction(aiNameHandler);


    graphicBoard.getChildren().addAll(p1name,name1,p2name,name2,setName,name3,pname,setpname);
  }

  /** Draws the background images for the main game and adds the images to the graphicBoard group
   */
  public void initBackGround(){
    Image feltTexture = new Image("images/feltboard.png");
    Image woodEdge = new Image("images/woodwalls.jpg");
    ImageView woodBack = new ImageView();
    ImageView feltBack = new ImageView();
    woodBack.setImage(woodEdge);
    woodBack.setFitWidth(1200);
    woodBack.setFitHeight(850);
    feltBack.setImage(feltTexture);
    feltBack.setX(25);
    feltBack.setY(25);
    feltBack.setFitWidth(800);
    feltBack.setFitHeight(800);
    Path boardBorder = new Path();
    MoveTo bbstart = new MoveTo(25,25);
    LineTo bb1 = new LineTo(25,826);
    LineTo bb2 = new LineTo(826,826);
    LineTo bb3 = new LineTo(826,25);
    LineTo bb4 = new LineTo(25,25);
    boardBorder.setStrokeWidth(5);
    boardBorder.getElements().add(bbstart);
    boardBorder.getElements().addAll(bb1,bb2,bb3,bb4);

    graphicBoard.getChildren().addAll(woodBack,feltBack,boardBorder);
  }

  /** Draws grid lines for the board to have a visual coordinate system
    and adds the lines to the graphicBoard group
  */
  public void initBoardGrid(){
    Text r1 = new Text(75,20,"1");
    r1.setFill(Color.WHITE);
    Text r2 = new Text(175,20,"2");
    r2.setFill(Color.WHITE);
    Text r3 = new Text(275,20,"3");
    r3.setFill(Color.WHITE);
    Text r4 = new Text(375,20,"4");
    r4.setFill(Color.WHITE);
    Text r5 = new Text(475,20,"5");
    r5.setFill(Color.WHITE);
    Text r6 = new Text(575,20,"6");
    r6.setFill(Color.WHITE);
    Text r7 = new Text(675,20,"7");
    r7.setFill(Color.WHITE);
    Text r8 = new Text(775,20,"8");
    r8.setFill(Color.WHITE);
    Text c1 = new Text(17,75,"1");
    c1.setFill(Color.WHITE);
    Text c2 = new Text(17,175,"2");
    c2.setFill(Color.WHITE);
    Text c3 = new Text(17,275,"3");
    c3.setFill(Color.WHITE);
    Text c4 = new Text(17,375,"4");
    c4.setFill(Color.WHITE);
    Text c5 = new Text(17,475,"5");
    c5.setFill(Color.WHITE);
    Text c6 = new Text(17,575,"6");
    c6.setFill(Color.WHITE);
    Text c7 = new Text(17,675,"7");
    c7.setFill(Color.WHITE);
    Text c8 = new Text(17,775,"8");
    c8.setFill(Color.WHITE);

    graphicBoard.getChildren().addAll(r1,r2,r3,r4,r5,r6,r7,r8,c1,c2,c3,c4,c5,c6,c7,c8);

    for (int i = 0; i<= 7; i++){
      Line vert = new Line(100*i + 25, 25, 100*i + 25, 825);
      graphicBoard.getChildren().add(vert);
    }

    for (int j = 0; j <= 7; j++){
      Line horizontal = new Line(25, 100*j+25, 825, 100*j + 25);
      graphicBoard.getChildren().add(horizontal);
    }
  }

  /** Draws the score board which will contain the player information
    and adds the images to the graphicBoard group
  */
  public void initScoreBackground(){
    Image feltTexture2 = new Image("images/greyfelt.jpg");
    ImageView scoreFelt = new ImageView();
    scoreFelt.setImage(feltTexture2);
    scoreFelt.setX(875);
    scoreFelt.setY(25);
    scoreFelt.setFitWidth(300);
    scoreFelt.setFitHeight(800);
    Path scoreBorder = new Path();
    MoveTo sbstart = new MoveTo(875,25);
    LineTo sb1 = new LineTo(875,825);
    LineTo sb2 = new LineTo(1175,825);
    LineTo sb3 = new LineTo(1175,25);
    LineTo sb4 = new LineTo(875,25);
    scoreBorder.setStrokeWidth(5);
    scoreBorder.getElements().add(sbstart);
    scoreBorder.getElements().addAll(sb1,sb2,sb3,sb4);
    
    graphicBoard.getChildren().addAll(scoreFelt,scoreBorder);
  }

  // Draws areas on the board where things will be handled based on the coordinates on the game
  public void initScoreButtons(){
    Rectangle exitArea = new Rectangle(900,725,110,75);
    exitArea.setFill(Color.SNOW);
    Path exitBorder = new Path();
    MoveTo ebstart = new MoveTo(900,725);
    LineTo eb1 = new LineTo(1010,725);
    LineTo eb2 = new LineTo(1010,800);
    LineTo eb3 = new LineTo(900,800);
    LineTo eb4 = new LineTo(900,725);
    exitBorder.setStrokeWidth(5);
    exitBorder.getElements().add(ebstart);
    exitBorder.getElements().addAll(eb1,eb2,eb3,eb4);
    Text exitText = new Text(930,775,"Exit");
    exitText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));

    Rectangle resetArea = new Rectangle(1040,725,110,75);
    resetArea.setFill(Color.SNOW);
    Path resetBorder = new Path();
    MoveTo rbstart = new MoveTo(1040,725);
    LineTo rb1 = new LineTo(1150,725);
    LineTo rb2 = new LineTo(1150,800);
    LineTo rb3 = new LineTo(1040,800);
    LineTo rb4 = new LineTo(1040,725);
    resetBorder.setStrokeWidth(5);
    resetBorder.getElements().add(rbstart);
    resetBorder.getElements().addAll(rb1,rb2,rb3,rb4);
    Text resetText = new Text(1060,775,"Reset");
    resetText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));

    Rectangle saveArea = new Rectangle(900,625,110,75);
    saveArea.setFill(Color.SNOW);
    Path saveBorder = new Path();
    MoveTo sbstart = new MoveTo(900,625);
    LineTo sb1 = new LineTo(1010,625);
    LineTo sb2 = new LineTo(1010,700);
    LineTo sb3 = new LineTo(900,700);
    LineTo sb4 = new LineTo(900,625);
    saveBorder.setStrokeWidth(5);
    saveBorder.getElements().add(sbstart);
    saveBorder.getElements().addAll(sb1,sb2,sb3,sb4);
    Text saveText = new Text(930,660,"Save\nGame");
    saveText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,15));

    Rectangle loadArea = new Rectangle(1040,625,110,75);
    loadArea.setFill(Color.SNOW);
    Path loadBorder = new Path();
    MoveTo lbstart = new MoveTo(1040,625);
    LineTo lb1 = new LineTo(1150,625);
    LineTo lb2 = new LineTo(1150,700);
    LineTo lb3 = new LineTo(1040,700);
    LineTo lb4 = new LineTo(1040,625);
    loadBorder.setStrokeWidth(5);
    loadBorder.getElements().add(lbstart);
    loadBorder.getElements().addAll(lb1,lb2,lb3,lb4);
    Text loadText = new Text(1070,660,"Load\nGame");
    loadText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,15));

    graphicBoard.getChildren().addAll(exitArea, exitBorder, exitText, resetArea, resetBorder, resetText, saveArea, saveBorder, saveText, loadArea, loadBorder, loadText);
  }

  /** Draws areas for player info and texts about the player names
    and adds the images to the graphicBoard group
  */
  public void initPlayerInfo(){
    Text player1Text = new Text(910,90,player_1.getName() + " (" + player_1.getColour() + ")");
    player1Text.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text player1Score = new Text(925,125,String.valueOf(board.turnScore("1")));
    player1Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Text player2Text = new Text(910,190,player_2.getName() + " (" + player_2.getColour() + ")");
    player2Text.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text player2Score = new Text(925,225,String.valueOf(board.turnScore("2")));
    player2Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Rectangle playerarea = new Rectangle(900,50,250,200);
    playerarea.setFill(Color.SNOW);
    Line pbmid = new Line(900,150,1150,150);
    pbmid.setStrokeWidth(5);
    Path playerBorder = new Path();
    MoveTo pbstart = new MoveTo(900,50);
    LineTo pb1 = new LineTo(1150,50);
    LineTo pb2 = new LineTo(1150,250);
    LineTo pb3 = new LineTo(900,250);
    LineTo pb4 = new LineTo(900,50);
    playerBorder.setStrokeWidth(5);
    playerBorder.getElements().add(pbstart);
    playerBorder.getElements().addAll(pb1,pb2,pb3,pb4);

    graphicBoard.getChildren().addAll(playerarea,playerBorder,pbmid,player1Text,player1Score,player2Text,player2Score);
  }

  /** Draws an area where textfields and a button is placed to handle input coordinates into the boardBorder
  */
  public void initInputBoard(){
    Rectangle inputArea = new Rectangle(900,300,250,200);
    inputArea.setFill(Color.SNOW);
    Path inputBorder = new Path();
    MoveTo ibstart = new MoveTo(900,300);
    LineTo ib1 = new LineTo(1150,300);
    LineTo ib2 = new LineTo(1150,500);
    LineTo ib3 = new LineTo(900,500);
    LineTo ib4 = new LineTo(900,300);
    inputBorder.setStrokeWidth(5);
    inputBorder.getElements().add(ibstart);
    inputBorder.getElements().addAll(ib1,ib2,ib3,ib4);

    Label xLabel = new Label("X:");
    xLabel.setLayoutX(925);
    xLabel.setLayoutY(320);
    NumberBinding xLabelCoord =
    xLabel.layoutXProperty().add(xLabel.widthProperty().add(10));
    xCoord.layoutXProperty().bind(xLabelCoord);
    xCoord.selectAll();
    xCoord.layoutYProperty().bind(xLabel.layoutYProperty());


    Label yLabel = new Label("Y:");
    yLabel.setLayoutX(925);
    yLabel.setLayoutY(350);
    NumberBinding yLabelCoord =
    yLabel.layoutXProperty().add(yLabel.widthProperty().add(10));
    yCoord.layoutXProperty().bind(yLabelCoord);
    yCoord.selectAll();
    yCoord.layoutYProperty().bind(yLabel.layoutYProperty());

    Button place = new Button("Place Piece");
    place.relocate(985,380);
    place.setOnAction(inputButtonHandler);

    graphicBoard.getChildren().addAll(inputArea,inputBorder,xLabel,xCoord,yLabel,yCoord,place);
  }

  // Draws and updates the message board to switch player turns and add the reset button area
  public void turnBoard(String player){
    Rectangle cov = new Rectangle(925,415,200,35);
    cov.setFill(Color.SNOW);
    Text playerText = new Text(920,450, player_1.getName() + "'s Turn!");
    if (player == "1") {
      playerText = new Text(920,450, player_1.getName() + "'s Turn!");
    } else {
      playerText = new Text(920,450, player_2.getName() + "'s Turn!");
    }
    playerText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));

    graphicBoard.getChildren().addAll(cov,playerText);
  }

  /**
  * Draws a white piece on the board and adds the shape to the graphicBoard group
  * @param midx the x coordinate of the middle of the piece that is drawn
  * @param midy the y coordinate of the middle of the piece that is drawn
  */
  public void createWhitePiece(double midx,double midy){
    Circle whitePieceBorder = new Circle(midx, midy, 45);
    whitePieceBorder.setFill(Color.BLACK);
    Circle whitePiece = new Circle(midx,midy,43);
    whitePiece.setFill(Color.IVORY);

    graphicBoard.getChildren().addAll(whitePieceBorder,whitePiece);
  }

  /**
  * Draws a black piece on the board and adds the shape to the graphicBoard group
  * @param midx gives the x coordinate about where the middle of the piece is drawn
  * @param midy gives the y coordinate about where the middle of the piece is drawn
  */
  public void createBlackPiece(double midx,double midy){
    Circle blackPieceBorder = new Circle(midx, midy, 45);
    blackPieceBorder.setFill(Color.IVORY);
    Circle blackPiece = new Circle(midx, midy, 43);
    blackPiece.setFill(Color.BLACK);

    graphicBoard.getChildren().addAll(blackPieceBorder,blackPiece);
  }

  // clears the screen in teh terminal to make it look more readable...
  public static void clearScreen() {
    for (int i = 0; i < 10; ++i) System.out.println();
  }

// Creates a stage and adds the group to the scene which is added to the stage and shown on the screen
  @Override
  public void start(Stage mstage){
    stage = mstage;
    scene.setRoot(mainMenu());
    scene.setOnMouseClicked(menuHandler);
    stage.setResizable(true);
    stage.setTitle("Othello");
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();

    Task<Void> sleeper = new Task<Void>() {
      @Override
      protected Void call() throws Exception {
          try {
              Thread.sleep(50);
          } catch (InterruptedException e) {
          }
          return null;
      }
    };
    sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
        @Override
        public void handle(WorkerStateEvent event) {
          if (board.isFull() == true || (!Check.AnyMovesLeft(player_1, board) && !Check.AnyMovesLeft(player_2, board))) { // Once graphicBoard is filled, end game and print out result
            drawVictoryScreen(); // will generate the end of the screen
          }
        }
    });
    new Thread(sleeper).start();
    }

// Initiates the program
  public static void main(String args[]){
    launch(args);
  }
}