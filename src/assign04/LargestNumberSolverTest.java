package assign04;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LargestNumberSolverTest {

    @Test
    public void testInsertion() {
        Integer[] array = new Integer[]{5, 13, 1, 8, 29, 3};
        Integer[] orderedArray = new Integer[]{1, 3, 5, 8, 13, 29};
        Comparator<Integer> comparator = new OrderIntegers();
        LargestNumberSolver.insertionSort(array, comparator);
        assertArrayEquals(array, orderedArray);
    }

    @Test
    public void testInsertionZero(){
        Integer[] array = new Integer[] {};
        Integer[] orderedArray = new Integer[]{};
        Comparator<Integer> comparator = new OrderIntegers();
        LargestNumberSolver.insertionSort(array, comparator);
        assertArrayEquals(orderedArray, array);
    }

    @Test
    public void testFindBigIntegerNumber() {
        String value = "6325093421";
        Integer[] array = new Integer[]{1, 2, 34, 509, 632};
        BigInteger real = new BigInteger(value);
        BigInteger result = LargestNumberSolver.findLargestNumber(array);
        assertEquals(real, result);
    }

    // go back and write a test to see how it handles negative values
    @Test
    public void testFindBigIntegerNumberZero() {
        String value = "0";
        Integer[] array = new Integer[]{};
        BigInteger real = new BigInteger(value);
        BigInteger result = LargestNumberSolver.findLargestNumber(array);
        assertEquals(real, result);
    }

    @Test
    public void testFindIntNumber() {
        Integer[] array = new Integer[]{2, 147483646};
        int real = 2147483646;
        int result = LargestNumberSolver.findLargestInt(array);
        assertEquals(real, result);
    }

    @Test
    public void testFindIntNumberZero() {
        Integer[] array = new Integer[]{};
        int real = 0;
        int result = LargestNumberSolver.findLargestInt(array);
        assertEquals(real, result);
    }

    @Test
    public void testFindIntNumberError() {
        Integer[] array = new Integer[]{2, 147483648};
        assertThrows(OutOfRangeException.class, () -> {
            LargestNumberSolver.findLargestInt(array);
        });
    }

    // go back and find negative values

    @Test
    public void testFindLongNumber() {
        Integer[] array = new Integer[]{9, 22, 33, 72, 0, 36, 85, 47};
        Long real = 98_572_473_633_220L;
        Long result = LargestNumberSolver.findLargestLong(array);
        assertEquals(real, result);
    }

    @Test
    public void testFindLongNumberZero() {
        Integer[] array = new Integer[]{};
        Long real = 0L;
        Long result = LargestNumberSolver.findLargestLong(array);
        assertEquals(real, result);
    }
    @Test
    public void testFindLongNumberError() {
        Integer[] array = new Integer[] {9, 23, 45, 65, 54, 32, 45, 67, 32, 12, 45, 78, 89};
        assertThrows(OutOfRangeException.class, () -> {
            LargestNumberSolver.findLargestLong(array);
        });
    }

    // go back and find negative values


    @Test
    public void testSumBigInteger() {
        Integer[] array1 = new Integer[]{1, 2, 3, 4};
        Integer[] array2 = new Integer[]{9, 10, 8, 17};
        Integer[] array3 = new Integer[]{28, 19, 32, 64};
        List<Integer[]> integerList = new ArrayList<Integer[]>();
        integerList.add(array1);
        integerList.add(array2);
        integerList.add(array3);
        BigInteger result = LargestNumberSolver.sum(integerList);
        BigInteger real = BigInteger.valueOf(65308850);
        assertEquals(result, real);
    }

    @Test
    public void testSumSizeZero() {
        Integer[] array1 = new Integer[]{};
        Integer[] array2 = new Integer[]{};
        Integer[] array3 = new Integer[]{};
        List<Integer[]> integerList = new ArrayList<Integer[]>();
        integerList.add(array1);
        integerList.add(array2);
        integerList.add(array3);
        BigInteger result = LargestNumberSolver.sum(integerList);
        BigInteger real = BigInteger.valueOf(0);
        assertEquals(result, real);
    }

    @Test
    public void testSumNumbersAndZero() {
        Integer[] array1 = new Integer[]{1, 2, 3, 4};
        Integer[] array2 = new Integer[]{};
        Integer[] array3 = new Integer[]{28, 19, 32, 64};
        List<Integer[]> integerList = new ArrayList<Integer[]>();
        integerList.add(array1);
        integerList.add(array2);
        integerList.add(array3);
        BigInteger result = LargestNumberSolver.sum(integerList);
        BigInteger real = BigInteger.valueOf(64327140);
        assertEquals(result, real);
    }

    // find negative sums

    @Test
    public void testFind0KthArray() {
        Integer[] array1 = new Integer[]{1, 2, 3, 4};
        Integer[] array2 = new Integer[]{3, 2, 6, 8, 8};
        Integer[] array3 = new Integer[]{28, 19, 32, 9};
        List<Integer[]> integerList = new ArrayList<Integer[]>();
        integerList.add(array1);
        integerList.add(array2);
        integerList.add(array3);
        Integer[] result = LargestNumberSolver.findKthLargest(integerList, 0);
        assertArrayEquals(result, array3);
    }
    @Test
    public void testFind1KthArray() {
        Integer[] array1 = new Integer[]{1, 2, 3, 4};
        Integer[] array2 = new Integer[]{3, 2, 6, 8, 8};
        Integer[] array3 = new Integer[]{28, 19, 32, 9};
        List<Integer[]> integerList = new ArrayList<Integer[]>();
        integerList.add(array1);
        integerList.add(array2);
        integerList.add(array3);
        Integer[] result = LargestNumberSolver.findKthLargest(integerList, 1);
        assertArrayEquals(result, array2);
    }

    @Test
    public void testFind2KthArray() {
        Integer[] array1 = new Integer[]{1, 2, 3, 4};
        Integer[] array2 = new Integer[]{3, 2, 6, 8, 8};
        Integer[] array3 = new Integer[]{28, 19, 32, 9};
        List<Integer[]> integerList = new ArrayList<Integer[]>();
        integerList.add(array1);
        integerList.add(array2);
        integerList.add(array3);
        Integer[] result = LargestNumberSolver.findKthLargest(integerList, 2);
        assertArrayEquals(result, array1);
    }

    // write a test for zero array case

    @Test
    public void testFile() {
        List<Integer[]> resultList = LargestNumberSolver.readFile("src/assign04/testFile.txt");
        Integer[] array1 = new Integer[]{12, 54, 78, 89};
        Integer[] array2 = new Integer[]{14, 78, 32, 43, 78, 98, 0, 9, 7, 6, 5, 3};
        Integer[] array3 = new Integer[]{12, 43, 89, 85, 32, 43, 11};
        List<Integer[]> realList = new ArrayList<Integer[]>();
        realList.add(array1);
        realList.add(array2);
        realList.add(array3);
        assertArrayEquals(resultList.get(0), realList.get(0));
        assertArrayEquals(resultList.get(1), realList.get(1));
        assertArrayEquals(resultList.get(2), realList.get(2));
    }

    // Test Empty file to throw exception
    @Test
    public void testEmptyFile() {
       List<Integer[]> resultList = new ArrayList<Integer[]>();
       List<Integer[]> realList = LargestNumberSolver.readFile("src/assign04/emptyFile.txt");
       assertTrue(resultList.equals(realList));
    }
}
