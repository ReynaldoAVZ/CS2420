package assign04;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class Order implements Comparator<Integer> {
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

public class LargestNumberSolverTest {

    @Test
    public void testInsertion() {
        Integer[] array = new Integer[]{5, 13, 1, 8, 29, 3};
        Integer[] orderedArray = new Integer[]{1, 3, 5, 8, 13, 29};
        Comparator<Integer> comparator = new Order();
        LargestNumberSolver.insertionSort(array, comparator);
        assertArrayEquals(array, orderedArray);
    }

    @Test
    public void testFindLargestNumber() {
        String value = "6325093421";
        Integer[] array = new Integer[]{1, 2, 34, 509, 632};
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
    public void testFindIntNumberError() {
        Integer[] array = new Integer[]{2, 147483648};
        assertThrows(OutOfRangeException.class, () -> {
            LargestNumberSolver.findLargestInt(array);
        });
    }

    @Test
    public void testFindLongNumber() {
        Integer[] array = new Integer[]{9, 22, 33, 72, 0, 36, 85, 47};
        Long real = 98_572_473_633_220L;
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
}
