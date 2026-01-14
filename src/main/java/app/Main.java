package app;

import scene.StageBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

/** Main.java
 *  Starts the program and makes a stage
 *
 *  @author Sina Akhavan
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // Formerly inside GameStart: keep a single StageBuilder reference at the class level.
    private static StageBuilder stageBuilder;

    @Override
    public void start(Stage stage) {
        // Create the UI (we don't reuse the provided Stage because StageBuilder creates its own Stage)
        startGame();
    }

    /**
     * Creates or (re)initializes the StageBuilder and window.
     */
    private static void startGame() {
        // Close any existing stage builder to avoid leaking windows/resources
        if (stageBuilder != null) {
            try {
                stageBuilder.closeStage();
            } catch (Exception ignored) {
                // be defensive: if closeStage throws, continue to recreate
            }
            stageBuilder = null;
        }

        // Construct a new StageBuilder. StageBuilder currently creates its own Stage.
        stageBuilder = new StageBuilder("Connect 4 Assistant");
        stageBuilder.setWindowSettings(false, false);
    }

    /**
     * Restarts the game window. Safe to call even if stageBuilder is null.
     */
    public static void restartGame() {
        if (stageBuilder != null) {
            try {
                stageBuilder.closeStage();
            } catch (Exception ignored) {
                // ignore close errors
            }
            stageBuilder = null;
        }

        // Create a fresh StageBuilder/window
        startGame();
    }
}