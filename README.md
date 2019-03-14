# Othello
Welcome to Othello! *Othello*, also known as *Reversii*, is a fun and strategic turn-based board game played by two players. 
Each player in the game is in a contest to claim the most territory on the board they possibly can using their pieces. Challenge
players in a duel of critical thinking and see if you can prevail!

## Getting Started
The currently running is a ![version](https://img.shields.io/badge/version-GUI-green.svg?style=flat-square) of the game. To start the game, the
following steps are advised:-
1. Make sure you have a working version of Java and JavaFX running on your computer.
2. With that confirmed, open up the terminal from the folder where you saved the game file named **Othello.java**. For instructions on how to open up a terminal, please refer to [Opening a Terminal](#opening-a-terminal), and for help with JavaFX, please refer to [JavaFX Help](#javafx-help) in the [Support](#support) section.
3. Compile the game file code in the terminal by using this following command ```javac Othello.java```
4. Next, you will have to get the graphics for the game compiled by using the command ```javac OthelloGraphics.java```
5. Now, run the program using this command ```java OthelloGraphics```
6. A new game window will pop up on the screen and you can go on right ahead and start playing.
7. You can move the cursor on the game board and click on a valid slot to place a token.
8. The game ends when the board becomes full. You can also click on the *"Click here to exit"* option anytime to exit out of the game.

## Description
The game is played on an 8 x 8 board. There are two players, Player 1 and Player 2. Each player's token on the board is represented by a black and white token. The game decides which player goes first at random, and that player can click on one of the appropiate slots on the board to place their token. The player going first is Player 1 and the player will get to use the *"BLACK"* token. Tokens can only be placed adjacent to or diagonally from the opposing person’s token. For example: *"BLACK"* tokens can only be placed next to or diagonally from a *"WHITE"* token and vice versa. If there are *"BLACK"* tokens that are surrounded by *"WHITE"* tokens on both sides horizontally, vertically or diagonally, the *"BLACK"* tokens then become *"WHITE"* and vice versa. The game ends when the board is filled with tokens or there are no more valid moves left. Then score is calculated by counting the number of tokens for each player.

For an elaborate set of game rules, please visit [Othello Wikipedia page ](https://en.wikipedia.org/wiki/Reversi).

## Usage
### ![version](https://img.shields.io/badge/version-GUI-green.svg?style=flat-square)
The game currently running is ![version](https://img.shields.io/badge/version-GUI-green.svg?style=flat-square).
- The board is displayed in a *Graphical User Interface* with two *"BLACK"* tokens and other two *"WHITE"* tokens in the center of the board. A *score* and other game options are also displayed on the game interface.
- It indicates the player in turn and prompts the player to make a move on the board.
- When a player makes a valid move, a token used by the player is placed in the board slot selected.
- If a *valid move* is made then, the *score* of the player who made the turn and the board is *updated* with the number of points scored and the flipped pieces respectively.
- After a player has made a valid move, it indicates the turn for the other player and waits for a valid move.
- If an *invalid move* is made then, it tells the player that the move made was invalid and prompts for a different move.

### ![version](https://img.shields.io/badge/version-text--based-lightgrey.svg?style=flat-square)
To play the ![version](https://img.shields.io/badge/version-text--based-lightgrey.svg?style=flat-square) of the game, after you compile the game file, use the command ```java Othello``` in the terminal to get it running.
- The game will greet you with a welcome message and provide you with gameplay options.
- You can enter **'p'** to play the player versus player mode.
  
  To challenge the **AI**, enter **'c'**.
  
  Enter **'q'** if you want to quit from the game.
- The game will ask you to provide the x and y co-ordinates respectively for placing a token on the board.
- The tokens are *"1"* and *"2"*.
- If it's a valid move then, the board will be updated with flipped tokens otherwise, the game will let you know that, the move was invalid and prompt you to make a new move.
- The game indicates the player's turn and the score for the players.
## GUI
The *Graphical User Interface* for this game includes:-
- **Gameboard:** It is a green coloured board with black lines going horizontally and vertically across the board. The board has 64 slots which can be clicked to place the tokens on. When a slot is clicked, a *"BLACK"* or a *"WHITE"* token is placed inside the slot.
- **Scoreboard:** The points scored by Player 1 and Player 2 is displayed and gets updated after valid moves are made.
- **Turn indicator:** Shows the player currently in turn.
- **Quit option:** A label showing *"Click here to exit"* that can be clicked on to exit out of the game.

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

## Roadmap
- Seting a timer to make the game more challenging.
- Constructing a smarter AI.
- Making the game interface more user-friendly.

## Authors
**Vivian Huynh** - Team Leader

**Himika Dastidar**

**Jayoo Hwang**

**Al Mahmud Dipto**

**Miguel Merin**

## Project Status
The **Othello** project is currently running on a fully working *Graphical User Interface(GUI)*. The game also has an included AI to play against.
