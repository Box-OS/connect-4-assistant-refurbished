package logic;

/**
 * Handles any board/pieces interactions to do with gravity
 * It makes sure all pieces are at the bottommost positions and has checks for if a column is filled (from gravity)
 *
 * @author Leqi Shen
 * @version 1.30.2023
 */
public class GravityHandler {

    //creates object to copy arrays without pointer
    ArrayHandler copier = new ArrayHandler();

    /**
     * inspects a column in a board and sees if there is a valid move
     *
     * @param board the board being inspected
     * @param column the column to check if there is a valid move
     * @return true if the topmost slot in the column is unoccupied
     */
    public boolean isValidMove(int[][] board, int column) {
        return board[0][column] == 0;
    }

    /**
     * Creates a deep copy of a board passed in and adds a disc in the lowest position in a specified column.
     *
     * @param board the board to add a disc to
     * @param column The specified column to add a disc to
     * @param player the int indicator for which player to add a disc for
     * @return a new 2D array, without a pointer to the previous one so that the parameter variable is not modified.
     */
    public int[][] addPiece(int[][] board, int column, int player) {
        //creates a deep copy of the array to make sure pointers are different
        int[][] newBoard = copier.deepCopy(board);

        //uses output from findLowest to place a disc in the correct height and the specified column.
       // edited by Betty
        try {
            newBoard[findLowest(newBoard, column)][column] = player;
        } catch (Exception e) {

        }

        return newBoard;
    }

    /**
     * Finds the index of the lowest available slot in a given column for a given board
     *
     * @param board the board to inspect
     * @param column the column of the board to inspect
     * @return the int index of the lowest spot
     */
    public int findLowest(int[][] board, int column) {
        //initializes lowestSlot as invalid array index
        int lowestSlot = -1;
        //iterates from bottom to top of the array
        for (int i = board.length - 1; i>=0; i--) {
            if (board[i][column] == 0) {
                lowestSlot = i;
                break;
            }
        }
        return lowestSlot;
    }
}
