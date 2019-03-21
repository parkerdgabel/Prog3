import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 * Parker Gabel
 * Assignment 3 - Columnsort, CSc 345, Spring 2019
 * Sorts list of numbers using the columnsort algorithm and times it.
 */
public class Prog3 {

    /** Generates a list of integers found in the input file.
     * @param infile            The file to process
     * @return                  A list of integers
     */
    private static List<Integer> getInput(File infile) {
        List<Integer> numList = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(infile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (scanner.hasNextInt()) {
           try {
               numList.add(scanner.nextInt());
           } catch (InputMismatchException e) {
               System.out.println("Token was not an integer!");
               System.exit(1);
           }
        }
        return numList;
    }

    /** Finds all factors of n that are less than the square root of n
     * @param n             The number to factor
     * @return              A list of small factors
     */
    private static List<Integer> generateSmallFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (int i = 2; i <= Math.floor(Math.sqrt(n)); i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    /** Chooses an appropriate s for columnsort
     * @param factors           A list of small factors of n
     * @param n                 The number of elements to sort
     * @return                  An appropriate s for columnsort
     */
    private static int choseS(List<Integer> factors, int n) {
        // If factors has size one then n is prime and s must be one
        if (factors.size() == 1) {
            return 1;
        }
        int s = 1;
        int r;
        // Work backwards in factor list so we don't trivially get zero
        for (int i = factors.size() - 1; i >= 0; i--) {
            s = factors.get(i);
            r = n / s;
            // r must both be greater than or equal 2(s-1)^2 and divisible by s
            if (r >=2 * Math.pow((s-1), 2) && r % s == 0) {
                return s;
            }
        }
        return s;
    }

    /** Main routine for Program 3
     * @param args          A path to input data
     */
    public static void main(String args[]) {
        final double BILLION = 1000000000.0; // a nanosec = billionth of a sec

        long   startTime, elapsedTime;
        double seconds;
        // We need input data to run
        if (args.length < 1) {
            System.err.println("You must provide an input file!");
            System.exit(1);
        }
        File infile = new File(args[0]);
        // Collect our numbers to sort
        List<Integer> numList = getInput(infile);
        int n = numList.size();
        if (n == 0) {
            System.err.println("Input file cannot be empty!");
            System.exit(1);
        }
        System.out.printf("n = %d\n", n);
        // Now get the factors of n that are less than sqrt(n)
        List<Integer> smallFactors = generateSmallFactors(n);
        // chose an appropriate s
        int s = choseS(smallFactors, n);
        int r = n / s;
        System.out.printf("r = %d\n", r);
        System.out.printf("s = %d\n", s);
        // If n is prime or s = 1there is no need to columnsort, just quicksort the whole list and be done.
        if (s == 1) {
            int[] arr = new int[numList.size()];
            for (int i = 0; i < numList.size(); i++) {
                arr[i] = numList.get(i);
            }
            System.gc();
            startTime = System.nanoTime();
            Quicksort.sort(arr, 0, numList.size() - 1);
            elapsedTime = System.nanoTime() - startTime;
            seconds = elapsedTime / BILLION;
            System.out.println("Elapsed time = " + seconds + " seconds.");
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        } else {
            // Use Columnsort otherwise
            ColumnSortMatrix matrix = new ColumnSortMatrix(numList, r, s);
            System.gc();
            startTime = System.nanoTime();
            matrix = ColumnSort.sortMatrix(matrix);
            elapsedTime = System.nanoTime() - startTime;
            seconds = elapsedTime / BILLION;
            System.out.println("Elapsed time = " + seconds + " seconds.");
            for (Integer num : matrix.toList()) {
                System.out.println(num);
            }
        }
    }
}
