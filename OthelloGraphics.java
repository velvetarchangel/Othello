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

public class OthelloGraphics extends Application{
  private Board board = new Board();
  private String player = "1";
  private Group graphicBoard = new Group();
  private Scene scene = new Scene(graphicBoard,1200,850,Color.BLACK);
  private Player player_1 = new Player();
  private Player player_2 = new Player();
  private int playerOneScore = player_1.getScore();
  private int playerTwoScore = player_2.getScore();
  private String versus = "menu";

  private EventHandler<MouseEvent> vsPlayerHandler = new EventHandler<MouseEvent>(){
    @Override
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
            drawBoard();
            drawScore();
            drawVictoryScreen();
        }

        else {
            if (board.gameOver() == false) {
                clearScreen();
                // Continues game if board is not full
                if (player.equals("1") && Check.AnyMovesLeft("1", board)) { // Player 1's turn
                    System.out.println("X: " + x + "    Y: " + y);
                    board.printBoard();
                    System.out.println("\n" + "It is player " + player + "'s turn");
                    int[] flipped = Check.move(x, y, player, board);
                    drawBoard();
                    drawScore();

                    if ((flipped[0] == 0)) { // Reprompts user if no pieces can be flipped
                        player = "1";
                        System.out.println("Invalid move, try again");
                    }

                    if (flipped[0] != 0) { // If pieces can be flipped, update board and scores
                        board.updateBoard(x, y, player, flipped);
                        board.printBoard();
                        playerOneScore = board.turnScore("1");
                        playerTwoScore = board.turnScore("2");
                        player_1.setScore(playerOneScore);
                        player_2.setScore(playerTwoScore);
                        System.out.println("\n" + "Player 1's score is " + playerOneScore);
                        System.out.println("Player 2's score is " + playerTwoScore);
                        drawBoard();
                        drawScore();
                    }

                    player = "2"; // Switch to player 2's turn
                    }

                else if (player.equals("2") && Check.AnyMovesLeft("2", board)) { // Player 2's turn
                    board.printBoard();
                    System.out.println("\n" + "It is player " + player + "'s turn");
                    int[] flipped = Check.move(x, y, player, board);
                    drawBoard();
                    drawScore();

                    if ((flipped[0] == 0)) { // Reprompts users if no pieces can be flipped
                        player = "2";
                        System.out.println("Invalid move, try again");
                        flipped = Check.move(x, y, player, board);
                    }

                    if (flipped[0] != 0) { // If pieces can be flipped, update the board and scores
                        board.updateBoard(x, y, player, flipped);
                        board.printBoard();
                        playerTwoScore = board.turnScore(player);
                        playerOneScore = board.turnScore("1");
                        player_2.setScore(playerTwoScore);
                        player_1.setScore(playerOneScore);
                        System.out.println("\n" + "Player 1's score is " + playerOneScore);
                        System.out.println("Player 2's score is " + playerTwoScore);
                        drawBoard();
                        drawScore();
                    }
                    player = "1"; // Switch to player 1's turn
                  }
                  drawBoard();
                  drawScore();
            }
        }
      }
    }
  };

  private EventHandler<MouseEvent> menuHandler = new EventHandler<MouseEvent>(){
    @Override
    public void handle(MouseEvent mouseEvent){
      double mouseX = mouseEvent.getX();
      double mouseY = mouseEvent.getY();
      if ((mouseX > 400) && (mouseX < 800) && (mouseY > 600) && (mouseY < 700)){
        System.exit(0);
      }
      if ((mouseX > 400) && (mouseX < 800) && (mouseY > 450) && (mouseY < 850)){
        versus = "start";
      }
    }
  };

  private Parent startup() throws FileNotFoundException{
    initBackGround();
    initBoardGrid();
    initScoreBoard();
    initPlayerInfo();
    drawBoard();
    createMessageBoard();
    return graphicBoard;
  }

  private Parent mainMenu() throws FileNotFoundException{
    initMenuBack();
    initMenuButtons();
    return graphicBoard;
  }

  public void drawBoard(){
    String[][] temp = board.getArray();
    for (int i = 0; i <= 8; i++){
      for (int j = 0; j <= 8; j++){
        if (temp[j][i].equals("1")){
          createWhitePiece((i*100)-25,(j*100)-25);
        }
        else if (temp[j][i].equals("2")){
          createBlackPiece((i*100)-25,(j*100)-25);
        }
      }
    }
  }

  public void drawScore(){
    Rectangle clear = new Rectangle();
    clear.setX(920);
    clear.setY(155);
    clear.setWidth(200);
    clear.setHeight(50);
    clear.setFill(Color.SNOW);
    Rectangle clear2 = new Rectangle();
    clear2.setX(920);
    clear2.setY(355);
    clear2.setWidth(200);
    clear2.setHeight(50);
    clear2.setFill(Color.SNOW);
    Text player1Score = new Text(925,200,String.valueOf(playerOneScore));
    player1Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Text player2Score = new Text(925,400,String.valueOf(playerTwoScore));
    player2Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    createMessageBoard();

    graphicBoard.getChildren().addAll(clear,clear2,player1Score,player2Score);
  }

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

  public void changeScenes() throws FileNotFoundException{
    if (versus.equals("menu")){
      scene.setRoot(mainMenu());
      scene.setOnMouseClicked(menuHandler);
    }
    if (versus.equals("start")){
      scene.setRoot(startup());
      scene.setOnMouseClicked(vsPlayerHandler);
    }
  }

  public void initMenuBack() throws FileNotFoundException{
    Image feltTexture = new Image(new FileInputStream("feltboard.png"));
    Image woodEdge = new Image(new FileInputStream("woodwalls.jpg"));
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
    Text title = new Text(270,350,"OTHELLO");
    title.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,200));
    Text title2 = new Text(560,400,"T-8  G-5");
    title2.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));

    graphicBoard.getChildren().addAll(woodBack,feltBack,boardBorder,title,title2);
  }

  public void initMenuButtons() throws FileNotFoundException{
    Image feltTexture2 = new Image(new FileInputStream("whitefelt.jpg"));
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
    Text startText = new Text(490,520,"Start Game");
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
    Text exitText = new Text(500,665,"Exit Game");
    exitText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,50));

    graphicBoard.getChildren().addAll(startFelt,startBorder,startText,exitarea,exitBorder,exitText);
  }

  public void initBackGround() throws FileNotFoundException{
    Image feltTexture = new Image(new FileInputStream("feltboard.png"));
    Image woodEdge = new Image(new FileInputStream("woodwalls.jpg"));
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

  public void initBoardGrid() throws FileNotFoundException{
    Line v1 = new Line(125,25,125,825);
    Line v2 = new Line(225,25,225,825);
    Line v3 = new Line(325,25,325,825);
    Line v4 = new Line(425,25,425,825);
    Line v5 = new Line(525,25,525,825);
    Line v6 = new Line(625,25,625,825);
    Line v7 = new Line(725,25,725,825);
    Line h1 = new Line(25,125,825,125);
    Line h2 = new Line(25,225,825,225);
    Line h3 = new Line(25,325,825,325);
    Line h4 = new Line(25,425,825,425);
    Line h5 = new Line(25,525,825,525);
    Line h6 = new Line(25,625,825,625);
    Line h7 = new Line(25,725,825,726);

    graphicBoard.getChildren().addAll(v1,v2,v3,v4,v5,v6,v7,h1,h2,h3,h4,h5,h6,h7);
  }

  public void initScoreBoard() throws FileNotFoundException{
    Image feltTexture2 = new Image(new FileInputStream("greyfelt.jpg"));
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
    Rectangle exitarea = new Rectangle();
    exitarea.setX(900);
    exitarea.setY(700);
    exitarea.setWidth(250);
    exitarea.setHeight(100);
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

  public void initPlayerInfo() throws FileNotFoundException{
    Text player1Text = new Text(925,100,"Player1");
    player1Text.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text player1Score = new Text(925,200,String.valueOf(playerOneScore));
    player1Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Text player2Text = new Text(925,300,"Player2");
    player2Text.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text player2Score = new Text(925,400,String.valueOf(playerTwoScore));
    player2Score.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,25));
    Rectangle playerarea = new Rectangle();
    playerarea.setX(900);
    playerarea.setY(50);
    playerarea.setWidth(250);
    playerarea.setHeight(425);
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

  public void drawWinnerOne(){

  }

  public void drawWinnerTwo(){

  }

  public void drawWinnerBoth(){

  }

  public void createMessageBoard(){
    Text playerText = new Text(925,600,player);
    playerText.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Text playerText2 = new Text(940,600,"'s Turn!");
    playerText2.setFont(Font.font("impact",FontWeight.NORMAL,FontPosture.REGULAR,25));
    Rectangle messagearea = new Rectangle();
    messagearea.setX(900);
    messagearea.setY(500);
    messagearea.setWidth(250);
    messagearea.setHeight(175);
    messagearea.setFill(Color.SNOW);
    Path messageBorder = new Path();
    MoveTo mstart = new MoveTo(900,500);
    LineTo mb1 = new LineTo(1150,500);
    LineTo mb2 = new LineTo(1150,675);
    LineTo mb3 = new LineTo(900,675);
    LineTo mb4 = new LineTo(900,500);
    messageBorder.setStrokeWidth(5);
    messageBorder.getElements().add(mstart);
    messageBorder.getElements().addAll(mb1,mb2,mb3,mb4);

    graphicBoard.getChildren().addAll(messagearea,messageBorder,playerText,playerText2);
  }

  public void createWhitePiece(double midx,double midy){
    Circle whitePieceBorder = new Circle();
    whitePieceBorder.setFill(Color.BLACK);
    whitePieceBorder.setCenterX(midx);
    whitePieceBorder.setCenterY(midy);
    whitePieceBorder.setRadius(45);
    Circle whitePiece = new Circle();
    whitePiece.setFill(Color.IVORY);
    whitePiece.setCenterX(midx);
    whitePiece.setCenterY(midy);
    whitePiece.setRadius(43);

    graphicBoard.getChildren().addAll(whitePieceBorder,whitePiece);
  }

  public void createBlackPiece(double midx,double midy){
    Circle blackPieceBorder = new Circle();
    blackPieceBorder.setFill(Color.IVORY);
    blackPieceBorder.setCenterX(midx);
    blackPieceBorder.setCenterY(midy);
    blackPieceBorder.setRadius(45);
    Circle blackPiece = new Circle();
    blackPiece.setFill(Color.BLACK);
    blackPiece.setCenterX(midx);
    blackPiece.setCenterY(midy);
    blackPiece.setRadius(43);

    graphicBoard.getChildren().addAll(blackPieceBorder,blackPiece);
  }

  public static void clearScreen() {
    for (int i = 0; i < 50; ++i) System.out.println();
  }

  @Override
  public void start(Stage stage) throws FileNotFoundException{
    scene.setRoot(startup());
    scene.setOnMouseClicked(vsPlayerHandler);
    stage.setResizable(true);
    stage.setTitle("Othello");
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }

  public static void main(String args[]){
    launch(args);
  }
}
