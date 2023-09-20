package assign04;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This Java class represents a NumberSolver that has built in methods to find various properties of a given array.
 *
 * @author Reynaldo Villarreal and Mikhail Ahmed
 * @version 2023-09-19
 */
public class LargestNumberSolver<T> {

    /**
     * This generic method sorts the input array using an insertion sort and the input Comparator object.
     *
     * @param arr The array being passed in
     * @param cmp The comparator object being passed in
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        // iterate through all the values of the passed in array
        for (int i = 1; i < arr.length; i++) {
            // index out the current value (second value in the arr for first iteration)
            T current = arr[i];
            // set j equal to the current i value minus 1
            int j = i - 1;
            // sort while the j value is greater than or equal to 0 (hasn't reached left side of array)
            // and also while the comparison between our current value and the one to the left of it is < 0
            // (which means that our current value is smaller than arr[j]
            while (j >= 0 && cmp.compare(current, arr[j]) < 0) {
                // shift the object at j up by one in the array
                arr[j + 1] = arr[j];
                // decrement the j value
                j--;
            }
            // place our current value into the correct index in the array
            arr[j + 1] = current;
        }
    }

    /**
     * This method returns the largest number that can be formed by arranging the integers of the given array, in any order.
     * If the array is empty, the largest number that can be formed is 0.  This method must not alter the given array and
     * must call your insertionSort method with a Comparator or lambda expression that you design.
     *
     * @param arr - (Integer[]) Array being passed in
     * @return biggestVal - (BigInteger) Number that represents the biggest possible combination when all the values
     *                      of the array are concatenated.
     */
    public static BigInteger findLargestNumber (Integer[] arr) {
        // create a copy of our array and sort it such that we don't alter the original array
        Integer[] arrCopy = arr.clone();
        // sort the array such that when concatenated, forms the biggest number possible
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestBigIntegerSorter);
        // instantiate holder variable
        BigInteger biggestVal;
        // if the array passed in has no terms in it
        if (arr.length == 0) {
            // our biggest possible (BigInteger) value is 0
            biggestVal = BigInteger.valueOf(0);
        }
        // if the array does have terms within it
        else {
            // create a (StringBuilder) holder variable that will hold our biggest combination
            StringBuilder bigNumber = new StringBuilder();
            // iterate through all the values and append it to our (StringBuilder) bigNumber
            for (int i = 0; i < arrCopy.length; i++) {
                bigNumber.append(arrCopy[i]);
            }
            // convert our (StringBuilder) bigNumber variable to a (String) variable BigNumber
            String BigNumber = bigNumber.toString();
            // convert our (String) BigNumber variable to a (BigInteger) variable biggestVal
            biggestVal = new BigInteger(BigNumber);
        }
        // return our biggest value combination possible
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
     * @param arr - (Integer[]) Array being passed in
     * @return realVal - (int) variable that holds the largest (int) combination possible
     * @throws OutOfRangeException if the largest (int) value possible is greater than the (int) limit
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        // create a copy of our array and sort it such that we don't alter the original array
        Integer[] arrCopy = arr.clone();
        // sort the array such that when concatenated, forms the biggest number possible
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestBigIntegerSorter);
        // instantiate (BigInteger) holder variable
        BigInteger biggestVal;
        // instantiate the upper limit that a (int) variable can be
        BigInteger limit = new BigInteger("2147483647");
        // instantiate (int) holder variable
        int realVal = 0;
        // if the (Integer[]) array is empty
        if (arr.length == 0) {
            biggestVal = BigInteger.valueOf(0);
        }
        else {
            // create a (StringBuilder) bigNumber variable that will hold the largest possible combination
            StringBuilder bigNumber = new StringBuilder();
            // append all the values to the (StringBuilder) bigNumber variable
            for (int i = 0; i < arrCopy.length; i++) {
                bigNumber.append(arrCopy[i]);
            }
            // convert the (StringBuilder) bigNumber to (String) BigNumber
            String BigNumber = bigNumber.toString();
            biggestVal = new BigInteger(BigNumber);
        }
        // if our (BigInteger) biggestVal is greater than the (int) limit
        if (biggestVal.compareTo(limit) > 0) {
            // throw a new error
            throw new OutOfRangeException("The value is out of range to be represented by an int.");
        }
        // if our (BigInteger) biggestVal is less than the (int) limit
        else {
            // convert our (BigInteger) biggestVal to (int) realVal
            realVal = biggestVal.intValue();
        }
        // return (int) realVal
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
     * @param arr - (Integer[]) Array being passed in
     * @return realVal - (long) variable that holds the largest (long) combination possible
     * @throws OutOfRangeException if the largest (long) value possible is greater than the (long) limit
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        // create a copy of our array and sort it such that we don't alter the original array
        Integer[] arrCopy = arr.clone();
        // sort the array such that when concatenated, forms the biggest number possible
        LargestNumberSolver.insertionSort(arrCopy, LargestNumberSolver::findLargestBigIntegerSorter);
        // instantiate (BigInteger) holder variable
        BigInteger biggestVal;
        // instantiate the upper limit that a (long) variable can be
        BigInteger limit = new BigInteger("9223372036854775807");
        // instantiate (Long) holder variable
        Long realVal = 0L;
        // if the (Integer[]) array is empty
        if (arr.length == 0) {
            biggestVal = BigInteger.valueOf(0L);
        }
        else {
            // create a (StringBuilder) bigNumber variable that will hold the largest possible combination
            StringBuilder bigNumber = new StringBuilder();
            // append all the values to the (StringBuilder) bigNumber variable
            for (int i = 0; i < arrCopy.length; i++) {
                bigNumber.append(arrCopy[i]);
            }
            // convert the (StringBuilder) bigNumber to (String) BigNumber
            String BigNumber = bigNumber.toString();
            biggestVal = new BigInteger(BigNumber);
        }
        // if our (BigInteger) biggestVal is greater than the (Long) limit
        if (biggestVal.compareTo(limit) > 0) {
            // throw a new error
            throw new OutOfRangeException("The value is out of range to be represented by a long.");
        }
        // if our (BigInteger) biggestVal is less than the (int) limit
        else {
            // convert our (BigInteger) biggestVal to (Long) realVal
            realVal = biggestVal.longValue();
        }
        // return (int) realVal
        return realVal;
    }

    /**
     * This method sums the largest numbers that can be formed by each array in the given list.
     * This method must not alter the given list.
     * @param list - (List<Integer[]>) list that holds arrays
     * @return sum - (BigInteger) sum that represents the sum of the biggest terms of each array
     */
    public static BigInteger sum(List<Integer[]> list) {
        // instantiate sum to equal 0
        BigInteger sum = BigInteger.valueOf(0);
        // iterate through the list
        for (int i = 0; i < list.size(); i++) {
            // index out the current array from the list
            Integer[] currentArray = list.get(i);
            // find the biggest value that can be formed from the current array
            BigInteger value = LargestNumberSolver.findLargestNumber(currentArray);
            // add the (BigInteger) value to the sum variable
            sum = sum.add(value);
        }
        // return the (BigInteger) sum value
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
     * @param list - (List<Integer[]>) List that contains (Integer[]) arrays
     * @param k - (int) kth value that represents which array is wanted
     * @return holderArray - (Integer[]) array that represents the kth array in the ordered array of arrays
     * @throws IllegalArgumentException
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        // declare an array of arrays that will hold all contents of the list
        Integer[][] holderArray = new Integer[list.size()][];
        // iterate through the list
        for (int i = 0; i < list.size(); i++) {
            // place the item in the list into the holder array
            holderArray[i] = list.get(i);
        }
        // sort our array of arrays using our pre-defined ordering for arrays in order to order it from greatest to least
        LargestNumberSolver.insertionSort(holderArray, new OrderArray());//
        // return the array positioned at k which is the kth largest value
        return holderArray[k];

        // Code used to find the run time of Java.sort()
        // list.sort(new OrderArray());
        // return list.get(k);
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
        // try to create a new arrayReader from the passed in filename
        try {
            // create a new (BufferedReader) arrayReader
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
        // if the filename is not valid or cannot be used
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
        }
        else if (bigNumber1.compareTo(bigNumber2) > 0) { // if number 2 is less than number 1
            return -1;
        }
        else { // if they're the same
            return 0;
        }
    }
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

