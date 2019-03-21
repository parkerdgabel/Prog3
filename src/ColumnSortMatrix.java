import java.util.ArrayList;
import java.util.List;

/**
 * @author Parker Gabel
 * Assignment 3- Columnsort, CSc 345, Spring 2019
 * ColumnSortMatrix abstracts the necessary Matrix operations required by Columnsort.
 */
public class ColumnSortMatrix {
    private int rows;
    private int cols;
    private int[][] matrix;

    /** ColumnSortMatrix is a constructor that accepts the number of rows and columns for the matrix.
     * @param rows          The number of rows in the matrix
     * @param cols          The number of columns in the matrix
     */
    public ColumnSortMatrix(int rows, int cols) {
        try {
            assert rows > 0 && cols > 0;
        } catch (AssertionError error) {
            System.err.println("Row and Column for the matrix must be greater than 0.");
            System.exit(1);
        }
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
    }

    /** ColumnSortMatrix is a constructor which accepts a list of numbers and the number of rows and columns.
     * The numbers are input in column major order.
     * @param nums          The numbers to input into the matrix
     * @param rows          The number of rows in the matrix
     * @param cols          The number of columns in the matrix
     */
    public ColumnSortMatrix(List<Integer> nums, int rows, int cols) {
        try {
            assert rows > 0 && cols > 0;
        } catch (AssertionError error) {
            System.err.println("Row and Column for the matrix must be greater than 0.");
            System.exit(1);
        }
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
        for (int index = 0; index < nums.size(); index++) {
            this.matrix[index % rows][index / rows] = nums.get(index);
        }
    }

    /** Returns the column of the matrix at a specific location
     * @param col           The column number
     * @return              An array containing all the integers in the column
     */
    private int[] getColumn(int col) {
        assert col < this.cols;
        int[] column = new int[this.rows];
        for (int i = 0; i < this.rows; i++) {
            column[i] = this.matrix[i][col];
        }
        return column;
    }

    /** Replaces a column in the matrix with an input array
     * @param column            The array to replace the column
     * @param col               The column number
     */
    private void replaceColumn(int[] column, int col) {
        assert column.length == this.rows;
        for (int i = 0; i < this.rows; i++) {
            this.matrix[i][col] = column[i];
        }
    }

    /** Sorts an individual column in the matrix
     * @param col               The column number
     */
    private void sortColumn(int col) {
        int[] column = this.getColumn(col);
        Quicksort.sort(column, 0, column.length - 1);
        this.replaceColumn(column, col);
    }

    /**
     * Sorts all the column in the matrix
     */
    public void sortAllColumns() {
        for (int col = 0; col < this.cols; col++) {
            this.sortColumn(col);
        }
    }

    /** Returns a new transposed matrix
     * @return              A transposed matrix
     */
    public ColumnSortMatrix transpose() {
        ColumnSortMatrix transposed = new ColumnSortMatrix(this.cols, this.rows);
        for (int i = 0; i < transposed.rows; i++) {
            transposed.matrix[i] = this.getColumn(i).clone();
        }
        return transposed;
    }

    /** Returns a list of the matrix entries in column-major row.
     * @return          A list containing the values of the matrix in column-major order.
     */
    public List<Integer> toList() {
        List<Integer> integerList = new ArrayList<>();
        int[] column;
        for (int col = 0; col < this.cols; col++) {
            column = this.getColumn(col);
            for (int i = 0; i < column.length; i++) {
                integerList.add(column[i]);
            }
        }
        return integerList;
    }

    /** Returns a list of the matrix entries in row-major order
     * @return          A list containing the values of the matrix in row-major order
     */
    public List<Integer> toListRowMajor() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < this.getRows(); i++) {
            for (int num : this.matrix[i]) {
                integerList.add(num);
            }
        }
        return integerList;
    }

    // Getters and Setters
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
