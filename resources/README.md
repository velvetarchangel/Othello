# Othello
Welcome to Othello! *Othello*, also known as *Reversii*, is a fun and strategic turn-based board game played by two players. 
Each player in the game is in a contest to claim the most territory on the board they possibly can using their pieces. Challenge
players in a duel of critical thinking and see if you can prevail!

## Getting Started
The currently running is a ![version](https://img.shields.io/badge/version-GUI--3.0-green.svg?style=flat-square) of the game. To start the game, the
following steps are advised:-
1. Make sure you have a working version of Java and JavaFX running on your computer.
2. With that confirmed, open up the terminal from the folder where you saved the game file named **OthelloGraphics.java**. For instructions on how to open up a terminal, please refer to [Opening a Terminal](#opening-a-terminal), and for help with JavaFX, please refer to [JavaFX Help](#javafx-help) in the [Support](#support) section.
3. Compile the game file code in the terminal by using this following command ```javac ./gui/OthelloGraphics.java```
4. Now, run the program using this command ```java gui.OthelloGraphics```
5. The game menu will pop up on the window which will give you the options to either start or exit the game. Click on the *"Start Game"* option and you can go on right ahead and start playing the game.
6. Next, the game will prompt you to select a game mode where you can choose between *"Player Vs Player"* mode to challenge another human and *"Player Vs AI"* mode to challenge the game AI.
9. You can type in your player names in the *"Player 1"* and *"Player 2"* fields and set the names by clicking on *"Set Names"*
10. With the game started, you can move the cursor on the game board and click on a valid slot to place a token.
11. The game provides you with options to save a game, load from a previous saved state or reset the entire game to start a new one.
12. The game ends when the board becomes full or both players do not have any moves left. You can also click on the *"Exit Game"* option anytime to exit out of the game.

## Description
The game is played on an 8 x 8 board. There are two players, Player 1 and Player 2. Each player's token on the board is represented by a black and white token. The game decides which player goes first at random, and that player can click on one of the appropiate slots on the board to place their token. The player going first is Player 1 and the player will get to use the *"BLACK"* token. Tokens can only be placed adjacent to or diagonally from the opposing person’s token. For example: *"BLACK"* tokens can only be placed next to or diagonally from a *"WHITE"* token and vice versa. If there are *"BLACK"* tokens that are surrounded by *"WHITE"* tokens on both sides horizontally, vertically or diagonally, the *"BLACK"* tokens then become *"WHITE"* and vice versa. The game ends when the board is filled with tokens or there are no more valid moves left. Then score is calculated by counting the number of tokens for each player.

For an elaborate set of game rules, please visit [Othello Wikipedia page ](https://en.wikipedia.org/wiki/Reversi).

## Usage
### ![version](https://img.shields.io/badge/version-GUI--2.0-green.svg?style=flat-square)
The game currently running is ![version](https://img.shields.io/badge/version-GUI--2.0-green.svg?style=flat-square).
- The board is displayed in a *Graphical User Interface* with two *"BLACK"* tokens and other two *"WHITE"* tokens in the center of the board. A *score* and other game options are also displayed on the game interface.
- It indicates the player in turn and prompts the player to make a move on the board.
- When a player makes a valid move, a token used by the player is placed in the board slot selected.
- If a *valid move* is made then, the *score* of the player who made the turn and the board is *updated* with the number of points scored and the flipped pieces respectively.
- After a player has made a valid move, it indicates the turn for the other player and waits for a valid move.
- If an *invalid move* is made then, it tells the player that the move made was invalid and prompts for a different move.

### ![version](https://img.shields.io/badge/version-text--based-lightgrey.svg?style=flat-square)
To play the ![version](https://img.shields.io/badge/version-text--based-lightgrey.svg?style=flat-square) of the game.
- To compile the game in the console, use the command ```javac ./text/Othello.java``` 
- After you compile the game file, use the command ```java text.Othello``` in the terminal to get it running.
- The game will greet you with a welcome message and provide you with gameplay options.
- You can enter **'p'** to play the player versus player mode.
  
  To challenge the **AI**, enter **'c'**.
  
  Enter **'q'** if you want to quit from the game.
- You can set your name as the game will prompt you to do so and then player turn will be randomly decided and you will be assigned with a token designated for you.
- The game will ask you to provide the x and y co-ordinates respectively for placing a token on the board.
- The tokens are *"1"* and *"2"*.
- If it's a valid move then, the board will be updated with flipped tokens otherwise, the game will let you know that, the move was invalid and prompt you to make a new move.
- The game keeps track of any valid moves left for a player and if there are no valid moves for a player then, it passes the turn on to the other player.
- When playing against the AI, it will make moves automatically after the human player has finished their turn.
- The game indicates the player's turn and the score for the players.
## GUI
The *Graphical User Interface* for this game includes:-
- **Game Menu:** The game menu cordially welcomes you in to the game and waits for your response to start the game.
- **Gameboard:** It is a green coloured board with black lines going horizontally and vertically across the board. The board has 64 slots which can be clicked to place the tokens on. When a slot is clicked, a *"BLACK"* or a *"WHITE"* token is placed inside the slot.
- **Scoreboard:** The points scored by Player 1 and Player 2 is displayed and gets updated after valid moves are made.
- **Turn indicator:** Shows the player currently in turn.
- **Save Game and Load Game options:** Labels showing *Save Game* that can be clicked to save game state, and *Load Game* to load a game savestate are present.
- **Reset option:** *Reset Game* button can be clicked to reset the game and clear the board to start a new one.
- **Quit option:** A label showing *Click here to exit* that can be clicked on to exit out of the game.

## Support
##### Opening a Terminal
- **For Linux/Mac:** *"Right-click"* inside the window and then select *"Open in Terminal"*.
- **For Windows:** Go to the folder > press *"alt-D"* and type in *"cmd"*.

##### JavaFX Help
Your computer will not display the game window if *JavaFX* is missing from it. JavaFX should be available with Java 8 and up. It is possible that you are working with a non-Oracle version of Java, in which case it may be missing. If that is the case then, you might want to take the following steps:-
1. Download the **jfxrt.jar** file from online or this repository, and place it in the game file folder.
2. Before compiling and running the game file, run the follwing command,
- **For Linux/Mac:** ```javac -cp .:jrxrt.jar *.java```
- **For Windows:**  ```javac -cp .;jrxrt.jar *.java```

With that you should be good to compile and run the game file.

##### Logic Class Tests
Logic class Junit tests are provided to test the logical functioning of the game file classes. You can run the test class files to check the working condition of the game class files. Instrunctions on how to run Junit tests are given below:-
1. Make sure that the game class files are present together with the test class files.
2. You also need to make sure that the files **hamcrest-core-1.3.jar** and **junit-4.12.jar** are present in the same directory as the game class files and the test class files.
3. To run the tests, you will need to use the following commands,
- **For Linux/Mac:** ```javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java```

followed by ```java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore <name of test class>```
- **For Windows:** ```javac -cp .;junit-4.12.jar;hamcrest-core-1.3.jar *.java```

followed by ```java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore <name of test class>```

## Authors
**Vivian Huynh** - Team Leader

**Himika Dastidar**

**Jayoo Hwang**

**Al Mahmud Dipto** 

**Miguel Merin**

## Project Status
The **Othello** project is currently running on a fully working *Graphical User Interface(GUI)*. The game also has an included AI to play against.
