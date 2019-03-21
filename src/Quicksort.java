/**
 * Quicksort implements the quicksort algorithm for Columnsort
 * This is a static class and should never be instantiated
 */
final public class Quicksort {

    /** Sorts an array inplace using the quicksort algorithm
     * @param arr           The array to sort
     * @param low           The lower index
     * @param high          The higher index
     */
    public static void sort(int arr[], int low, int high) {
        if (low < high) {
            // partition the array
            int index = partition(arr, low, high);
            // Sort the partitions
            sort(arr, low, index - 1);
            sort(arr, index + 1, high);
        }
    }

    /** Partitions the array around a pivot such that all elements in the array below the pivot are less than the pivot
     *  and all elements above the pivot are greater than the pivot
     * @param arr           The array to sort
     * @param low           The lower index
     * @param high          The higher index
     * @return              The index that the pivot in its proper place
     */
    private static int partition(int[] arr, int low, int high) {
        // We use a choice of pivot that is a kind of median in the array
        // If we just chose the leftmost position as a pivot we get a worst case when the array is already sorted
        // This choice of pivot guards against that.
        int mid = (low + high) / 2;
        if (arr[mid] < arr[low]) {
            int temp = arr[low];
            arr[low] = arr[mid];
            arr[mid] = temp;
        }
        if (arr[high] < arr[low]) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
        }
        if (arr[mid] < arr[high]) {
            int temp = arr[mid];
            arr[mid] = arr[high];
            arr[high] = temp;
        }
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
