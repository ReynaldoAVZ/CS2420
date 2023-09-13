package assign03;

import org.junit.jupiter.api.DisplayNameGenerator;

public class SimplePriorityQueueTimer {
    /**
     * This program tries to determine how long it takes to compute the square root
     * of each number from 1 through 10.
     *
     * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
     * @version 2023-09-12
     */

    public static void main(String[] args) {
        for (int n = 100000; n <= 2000000; n += 100000) {
            SimplePriorityQueue<Integer> testSPQ = new SimplePriorityQueue<Integer>();
            long startTime, midpointTime, stopTime;
            for (int i = 0; i < n; i++) {
                testSPQ.insert(i);
            }

            //Integer[] array = (Integer[]) intQTester.getArray();
            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            long warmup = 0;
            while (System.nanoTime() - startTime < 1000000000) { // empty block
                warmup += 0.01;
            }
            // Now, run the test.
            long timesToLoop = 100;
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                // where you want to put the code you want to time
                // Time it takes to find a value in an array
                testSPQ.findMax();
            }

            midpointTime = System.nanoTime();
            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++) { // empty block
                for (double d = 1; d <= 10; d++) {
                }
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
