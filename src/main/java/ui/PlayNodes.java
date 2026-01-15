package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.BoardHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * PlayNodes.java
 * Contains the GUI nodes for the main interface
 *
 * @author Sina Akhavan
 */
public class PlayNodes {
    private final VBox ROOT;
    private final Board board;
    private final List<DropButton> dropButtonList = new ArrayList<>();
    private boolean isYourTurn = true;
    private boolean gameOver = false;
    private String winner = "";

    public PlayNodes() {
        ROOT = new VBox();
        ROOT.setAlignment(Pos.CENTER);
        ROOT.setPadding(new Insets(20));
        ROOT.setSpacing(20);
        ROOT.setStyle("-fx-background-color: #C3DBC5;");

        board = new Board();
        BoardHandler boardHandler = new BoardHandler();
        
        VBox boardContainer = new VBox();
        boardContainer.setAlignment(Pos.CENTER);
        boardContainer.setSpacing(10);

        HBox dropButtons = new HBox();
        dropButtons.setAlignment(Pos.CENTER);
        
        Output output = new Output();
        for (int i = 0; i < board.getCOLS(); i++) {
            DropButton db = new DropButton(i, this, boardHandler, output);
            dropButtonList.add(db);
            dropButtons.getChildren().add(db.getButton());
        }

        TextBuilder txtOutput = new TextBuilder("CONSOLE", 16);
        HelpButton btnHelp = new HelpButton();
        RestartButton btnRestart = new RestartButton();

        HBox controlButtons = new HBox();
        controlButtons.setAlignment(Pos.CENTER);
        controlButtons.setSpacing(20);
        controlButtons.getChildren().addAll(btnHelp.getButton(), btnRestart.getButton());

        //Board Layout
        boardContainer.getChildren().addAll(dropButtons, board.getRoot(), txtOutput.getTEXT(), output.getTextArea(), controlButtons);
        
        //Root Children
        ROOT.getChildren().add(boardContainer);
    }

    //Getters and Setters
    public VBox getROOT() {
        return ROOT;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public void toggleTurn() {
        isYourTurn = !isYourTurn;
        updateDropButtonColors();
    }

    private void updateDropButtonColors() {
        Color nextColor = isYourTurn ? Color.RED : Color.BLUE;
        for (DropButton db : dropButtonList) {
            db.updateColor(nextColor);
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver, String winner) {
        this.gameOver = gameOver;
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}