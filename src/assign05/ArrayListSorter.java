package assign05;

import java.util.ArrayList;
import java.util.Random;
import static java.util.Collections.shuffle;

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
 * @version 2023-10-05
 */
public class ArrayListSorter {

    // Initializing the threshold value for mergesort and pivot selection
    private static int INSERTION_SORT_THRESHOLD = 1000;
    private static int PIVOT_SELECTION = 2;

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
        // Pre-allocating the memory and space in the arraylist
        int size = arrayList.size();
        ArrayList<T> storageList = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            storageList.add(null);
        }
        // Calling the recursive method within mergesort
        mergesort(arrayList, 0, arrayList.size() - 1, storageList);
    }

    /**
     * The method mergesort() is the private driver method that is recursively called in order to correctly implement
     * the mergesort algorithm.
     *
     * @param arrayList The arraylist that is passed in
     * @param left The lower bound of the sublist
     * @param right The upper bound of the sublist
     * @param storageList the storage list being used to contain temporary items in it used to sort
     * @param <T> Generic type so any object can be used
     */
    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList, int left, int right, ArrayList<T> storageList) {
        // When the appropriate length of the sublist is reached, insertion sort is called
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arrayList, left, right);
            return;
        }
        // Recursively calling mergesort if and only if the left is less than right
        if (left < right) {
            int middle = (left + right) / 2;
            mergesort(arrayList, left, middle, storageList);
            mergesort(arrayList, middle + 1, right, storageList);
            merge(arrayList, left, middle, right, storageList);
        }
    }

    /**
     * This is the insertion sort method that is utilized by the mergesort method above.
     *
     * @param arrayList The arraylist that is passed in
     * @param left The lower bound of the sublist
     * @param right The upper bound of the sublist
     * @param <T> Generic type so any object can be used
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arrayList, int left, int right) {
        // Comparing the value at j with the item, setting, and decrementing if that's the case
        for (int i = left + 1; i <= right; i++) {
            T item = arrayList.get(i);
            int j = i - 1;
            while (j >= left && arrayList.get(j).compareTo(item) > 0) {
                arrayList.set(j + 1, arrayList.get(j));
                j--;
            }
            arrayList.set(j + 1, item);
        }
    }

    /**
     * This method is called recursively by the mergesort method above and is responsible for merging the subunits
     * of the sublist.
     *
     *
     * @param arrayList the arraylist being passed in
     * @param left the lower bound of the list
     * @param middle the middle bound of the list between left and right bounds
     * @param right the upper bound of the list
     * @param storageList the list the contents were stored into before sorting
     * @param <T> Generic type so any object may be used
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arrayList, int left, int middle, int right, ArrayList<T> storageList) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Copy elements from the original array to the storageList
        for (int i = 0; i < n1; i++) {
            storageList.set(i, arrayList.get(left + i));
        }

        int i = 0, j = 0;
        int k = left;

        // Merge elements from storageList and the right half of the original array back into the original array
        while (i < n1 && j < n2) {
            if (storageList.get(i).compareTo(arrayList.get(middle + 1 + j)) <= 0) {
                arrayList.set(k, storageList.get(i));
                i++;
            } else {
                arrayList.set(k, arrayList.get(middle + 1 + j));
                j++;
            }
            k++;
        }

        // Copy any remaining elements from storageList back to the original array
        while (i < n1) {
            arrayList.set(k, storageList.get(i));
            i++;
            k++;
        }

        // Copy any remaining elements from the right half of the original array (if any)
        while (j < n2) {
            arrayList.set(k, arrayList.get(middle + 1 + j));
            j++;
            k++;
        }
    }

    /**
     * This method performs a quicksort on the generic ArrayList given as input.
     * <p></p>
     * Note: We must implement three different strategies for determining the pivot, and our implementation should be
     * able to easily switch among these strategies.  (Consider using a few private helper methods for our different
     * pivot-selection strategies). We will perform timing experiments to determine which strategy works best.
     * Our implementation may switch to insertion sort on some small threshold, if we wish. In designing a strategy
     * for choosing a pivot, keep in mind that its running time affects the overall running time of the quicksort.
     * <p></p>
     * Option "1": Returns the median index of three values, the first index, the last index, and the middle index
     * <p>
     * Option "2": Returns the index of the middle object
     * <p>
     * Option "3": Returns the last index of the list.
     * <p>
     *<p></p>
     * REMINDER: Each sort method must be a "driver" method, which calls the private recursive method that
     * contains the full implementation each sort, as appropriate.
     * <p></p>
     * @param <T> arrayList - (ArrayList<T>) object that needs to be sorted
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList) {
        quicksort(arrayList, 0, arrayList.size() - 1);
    }
    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arrayList, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(arrayList, start, end);
            quicksort(arrayList, start, partitionIndex - 1);
            quicksort(arrayList, partitionIndex + 1, end);
        }
    }
    /**
     * This method is a helper method that determines the pivot value that will be used based off a user input in the
     * PIVOT_SELECTION variable.
     *
     * @param arrayList the array list being passed in
     * @param start the beginning index of the sublist
     * @param end the last index of the sublist
     * @return the correct pivot index based off the user selection.
     * @param <T> Generic type so any object can be used
     */
    private static <T extends Comparable<? super T>> int choosePivot(ArrayList<T> arrayList, int start, int end) {
        // You can choose one of the following pivot selection strategies:
        // 1. Random pivot
        // 2. Median-of-three pivot
        // 3. Last element as pivot

        // using random pivot strategy:
        if (PIVOT_SELECTION == 1) {
            return start + new Random().nextInt(end - start + 1);
        }

        // using median-of-three pivot strategy:
        else if (PIVOT_SELECTION == 2) {
            int middle = start + (end - start) / 2;
            T leftValue = arrayList.get(start);
            T middleValue = arrayList.get(middle);
            T rightValue = arrayList.get(end);
            if (middleValue.compareTo(leftValue) > 0 && middleValue.compareTo(rightValue) < 0) {
                return middle;
            }
            else if (leftValue.compareTo(middleValue) > 0 && leftValue.compareTo(rightValue) < 0) {
                return start;
            }
            else {
                return end;
            }
        }
        // default case, use the last index in the array list
        else
            return end;
    }

    /**
     * This partition method is utilized by the quicksort method above and splits the array into two parts without
     * counting the index containing the pivot.
     *
     * @param arrayList the arraylist being passed in
     * @param start the beginning index of the sublist
     * @param end the last index of the sublist
     * @return the int value of where the pivot's correct position is
     * @param <T> Generic type so any object can be used
     */
    private static <T extends Comparable<? super T>> int partition(ArrayList<T> arrayList, int start, int end) {
        int pivotIndex = choosePivot(arrayList, start, end);

        // Move the pivot value to the end
        T temp = arrayList.get(pivotIndex);
        arrayList.set(pivotIndex, arrayList.get(end));
        arrayList.set(end, temp);
        pivotIndex = end;
        int lower = start;
        int upper = pivotIndex - 1;
        while (lower <= upper) {
            // Find the first element from the left that is greater than or equal to the pivot
            while (lower < pivotIndex && arrayList.get(lower).compareTo(arrayList.get(pivotIndex)) <= 0) {
                lower++;
            }
            // Find the first element from the right that is less than or equal to the pivot
            while (upper >= start && arrayList.get(upper).compareTo(arrayList.get(pivotIndex)) > 0) {
                upper--;
            }
            // perform an element swap
            if (lower < upper) {
                temp = arrayList.get(lower);
                arrayList.set(lower, arrayList.get(upper));
                arrayList.set(upper, temp);
            }
        }
        // place the pivot value into the correct index
        temp = arrayList.get(lower);
        arrayList.set(lower, arrayList.get(pivotIndex));
        arrayList.set(pivotIndex, temp);

        return lower; // Return the index where the pivot element is now located
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in ascending order.
     *
     * @param size - number of objects in the (ArrayList<Integer>) object.
     * @return (ArrayList<Integer>) arrayList - object that contains values 1-size
     */
    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 1; i <= size; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly ordered).
     * We may adapt the shuffle method written in Class Meeting 8 or make use of Java's Collections.shuffle method.
     *
     * @param size represents the number of elements in the ArrayList
     * @return the ArrayList in a random, permuted order
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 1; i <= size; i++) {
            arrayList.add(i);
        }
        shuffle(arrayList, new Random());
        return arrayList;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in descending order.
     *
     * @param size represents the number of elements in the ArrayList
     * @return the ArrayList in descending order from size to 1
     */
    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = size; i >= 1; i--) {
            arrayList.add(i);
        }
        return arrayList;
    }
}
