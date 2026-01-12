package util.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;

/** BaseSceneBaseScene.java
 *  Creates a scene
 *
 *   @author Sina Akhavan
 */
public abstract class BaseScene {
    protected Scene scene;
    public BaseScene(Parent root) {
        scene = new Scene(root);
    }

    //Getters and Setters
    public Scene getScene() {return scene;}
}
