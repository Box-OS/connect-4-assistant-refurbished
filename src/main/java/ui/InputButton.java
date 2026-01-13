package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.BoardHandler;
import logic.InputHandler;
import scene.PlayNodes;


/** InputButton.java
 *  Extends ButtonBuilder.java
 *  Creates a button that manages Input.java
 *
 *   @author Sina Akhavan & Leqi Shen
 */
public class InputButton extends ButtonBuilder {
    private boolean isYourTurn = true;
    BoardHandler b = new BoardHandler();
    InputHandler inputHandler;

    public InputButton(PlayNodes ui, Input input, Output output) {
        super("Enter", 65,20);
        inputHandler = new InputHandler(ui.getBoard().getCOLS());

        EventHandler<ActionEvent> event = e -> {

            //clears output text box
            output.clearText();
            //clears previous highlights
            ui.getBoard().unhighlightAll();

            // edited  by Betty
            if (!b.isWin(!isYourTurn)) {
                int in = -1;
                try {
                    in = Integer.parseInt(input.getInputString());
                } catch (Exception exception) {
                    output.addText ("Please input one integer between 1-7 in the box");
                    input.clearText();
                }


                //takes integer input from string input box
                //checks if string is within valid columns
                if(inputHandler.checkInRange(in)) {
                    //determines current color playing based on isYourTurn
                    String currentColor = isYourTurn ? "red" : "blue";
                    b.dump();   //debugging
                    try {
                        //inserts circle based on turn color
                        ui.getBoard().insertCircle(currentColor, b.lowestRow(in - 1), in);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        System.err.println("column is full");
                        isYourTurn = !isYourTurn;
                    }
                    b.dump();   //debugging
                    //places the piece in back-end array
                    b.placePiece(in - 1, isYourTurn ? 1 : 2);
                    b.dump();   //debugging

                    //determines next move from BoardHandler logic
                    int nextMove = b.nextMove(isYourTurn ? 1 : 2) + 1;

                    //outputs text information based on current color playing and next best move
                    output.addText(currentColor + " played in column " + in);
                    currentColor = !isYourTurn ? "red" : "blue";
                    output.addText("It's " + currentColor + "'s turn now");

                    output.addText("The current best move is column " + nextMove);

                    //highlights the best column to play in
                    for (int i = 1; i < 7; i++) {
                        ui.getBoard().highlightSlot(nextMove, i);
                    }

                    b.dump();   //debugging
                    //clears input bar
                    input.clearText();

                    //advances turn
                    isYourTurn = !isYourTurn;
                } else {
                    input.clearText();
                }
            } else {
                //does nothing and gives winning text
                output.addText(!isYourTurn ? "red" : "blue" + " has won the game");
            }

        };

        button.setOnAction(event);
    }


}
