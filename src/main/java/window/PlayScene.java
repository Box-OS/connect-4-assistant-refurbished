package scene;

/** PlayScene.java
 *  Extends BaseScene. Makes a scene that contains PlayNodes.java
 *
 *   @author Sina Akhavan
 */
public class PlayScene extends BaseScene {
    public PlayScene() {
        super(new PlayNodes().getROOT());
    }
}
