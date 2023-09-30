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
    private static int threshold = 0;
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
        if (arrayList.size() <= 1) { // this value can change such that insertion sort is called at different list sizes
            insertionSort(arrayList);
            return;
        }
        // find the middle index of the current list
        int middle = arrayList.size() / 2;
        // create two new lists that will contain left and right side of the current list
        ArrayList<T> leftList = new ArrayList<T>();
        ArrayList<T> rightList = new ArrayList<T>();
        // iterate through the current list to split up the values
        for (int i = 0; i < arrayList.size(); i++) {
            // if our index is on the left side of the middle index
            if (i < middle) {
                leftList.set(i, arrayList.get(i));
            }
            // index is on right side of the middle index
            else {
                rightList.set(i, arrayList.get(i));
            }
        }
        // recursive call to split up left and right sub-lists into even smaller lists
        mergesort(leftList);
        mergesort(rightList);
        // combine the results of mergesort recursive calls
        merge(leftList, rightList, arrayList);
    }

    /**
     * Insertion sort method that is called when our merge sort reaches a certain threshold value.
     * @param arrayList
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arrayList) {
        // iterate through each value in the list
        for (int i = 1; i < arrayList.size(); i++) {
            // tracker value that moves backwards through our list to compare our current value with all previous in list
            for (int j = i; j > 0; j--) {
                // use comparable to compare our current value at i with value at j - 1
                // if our current item is "less than" our compared item, switch
                if (arrayList.get(i).compareTo(arrayList.get(j - 1)) < 0) {
                    T temp = arrayList.get(i);
                    arrayList.add(i, arrayList.get(j -1));
                    arrayList.add(j - 1, temp);
                }
                // our item is no longer less than the items that are on the left side of the list
                else {
                    break;
                }
            }
        }
    }

    /**
     * Private recursive method that contains the full implementation of merge sort.
     * @param leftList
     * @param rightList
     * @param arrayList
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> leftList, ArrayList<T> rightList, ArrayList<T> arrayList) {
        // get the sizes of the two lists that need to be sorted
        int leftSize = leftList.size();
        int rightSize = rightList.size();

        // indexing terms for our sub-array lists
        int l = 0;
        int r = 0;
        int i = 0;

        // add terms into the list based off written conditions
        while (l < leftSize && r < rightSize) {
            // if our item in the left list is "less than" our item in the right list
            if (leftList.get(l).compareTo(rightList.get(r)) < 0) {
                // change our original array list to now have the left list item at the current index
                arrayList.set(i, leftList.get(l));
                i++;
                l++;
            }
            // our item in the left list is "greater than" the item in the right list
            else {
                // change our original array list to now have the right list item at the current index
                arrayList.set(i, rightList.get(r));
                i++;
                r++;
            }
        }

        // check and add any remaining left elements
        while (l < leftSize) {
            arrayList.set(i, leftList.get(l));
            i++;
            l++;
        }

        // check and add any remaining right elements
        while (r < rightSize) {
            arrayList.set(i, rightList.get(r));
            i++;
            r++;
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
        // call helper methods to find the corresponding pivot chosen by the user
        int pivot = findPivot(arrayList);

    }

    /**
     * Helper method that is called by quicksort in order to find the corresponding pivot based off the input of a user.
     * @param arrayList
     * @param option
     * @return
     * @param <T>
     */
    private static <T extends Comparable<? super T>> int findPivot(ArrayList<T> arrayList) {
        int pivot;
        // use the median to find the pivot
        if (option == 1) {
            pivot = findMedian(arrayList);
        }
        // use the middle index of the arrayList as the pivot
        else if (option == 2) {
            pivot = (arrayList.size() -1) / 2;
        }
        // use the last value in the array list as the pivot
        else {
            pivot = arrayList.size() - 1;
        }
        return pivot;
    }

    /**
     * Helper method that finds the median value selected from the first, last, and middle index of the array list.
     * Called by the helper method findPivot().
     * @param arrayList
     * @return
     * @param <T>
     */
    private static <T extends Comparable<? super T>> int findMedian(ArrayList<T> arrayList) {
        T high = arrayList.get(arrayList.size()-1);
        T med = arrayList.get((arrayList.size() - 1) / 2);
        T low = arrayList.get(0);
        if (med.compareTo(high) < 0 && med.compareTo(low) > 0) {
            return (arrayList.size() - 1)/ 2;
        }
        else if (high.compareTo(med) < 0 && high.compareTo(low) > 0) {
            return arrayList.size() - 1;
        }
        else {
            return 0;
        }
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
