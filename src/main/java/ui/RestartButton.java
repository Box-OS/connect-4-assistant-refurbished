package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import app.Main;

/** RestartButton.java
 *  Extends ButtonBuilder.java
 *  Creates a button that restarts the game
 *
 *   @author Sina Akhavan
 */
public class RestartButton extends ButtonBuilder {
    private final Alert ALERT;
    public RestartButton() {
        super("Restart", 60, 25);
        ALERT = new Alert(Alert.AlertType.CONFIRMATION);
        EventHandler<ActionEvent> event = e -> {
            setupAlert();
            Optional<ButtonType> result = ALERT.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                Main.GameStart.restartGame();
            }
        };
        button.setOnAction(event);
    }

    /**
     * Edits alert's text, button labels, and the default pre-selected button
     */
    private void setupAlert() {
        ALERT.setContentText("Are you sure you want to restart the game? All progress will be lost.");
        ALERT.getButtonTypes().clear();
        ALERT.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        Button yesButton = (Button) ALERT.getDialogPane().lookupButton( ButtonType.YES );
        yesButton.setDefaultButton(false);
        Button noButton = (Button) ALERT.getDialogPane().lookupButton( ButtonType.NO );
        noButton.setDefaultButton(true);
    }
}
