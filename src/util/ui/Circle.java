package util.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Circle.java
 *  creates the checkers for player1 (red) and player2 (blue)
 *
 *   @author Sina Akhavan
 */
public class Circle {
    private final int HEIGHT, WIDTH;
    private final String TYPE;
    private ImageView img;
    Image redCircle = new Image("red-circle.png");
    Image blueCircle = new Image("blue-circle.png");

    public Circle(String TYPE) {
        this.TYPE = TYPE;
        HEIGHT = 45;
        WIDTH = 45;
        draw();
    }

    /**
     * draws the circle image
     */
    private void draw() {
        img = new ImageView();
        if (TYPE.equals("red")) {
            img.setImage(redCircle);
        } else if (TYPE.equals("blue")) {
            img.setImage(blueCircle);
        }
            img.setFitHeight(HEIGHT);
            img.setFitWidth(WIDTH);
    }

    //Getters and Setters
    public ImageView getImg() {return img;}
}
