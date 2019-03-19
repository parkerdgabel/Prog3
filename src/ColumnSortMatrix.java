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

    // Getters and Setters
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
