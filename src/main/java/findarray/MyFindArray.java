package findarray;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Full code hosted at https://github.com/mdjebaile/atlassian.git
 * 
 * This implementation is part of Atlassian interview screenings.
 * 
 * @author miguel.djebaile
 *
 */
public class MyFindArray implements FindArray {

    private static final int NOT_FOUND_CODE = -1;
    private static final Logger logger = Logger.getLogger(MyFindArray.class.getName());

    /**
     * Finds a given subarray inside a given array.
     *
     * @param array
     * @param subarray to look for
     * @return index of subarray if present. If not, returns -1.
     */
    public int findArray(int[] array, int[] subarray) {

        // Check that they are not null or empty, and that subarray length is less than array length.
        boolean meetPreconditions = this.validateFindArray(array, subarray);

        if (meetPreconditions) {

            // This IF may be unnecessary, but is more efficient in the case we hit this scenario of content
            // equality.
            if (subarray.length == array.length) {
                // If both have same length, we only care if they are content-equals
                if (Arrays.equals(subarray, array)) {
                    logger.log(Level.INFO, "Both arrays have same content");
                    return 0;
                } else {
                    logger.log(Level.INFO, "Subarray {0} not found within array {1}", new String[] {
                            Arrays.toString(subarray), Arrays.toString(array)});
                    return NOT_FOUND_CODE;
                }
            } else {
                // They have different lengths
                for (int i = 0; i < array.length; i++) {
                    if (array[i] == subarray[0]) {
                        boolean subArrayFound = true;
                        for (int j = 0; j < subarray.length; j++) {
                            if (array.length <= i + j || subarray[j] != array[i + j]) {
                                subArrayFound = false;
                                break;
                            }
                        }
                        if (subArrayFound) {
                            logger.log(Level.INFO, "Subarray found. Starts at index = {0}", i);
                            return i;
                        }
                    }
                }
            }
        }

        logger.log(Level.INFO, "Subarray {0} not found within array {1}", new String[] {
                Arrays.toString(subarray), Arrays.toString(array)});

        return NOT_FOUND_CODE;
    }

    private boolean validateFindArray(int[] array, int[] subarray) {
        if (array == null || array.length == 0) {
            logger.log(Level.WARNING, "Larger array param is null or empty");
            return false;
        }
        if (subarray == null || subarray.length == 0) {
            logger.log(Level.WARNING, "Subarray param is null or empty");
            return false;
        }
        if (subarray.length > array.length) {
            logger.log(Level.WARNING, "Subarray length is bigger than array length");
            return false;
        }
        return true;
    }

    // Main method
    public static void main(String[] CHAND) {
        MyFindArray objFindArray = new MyFindArray();
        int[] array = {4, 4, 4, 4, 4, 4, 9, 3, 7, 8};
        int[] sub_array = {4, 4, 4, 4, 9, 3, 7, 8};
        System.out.println(objFindArray.findArray(array, sub_array));
    }

}