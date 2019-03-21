import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuicksortTest {

    @Test
    void sort() {
        int[] arr = new int[5];
        arr[0] = 4;
        arr[1] = 3;
        arr[2] = 9;
        arr[3] = 7;
        arr[4] = 2;
        Quicksort.sort(arr, 0, 4);
        int[] expected = new int[5];
        expected[0] = 2;
        expected[1] = 3;
        expected[2] = 4;
        expected[3] = 7;
        expected[4] = 9;
        assertArrayEquals(expected, arr);
    }
}