package util.logic;

/**
 * The BoardHandler class runs the logic for finding the best move given a 2D array of a board.
 * The class keeps track of 2 2D arrays for 2 boards, one for each perspective of each player, as the logic sees 1 as the friendly pieces and 2 as the enemy pieces.
 *
 * @author Leqi Shen
 * @version 1.30.2023
 */
public class BoardHandler extends GravityHandler {

    //int indicating the user's pieces
    private final int USER = 1;
    //int indicating the opponent's pieces
    private final int OPPONENT = 2;

    //board for one piece color, red
    private int[][] player1Board = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0}
    };

    //board for one piece color, blue
    private int[][] player2Board = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0}
    };

    /**
     * Looks for the lowest row available in a specified column for placing a piece into.
     *
     * @param column the user-defined column to look into
     * @return an int indicating the lowest unoccupied row
     */
    public int lowestRow(int column) {
        return findLowest(player1Board, column);
    }

    /**
     * Adds a piece into the lowest position on both boards with opposite integer tags so algorithm can aid both sides
     *
     * @param column the user-defined column to place a piece into
     * @param player the player to place a piece for
     */
    public void placePiece(int column, int player) {
        player1Board = addPiece(player1Board, column, player);
        player2Board = addPiece(player2Board, column, player==1?2:1);       //pl
    }

    /**
     * analyzes a board through method findBestMove and returns its output, which is the best move for that current board position
     *
     * @param boardSelection int selection for either player 1 or 2's board
     * @return The best move given the board
     */
    public int nextMove(int boardSelection) {
        int[][] tempBoard;
        if (boardSelection == OPPONENT) {
            tempBoard = player2Board;
        } else {
            tempBoard = player1Board;
        }
        return findBestMove(tempBoard);
    }

    /**
     * checks the given board whether there is a winning set of 4 pieces or not using the checkForWin method
     *
     * @param currentTurn Boolean true/false for indicating player 1/2's turn
     * @return true if there is a consecutive set of 4 1's indicating a win for that player
     */
    public boolean isWin(boolean currentTurn) {
        if(currentTurn) {
            return checkForWin(player1Board);
        }
        return checkForWin(player2Board);
    }

    /**
     * debugging method for dumping both boards and seeing if back-end is entering pieces into the right positions
     */
    public void dump() {
        //prints the first board through looping
        for (int j = 0; j< player1Board.length; j++) {
            for (int i = 0; i<7; i++) {
                System.out.print(player1Board[j][i]);
            }
            System.out.println();
        }
        //double line indent for formatting
        System.out.println();
        System.out.println();
        //prints the second board
        for (int j = 0; j< player2Board.length; j++) {
            for (int i = 0; i<7; i++) {
                System.out.print(player2Board[j][i]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Iterates through placing a piece in each column and calling upon evalBoard() to rate the position
     *
     * @param board the game board being passed in
     * @return the column for the best move
     */
    public int findBestMove(int[][] board) {

        // Initialize best move as an invalid move
        int bestMove = -1;
        // Initialize best score as the lowest possible value
        int bestScore = Integer.MIN_VALUE;

        // Loop through all possible moves
        for (int i = 0; i < 7; i++) {
            // Check if the move is valid
            if (isValidMove(board, i)) {
                int[][] newBoard;
                // Make the move and get the new board state
                newBoard = addPiece(board, i, USER);
                //if move is winning, break and output move
                if (checkForWin(newBoard)) {
                    bestMove = i;
                    break;
                }
                //checks score of board
                int score = evalBoard(newBoard);
                // If score of position is better than previous, update
                if ( score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }


        }
        return bestMove;
    }

    /**
     * checks if there are 4 same-player pieces in a row and gives a true-or-false return
     *
     * @param board the game board in a 2D array
     * @return true if 4 pieces are in a row in any orientation
     */
    public boolean checkForWin(int[][] board) {
        // check for horizontal win
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                boolean flag = board[i][j] == USER && board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j + 2] == board[i][j + 3];
                if (flag) {
                    return true;
                }
            }
        }

        // check for vertical win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                boolean flag = board[i][j] == USER && board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j] && board[i + 2][j] == board[i + 3][j];
                if (flag) {
                    return true;
                }
            }
        }

        // check for diagonal win (top left to bottom right)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                boolean flag = board[i][j] == USER && board[i][j] == board[i + 1][j + 1] && board[i + 1][j + 1] == board[i + 2][j + 2] && board[i + 2][j + 2] == board[i + 3][j + 3];
                if (flag) {
                    return true;
                }
            }
        }

        // check for diagonal win (top right to bottom left)
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                boolean flag = board[i][j] == USER && board[i][j] == board[i + 1][j - 1] && board[i + 1][j - 1] == board[i + 2][j - 2] && board[i + 2][j - 2] == board[i + 3][j - 3];
                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * evaluates and gives a total score for the entire board situation
     *
     * @param board the game board in a 2D array
     * @return a total board score based on rateLine's output of each set of 4 adjacent spots
     */
    public int evalBoard(int[][] board) {
        int boardScore = 0;
        // check for horizontal lines
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                int tempScore = rateLine(board[i][j], board[i][j + 1], board[i][j + 2], board[i][j + 3]);
                if (tempScore > boardScore) {
                    boardScore = tempScore;
                }
            }
        }
        // check for vertical lines
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                int tempScore = rateLine(board[i][j], board[i + 1][j], board[i + 2][j], board[i + 3][j]);
                if (tempScore > boardScore) {
                    boardScore = tempScore;
                }
            }
        }
        // check for diagonal lines (left to right)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                int tempScore = rateLine(board[i][j], board[i + 1][j + 1], board[i + 2][j + 2], board[i + 3][j + 3]);
                if (tempScore > boardScore) {
                    boardScore = tempScore;
                }
            }
        }
        // check for diagonal lines (right to left)
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                int tempScore = rateLine(board[i][j], board[i + 1][j - 1], board[i + 2][j - 2], board[i + 3][j - 3]);
                if (tempScore > boardScore) {
                    boardScore = tempScore;
                }
            }
        }
        return boardScore;
    }

    /**
     * Rates a group of 4 pieces and assigns a score based on amount of pieces in a row
     *
     * @param a first piece in row
     * @param b second piece in row
     * @param c third piece in row
     * @param d fourth piece in row
     * @return a score based on amount of user and opponent pieces in a row
     */
    public int rateLine(int a, int b, int c, int d) {

        //puts input values into an array
        int[] line = new int[] {a, b, c, d};

        //initializes rating value with 0
        int lineRating = 0;

        //creates variables for pieces in a row for each party
        int userCount;
        int opponentCount;

        //counts the pieces in the given set
        userCount = pieceCounter(line, USER);
        opponentCount = pieceCounter(line, OPPONENT);

        // check if user has winning line
        if (userCount == 4 && opponentCount == 0) {
            lineRating += 1000;
        } else if (userCount == 3 && opponentCount == 0) {
            lineRating += 100;
        } else if (userCount == 2 && opponentCount == 0) {
            lineRating += 10;
        } else if (userCount == 1 && opponentCount == 0) {
            lineRating +=5;
        }

        // check if user has a row of pieces but also has an opponent piece next to it
        else if (userCount == 3 && opponentCount == 1) {
            lineRating += 50;
        } else if (userCount == 2 && opponentCount == 1) {
            lineRating += 25;
        }

        // check if opponent has winning line
        if (opponentCount == 4 && userCount == 0) {
            lineRating -= 1000;
        } else if (opponentCount == 3 && userCount == 0) {
            lineRating -= 100;
        } else if (opponentCount == 2 && userCount == 0) {
            lineRating -= 10;
        } else if (opponentCount == 1 && userCount == 0) {
            lineRating -=5;
        }

        // check if opponent has a row of pieces but also has a user piece next to it
        else if (opponentCount == 3 && userCount == 1) {
            lineRating -= 50;
        } else if (opponentCount == 2 && userCount == 1) {
            lineRating -= 25;
        }
        // if a line consists of the same amount of user/opponent spots, the ranking is neutral, 0.

        return lineRating;
    }

    /**
     * pieceCounter counts a player's pieces in a row for any given 4 adjacent pieces.
     *
     * @param line an array of 4 adjacent pieces in any orientation specified by rateLine
     * @param player indicates if method should check for player 1 (user) or player 2 (opponent)'s pieces
     * @return the amount of pieces that are in a row
     */
    public int pieceCounter(int[] line, int player) {
        // Initialize the number of pieces in a row as 0
        int inRow = 0;
        for (int j = 0; j < line.length; j++) {
            int i = 1;
            // Check if the current piece belongs to the specified player
            boolean flag = line[j] == player;
            // Iterate through the remaining pieces in the line
            while (flag && i + j <= line.length) {
                try {
                    // Check if the next piece belongs to the specified player
                    flag = line[j] == line[j + i];
                } catch (ArrayIndexOutOfBoundsException ignored) {}
                i++;
            }
            // Update the number of pieces in a row
            inRow = i - 1;
            if (inRow > 1) {
                break; // If there are more than one consecutive pieces belonging to the specified player, break the loop
            }
        }
        return inRow;
    }
}
