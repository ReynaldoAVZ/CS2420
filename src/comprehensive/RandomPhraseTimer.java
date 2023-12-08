package comprehensive;

import assign10.FindKLargest;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomPhraseTimer {
    public static void main(String[] args) throws IOException {
        for (int n = 10000; n <= 100000; n += 10000) {

            long startTime, midpointTime, stopTime;

            // "Warm up" the system for one second to allow things to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 100;
            // keep track of collisions
            Random rng = new Random();
            // write a file of size n
            PrintWriter writer = new PrintWriter("src/comprehensive/file.g", StandardCharsets.UTF_8);
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            writer.println("{");
            writer.println("<start>");
            for (int j = 0; j < n; j++) { // terminal (productions)
                writer.println(j);
            }
            writer.println("}");
            for (int i = 0; i < 10; i++) { // non terminals
                writer.println("{");
                int nonTerminalLength = rng.nextInt(3, 8);
                String nonTerminal = "";
                for (int j = 0; j < nonTerminalLength; j++) {
                    nonTerminal = nonTerminal + characters.charAt(rng.nextInt(characters.length()));
                }
                writer.println("<" + nonTerminal + ">");
                for (int j = 0; j < 10; j++) { // terminal (productions)
                    writer.println(j);
                }
                writer.println("}");
            }
            writer.close();



            startTime = System.nanoTime();

            // code we want to time goes in here
            for (long j = 0; j < timesToLoop; j++) {
                String[] array = {"src/comprehensive/file.g", String.valueOf(n)};
                RandomPhraseGenerator.main(array);
            }
            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            // also include any other code whose time you want to remove
            for (long j = 0; j < timesToLoop; j++) {
                String[] array = {"src/comprehensive/file.g", String.valueOf(n)};
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

