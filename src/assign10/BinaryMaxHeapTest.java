package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The BinaryMaxHeapTest class is a class used to test all methods in BinaryMaxHeap using both Java's Comparable and a
 * custom Comparator.
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 11-28-23
 */
class BinaryMaxHeapTest {
private BinaryMaxHeap<Integer> BMH;
private List<Integer> integerList;
private BinaryMaxHeap<Integer> BMHEmpty;
    @BeforeEach
    void setUp() {
        integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(7);
        integerList.add(56);
        integerList.add(9);
        integerList.add(65);
        integerList.add(10);

        BMH = new BinaryMaxHeap<>(integerList);

        BMHEmpty = new BinaryMaxHeap<>();
    }

    @Test
    void add() {
        BMH.add(70);
        int real = 70;
        assertEquals(real, BMH.extractMax());
        BMH.add(70);
        Integer[] realArray = {70, 65, 10, 56, 9, 7, 1, 2};
        assertArrayEquals(realArray, BMH.toArray());
    }

    @Test
    void peek() {
        BMH.add(70);
        int real = 70;
        assertEquals(real, BMH.peek());
    }

    @Test
    void peekError() {
        assertThrows(NoSuchElementException.class, () -> {
            BMHEmpty.peek();
        });
    }
    @Test
    void extractMax() {
        BMH.add(70);
        assertEquals(70, BMH.extractMax());
    }

    @Test
    void extractMaxError() {
        assertThrows(NoSuchElementException.class, () -> {
            BMHEmpty.extractMax();
        });
    }
    @Test
    void size() {
        int real = 7;
        assertEquals(real, BMH.size());
    }

    @Test
    void sizeEmpty() {
        int real = 0;
        assertEquals(real, BMHEmpty.size());
    }
    @Test
    void isEmptyFalse() {
        assertFalse(BMH.isEmpty());
    }

    @Test
    void isEmptyTrue() {
        assertTrue(BMHEmpty.isEmpty());
    }
    @Test
    void clear() {
        BMH.clear();
        assertTrue(BMH.isEmpty());
        assertEquals(0, BMH.size());
    }

    @Test
    void toArray() {
        BMH.add(70);
        Integer[] real = {70, 65, 10, 56, 9, 7, 1, 2};
        assertArrayEquals(real, BMH.toArray());
    }

    @Test
    void toArrayEmpty() {
        Integer[] real = {};
        assertArrayEquals(real, BMHEmpty.toArray());
    }

    // tests using comparator
    @Test
    void addWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(integerList, reverseComparator);

        customHeap.add(-1);
        int real = -1;
        assertEquals(real, customHeap.extractMax());

        customHeap.add(-1);
        Integer[] realArray = {-1, 1, 7, 2, 9, 65, 10, 56};  // Reversed order
        assertArrayEquals(realArray, customHeap.toArray());
    }
    @Test
    void peekWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(integerList, reverseComparator);

        customHeap.add(-1);
        int real = -1;
        assertEquals(real, customHeap.peek());
    }

    @Test
    void peekErrorWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(reverseComparator);
        assertThrows(NoSuchElementException.class, () -> {
            customHeap.peek();
        });
    }
    @Test
    void extractMaxWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(integerList, reverseComparator);
        customHeap.add(-1);
        assertEquals(-1, customHeap.extractMax());
    }

    @Test
    void extractMaxErrorWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(reverseComparator);
        assertThrows(NoSuchElementException.class, () -> {
            customHeap.extractMax();
        });
    }
    @Test
    void sizeWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(integerList, reverseComparator);
        int real = 7;
        assertEquals(real, customHeap.size());
    }

    @Test
    void sizeEmptyWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(reverseComparator);
        int real = 0;
        assertEquals(real, customHeap.size());
    }
    @Test
    void isEmptyFalseWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(integerList, reverseComparator);
        assertFalse(customHeap.isEmpty());
    }

    @Test
    void isEmptyTrueWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(reverseComparator);
        assertTrue(customHeap.isEmpty());
    }
    @Test
    void clearWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(integerList, reverseComparator);
        customHeap.clear();
        assertTrue(customHeap.isEmpty());
        assertEquals(0, customHeap.size());
    }

    @Test
    void toArrayWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(integerList, reverseComparator);
        customHeap.add(-1);
        Integer[] realArray = {-1, 1, 7, 2, 9, 65, 10, 56};  // Reversed order
        assertArrayEquals(realArray, customHeap.toArray());
    }

    @Test
    void toArrayEmptyWithCustomComparator() {
        Comparator<Integer> reverseComparator = new ReverseComparator();
        BinaryMaxHeap<Integer> customHeap = new BinaryMaxHeap<>(reverseComparator);
        Integer[] real = {};
        assertArrayEquals(real, customHeap.toArray());
    }

    // findKLargestTests
    @Test
    void findKLargestHeap() {
        Integer[] real = {65, 56, 10, 9};
        List<Integer> result = FindKLargest.findKLargestHeap(integerList, 4);
        assertArrayEquals(real, result.toArray());
    }
    @Test
    void findKLargestHeapError() {
        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestHeap(integerList, 8);
        });
    }
    @Test
    void findKLargestHeapComparator() {
        Integer[] real = {1, 2, 7, 9};
        List<Integer> result = FindKLargest.findKLargestHeap(integerList, 4, new ReverseComparator());
        assertArrayEquals(real, result.toArray());
    }
    @Test
    void findKLargestHeapErrorComparator() {
        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestHeap(integerList, 8, new ReverseComparator());
        });
    }
    @Test
    void findKLargestSort() {
        Integer[] real = {65, 56, 10, 9};
        List<Integer> result = FindKLargest.findKLargestSort(integerList, 4);
        assertArrayEquals(real, result.toArray());
    }
    @Test
    void findKLargestSortError() {
        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestSort(integerList, 8);
        });
    }
    @Test
    void findKLargestSortComparator() {
        Integer[] real = {1, 2, 7, 9};
        List<Integer> result = FindKLargest.findKLargestSort(integerList, 4, new ReverseComparator());
        assertArrayEquals(real, result.toArray());
    }
    @Test
    void findKLargestSortErrorComparator() {
        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestSort(integerList, 8, new ReverseComparator());
        });
    }
}
class ReverseComparator implements Comparator<Integer> {
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
            result = 1;
        else if ((object1 - object2) == 0) {
            result = 0;
        }
        else
            result = -1;
        return result;
    }
}