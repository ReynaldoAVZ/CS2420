package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents a JUnit testing class for the LinkedListStack and all the corresponding methods.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-00
 */
class LinkedListStackTest {
    private LinkedListStack<Integer> LLS;
    private LinkedListStack<Integer> RealLLS;
    private Integer[] intSLLRealArray;
    @BeforeEach
    void setUp() {
        LLS = new LinkedListStack<>();
        LLS.push(1);
        LLS.push(2);
        LLS.push(3);
        LLS.push(4);
        LLS.push(5);

        RealLLS = new LinkedListStack<>();
        RealLLS.push(1);
        RealLLS.push(2);
        RealLLS.push(3);
        RealLLS.push(4);
        RealLLS.push(5);

        intSLLRealArray = new Integer[]{};
    }

    @Test
    void clear() {
        LinkedListStack<Integer> cleared = new LinkedListStack<>();
        LLS.clear();
        assertEquals(cleared.size(), LLS.size());
    }

    @Test
    void isEmptyTrue() {
        LLS.clear();
        assertTrue(LLS.isEmpty());
    }

    @Test
    void isEmptyFalse() {
        assertFalse(LLS.isEmpty());
    }

    @Test
    void peek() {
        int val = 5;
        assertEquals(val, LLS.peek());
    }

    @Test
    void peekError() {
        LLS.clear();
        assertThrows(NoSuchElementException.class, () -> {
            LLS.peek();
        });
    }

    @Test
    void pop() {
        int val = 5;
        int size = 4;
        assertEquals(val, LLS.pop());
        assertEquals(size, LLS.size());
    }

    @Test
    void popError() {
        LLS.clear();
        assertThrows(NoSuchElementException.class, () -> {
            LLS.pop();
        });
    }

    @Test
    void push() {
        LLS.push(6);
        int val = 6;
        int size = 6;
        assertEquals(val, LLS.peek());
        assertEquals(size, LLS.size());
    }

    @Test
    void size() {
        int size = 5;
        assertEquals(size, LLS.size());
    }
}