package util.logic;

import java.util.Arrays;

/**
 * Class for handling 2d array copying
 *
 * @author Leqi Shen
 * @version 1.30.2023
 */
public class ArrayHandler {
    /**
     * Method to copy a 2d array iteratively, one array at a time.
     *
     * @param original the 2d array to be copied
     * @return A new array, without a pointer to the original
     */
    public int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][7];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
