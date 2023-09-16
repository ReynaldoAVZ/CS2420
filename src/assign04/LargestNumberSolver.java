package assign04;


import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

/**
 * This Java class represents an
 *
 * @author Reynaldo Villarreal and Mikhail Ahmed
 * @version 2023-09-18
 */
public class LargestNumberSolver<T> {

    /**
     * This generic method sorts the input array using an insertion sort and the input Comparator object.
     *
     * @param arr The array being passed in
     * @param cmp The comparator object being passed in
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int j = i - 1;

            while (j >= 0 && cmp.compare(current, arr[j]) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = current;
        }
    }

    /**
     * This method returns the largest number that can be formed by arranging the integers of the given array, in any order.
     * If the array is empty, the largest number that can be formed is 0.  This method must not alter the given array and
     * must call your insertionSort method with a Comparator or lambda expression that you design.
     *
     * @param arr The array being passed in
     * @return
     */
    public static BigInteger findLargestNumber (Integer[] arr) {
        Integer[] arrCopy = arr.clone();
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestBigIntegerSorter);
        BigInteger biggestVal;
        if (arr.length == 0) {
            biggestVal = BigInteger.valueOf(0);
        } else {
            StringBuilder bigNumber = new StringBuilder();
            for (int i = 0; i < arrCopy.length; i++) {
                bigNumber.append(arrCopy[i]);
            }
            String BigNumber = bigNumber.toString();
            biggestVal = new BigInteger(BigNumber);
        }
        return biggestVal;
    }

    /**
     * This method returns the largest int value that can be formed
     * by arranging the integers of the given array, in any order.
     * An OutOfRangeException (Download OutOfRangeException) is thrown
     * if the largest number that can be formed is too large for the int data type.
     * Logic for solving the problem of determining the largest number should not appear again in this method — call an existing public method or a helper method.
     * This method must not alter the given array.
     *
     * @param arr The array being passed in
     * @return the largest long value
     * @throws OutOfRangeException
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        Integer[] arrCopy = arr.clone();
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestIntSorter);
        BigInteger biggestVal = null;
        BigInteger limit = new BigInteger("2147483647");
        int realVal = 0;
        if (arr.length == 0) {
        }
        else {
            StringBuilder bigNumber = new StringBuilder();
            for (int i = 0; i < arrCopy.length; i++) {
                bigNumber.append(arrCopy[i]);
            }
            String BigNumber = bigNumber.toString();
            biggestVal = new BigInteger(BigNumber);
        }
        if (biggestVal.compareTo(limit) > 0) {
            throw new OutOfRangeException("The value is out of range to be represented by an int.");
        }
        else {
            realVal = biggestVal.intValue();
        }
        return realVal;
    }


    /**
     * This method returns the largest long value that can be formed
     * by arranging the longs of the given array, in any order.
     * An OutOfRangeException (Download OutOfRangeException) is thrown
     * if the largest number that can be formed is too large for the long data type.
     * Logic for solving the problem of determining the largest number should not appear again in this method — call an existing public method or a helper method.
     * This method must not alter the given array.
     *
     * @param arr The array being passed in
     * @return the largest long value
     * @throws OutOfRangeException
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        Integer[] arrCopy = arr.clone();
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestLongSorter);
        BigInteger biggestVal = null;
        BigInteger limit = new BigInteger("9223372036854775807");
        Long realVal = 0L;
        if (arr.length == 0) {
        }
        else {
            StringBuilder bigNumber = new StringBuilder();
            for (int i = 0; i < arrCopy.length; i++) {
                bigNumber.append(arrCopy[i]);
            }
            String BigNumber = bigNumber.toString();
            biggestVal = new BigInteger(BigNumber);
        }
        if (biggestVal.compareTo(limit) > 0) {
            throw new OutOfRangeException("The value is out of range to be represented by a long.");
        }
        else {
            realVal = biggestVal.longValue();
        }
        return realVal;
    }

    /**
     * This method behaves the same as the previous method, but for data type long instead of data type int.
     *
     * @param list
     * @return
     */
    public static BigInteger sum(List<Integer[]> list) {
        return null;
    }

    /**
     * This method determines the kth largest number that can be formed by each array in the given list.
     * <p>
     * E.g., if k=0 returns the largest overall, if k=list.size()-1 returns the smallest overall.
     * <p>
     * This method returns the original array that represents the kth largest number, not the kth largest number itself.
     * An IllegalArgumentExceptionLinks to an external site is thrown if k is not a valid position in the list.
     * This method must not alter the given list and must call your insertionSort method with a Comparator or
     * lambda expression that you design.
     *
     * @param list
     * @param k
     * @return
     * @throws IllegalArgumentException
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        return null;
    }

    /**
     * This method generates list of integer arrays from an input file, such that each line corresponds to one array of
     * integers separated by blank spaces, and returns an empty list if the file does not exist.
     *
     * @param filename - The name of the file whose data we're accessing
     * @return - A list of the integer arrays from the filename
     */
    public static List<Integer[]> readFile(String filename) {
        return null;
    }

    /**
     *
     */
    public static int findLargestBigIntegerSorter(Integer o1, Integer o2) {
        // Declare all the objects that will hold values
        StringBuilder object1 = new StringBuilder();
        object1.append(o1);
        StringBuilder object2 = new StringBuilder();
        object2.append(o2);
        StringBuilder bigNumber1 = new StringBuilder();
        StringBuilder bigNumber2 = new StringBuilder();

        // build two big numbers

        // build big number 1 (XY)
        bigNumber1.append(object1);
        bigNumber1.append(object2);

        // build big number 2 (YX)
        bigNumber2.append(object2);
        bigNumber2.append(object1);

        // compare big number 1 & 2

        if (bigNumber1.compareTo(bigNumber2) < 0) { // if number 1 is less than number 2
            return 1;
        } else if (bigNumber1.compareTo(bigNumber2) > 0) { // if number 2 is less than number 1
            return -1;
        } else { // if they're the same
            return 0;
        }
    }

    public static int findLargestIntSorter(Integer o1, Integer o2) {
        // Declare all the objects that will hold values
        StringBuilder object1 = new StringBuilder();
        object1.append(o1);
        StringBuilder object2 = new StringBuilder();
        object2.append(o2);
        StringBuilder bigNumber1 = new StringBuilder();
        StringBuilder bigNumber2 = new StringBuilder();

        // build two big numbers

        // build big number 1 (XY)
        bigNumber1.append(object1);
        bigNumber1.append(object2);

        // build big number 2 (YX)
        bigNumber2.append(object2);
        bigNumber2.append(object1);

        // compare big number 1 & 2

        if (bigNumber1.compareTo(bigNumber2) < 0) { // if number 1 is less than number 2
            return 1;
        } else if (bigNumber1.compareTo(bigNumber2) > 0) { // if number 2 is less than number 1
            return -1;
        } else { // if they're the same
            return 0;
        }
    }


    public static int findLargestLongSorter(Integer o1, Integer o2) {
        // Declare all the objects that will hold values
        StringBuilder object1 = new StringBuilder();
        object1.append(o1);
        StringBuilder object2 = new StringBuilder();
        object2.append(o2);
        StringBuilder bigNumber1 = new StringBuilder();
        StringBuilder bigNumber2 = new StringBuilder();

        // build two big numbers

        // build big number 1 (XY)
        bigNumber1.append(object1);
        bigNumber1.append(object2);

        // build big number 2 (YX)
        bigNumber2.append(object2);
        bigNumber2.append(object1);

        // compare big number 1 & 2

        if (bigNumber1.compareTo(bigNumber2) < 0) { // if number 1 is less than number 2
            return 1;
        } else if (bigNumber1.compareTo(bigNumber2) > 0) { // if number 2 is less than number 1
            return -1;
        } else { // if they're the same
            return 0;
        }
    }
}


