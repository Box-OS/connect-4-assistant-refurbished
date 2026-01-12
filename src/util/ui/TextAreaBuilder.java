package util.ui;

import javafx.scene.control.TextArea;

/** TextAreaBuilder.java
 *  Abstract class for making the blueprint of a textarea
 *
 *   @author Sina Akhavan
 */
public abstract class TextAreaBuilder {
    protected TextArea textArea;
    public TextAreaBuilder(int width, int length) {
        textArea = new TextArea();
        textArea.setPrefRowCount(width);
        textArea.setPrefColumnCount(length);
    }

    /**
     * clears the contents inside textArea
     */
    public void clearText() {
        textArea.clear();
    }

    //Getters and Setters
    public TextArea getTextArea() {
        return textArea;
    }
}
