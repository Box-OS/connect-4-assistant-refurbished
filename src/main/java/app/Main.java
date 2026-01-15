package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MenuNodes;
import ui.PlayNodes;

/** Main.java
 *  Starts the program and makes a stage
 *
 *  @author Sina Akhavan
 */
public class Main extends Application {
    private static Stage primaryStage;
    private static Scene menuScene;
    private static Scene playScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        setupScenes();
        showMenu();
        
        primaryStage.setTitle("Connect 4 Assistant");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static void setupScenes() {
        MenuNodes menuNodes = new MenuNodes(600, 300);
        menuScene = new Scene(menuNodes.getROOT());
        
        PlayNodes playNodes = new PlayNodes();
        playScene = new Scene(playNodes.getROOT());

        menuScene.setOnMousePressed(e -> showPlay());
    }

    public static void showMenu() {
        primaryStage.setScene(menuScene);
    }

    public static void showPlay() {
        primaryStage.setScene(playScene);
    }

    /**
     * Restarts the game window.
     */
    public static void restartGame() {
        setupScenes();
        showMenu();
    }
}