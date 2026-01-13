package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

/** Slot.java
 *  creates a slot that can have a checker inside
 *
 *   @author Sina Akhavan
 */
public class Slot {
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private final Button BUTTON;
    private Circle circle;

    public Slot() {
        BUTTON = new Button();
        BUTTON.setMinSize(WIDTH, HEIGHT);
        BUTTON.setMaxSize(WIDTH, HEIGHT);
        BUTTON.setPadding(new Insets(0));
        BUTTON.setDisable(true);
        BUTTON.setOpacity(1);
    }

    /**
     * changes the slot graphic to have a checker inside
     * @param type the type of circle (red or blue)
     */
    public void createCircle(String type){
        circle = new Circle(type);
        BUTTON.setGraphic(circle.getImg());
    }

    /**
     * highlights slot by changing its style through css
     */
    public void highlight() {
        BUTTON.setStyle(
               "-fx-outline:none;" +
               "-fx-border-color:#9ecaed;" +
               "-fx-box-shadow: inset 0 0 500px 500px #9ecaed;" +
               "-fx-background-color: lightblue;"
        );
    }

    /**
     * reverts css effects of slot
     */
    public void unhighlight() {
        BUTTON.setStyle(
                "all: revert"
        );
    }


    //Getters and Setters
    public Button getBUTTON() {return BUTTON;}
    public int getWIDTH() {
        return WIDTH;
    }
    public int getHEIGHT() {
        return HEIGHT;
    }
}
