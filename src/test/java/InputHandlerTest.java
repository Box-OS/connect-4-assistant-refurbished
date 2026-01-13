import logic.InputHandler;
import org.junit.Test;
/**
 * InputHandlerTest
 * This class is used for testing InputHandler Class
 * @author Betty Sun
 * @version 1.31.2023
 */
public class InputHandlerTest {

    // Initialization (initialize the number of columns to 5)
    InputHandler inputHandler = new InputHandler(5);

    /**
     * This method checks if the number of input columns meets the required range.
     */
    @Test
    public void checkInRange(){
        final boolean inRange = inputHandler.checkInRange(5);
        if (inRange)
            System.out.println("Meet the requirements");
        else
            System.out.println("Does not meet the requirements");
    }

}
