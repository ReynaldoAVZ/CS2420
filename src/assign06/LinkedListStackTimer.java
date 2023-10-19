package assign06;

import java.util.ArrayList;
public class LinkedListStackTimer {

    public static void main(String[] args) {
        for (int n = 10000; n <= 100000; n += 10000) {

            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 1000;
            ArrayStack<Integer> arrayStack = new ArrayStack<>();
            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++) {
                    arrayStack.push(j);
                    arrayStack.pop();
                }
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (long i = 0; i < timesToLoop; i++) { // empty block
                for (int t = 0; t < n; t++) {
                    arrayStack.push(t);
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
