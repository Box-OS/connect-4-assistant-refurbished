package util.logic;

/**
 * Checks user input for placing pieces in the correct range of columns, specified by a constructor
 *
 * @author Leqi Shen
 * @version 1.30.2023
 */
public class InputHandler {

    //variable for holding total column size
    private int columns;

    /**
     * constructor
     *
     * @param columns indicates the max amount of columns in a given game
     */
    public InputHandler(int columns) {
        this.columns = columns;
    }

    /**
     * Checks if user's indicated column is within the range of columns available for a given game
     * The range is from 1 to the amount of columns, inclusive, as that
     *
     * @param userInput the column the user wishes to place a piece in
     * @return true if the desired input is within the range
     */
    public boolean checkInRange(int userInput) {
        return userInput >= 1 && userInput <=columns;
    }
}
