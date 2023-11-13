package lab11;

import java.io.File;
import java.util.*;

/**
 * This class contains three methods / strategies for counting duplicates in a dataset,
 * as well as code for code for collecting the running time of each method.
 *
 * @author Aaron Wood and ?
 * @version 2023-11-10
 */
public class DuplicationAnalysis {

	/**
	 * Counts the number of duplicate items in the given list,
	 * using a brute-force, all pairs strategy.
	 *
	 * @param dataset - the given list
	 * @return the number of duplicates
	 */
	public static <T extends Comparable<? super T>> int countDuplicatesAllPairs(List<T> dataset) {
		int duplicateCount = 0;
		for (int i = 0; i < dataset.size(); i++) {
			for (int j = i + 1; j < dataset.size(); j++) {
				if (dataset.get(i).equals(dataset.get(j))) {
					duplicateCount++;
					break;
				}
			}
		}
		return duplicateCount;
	}

	/**
	 * Counts the number of duplicate items in the given list,
	 * using a hash table strategy.
	 *
	 * @param dataset - the given list
	 * @return the number of duplicates
	 */
	public static <T extends Comparable<? super T>> int countDuplicatesHashing(List<T> dataset) {
		Hashtable<T, Integer> tracker = new Hashtable<>();
		int duplicateCount = 0;
		for (T item : dataset) {
			if (tracker.put(item, 0) != null) {
				duplicateCount++;
			}
		}
		return duplicateCount;
	}

	/**
	 * Counts the number of duplicate items in the given list,
	 * using a sorting strategy.
	 *
	 * @param dataset - the given list
	 * @return the number of duplicates
	 */
	public static <T extends Comparable<? super T>> int countDuplicatesSorting(List<T> dataset) {
		Collections.sort(dataset);
		int duplicateCount = 0;
		for (int i = 0; i < dataset.size() - 1; i++) {
			if (dataset.get(i).equals(dataset.get(i + 1))) {
				duplicateCount++;
			}
		}
		return duplicateCount;
	}

	public static void main(String[] args) {

		// create a small list of phone numbers with 1 duplicate
		List<PhoneNumber> contactsSmall = new ArrayList<PhoneNumber>();
		contactsSmall.add(new PhoneNumber("801", "581", "8224"));
		contactsSmall.add(new PhoneNumber("801", "581", "4553"));
		contactsSmall.add(new PhoneNumber("801", "585", "1545"));
		contactsSmall.add(new PhoneNumber("801", "581", "8224"));
		contactsSmall.add(new PhoneNumber("801", "581", "4345"));

		System.out.println("Counting duplicates for small list with all pairs method\n" + countDuplicatesAllPairs(contactsSmall));
		System.out.println("Counting duplicates for small list with hashing method\n" + countDuplicatesHashing(contactsSmall));
		System.out.println("Counting duplicates for small list with sorting method\n" + countDuplicatesSorting(contactsSmall));


		// creates a large list of phone numbers with 15 duplicates
		List<PhoneNumber> contactsLarge = new ArrayList<PhoneNumber>();
		try {
			Scanner scan = new Scanner(new File("src/lab11/phone_numbers.csv"));
			while(scan.hasNextLine())
				contactsLarge.add(new PhoneNumber(scan.nextLine()));
			scan.close();
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}

		System.out.println("Counting duplicates for small list with all pairs method\n" + countDuplicatesAllPairs(contactsLarge));
		System.out.println("Counting duplicates for small list with hashing method\n" + countDuplicatesHashing(contactsLarge));
		System.out.println("Counting duplicates for small list with sorting method\n" + countDuplicatesSorting(contactsLarge));

		// Uncomment to collect running times for each method and verify your expected Big-O behaviors.
		//collectRunTimes();
	}

	@SuppressWarnings("unused")
	private static void collectRunTimes() {

		// IMPORTANT NOTE: These values are purposely small to allow collection of running times
		//                 during a short lab period -- adjust as needed and be sure to use larger
		//                 values for analysis documents.
		int timesToLoop = 10;
		int minN = 1000;
		int maxN = 10000;
		int incrementN = 1000;

		System.out.println("\nN\trunning time");

		for(int N = minN; N <= maxN; N += incrementN) {

			// Generate a list of N random phone numbers
			List<PhoneNumber> randList = new ArrayList<PhoneNumber>();
			for(int i = 0; i < N; i++)
				randList.add(new PhoneNumber());

			// Let things stabilize
			long startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000)
				;

			// Time the routine
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				countDuplicatesAllPairs(randList);     // CHANGE method call to time other methods
			}

			long midTime = System.nanoTime();

			// Time the empty loop
			for(int i = 0; i < timesToLoop; i++) {
				;
			}

			long stopTime = System.nanoTime();

			double avgTime = ((midTime - startTime) - (stopTime - midTime)) / (double) timesToLoop;

			System.out.println(N + "\t" + avgTime);
		}
	}
}
