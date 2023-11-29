package lab12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Demonstration of Java's PriorityQueue class.
 *
 * @author Aaron Wood
 * @version 2023-11-17
 */
public class PriorityQueueDemo {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
				(Integer o1, Integer o2) -> {return o2 - o1;});
		pq.add(36);
		pq.add(17);
		pq.add(3);
		pq.add(100);
		pq.add(19);
		pq.add(2);
		pq.add(70);

		System.out.println("Array: " + Arrays.toString(pq.toArray()));

		System.out.println("First item removed: " + pq.remove());
		System.out.println("Second item removed: " + pq.remove());
		System.out.println("Third item removed: " + pq.remove());
		System.out.println("Fourth item removed: " + pq.remove());
		System.out.println("Fifth item removed: " + pq.remove());
		System.out.println("Sixth item removed: " + pq.remove());
		System.out.println("Seventh item removed: " + pq.remove());

		PriorityQueue<String> pqString = new PriorityQueue<>(new ReverseStringComparator());
		pqString.add("a");
		pqString.add("b");
		pqString.add("c");
		pqString.add("d");
		pqString.add("e");
		pqString.add("f");

		System.out.println("Array: " + Arrays.toString(pqString.toArray()));
		System.out.println("First item removed: " + pqString.remove());
		System.out.println("Second item removed: " + pqString.remove());
		System.out.println("Third item removed: " + pqString.remove());
		System.out.println("Fourth item removed: " + pqString.remove());
		System.out.println("Fifth item removed: " + pqString.remove());
		System.out.println("Sixth item removed: " + pqString.remove());
		System.out.println("Seventh item removed: " + pqString.remove());
	}
	public static class ReverseStringComparator implements Comparator<String> {
		public int compare(String arg0, String arg1) {
			return arg1.compareTo(arg0);
		}
	}
}
