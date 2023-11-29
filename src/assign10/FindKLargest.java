package assign10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 *
 * @author Aaron Wood and Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 11-28-23
 */
public class FindKLargest {

	/**
	 * Determines the k largest items in the given list, using a binary max heap and the
	 * natural ordering of the items.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException();
		}
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items); // create a new heap with the list of items
		List<E> returnedList = new ArrayList<>(); // create a new empty list
		for (int i = 0; i < k; i++) { // iterate from root to the kth item in the heap
			E currentMax = heap.extractMax(); // extract the max and place in list
			returnedList.add(currentMax);
		}
		return returnedList; // return the list
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException();
		}
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items, cmp); // create a heap using the list and the comparator
		List<E> returnedList = new ArrayList<>(); // create an empty list
		for (int i = 0; i < k; i++) { // iterate from the root to the kth item in the heap
			E currentMax = heap.extractMax(); // extract the max and place into the list
			returnedList.add(currentMax);
		}
		return returnedList; // return the list
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the
	 * natural ordering of the items.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException();
		}
		items.sort(null); // sort the list of items using Java's sort routine
		List<E> returnedList = new ArrayList<>(); // create a new list
		for (int i = items.size() - 1; i >= items.size() - k; i--) { // iterate from the end of the sorted list to the kth item
			returnedList.add(items.get(i)); // add the item to the list
		}
		return returnedList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k > items.size() || k < 0) {
			throw new IllegalArgumentException();
		}
		items.sort(cmp); // sort the list using java's sort routine passing in a comparator
		List<E> returnedList = new ArrayList<>(); // create a new list
		for (int i = items.size() - 1; i >= items.size() - k; i--) { // iterate from the last value of list to kth item
			returnedList.add(items.get(i)); // add the item into the list
		}
		return returnedList;
	}
}
