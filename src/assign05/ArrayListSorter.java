package assign05;

import java.util.ArrayList;

/**
 * This Java class represents an ArrayListSorter that has built in methods to use various different sorting algorithms
 * (specifically merge sort and quicksort)
 * <p></p>
 * We will perform experiments to see which of these two sorting algorithms has the fastest running times for
 * Java ArrayLists of various sizes in the following three categories:
 * <p></p>
 * The ArrayList contains objects in ascending (i.e., sorted) order.
 * <p></p>
 * The ArrayList contains objects in a permuted (i.e., shuffled) order.
 * <p></p>
 * The ArrayList contains objects in a descending (i.e., reverse-sorted) order.
 * <p></p>
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-00
 */
public class ArrayListSorter {

    /**
     * This method performs a merge sort on the generic ArrayList given as input.
     * <p></p>
     * Note: Our implementation must switch over to insertion sort when the size of the
     * sublist to be sorted meets a certain threshold (i.e., becomes small enough).
     * Make this threshold value a private static variable that we can easily change.
     * We will perform timing experiments to determine which threshold value works best.
     * <p></p>
     * REMINDER: Each sort method must be a "driver" method, which calls the private recursive method that
     * contains the full implementation each sort, as appropriate.
     * <p></p>
     * @param <T> arrayList - (ArrayList<T>) object that needs to be sorted
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList) {

    }

    /**
     * This method performs a quicksort on the generic ArrayList given as input.
     * <p></p>
     * Note: We must implement three different strategies for determining the pivot, and our implementation should be
     * able to easily switch among these strategies.  (Consider using a few private helper methods for our different
     * pivot-selection strategies). We will perform timing experiments to determine which strategy works best.
     * Our implementation may switch to insertion sort on some small threshold, if we wish. In designing a strategy
     * for choosing a pivot, keep in mind that its running time affects the overall running time of the quicksort.
     *<p></p>
     * REMINDER: Each sort method must be a "driver" method, which calls the private recursive method that
     * contains the full implementation each sort, as appropriate.
     * <p></p>
     * @param <T> arrayList - (ArrayList<T>) object that needs to be sorted
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList) {

    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in ascending order.
     * @param size - number of objects in the (ArrayList<Integer>) object.
     * @return (ArrayList<Integer>) arrayList - object that contains values 1-size
     */
    public static ArrayList<Integer> generateAscending(int size) {
        return null;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly ordered).
     * We may adapt the shuffle method written in Class Meeting 8 or make use of Java's Collections.shuffle method.
     * @param size
     * @return
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        return null;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in descending order.
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateDescending(int size) {
        return null;
    }
}
