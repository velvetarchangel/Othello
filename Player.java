/**
 * Player class for Othello game. This class contains the attributes of a
 * player.
 * 
 * @author T08 Group 5 (CPSC 233)
 * @version 1.0 (February 27, 2019)
 */
public class Player {

  // Instance variables
  String colour; // black or white
  int score;

  // Constructors

  /**
   * This default constructor sets the player's colour and score.
   */
  public Player() {
    colour = "black";
    score = 0;
  }

  /**
   * @param colour The player's colour
   * @param score  THe player's score
   */
  public Player(String colour, int score) {
    this.colour = colour;
    this.score = score;
  }

  // Getters

  /** @return colour */
  public String getColour() {
    return colour;
  }

  /** @return score */
  public int getScore() {
    return score;
  }


  // Setters

  /**
   * Sets the player's colour
   * 
   * @param The colour
   */
  public void setColour(String colour) {
    this.colour = colour;
  }

  /**
   * Sets the player's score
   * 
   * @param The score
   */
  public void setScore(int score) {
    this.score = score;
  }

}