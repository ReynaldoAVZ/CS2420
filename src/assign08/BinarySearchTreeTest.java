package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
private BinarySearchTree<Integer> BST;
private BinarySearchTree<Integer> BSTEmpty;
private ArrayList<Integer> intReal;
private Collection<Integer> cNew;
private Collection<Integer> cOld;

    @BeforeEach
    void setUp() {
        BST = new BinarySearchTree<>();
        BST.add(5);
        BST.add(1);
        BST.add(9);
        BST.add(3);
        BST.add(7);
        BST.add(-2);
        BST.add(6);
        BST.add(4);
        BST.add(8);
        BST.add(12);
        BST.add(11);
        BST.add(13);
        BST.add(2);
        BST.add(-1);
        BST.add(-3);

        BSTEmpty = new BinarySearchTree<>();

        intReal = new ArrayList<>();
        intReal.add(-3);
        intReal.add(-2);
        intReal.add(-1);
        intReal.add(1);
        intReal.add(2);
        intReal.add(3);
        intReal.add(4);
        intReal.add(5);
        intReal.add(6);
        intReal.add(7);
        intReal.add(8);
        intReal.add(9);
        intReal.add(11);
        intReal.add(12);
        intReal.add(13);

        cNew = new ArrayList<>();
        cNew.add(-60);
        cNew.add(-30);
        cNew.add(30);
        cNew.add(60);

        cOld = new ArrayList<>();
        cOld.add(1);
        cOld.add(-3);
        cOld.add(9);
        cOld.add(6);
    }

    @Test
    void addTrue() {
        boolean trueResult = BST.add(20);
        Integer result = BST.last();
        assertEquals(20, result);
        assertTrue(trueResult);
    }

    @Test
    void addFalse() {
        boolean result = BST.add(9);
        assertFalse(result);
        assertEquals(15, BST.size());
    }
    @Test
    void addAllTrue() {
        boolean result = BST.addAll(cNew);
        Integer[] arrayReal = new Integer[]{-60, -30, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 30, 60};
        assertTrue(result);
        assertArrayEquals(arrayReal, BST.toArrayList().toArray());
    }

    @Test
    void addAllFalse() {
        boolean result = BST.addAll(cOld);
        Integer[] arrayReal = new Integer[]{-3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13};
        assertFalse(result);
        assertArrayEquals(arrayReal, BST.toArrayList().toArray());
    }
    @Test
    void clear() {
        BST.clear();
        assertEquals(0, BST.size());
        assertArrayEquals(BSTEmpty.toArrayList().toArray(), BST.toArrayList().toArray());
        assertTrue(BST.isEmpty());
    }

    @Test
    void containsTrue() {
        assertTrue(BST.contains(11));
    }

    @Test
    void containsFalse() {
        assertFalse(BST.contains(20));
    }
    @Test
    void containsAll() {
        assertTrue(BST.containsAll(cOld));
    }

    @Test
    void containsAllFalse() {
        assertFalse(BST.containsAll(cNew));
    }
    @Test
    void first() {
        Integer result = BST.first();
        assertEquals(-3, result);
    }

    @Test
    void isEmptyTrue() {
        assertTrue(BSTEmpty.isEmpty());
    }

    @Test
    void isEmptyFalse() {
        assertFalse(BST.isEmpty());
    }
    @Test
    void last() {
        Integer result = BST.last();
        assertEquals(13, result);
    }

    @Test
    void remove() {
        // ask tutors about why remove 7 leaves a null object in our BST
        boolean result = BST.remove(7);
        assertTrue(result);
        Integer[] real = new Integer[]{-3, -2, -1, 1, 2, 3, 4, 5, 6, 8, 9, 11, 12, 13};
        ArrayList<Integer> output = BST.toArrayList();
        assertArrayEquals(real, output.toArray());
    }

    @Test
    void removeFalse() {
        boolean result = BST.remove(60);
        assertFalse(result);
        assertEquals(15, BST.size());
    }
    @Test
    void removeAllTrue() {
        boolean result = BST.removeAll(cOld);
        assertTrue(result);
        assertEquals(11, BST.size());
    }

    @Test
    void removeAllFalse() {
        boolean result = BST.removeAll(cNew);
        assertFalse(result);
        assertEquals(15, BST.size());
    }
    @Test
    void size() {
    }

    @Test
    void toArrayList() {
        ArrayList<Integer> intResult = BST.toArrayList();
        assertArrayEquals(intReal.toArray(), intResult.toArray());
    }

    @Test
    void toArrayListEmpty() {
        ArrayList<Integer> intResult = BSTEmpty.toArrayList();
        assertArrayEquals(new ArrayList<Integer>().toArray(), intResult.toArray());
    }
}