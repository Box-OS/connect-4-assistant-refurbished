package util;

import util.scene.StageBuilder;
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

        @Override
        public void start(Stage stage) {
        GameStart.startGame(stage);
            System.out.println("hi");
        }

        public static class GameStart {
            private static StageBuilder primaryStage;

            /**
             * Creates stage
             */
            private static void startGame(Stage stage) {

                primaryStage = new StageBuilder("Connect 4 Assistant");
                primaryStage.setWindowSettings(false, false);
            }

            /**
             * Restarts stage
             */
            public static void restartGame() {
                primaryStage.closeStage();
                GameStart.startGame(primaryStage.getSTAGE());
            }
        }
}