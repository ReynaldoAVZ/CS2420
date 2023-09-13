package assign03;

/**
* This program times how long it takes for our findmax() and insert() method to run through different SimplePriorityQueues of
 * different sizes, ranging from 100,000 to 2,000,000 in increments of 100,000.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-09-13
 */
public class SimplePriorityQueueTimer {
    public static void main(String[] args) {
        for (int n = 100000; n <= 2000000; n += 100000) {
            // used to use findMax()
            SimplePriorityQueue<Integer> spq = new SimplePriorityQueue<Integer>();
//            for (int i = 0; i < n; i++) {
//                spq.insert(i);
//            }

            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            long warmup = 0;
            while (System.nanoTime() - startTime < 1000000000) { // empty block
                warmup += 0.01;
            }

            // Now, run the test.

            double timesToLoop = 2;

            startTime = System.nanoTime();
            //int max;
            // loop to find the total time it takes to findMax() a total of timesToLoop
//            for (long i = 0; i < timesToLoop; i++) {
//                max = spq.findMax();
//            }
            for (long i = 0; i < timesToLoop; i++) {
                for (int j = 0; j < n; j++) {
                    spq.insert(j);
                }
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
