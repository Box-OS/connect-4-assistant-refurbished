import logic.BoardHandler;

import org.junit.Test;
/**
 * BoardHandlerTest
 * This class is used for testing BoardHandler Class
 * @author Betty Sun
 * @version 1.31.2023
 */

public class BoardHandlerTest {
    BoardHandler boardHandler = new BoardHandler();

    /**
     * This method finds the x-coordinate position to place a disc under the current disc state in a certain column.
     */
    @Test
    public void findLowestRow(){
        final int i = boardHandler.lowestRow(0);
        System.out.println("The x-coordinate to place the next disc on the current board is："+ i);
    }

    /**
     * This method prints the board state of user 1 and user 2's 2D arrays.
     */
    @Test
    public void dump(){
        boardHandler.dump();
    }
    /**
     * This method updates the board state of user 1 and user 2 after each user makes a move (where in each board,
     * 1 always represents their own disc, and 2 represents the opponent's disc).
     */
    @Test
    public void placePiece() {
        System.out.println("Util.Board starting state-------");
        boardHandler.dump();

        System.out.println("State after user 1 places a disc in the first column");
        boardHandler.placePiece(2,1);

        boardHandler.dump();
    }

    /**
     *  This method determines if the current board is a win state by looking at the position of "1" (where 1 always represents
     *  their own disc) in the board 2D array, and seeing if it forms a line of 4 horizontally, vertically, or diagonally.
     */
    @Test
    public void checkForWin(){
        int[][] board = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,2},
                {0,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {2,2,0,0,0,0,1}

        };
        final boolean flag = boardHandler.checkForWin(board);
        if (flag)
            System.out.println("Win!!!");
        else
            System.out.println("Go on !!!");
    }

    /**
     * This method calculates the player's disc for any given 4 adjacent disc pieces.
     */
    @Test
    public void pieceCounter(){
        int[] line = {1,2,1,2};
        final int i = boardHandler.pieceCounter(line, 2);
        System.out.println(i);
    }
    /**
     * This method core a set of 4 disc pieces and assign a score based on the number of consecutive pieces,
     * where 1 represents the player itself and 2 represents the opponent.
     */
    @Test
    public void rateLine(){
        final int i = boardHandler.rateLine(1, 2, 1, 2);
        System.out.println(i);
    }
    /**
     * This method calculate the current score of the board
     */
    @Test
    public void evalBoard(){
        int[][] board = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {2,0,0,0,0,0,1},
                {2,0,0,0,0,0,1},
                {2,0,0,0,0,0,1}

        };

        int[][] board1 = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {2,0,0,0,0,0,0},
                {2,0,0,0,0,0,1},
                {2,0,0,0,0,1,1}

        };
        int[][] board2 = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {2,0,1,0,0,0,0},
                {2,2,1,0,1,0,1},
                {2,2,2,0,1,1,1}

        };
        final int evalBoard = boardHandler.evalBoard(board);
        final int evalBoard1 = boardHandler.evalBoard(board1);
        final int evalBoard2 = boardHandler.evalBoard(board2);
        System.out.println("Points earned："+evalBoard2);
    }
    /**
     *  This method finds the most winnable column on the board.
     */
    @Test
    public void findBestMove(){
        int[][] board = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {2,0,0,0,0,0,1},
                {2,0,0,0,0,0,1},
                {2,0,0,0,0,0,1}

        };
        final int bestMove = boardHandler.findBestMove(board);
        System.out.println("The most winnable column is:" + bestMove);
    }
    /**
     * This method infers the location where the next disc will fall based on the disc pieces on the board.
     */
    @Test
    public void nextMove() {
        System.out.println("Starting state of the board");
        boardHandler.dump();

        System.out.println("The next landing position is："+ boardHandler.nextMove(1));
    }

    @Test
    public void nextMoveNoMoves() {
        // Fill the board
        for (int col = 0; col < 7; col++) {
            for (int i = 0; i < 6; i++) {
                boardHandler.placePiece(col, (i % 2) + 1);
            }
        }
        int move = boardHandler.nextMove(1);
        System.out.println("Move on full board: " + move);
        org.junit.Assert.assertEquals(-1, move);
    }

    @Test
    public void nextMoveAfterWin() {
        // Player 1 wins
        boardHandler.placePiece(0, 1);
        boardHandler.placePiece(1, 1);
        boardHandler.placePiece(2, 1);
        boardHandler.placePiece(3, 1);
        
        // Even if board is not full, findBestMove might still return something 
        // because it doesn't check if the game is ALREADY won at the start of the method.
        // It only checks if the NEW move leads to a win.
        int move = boardHandler.nextMove(1);
        System.out.println("Move after win: " + move);
    }

    @Test
    public void testBlockingMove() {
        // Setup: Player 2 (opponent for nextMove(1)) has 3 in a row
        // Player 1 = Red, Player 2 = Blue
        // Red's turn (nextMove(1))
        
        // Opponent (Blue) pieces
        boardHandler.placePiece(0, 2);
        boardHandler.placePiece(1, 2);
        boardHandler.placePiece(2, 2);
        
        System.out.println("Board state before suggestion:");
        boardHandler.dump();
        
        int suggestion = boardHandler.nextMove(1);
        System.out.println("Suggested move for Red: " + suggestion);
        
        // Red should block at column 3
        org.junit.Assert.assertEquals("Red should block Blue's win at column 3", 3, suggestion);
    }

    @Test
    public void testWinningMovePriority() {
        // Red has 3 in a row (0, 1, 2)
        // Blue has 3 in a row (0, 1, 2) on the next row up
        // Red should win at column 3 rather than blocking Blue at column 3 (if it was a threat)
        // Actually blocking a threat is good, but winning is better.
        
        // Let's make it more clear:
        // Red can win at col 3.
        // Blue can win at col 4.
        // Red should pick 3.
        
        boardHandler.placePiece(0, 1);
        boardHandler.placePiece(1, 1);
        boardHandler.placePiece(2, 1);
        
        boardHandler.placePiece(0, 2);
        boardHandler.placePiece(1, 2);
        boardHandler.placePiece(2, 2);
        
        int suggestion = boardHandler.nextMove(1);
        org.junit.Assert.assertEquals("Red should prefer winning at column 3", 3, suggestion);
    }


}




