package lab02;

/**
 * This program tries to determine how long it takes to compute the square root
 * of each number from 1 through 10.
 *
 * @author Aaron Wood
 * @version 2023-08-29
 */
public class TimingExperiment08 {

	public static void main(String[] args) {
		for (int n = 1000; n <= 10000; n += 1000) {

		long startTime, midpointTime, stopTime;

		// "Warm up" the system for one second to allow things to stabilize.
		startTime = System.nanoTime();
		long warmup = 0;
		while (System.nanoTime() - startTime < 1000000000) { // empty block
			warmup += 0.01;
		}

		// Now, run the test.

		long timesToLoop = 10000;

		startTime = System.nanoTime();

		for (long i = 0; i < timesToLoop; i++) {

			// where you want to put the code you want to time
			// Time it takes to find the square root of numbers 1-n
			for (double d = 1; d <= n; d++) {
				Math.sqrt(d);
			}
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
