package assign05;

import java.util.ArrayList;
import java.util.Collection;
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
 * @version 2023-10-00
 */
public class ArrayListSorter {
    private static final int INSERTION_SORT_THRESHOLD = 2;
    private static final int PIVOT_SELECTION_SORT = 10;
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
        int size = arrayList.size();
        ArrayList<T> storageList = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            storageList.add(null);
        }
        mergesort(arrayList, 0, arrayList.size() - 1, storageList);
    }

    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arrayList, int left, int right, ArrayList<T> storageList) {
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arrayList, left, right);
            return;
        }
        if (left < right) {
            int middle = (left + right) / 2;
            mergesort(arrayList, left, middle, storageList);
            mergesort(arrayList, middle + 1, right, storageList);
            merge(arrayList, left, middle, right, storageList);
        }
    }

    /**
     *
     * @param arrayList
     * @param left
     * @param right
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arrayList, int left, int right) {
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
     *
     * @param arrayList
     * @param left
     * @param middle
     * @param right
     * @param storageList
     * @param <T>
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
        if (end - start + 1 <= INSERTION_SORT_THRESHOLD) {
            // Use insertion sort for small subarrays
            insertionSort(arrayList, start, end);
        }
        else {
            int pivotIndex = choosePivot(arrayList, start, end);
            int partitionIndex = partition(arrayList, start, end, pivotIndex);
            quicksort(arrayList, start, partitionIndex - 1);
            quicksort(arrayList, partitionIndex + 1, end);
        }
    }
    // Choose the pivot based on the selected strategy

    /**
     *
     * @param arrayList
     * @param start
     * @param end
     * @return
     * @param <T>
     */
    private static <T extends Comparable<? super T>> int choosePivot(ArrayList<T> arrayList, int start, int end) {
        // You can choose one of the following pivot selection strategies:
        // 1. Random pivot
        // 2. Median-of-three pivot
        // 3. Last element as pivot

        // using random pivot strategy:
        if (PIVOT_SELECTION_SORT == 1) {
            return start + new Random().nextInt(end - start + 1);
        }

        // Example using median-of-three pivot strategy:
        else if (PIVOT_SELECTION_SORT == 2) {
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
     *
     * @param arrayList
     * @param start
     * @param end
     * @param pivotIndex
     * @return
     * @param <T>
     */
    private static <T extends Comparable<? super T>> int partition(ArrayList<T> arrayList, int start, int end, int pivotIndex) {
        T pivotValue = arrayList.get(pivotIndex);
        T temp = arrayList.get(pivotIndex);
        arrayList.set(pivotIndex, arrayList.get(end));
        arrayList.set(end, temp);
        int storeIndex = start;
        for (int i = start; i < end; i++) {
            if (arrayList.get(i).compareTo(pivotValue) < 0) {
                temp = arrayList.get(i);
                arrayList.set(i, arrayList.get(storeIndex));
                arrayList.set(storeIndex, temp);
                storeIndex++;
            }
        }
        temp = arrayList.get(storeIndex);
        arrayList.set(storeIndex, arrayList.get(end));
        arrayList.set(end, temp);
        return storeIndex;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in ascending order.
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
     * @param size
     * @return
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
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = size; i >= 1; i--) {
            arrayList.add(i);
        }
        return arrayList;
    }
}
