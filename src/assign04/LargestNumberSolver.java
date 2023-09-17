package assign04;

import java.io.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
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
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestBigIntegerSorter);
        BigInteger biggestVal;
        BigInteger limit = new BigInteger("2147483647");
        int realVal = 0;
        if (arr.length == 0) {
            biggestVal = BigInteger.valueOf(0);
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
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestBigIntegerSorter);
        BigInteger biggestVal;
        BigInteger limit = new BigInteger("9223372036854775807");
        Long realVal = 0L;
        if (arr.length == 0) {
            biggestVal = BigInteger.valueOf(0L);
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
     * This method sums the largest numbers that can be formed by each array in the given list.
     * This method must not alter the given list.
     * @param list - A list that holds arrays
     * @return sum - A value that represents the sum of the biggest terms of each array
     */
    public static BigInteger sum(List<Integer[]> list) {
        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 0; i < list.size(); i++) {
            Integer[] currentArray = list.get(i);
            BigInteger value = LargestNumberSolver.findLargestNumber(currentArray);
            sum = sum.add(value);
        }
        return sum;
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
        Integer[][] holderArray = new Integer[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            holderArray[i] = list.get(i);
        }
        LargestNumberSolver.insertionSort(holderArray, new OrderArray());
        return holderArray[k];
    }

    /**
     * This method generates list of integer arrays from an input file, such that each line corresponds to one array of
     * integers separated by blank spaces, and returns an empty list if the file does not exist.
     *
     * @param filename - The name of the file whose data we're accessing
     * @return - A list of the integer arrays from the filename
     */
    public static List<Integer[]> readFile(String filename) {
        // initialize our List that will be returned
        List<Integer[]> intList = new ArrayList<Integer[]>();

        try {
            BufferedReader arrayReader = new BufferedReader(new FileReader(filename));
            String currentLine;
            // while there are still lines that can be read in the file
            while ((currentLine = arrayReader.readLine()) != null) {
                // read the current line
                String[] token = currentLine.split("\\s+");
                Integer[] intArray = new Integer[token.length];
                for(int i = 0; i < intArray.length; i++) {
                    intArray[i] = Integer.valueOf(token[i]);
                }
                intList.add(intArray);
            }
        }
        catch (IOException ignored) {
        }
        return intList;
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

//    public static int findLargestIntSorter(Integer o1, Integer o2) {
//        // Declare all the objects that will hold values
//        StringBuilder object1 = new StringBuilder();
//        object1.append(o1);
//        StringBuilder object2 = new StringBuilder();
//        object2.append(o2);
//        StringBuilder bigNumber1 = new StringBuilder();
//        StringBuilder bigNumber2 = new StringBuilder();
//
//        // build two big numbers
//
//        // build big number 1 (XY)
//        bigNumber1.append(object1);
//        bigNumber1.append(object2);
//
//        // build big number 2 (YX)
//        bigNumber2.append(object2);
//        bigNumber2.append(object1);
//
//        // compare big number 1 & 2
//
//        if (bigNumber1.compareTo(bigNumber2) < 0) { // if number 1 is less than number 2
//            return 1;
//        } else if (bigNumber1.compareTo(bigNumber2) > 0) { // if number 2 is less than number 1
//            return -1;
//        } else { // if they're the same
//            return 0;
//        }
//    }


//    public static int findLargestLongSorter(Integer o1, Integer o2) {
//        // Declare all the objects that will hold values
//        StringBuilder object1 = new StringBuilder();
//        object1.append(o1);
//        StringBuilder object2 = new StringBuilder();
//        object2.append(o2);
//        StringBuilder bigNumber1 = new StringBuilder();
//        StringBuilder bigNumber2 = new StringBuilder();
//
//        // build two big numbers
//
//        // build big number 1 (XY)
//        bigNumber1.append(object1);
//        bigNumber1.append(object2);
//
//        // build big number 2 (YX)
//        bigNumber2.append(object2);
//        bigNumber2.append(object1);
//
//        // compare big number 1 & 2
//
//        if (bigNumber1.compareTo(bigNumber2) < 0) { // if number 1 is less than number 2
//            return 1;
//        } else if (bigNumber1.compareTo(bigNumber2) > 0) { // if number 2 is less than number 1
//            return -1;
//        } else { // if they're the same
//            return 0;
//        }
//    }
}
class OrderIntegers implements Comparator<Integer> {
    /**
     * This compare sorts Integers in reverse order, such that the greatest value is placed in the back and the
     * smallest value is in the front. This is a reverse ordering to the natural ordering of comparable in java.
     *
     * @param object1 - the first object to be compared.
     * @param object2 - the second object to be compared.
     * @return result - (int) result variable that determines the ordering within a SimplePriorityQueue<Integer>
     */
    public int compare(Integer object1, Integer object2) {
        int result;
        if ((object1 - object2) < 0)
            result = -1;
        else if ((object1 - object2) == 0) {
            result = 0;
        }
        else
            result = 1;
        return result;
    }
}
class OrderArray implements Comparator<Integer[]> {
    /**
     * This compare sorts Integer Arrays in order, such that the greatest value is placed in the back and the
     * smallest value is in the front. This is a reverse ordering to the natural ordering of comparable in java.
     *
     * @param object1 - the first object to be compared.
     * @param object2 - the second object to be compared.
     * @return result - (int) result variable that determines the ordering within a SimplePriorityQueue<Integer>
     */
    public int compare(Integer[] object1, Integer[] object2) {
        int result;
        if ((LargestNumberSolver.findLargestInt(object1) - LargestNumberSolver.findLargestInt(object2)) < 0) {
            result = 1;
        }
        else if ((LargestNumberSolver.findLargestInt(object1) - LargestNumberSolver.findLargestInt(object2)) == 0) {
            result = 0;
        }
        else
            result = -1;
        return result;
    }
}

