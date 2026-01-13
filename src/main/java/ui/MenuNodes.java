package ui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

/** MenuNodes.java
 *  Contains the GUI nodes for the starting menu
 *
 *  @author Sina Akhavan
 */
public class MenuNodes {
    private final AnchorPane ROOT;

    public MenuNodes(int width, int height) {
        BackgroundImage B = new BackgroundImage(new Image("menu-background.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        ROOT = new AnchorPane();
        TextBuilder title = new TextBuilder("Connect Four Assistant", 40);
        TextBuilder subtitle = new TextBuilder("Click anywhere to continue", 16);

        AnchorPane.setTopAnchor(title.getTEXT(), 75.0);
        AnchorPane.setLeftAnchor(title.getTEXT(), 100.0);
        AnchorPane.setTopAnchor(subtitle.getTEXT(), 250.0);
        AnchorPane.setLeftAnchor(subtitle.getTEXT(), 210.0);
        ROOT.setMinWidth(width);
        ROOT.setMinHeight(height);
        ROOT.setBackground(new Background(B));
        ROOT.getChildren().addAll(title.getTEXT(), subtitle.getTEXT());

    }

    //Getters and Setters
    public AnchorPane getROOT() {
        return ROOT;
    }
}
