package assign06;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents a JUnit testing class for the SinglyLinkedList and all the corresponding methods.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-19
 */
class SinglyLinkedListTest {
    private SinglyLinkedList<Integer> intSLLReal;
    private SinglyLinkedList<Integer> intSLL;
    private Iterator<Integer> iterReal;
    private Integer[] intSLLRealArray;

    @BeforeEach
    void setUp() {
        intSLLReal = new SinglyLinkedList<>();
        intSLLReal.insertFirst(1);
        intSLLReal.insert(1, 2);
        intSLLReal.insert(2, 3);
        intSLLReal.insert(3, 4);
        intSLLReal.insert(4, 5);

        intSLLRealArray = new Integer[]{1, 2, 3, 4, 5};

        intSLL = new SinglyLinkedList<>();

        iterReal = intSLLReal.iterator();


    }

    @Test
    void insertFirst() {
        intSLL.insertFirst(5);
        intSLL.insertFirst(4);
        intSLL.insertFirst(3);
        intSLL.insertFirst(2);
        intSLL.insertFirst(1);
        Object[] result = intSLL.toArray();
        assertArrayEquals(result, intSLLRealArray);
    }

    @Test
    void insert() {
        intSLL.insertFirst(1);
        intSLL.insert(1, 2);
        intSLL.insert(2, 3);
        intSLL.insert(3, 4);
        intSLL.insert(4, 5);
        Object[] result = intSLL.toArray();
        assertArrayEquals(result, intSLLRealArray);
    }

    @Test
    void insertError() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intSLLReal.insert(20, 20);
        });
    }

    @Test
    void getFirst() {
        intSLL.insertFirst(34);
        intSLL.insert(1, 2);
        intSLL.insert(2, 3);
        intSLL.insert(3, 4);
        intSLL.insert(4, 5);
        intSLL.insert(5, 6);
        intSLL.insert(6, 7);
        intSLL.insertFirst(25);
        assertEquals(25, (int) intSLL.getFirst());
    }

    @Test
    void getFirstError() {
        assertThrows(NoSuchElementException.class, () -> {
            intSLL.getFirst();
        });
    }

    @Test
    void getInt() {
        int value = intSLLReal.get(4);
        assertEquals(5, value);
    }

    @Test
    void getIntError() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intSLLReal.get(20);
        });
    }

    @Test
    void deleteFirst() {
        int val = intSLLReal.deleteFirst();
        assertEquals(val, 1);
    }

    @Test
    void deleteFirstError() {
        assertThrows(NoSuchElementException.class, () -> {
            intSLL.deleteFirst();
        });
    }

    @Test
    void delete() {
        int val = intSLLReal.delete(1);
        assertEquals(2, val);
    }

    @Test
    void deleteZeroIndex() {
        int val = intSLLReal.delete(0);
        assertEquals(1, val);
    }

    @Test
    void deleteError() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intSLLReal.delete(20);
        });
    }

    @Test
    void indexOf() {
        int index = intSLLReal.indexOf(5);
        assertEquals(4, index);
    }

    @Test
    void indexOfNotFound() {
        int index = intSLLReal.indexOf(9);
        assertEquals(-1, index);
    }

    @Test
    void size() {
        int size = intSLLReal.size();
        assertEquals(5, size);
    }

    @Test
    void sizeZero() {
        int size = intSLL.size();
        assertEquals(0, size);
    }

    @Test
    void isEmptyFalse() {
        boolean result = intSLLReal.isEmpty();
        assertFalse(result);
    }

    @Test
    void isEmptyTrue() {
        boolean result = intSLL.isEmpty();
        assertTrue(result);
    }

    @Test
    void clear() {
        intSLLReal.clear();
        boolean result = intSLLReal.isEmpty();
        assertTrue(result);
    }

    @Test
    void toArray() {
        intSLL.insertFirst(1);
        intSLL.insert(1, 2);
        intSLL.insert(2, 3);
        intSLL.insert(3, 4);
        intSLL.insert(4, 5);
        Object[] result = intSLL.toArray();
        assertArrayEquals(result, intSLLRealArray);
    }

    @Test
    void iteratorMovingForward() {
        int i = 0;
        while (iterReal.hasNext()) {
            int val = iterReal.next();
            assertEquals(val, intSLLRealArray[i]);
            i++;
        }
    }

    @Test
    void iteratorMoveAndDeleteCheck() {
        Integer[] real = new Integer[]{1, 2, 5};
        iterReal.next();
        iterReal.next();
        iterReal.next();
        iterReal.remove();
        iterReal.next();
        iterReal.remove();
        assertArrayEquals(real, intSLLReal.toArray());
    }

    @Test
    void iteratorWhileLoop() {
        Integer[] real = new Integer[]{1, 2};
        while (iterReal.hasNext()) {
            Integer val = iterReal.next();
            if (val > 2) {
                iterReal.remove();
            }
        }
        assertArrayEquals(real, intSLLReal.toArray());
    }

    @Test
    void iteratorError() {
        iterReal.next();
        iterReal.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterReal.remove();
        });
    }
}