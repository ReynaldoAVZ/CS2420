package assign08;

import java.util.*;

public class BinarySearchTreeTimer {
    public static void main(String[] args) {
        for (int n = 10000; n <= 200000; n += 10000) {

            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 100;

            startTime = System.nanoTime();

            // code we want to time goes in here
            for (long j = 0; j < timesToLoop; j++) {
                // generate a list of vertices
                //TreeSet<Integer> treeSet = new TreeSet<>();
                BinarySearchTree<Integer> BST = new BinarySearchTree<>();
                Random rng = new Random();
                List<Integer> randList = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    randList.add(i);
                }
                Collections.shuffle(randList);
                for (int i = 0; i < n; i++) {
                    BST.add(randList.get(i));
                }
                for (int i = 0; i < n; i++) {
                    boolean result = BST.contains(randList.get(i));
                }
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            // also include any other code whose time you want to remove
            for (long j = 0; j < timesToLoop; j++) {
                // empty block
                Random rng = new Random();
                //TreeSet<Integer> treeSet = new TreeSet<>();
                BinarySearchTree<Integer> BST = new BinarySearchTree<>();
                List<Integer> randList = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    randList.add(i);
                }
                Collections.shuffle(randList);
                for (int i = 0; i < n; i++) {
                    BST.add(randList.get(i));
                }
                for (int i = 0; i < n; i++) {
                }
            }
            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop) / n;

            System.out.println(n + "\t" + averageTime);
        }
    }
}
