package assign04;

import java.util.ArrayList;
import java.util.List;

/**
 * This program times how long it takes for our findKthLargest() with our insertionSort() method and also Java's
 * sort() method.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-09-18
 */
public class LargestNumberSolverTimer {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 4, 3, 2};
        for (int n = 10000; n <= 200000; n += 10000) {
            List<Integer[]> myList = new ArrayList<Integer[]>();
            // loop to create a list of size n
            for(int i = 0; i < n; i++) {
                myList.add(array);
            }
            myList.sort(new OrderArray());
            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            long warmup = 0;
            while (System.nanoTime() - startTime < 1000000000) { // empty block
                warmup += 0.01;
            }

            // Now, run the test.

            double timesToLoop = 100;

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++) {
                // used for finding the time for findKthLargest() using both insertionSort and Java.sort()
                Integer[] result = LargestNumberSolver.findKthLargest(myList, n - 1);
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (long i = 0; i < timesToLoop; i++) { // empty block
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

            System.out.println(n + "\t" + averageTime);
        }
    }

}

