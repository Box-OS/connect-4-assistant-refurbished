package ui;

import javafx.scene.control.Button;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import logic.BoardHandler;

/** DropButton.java
 *  A triangle button used to drop a piece into a column
 */
public class DropButton {
    private final Button button;
    private final int column;
    private final Polygon triangle;

    public DropButton(int column, PlayNodes ui, BoardHandler boardHandler, Output output) {
        this.column = column;
        this.button = new Button();
        
        // Create a triangle pointing down
        this.triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
            0.0, 0.0,
            20.0, 0.0,
            10.0, 15.0
        });
        updateColor(ui.isYourTurn() ? Color.RED : Color.BLUE);
        
        button.setGraphic(triangle);
        button.setMinSize(50, 30);
        button.setMaxSize(50, 30);
        button.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        
        button.setOnAction(e -> {
            dropPiece(ui, boardHandler, output);
        });
    }

    public void updateColor(Color color) {
        triangle.setFill(color);
    }

    private void dropPiece(PlayNodes ui, BoardHandler b, Output output) {
        if (ui.isGameOver()) {
            output.addText(ui.getWinner() + " has won the game");
            return;
        }

        output.clearText();
        ui.getBoard().unhighlightAll();

        int col1Based = column + 1;
        boolean isYourTurn = ui.isYourTurn();
        String currentColor = isYourTurn ? "red" : "blue";

        try {
            int row = b.lowestRow(column);
            if (row == -1) {
                output.addText("Column " + col1Based + " is full!");
                return;
            }
            ui.getBoard().insertCircle(currentColor, row, col1Based);
            b.placePiece(column, isYourTurn ? 1 : 2);
            
            output.addText(currentColor + " played in column " + col1Based);
            
            if (b.isWin(isYourTurn)) {
                ui.setGameOver(true, currentColor);
                output.addText(currentColor + " has won the game!");
                return;
            }

            // Switch turn
            ui.toggleTurn();
            isYourTurn = ui.isYourTurn();
            String nextColor = isYourTurn ? "red" : "blue";
            output.addText("It's " + nextColor + "'s turn now");

            // AI Suggestion
            int nextMoveIdx = b.nextMove(isYourTurn ? 1 : 2);
            if (nextMoveIdx != -1) {
                int nextMove = nextMoveIdx + 1;
                output.addText("The current best move is column " + nextMove);
                for (int i = 1; i <= 6; i++) {
                    ui.getBoard().highlightSlot(nextMove, i);
                }
            } else {
                output.addText("No more moves possible.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            output.addText("Column " + col1Based + " is full!");
        }
    }

    public Button getButton() {
        return button;
    }
}
