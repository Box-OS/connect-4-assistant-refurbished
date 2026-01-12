package tests;

import util.logic.GravityHandler;
import org.junit.Test;
/**
 * GravityHandlerTest
 * This class is used for testing GravityHandler Class
 * @author Betty Sun
 * @version 1.31.2023
 */

public class GravityHandlerTest {

    GravityHandler gravityHandler = new GravityHandler();

    /**
     * This method updates the position of the 2D array after the user places the next disc.
     */
    @Test
    public void addPiece () {
        // board[0][column] == 0;
        int[][] board = {
                {1,0,0,0,0,0,0},
                {1,0,0,0,0,0,0},
                {1,0,0,0,0,0,0},
                {1,0,0,0,0,0,0},
                {1,0,2,0,0,0,0},
                {1,1,1,1,0,0,0}

        };
        // User 1 places a piece in column 1 (array starts counting from 0, i.e. first column col=0)
        board = gravityHandler.addPiece(board, 1, 2);

        // Print board to view current state of the board.
        for(int i = 0; i< board.length;i++){
            for (int j= 0; j< board[0].length;j++)
                System.out.print(board[i][j]);
            System.out.println();
        }

    }

    /**
     * This method Find the horizontal coordinate position to place a disc in a certain column on the board.
     * Find the horizontal coordinate position to place a disc in a certain column on the board.
     */
    @Test
    public void findLowest(){
        int[][] board = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {2,0,0,0,0,0,0},
                {1,1,0,0,0,2,2}

        };
        // Taking the first column as an example (in this case counting from 0 based on the array order)
        final int lowest = gravityHandler.findLowest(board, 0);

        System.out.print("Placement of  disc in the column with its x-coordinate as：" + lowest);
    }

    /**
     * This method checks if a  disc can still be placed in a certain column on the board,
     * for example, if a column in the 2D array of the board exceeds the length of the array.
     */
    @Test
    public void isValidMove(){
        int[][] board = {
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1},
                {1,1,0,0,0,0,1}

        };
        // Place another disk in column 1
        final boolean flag1 = gravityHandler.isValidMove(board, 0);
        if (flag1)
            System.out.println("Can continue to place a disc ");
        else
            System.out.println("Cannot continue to place a disc ");
        // Place a disc in column 7
        final boolean flag2 = gravityHandler.isValidMove(board, 6);
        if (flag2)
            System.out.println("Place another disc in column 7");
        else
            System.out.println("Cannot place another disc in column 7");
    }
}
