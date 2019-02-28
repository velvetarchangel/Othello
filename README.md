# Othello
Welcome to Othello! *Othello*, also known as *Reversii*, is a fun and strategic turn-based board game played by two players. 
Each player in the game is in a contest to claim the most territory on the board they possibly can using their pieces. Challenge
players in a duel of critical thinking and see if you can prevail!

## Getting Started
This is a ![version](https://img.shields.io/badge/version-text--based-green.svg?style=flat-square) of the game. To start the game, the
following steps are advised,
1. Make sure you have a working version of Java running on your computer.
2. With that confirmed, open up the terminal from the folder where you saved the game file named **Othello.java**. For instructions, please
refer to [Opening a Terminal](#opening-a-terminal) in the [Support](#support) section.
3. Compile the code in the terminal by using this command ```javac Othello.java```
4. Run the program using this command ```java Othello```
5. To exit the game, close the terminal window.

## Description
The game is played on an 8 x 8 board and is divided in to 64 squares. Two kinds of *token* are used to play with and they are coloured
*Black* and *White*. Black has the first move in the game and places a token on an empty spot. Tokens can only be placed next to the opposing
person’s token. For example: white tokens can only be placed next to a black token and vice versa. If there are white tokens that are
surrounded by black tokens on both sides horizontally, vertically or diagonally, the white tokens then flip to black and vice versa. The game
ends when the board is filled with tokens or there are no more valid moves left. Then score is calculated by counting the number of tokens for
each colour.

For an elaborate set of game rules, please visit [Othello Wikipedia page](https://en.wikipedia.org/wiki/Reversi).

## Usage
The game runs entirely in the *terminal* window as it is currently ![version](https://img.shields.io/badge/version-text--based-green.svg?style=flat-square).
- The board is displayed in terminal showing the x and y co-ordinate numbers and with 2 tokens numbered as *1*
and other 2 tokens numbered as *2* in the centre of the board.
- It indicates the player in turn and asks for the *x co-ordinate* first and then the *y co-ordinate* for the spot where the token is to be put. You may input the numbers of the co-ordinates.
- If a *valid move* is made then, the *score* of the player who made the turn is shown and the board is *refreshed* with the flipped pieces.
This new board is he current game board. It indicates the turn for the other player and asks input for x and y co-ordinate again.
- If an *invalid move* is made then, it tells the player that the move made was invalid and asks for co-ordinate inputs again. 

## Support
##### Opening a Terminal
- **For Linux:** *"Right-click"* inside the window and then select *"Open in Terminal"*.
- **For Windows:** Go to the folder > press *"alt-D"* and type in *"cmd"*.

## Roadmap
Future goals,
- Graphical interface for the game.
- Develop an AI to play against.

## Authors
**Vivian Huynh** - Team Leader

**Himika Dastidar**

**Jayoo Hwang**

**Al Mahmud Dipto**

**Miguel Merin**

## Project Status
The **Othello** project is currently under development for further and advanced improvemnets.
