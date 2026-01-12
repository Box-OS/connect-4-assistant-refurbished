package util.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/** ButtonBuilder.java
    *  Abstract class for making the blueprint of a button
    *
    *   @author Sina Akhavan
    */
public abstract class ButtonBuilder {
    protected Button button;

    public ButtonBuilder(String label, int width, int height) {
        button = new Button();
        button.setText(label);
        button.setPadding(Insets.EMPTY);
        button.setMinSize(width, height);
        stylize();
    }

    /**
     * Changes the style of button through css
     */
    public void stylize() {
        button.setStyle(
        "-fx-background-color: #2E4052;" +
        "color: white;"
        );
        button.setTextFill(Color.WHITESMOKE);
    }

    //Getters and Setters
    public Button getButton() {
        return button;
    }
}



