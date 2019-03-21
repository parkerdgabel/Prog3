import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Columnsort algorithm.
 * This is a static class and should never be instantiated
 */
final public class ColumnSort {

    /** Sorts a matrix using the columnsort algorithm.
     * @param matrix            The matrix to sort
     * @return                  A sorted matrix
     */
    public static ColumnSortMatrix sortMatrix(ColumnSortMatrix matrix) {
        // Step 1: Sort
        matrix.sortAllColumns();
        // Step 2: Transpose and Reshape
        ColumnSortMatrix transposed = matrix.transpose();
        reshape(matrix, transposed);
        // Step 3: Sort
        matrix.sortAllColumns();
        // Step 4: Reshape and Transpose
        matrix = invertReshape(matrix);
        // Step 5: Sort
        matrix.sortAllColumns();
        // Step 6: Shift down by r / 2
        ColumnSortMatrix shiftedMatrix = shiftDown(matrix);
        // Step 7: Sort
        shiftedMatrix.sortAllColumns();
        // Step 8: Shift up by r/ 2
        return shiftUp(shiftedMatrix);
    }

    /** Reshapes the matrix from its transposition as described in step 2
     * @param matrix            The matrix to reshape
     * @param transposed        The transposition of the original matrix
     */
    private static void reshape(ColumnSortMatrix matrix, ColumnSortMatrix transposed) {
       List<Integer> nums = transposed.toListRowMajor();
       for (int i = 0; i < matrix.getRows(); i++) {
           for (int j = 0; j < matrix.getCols(); j++) {
               matrix.getMatrix()[i][j] = nums.remove(0);
           }
       }
    }

    /** Inverts the reshaping permutation from step 2
     * @param matrix        The matrix to invert
     * @return              The matrix after the inversion
     */
    private static ColumnSortMatrix invertReshape(ColumnSortMatrix matrix) {
        ColumnSortMatrix transposed = new ColumnSortMatrix(matrix.getCols(), matrix.getRows());
        // Collect all the values from the matrix in row-major order
        List<Integer> nums = matrix.toListRowMajor();
        for (int i = 0; i < matrix.getCols(); i++) {
            for (int j = 0; j < matrix.getRows(); j++) {
                transposed.getMatrix()[i][j] = nums.remove(0);
            }
        }
        return transposed.transpose();
    }

    /** Shifts the matrix columns down as described by step 6
     * @param matrix         The matrix to shift down
     * @return               The shifted matrix
     */
    private static ColumnSortMatrix shiftDown(ColumnSortMatrix matrix) {
        int shiftAmount = matrix.getRows() / 2;
        List<Integer> newVals = new ArrayList<>();
        // Add negative infinity
        for (int i = 0; i < shiftAmount; i++) {
            newVals.add(Integer.MIN_VALUE);
        }
        // add the matrix values
        newVals.addAll(matrix.toList());
        // if r is not even we must add 1 more positive infinity
        if (matrix.getRows() % 2 != 0) {
            shiftAmount++;
        }
        // Add positive infinity
        for (int i = 0; i < shiftAmount; i++) {
            newVals.add(Integer.MAX_VALUE);
        }
        return new ColumnSortMatrix(newVals, matrix.getRows(), matrix.getCols() + 1);
    }

    /** Shifts the matrix up as described in Step 8
     * @param shiftedMatrix         The matrix to shift up
     * @return                      The up shifted matrix
     */
    private static ColumnSortMatrix shiftUp(ColumnSortMatrix shiftedMatrix) {
        List<Integer> newVals = shiftedMatrix.toList();
        List<Integer> infinities = new ArrayList<>();
        // Set the infinities to remove
        infinities.add(Integer.MIN_VALUE);
        infinities.add(Integer.MAX_VALUE);
        newVals.removeAll(infinities);
        return new ColumnSortMatrix(newVals, shiftedMatrix.getRows(), shiftedMatrix.getCols() - 1);
    }
}
