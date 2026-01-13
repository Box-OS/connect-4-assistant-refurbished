package scene;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ui.MenuNodes;

/** MenuScene.java
 *  Extends BaseScene. The initial scene when running stage.
 *
 *   @author Sina Akhavan
 */
public class MenuScene extends BaseScene {
    public MenuScene(StageBuilder stage) {
        super(new MenuNodes(600, 300).getROOT());

        EventHandler<MouseEvent> event = e -> stage.switchScene(stage.getPLAY_SCENE().getScene());
        scene.setOnMousePressed(event);
    }
}
