package ui;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/** TextBuilder.java
 *  Makes text
 *
 *   @author Sina Akhavan
 */
public class TextBuilder {
    private final Text TEXT;

    public TextBuilder(String t, int size, String font) {
        TEXT = new Text();
        TEXT.setText(t);
        TEXT.setFont(Font.font(font));
        TEXT.setFont(Font.font(size));

        TEXT.setMouseTransparent(true);
    }

    public TextBuilder(String t, int size) {
        this(t, size, "SF Pro");
    }

    //Getters and Setters
    public Text getTEXT() {return TEXT;}
}
