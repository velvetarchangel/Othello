import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.MouseInfo;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.Parent;
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
import java.lang.Math;

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
  private String player = "1";
  private Player player_1 = new Player();
  private Player player_2 = new Player();
  private int playerOneScore = player_1.getScore();
  private int playerTwoScore = player_2.getScore();
  private String versus = "menu";

  private EventHandler<MouseEvent> vsPlayerHandler = new EventHandler<MouseEvent>(){
    @Override
    /**
    * Handles mouse clicks, checks if space clicked is a valid move or a button and
    * updates the game based on action
    * @param mouseEvent
    */
    public void handle(MouseEvent mouseEvent){
      double mouseX = mouseEvent.getX();
      double mouseY = mouseEvent.getY();
      if ((mouseX > 900) && (mouseX < 1150) && (mouseY > 700) && (mouseY < 800)){
        System.exit(0);
      }
      if ((mouseX > 25) && (mouseX < 825) && (mouseY > 25) && (mouseY < 825)){
        int x = (int)(Math.floor((mouseX - 25.0) / 100.0) + 1);
        int y = (int)(Math.floor((mouseY - 25.0) / 100.0) + 1);

        /**
         * Checks whether the board is full of pieces. If the board is full, the game
         * end, and the winners are announce. Otherwise the game continues.
         */

        if (board.gameOver() == true) { // Once graphicBoard is filled, end game and print out result
            drawVictoryScreen(); // will generate the end of the screen
        }

        // if board is not filled continue the game
        else {
            if (board.gameOver() == false) {
                clearScreen();
                // what to do if it is player 1's turn
                if (player.equals("1") && Check.AnyMovesLeft("1", board)) { // Player 1's turn
                    System.out.println("X: " + x + "    Y: " + y);
                    board.printBoard();
                    System.out.println("\n" + "It is player " + player + "'s turn");
                    int[] flipped = Check.move(x, y, player, board);

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
                        player = "2";
                    }
                  }

                // checks it if it is player 2's turn and runs through the game sequence
                else if (player.equals("2") && Check.AnyMovesLeft("2", board)) {
                    board.printBoard();
                    System.out.println("\n" + "It is player " + player + "'s turn");
                    int[] flipped = Check.move(x, y, player, board);

                    // Reprompts users if no pieces can be flipped
                    if ((flipped[0] == 0))
                    {
                        player = "2";
                        System.out.println("Invalid move, try again");
                        flipped = Check.move(x, y, player, board);
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
                        System.out.println("\n" + "Player 1's score is " + playerOneScore);
                        System.out.println("Player 2's score is " + playerTwoScore);
                        player = "1";
                    }
                  }
            }
        }
        redrawBoard();
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
    initScoreBoard();
    initPlayerInfo();
    drawBoard();
    drawScore();
    createMessageBoard();
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

  private Parent vsScreen(){
    initVsScreen();
    initVsScreenButtons();
    return graphicBoard;
  }

  //redraws and updates the board and its pieces
  public void redrawBoard(){
    graphicBoard.getChildren().removeAll();
    startup();
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
  }

  //draws the scores of both players in a box on the right hand side of the window
  public void drawScore(){
    Rectangle clear = new Rectangle(920,155,200,50);
    clear.setFill(Color.SNOW);
    Rectangle clear2 = new Rectangle(920,355,200,50);
    clear2.setFill(Color.SNOW);
    Text player1Score = new Text(925,200,String.valueOf(playerOneScore));
    player1Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Text player2Score = new Text(925,400,String.valueOf(playerTwoScore));
    player2Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    createMessageBoard();

    graphicBoard.getChildren().addAll(clear,clear2,player1Score,player2Score);
  }

  //Displays text that declares the winner and the scores of the players
  public void drawVictoryScreen(){
    if (player_1.getScore() > player_2.getScore()) {
        System.out.println("Player 1 wins! Final score is: " + player_1.getScore());
        drawWinnerOne();
    } else if (player_2.getScore() < player_2.getScore()) {
        System.out.println("Player 2 wins! Final score is: " + player_2.getScore());
        drawWinnerTwo();
    } else if (player_2.getScore() == player_2.getScore()) {
        System.out.println("It's a draw! " + "Player 1's score: " + player_1.getScore()
                + "Player 2's score: " + player_2.getScore());
        drawWinnerBoth();
    }
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
  }

  /** Creates a part of the main menu scene which draws the images in the background and other graphic properties
    and adds the images to the graphicBoard group
  */
  public void initMenuBack(){
    Image feltTexture = new Image("feltboard.png");
    Image woodEdge = new Image("woodwalls.jpg");
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
    Image feltTexture2 = new Image("whitefelt.jpg");
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
    Image feltTexture = new Image("feltboard.png");
    Image woodEdge = new Image("woodwalls.jpg");
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
    Text title = new Text(250,250,"Select Your");
    title.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,125));
    Text title2 = new Text(230,375,"Game Mode");
    title2.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,125));

    graphicBoard.getChildren().addAll(woodBack,feltBack,boardBorder,title,title2);
  }

  //Draws the areas where the 'vs Player' and 'vs AI' button will be handled if clicked and adds the images to the graphicBoard group
  public void initVsScreenButtons(){
    Image feltTexture2 = new Image("whitefelt.jpg");
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
    Text startText = new Text(440,510,"Player Vs Player");
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

  /** Draws the background images for the main game and adds the images to the graphicBoard group
   */
  public void initBackGround(){
    Image feltTexture = new Image("feltboard.png");
    Image woodEdge = new Image("woodwalls.jpg");
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
  public void initScoreBoard(){
    Image feltTexture2 = new Image("greyfelt.jpg");
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
    Rectangle exitarea = new Rectangle(900,700,250,100);
    exitarea.setFill(Color.SNOW);
    Path exitBorder = new Path();
    MoveTo ebstart = new MoveTo(900,700);
    LineTo eb1 = new LineTo(1150,700);
    LineTo eb2 = new LineTo(1150,800);
    LineTo eb3 = new LineTo(900,800);
    LineTo eb4 = new LineTo(900,700);
    exitBorder.setStrokeWidth(5);
    exitBorder.getElements().add(ebstart);
    exitBorder.getElements().addAll(eb1,eb2,eb3,eb4);
    Text exitText = new Text(920,760,"Click here to exit.");
    exitText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));

    graphicBoard.getChildren().addAll(scoreFelt,scoreBorder,exitarea,exitBorder,exitText);
  }

  /** Draws areas for player info and texts about the player names
    and adds the images to the graphicBoard group
  */
  public void initPlayerInfo(){
    Text player1Text = new Text(925,100,"Player1");
    player1Text.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text player1Score = new Text(925,200,String.valueOf(playerOneScore));
    player1Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Text player2Text = new Text(925,300,"Player2");
    player2Text.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text player2Score = new Text(925,400,String.valueOf(playerTwoScore));
    player2Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Rectangle playerarea = new Rectangle(900,50,250,425);
    playerarea.setFill(Color.SNOW);
    Path playerBorder = new Path();
    MoveTo pbstart = new MoveTo(900,50);
    LineTo pb1 = new LineTo(1150,50);
    LineTo pb2 = new LineTo(1150,475);
    LineTo pb3 = new LineTo(900,475);
    LineTo pb4 = new LineTo(900,50);
    playerBorder.setStrokeWidth(5);
    playerBorder.getElements().add(pbstart);
    playerBorder.getElements().addAll(pb1,pb2,pb3,pb4);

    graphicBoard.getChildren().addAll(playerarea,playerBorder,player1Text,player1Score,player2Text,player2Score);
  }

  // display when player 1 wins
  public void drawWinnerOne(){
      Text winner = new Text(200,500,"Player 1 Wins!");
      winner.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,150));
      winner.setFill(Color.MEDIUMSPRINGGREEN);

      graphicBoard.getChildren().add(winner);
  }

  // display when player 2 wins
  public void drawWinnerTwo(){
    Text winner = new Text(200,500,"Player 2 Wins!");
    winner.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,150));
    winner.setFill(Color.MEDIUMSPRINGGREEN);

    graphicBoard.getChildren().add(winner);
  }

  // display when it is a draw
  public void drawWinnerBoth(){
    Text winner = new Text(200,500,"It's a draw");
    winner.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,150));
    winner.setFill(Color.MEDIUMSPRINGGREEN);

    graphicBoard.getChildren().add(winner);
  }

  // Draws and updates the message board to switch player turns and add the reset button area
  public void createMessageBoard(){
    Text playerText = new Text(965,560,player + "'s Turn!");
    playerText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text resetText = new Text(955,630,"Reset Game");
    resetText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Rectangle messagearea = new Rectangle(900,500,250,175);
    messagearea.setFill(Color.SNOW);
    Path messageBorder = new Path();
    MoveTo mstart = new MoveTo(900,500);
    LineTo mb1 = new LineTo(1150,500);
    LineTo mb2 = new LineTo(1150,675);
    LineTo mb3 = new LineTo(900,675);
    LineTo mb4 = new LineTo(900,500);
    Line mbmid = new Line(900,587,1150,587);
    mbmid.setStrokeWidth(5);
    messageBorder.setStrokeWidth(5);
    messageBorder.getElements().add(mstart);
    messageBorder.getElements().addAll(mb1,mb2,mb3,mb4);

    graphicBoard.getChildren().addAll(messagearea,messageBorder,mbmid,playerText);
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

  public static void clearScreen() {
    for (int i = 0; i < 10; ++i) System.out.println();
  }

// Creates a stage and adds the group to the scene which is added to the stage and shown on the screen
  @Override
  public void start(Stage mstage) throws FileNotFoundException{
    stage = mstage;
    scene.setRoot(mainMenu());
    scene.setOnMouseClicked(menuHandler);
    stage.setResizable(true);
    stage.setTitle("Othello");
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }

// Initiates the program
  public static void main(String args[]){
    launch(args);
  }
}
