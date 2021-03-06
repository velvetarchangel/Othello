package logic;

import java.util.*;

/**
 * Player class for Othello game. This class contains the attributes of a
 * player.
 *
 * @author Vivian Huynh, Himika Dastidar
 * @version 1.0 (February 27, 2019)
 */
public class Player {

    // Instance variables
    String number = "1";
    String colour = "Black"; // black or white
    int score = 0;
    String name = "Player";

    // Constructors

    /**
     * This default constructor sets the player's colour and score.
     */
    public Player() {
        number = "1";
        colour = "Black";
        score = 0;
    }

    /**
     * @param colour The player's colour
     * @param number The player's number 1 or 2
     */
    public Player(String number, String colour) {
        setNumber(number);
        setColour(colour);
    }

    /**
     * @param number The player's number 1 or 2
     * @param score  THe player's score
     * @param name   The player's name
     */
    public Player(String number, String colour, String name) {
        this(number, colour);
        this.name = name;
    }

    /**
     * @param number The player's number
     * @param colour The player's colour
     * @param score  THe player's score
     */
    public Player(String number, String colour, int score) {
        this(number, colour);
        this.score = score;
    }

    /**
     * @param number The player's number
     * @param colour The player's colour
     * @param name   The player's name
     * @param score  THe player's score
     */
    public Player(String number, String colour, String name, int score) {
        this(number, colour, score);
        this.name = name;
    }

    /**
     * The copy constructor
     * 
     * @param player to type player and copies the information from the other player
     *               into the new player object
     */
    public Player(Player player) {
        setNumber(player.getNumber());
        setColour(player.getColour());
        this.name = player.getName();
        setScore(player.getScore());

    }

    /**
     * Method to determine if the user input is of type integer, throws an exception
     * if it is not an integer and returns false
     * 
     * @param input takes in user input from the getInput method
     * @return whether it is integer or not
     */
    public boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to determine if the user input within the board
     * 
     * @param input: takes in user input from the getInput method
     * @return whether the input is between 1 and 8
     */
    public boolean withinRange(String input) {
        int number = Integer.parseInt(input);
        return (number >= 1 && number <= 8);
    }

    /**
     * Method to determine if the user input is valid, ie, if it will flip any
     * pieces belonging to the opponent
     * 
     * @param move:  takes in an array with x, y coordinates
     * @param board: takes in the current state of the board together the method
     *               checks whether the move provided will be able to flip any
     *               pieces of the opponent, if atleast 1 piece can be flipped
     * @return true, else @return false
     */
    public boolean flipsPieces(int[] move, Board board) {
        int x = move[0];
        int y = move[1];
        int[] flipped = Check.move(x, y, this, board);
        return (flipped[0] != 0);
    }

    /**
     * Gets the x and y coordinate from the user. If the input is a q, quit the
     * game. If the input is invalid (not an integer, out of range, doens't flip any
     * pieces), reprompt.
     * 
     * @param currentPlayer The player who's turn it is
     * @return x_y A 2D integer array of the x and y coordinates
     */
    public int[] getInput(Board board) {
        Scanner coord = new Scanner(System.in);
        int[] x_y = new int[2];
        boolean xvalid = false;
        boolean yvalid = false;

        while (xvalid == false) { // while invalid
            System.out.print("Enter a number between 1 to 8 for the x coordinate: ");
            String xcoord = coord.nextLine();
            if (xcoord.equals("q")) {
                x_y = null;
                xvalid = true;
                yvalid = true;
            } else if (!isInt(xcoord)) {
                System.out.println("Please enter an integer.");
            } else if (!withinRange(xcoord)) {
                System.out.println("Please enter a number within range of the board.");
            } else {
                x_y[0] = Integer.parseInt(xcoord);
                xvalid = true;
            }
        }

        while (yvalid == false) { // while invalid
            System.out.print("Enter a number between 1 to 8 for the y coordinate: ");
            String ycoord = coord.nextLine();
            if (ycoord.equals("q")) {
                x_y = null;
                yvalid = true;
            } else if (!isInt(ycoord)) {
                System.out.println("Please enter an integer.");
            } else if (!withinRange(ycoord)) {
                System.out.println("Please enter a number within range of the board.");
            } else {
                x_y[1] = Integer.parseInt(ycoord);
                yvalid = true;
            }
        }
        return x_y;
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

    /** @return player's assigned number */
    public String getNumber() {
        return this.number;
    }

    /** @return name */
    public String getName() {
        return this.name;
    }

    // Setters

    /**
     * Sets the number of the player only if it is either 1 or 2
     * 
     * @param number
     */
    public void setNumber(String number) {
        if (number.equals("1") || number.equals("2"))
            this.number = number;
    }

    /**
     * Sets the player's colour
     * 
     * @param colour
     */
    public void setColour(String colour) {
        if (colour.equals("BLACK") || colour.equals("WHITE"))
            this.colour = colour;
    }

    /**
     * Sets the player's name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the player's score
     *
     * @param score
     */
    public void setScore(int score) {
        if (score >= 0)
            this.score = score;
    }

}
