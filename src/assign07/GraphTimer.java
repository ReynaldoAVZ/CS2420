package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphTimer {
    public static void main(String[] args) {
        for (int n = 10000; n <= 100000; n += 10000) {

            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 500;

            startTime = System.nanoTime();

            // code we want to time goes in here
            for (long j = 0; j < timesToLoop; j++) {
                Random rng = new Random();

                // generate a list of vertices
                Integer[] vertex = new Integer[n];

                List<Integer> vertexSource = new ArrayList<>();
                List<Integer> vertexDestination = new ArrayList<>();
                for(int i = 0; i < n ; i++)
                    vertex[i] = i;

                // randomly connect the vertices using 2 * |V| edges
                for(int i = 0; i < n - 1; i++) {
                    vertexSource.add(i);
                    vertexDestination.add(vertex[i + 1 + rng.nextInt(n - (i + 1))]);
                }

                for(int i = 0; i < n - 1; i++) {
                    vertexSource.add(i);
                    vertexDestination.add(vertex[i + 1 + rng.nextInt(n - (i + 1))]);
                }
                GraphUtility.shortestPath(vertexSource, vertexDestination, 0, n-1);
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            // also include any other code whose time you want to remove
            for (long j = 0; j < timesToLoop; j++) { // empty block
                Random rng = new Random();

                // generate a list of vertices
                Integer[] vertex = new Integer[n];

                List<Integer> vertexSource = new ArrayList<>();
                List<Integer> vertexDestination = new ArrayList<>();
                for(int i = 0; i < n; i++)
                    vertex[i] = i;

                // randomly connect the vertices using 2 * |V| edges
                for(int i = 0; i < n - 1; i++) {
                    vertexSource.add(i);
                    vertexDestination.add(vertex[i + 1 + rng.nextInt(n - (i + 1))]);
                }

                for(int i = 0; i < n - 1; i++) {
                    vertexSource.add(i);
                    vertexDestination.add(vertex[i + 1 + rng.nextInt(n - (i + 1))]);
                }
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
