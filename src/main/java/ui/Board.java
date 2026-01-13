package ui;

import javafx.scene.layout.GridPane;

/** Util.Board.java
 *  Creates a grid of slots
 *
 *   @author Sina Akhavan
 */
public class Board {
    final private int ROWS = 6;
    final private int COLS = 7;
    private GridPane root;
    final private Slot[][] slot;

    public Board(){
        slot = new Slot[ROWS][COLS];
        createGrid();
    }


    /**
     *  Creates grid the size of (ROWS x COLS)
     */
    private void createGrid(){
        root = new GridPane();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                slot[row][col] = new Slot();
                root.add(slot[row][col].getBUTTON(), col, row);
            }
        }
    }

    /**
     *  highlights the selected slot
     * @param col x position (1-7)
     * @param row y position (1-6)
     */
    public void highlightSlot(int col, int row) {
        slot[ROWS-(row)][col-1].highlight();
    }

    /**
     * Reverts the highlight effect across all slots
     */
    public void unhighlightAll() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                slot[row][col].unhighlight();
            }
        }
    }

    /**
     * inserts a circle inside the selected slot
     * @param type type of circle (red or blue)
     * @param col x position (1-7)
     * @param row y position (1-6)
     */
    public void insertCircle(String type, int row, int col) {
         slot[row][col-1].createCircle(type);
    }

    //Getters and Setters
    public GridPane getRoot() {return root;}
    public int getROWS() {return ROWS;}
    public int getCOLS() {return COLS;}
}
