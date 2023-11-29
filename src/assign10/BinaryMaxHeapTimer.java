package assign10;

import java.util.*;

public class BinaryMaxHeapTimer {
    public static void main(String[] args) {
        for (int n = 50000; n <= 1000000; n += 50000) {

            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 100;
            // keep track of collisions
            Random rng = new Random();
            startTime = System.nanoTime();

            // code we want to time goes in here
            for (long j = 0; j < timesToLoop; j++) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    list.add(i);
                }
                Collections.shuffle(list);
                List<Integer> returnedList = FindKLargest.findKLargestSort(list, n);
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            // also include any other code whose time you want to remove
            for (long j = 0; j < timesToLoop; j++) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    list.add(i);
                }
                Collections.shuffle(list);
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop);
            System.out.println(n + "\t" + averageTime);
        }
    }
}

