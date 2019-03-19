import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColumnSortMatrixTest {

    @Test
    void testBasicConstructor() {
       ColumnSortMatrix columnSortMatrix = new ColumnSortMatrix(10, 5);
       assertEquals(columnSortMatrix.getMatrix()[0].length, 5);
       assertEquals(columnSortMatrix.getMatrix().length, 10);
    }

    @Test
    void testListConstructor() {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        nums.add(6);
        ColumnSortMatrix columnSortMatrix = new ColumnSortMatrix(nums, 3, 2);
        assertEquals(1, columnSortMatrix.getMatrix()[0][0]);
        assertEquals(2, columnSortMatrix.getMatrix()[1][0]);
        assertEquals(3, columnSortMatrix.getMatrix()[2][0]);
        assertEquals(4, columnSortMatrix.getMatrix()[0][1]);
        assertEquals(5, columnSortMatrix.getMatrix()[1][1]);
        assertEquals(6, columnSortMatrix.getMatrix()[2][1]);
    }
}