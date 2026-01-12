package util.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/** HelpButton.java
 *  Extends ButtonBuilder.java
 *  Creates a button that upon click, shows a dialog that explains expected inputs
 *
 *   @author Sina Akhavan
 */
public class HelpButton extends ButtonBuilder {
    public HelpButton() {
        super("Help",60,25);
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(
            "Inside input box, type the column number (1-7) to insert a checker, then press enter.\n"
                +"\n \n \n"+


            "──▒▒▒▒▒▒───▄████▄\n" +
            "─▒─▄▒─▄▒──███▄█▀\n" +
            "─▒▒▒▒▒▒▒─▐████──█─█\n" +
            "─▒▒▒▒▒▒▒──█████▄\n" +
            "─▒─▒─▒─▒───▀████▀\n" +
            "-Tool developed by [team name]"
        );

        EventHandler<ActionEvent> helpPopup = e -> alert.show();
        button.setOnAction(helpPopup);
    }
}
