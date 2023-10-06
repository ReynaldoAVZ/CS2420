package assign05;

import java.util.ArrayList;

/**
 * This program tries to determine how long it takes to compute the square root
 * of each number from 1 through 10.
 *
 * @author Aaron Wood
 * @version 2023-08-29
 */
public class ArrayListSorterTimer {

    public static void main(String[] args) {
        for (int n = 10000; n <= 100000; n += 10000) {

            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 100;
            ArrayList<Integer> newList = ArrayListSorter.generatePermuted(n);

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++) {
                ArrayListSorter.quicksort(new ArrayList<>(newList));
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (long i = 0; i < timesToLoop; i++) { // empty block
                new ArrayList<>(newList);
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
