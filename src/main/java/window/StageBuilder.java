package scene;

import javafx.scene.Scene;
import javafx.stage.Stage;

/** StageBuilder.java
 *  creates the main stage and its scenes
 *
 *   @author Sina Akhavan
 */
public class StageBuilder {
    private final Stage STAGE;
    private final MenuScene MENU_SCENE;
    private final PlayScene PLAY_SCENE;

    public StageBuilder(String title) {
        STAGE = new Stage();
        MENU_SCENE = new MenuScene(this);
        PLAY_SCENE = new PlayScene();

        STAGE.setScene(MENU_SCENE.getScene());
        STAGE.setTitle(title);
        STAGE.show();
    }

    /**
     * switches the current scene of stage
     * @param scene scene to be shown
     */
    public void switchScene(Scene scene) {
        STAGE.setScene(scene);
    }

    /**
     * closes the stage
     */
    public void closeStage() {
        STAGE.close();
    }

    /**
     * changes window related settings of Stage
     * @param canMaximize whether the user is allowed to maximize the stage window
     * @param resizable whether the user is allowed to resize the stage window
     */
    public void setWindowSettings(boolean canMaximize, boolean resizable) {
        STAGE.setMinHeight(PLAY_SCENE.getScene().getHeight());
        STAGE.setMinWidth(PLAY_SCENE.getScene().getWidth());;
        STAGE.setMaximized(canMaximize);
        STAGE.setResizable(resizable);
    }

    //Getters and Setters
    public MenuScene getStartMenu() {return MENU_SCENE;}
    public PlayScene getPLAY_SCENE() {return PLAY_SCENE;}
    public Stage getSTAGE() {return STAGE;}
}
