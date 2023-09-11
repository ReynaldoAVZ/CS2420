package assign03;

import assign01.MathVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;



class SimplePriorityQueueTest {
    // declare non-comparator variables
    private SimplePriorityQueue<Integer> intNumbersSimple;
    private SimplePriorityQueue<Integer> intNumbersSimpleCopy;
    private SimplePriorityQueue<Integer> intCopy;
    private SimplePriorityQueue<Integer> intEmpty;
    private Collection<Integer> intSimpleCollection;
    private SimplePriorityQueue<String> wordsSimple;
    private SimplePriorityQueue<String> wordsSimpleCopy;
    private SimplePriorityQueue<String> wordsCopy;
    private SimplePriorityQueue<String> wordsEmpty;
    private Collection<String> wordsSimpleCollection;
    private SimplePriorityQueue<Double> decimalNumbersSimple;
    private SimplePriorityQueue<Double> decimalNumbersSimpleCopy;
    private SimplePriorityQueue<Double> decimalNumbersCopy;
    private SimplePriorityQueue<Double> decimalNumbersEmpty;
    private Collection<Double> decimalNumbersCollection;

    // declare comparator variables
    private SimplePriorityQueue<Integer> intNumberSimpleComparator;
    private SimplePriorityQueue<Integer> intNumbersSimpleCopyComparator;
    private SimplePriorityQueue<Integer> int
    private SimplePriorityQueue
    private SimplePriorityQueue
    private SimplePriorityQueue
    private SimplePriorityQueue
    private SimplePriorityQueue
    private SimplePriorityQueue
    private SimplePriorityQueue





    @BeforeEach
    void setUp() throws Exception {
        // declare int stuff (without comparator)
        intNumbersSimple = new SimplePriorityQueue<Integer>();
        intNumbersSimpleCopy = new SimplePriorityQueue<Integer>();
        intEmpty = new SimplePriorityQueue<Integer>();
        intSimpleCollection = new ArrayList<Integer>();
        intCopy = new SimplePriorityQueue<Integer>();

        intNumbersSimple.insert(1);
        intNumbersSimple.insert(2);
        intNumbersSimple.insert(3);
        intNumbersSimple.insert(4);
        intNumbersSimple.insert(5);
        intNumbersSimple.insert(6);
        intNumbersSimple.insert(7);
        intNumbersSimple.insert(40);

        intSimpleCollection.add(1);
        intSimpleCollection.add(4);
        intSimpleCollection.add(47);

        intCopy.insert(1);
        intCopy.insert(2);
        intCopy.insert(3);
        intCopy.insert(4);
        intCopy.insert(5);
        intCopy.insert(6);
        intCopy.insert(7);
        intCopy.insert(40);

        // declare string stuff (without comparator)
        wordsSimple = new SimplePriorityQueue<String>();
        wordsSimpleCopy = new SimplePriorityQueue<String>();
        wordsCopy = new SimplePriorityQueue<String>();
        wordsEmpty = new SimplePriorityQueue<String>();
        wordsSimpleCollection = new ArrayList<String>();

        wordsSimple.insert("reynaldo");
        wordsSimple.insert("mikhail");
        wordsSimple.insert("allergy");
        wordsSimple.insert("sunflower");

        wordsSimpleCollection.add("biomed");
        wordsSimpleCollection.add("lab");
        wordsSimpleCollection.add("kendrick");

        wordsCopy.insert("reynaldo");
        wordsCopy.insert("mikhail");
        wordsCopy.insert("allergy");
        wordsCopy.insert("sunflower");

        // declare double stuff (without comparator)
        decimalNumbersSimple = new SimplePriorityQueue<Double>();
        decimalNumbersSimpleCopy = new SimplePriorityQueue<Double>();
        decimalNumbersCopy = new SimplePriorityQueue<Double>();
        decimalNumbersEmpty = new SimplePriorityQueue<Double>();
        decimalNumbersCollection = new ArrayList<Double>();

        decimalNumbersSimple.insert(1.2);
        decimalNumbersSimple.insert(2.5);
        decimalNumbersSimple.insert(3.4);
        decimalNumbersSimple.insert(4.6);
        decimalNumbersSimple.insert(5.7);
        decimalNumbersSimple.insert(6.2);
        decimalNumbersSimple.insert(7.3);
        decimalNumbersSimple.insert(40.9);

        decimalNumbersCollection.add(1.4);
        decimalNumbersCollection.add(4.20);
        decimalNumbersCollection.add(47.6);

        decimalNumbersCopy.insert(1.2);
        decimalNumbersCopy.insert(2.5);
        decimalNumbersCopy.insert(3.4);
        decimalNumbersCopy.insert(4.6);
        decimalNumbersCopy.insert(5.7);
        decimalNumbersCopy.insert(6.2);
        decimalNumbersCopy.insert(7.3);
        decimalNumbersCopy.insert(40.9);

        // declare int stuff (with comparator)
//        intNumberSimpleComparator = new SimplePriorityQueue<>(OrderInt());
//        intNumbersSimpleCopy = new SimplePriorityQueue<Integer>();
//        intEmpty = new SimplePriorityQueue<Integer>();
//        intSimpleCollection = new ArrayList<Integer>();
//        intCopy = new SimplePriorityQueue<Integer>();
//
//        intNumbersSimple.insert(1);
//        intNumbersSimple.insert(2);
//        intNumbersSimple.insert(3);
//        intNumbersSimple.insert(4);
//        intNumbersSimple.insert(5);
//        intNumbersSimple.insert(6);
//        intNumbersSimple.insert(7);
//        intNumbersSimple.insert(40);
//
//        intSimpleCollection.add(1);
//        intSimpleCollection.add(4);
//        intSimpleCollection.add(47);
//
//        intCopy.insert(1);
//        intCopy.insert(2);
//        intCopy.insert(3);
//        intCopy.insert(4);
//        intCopy.insert(5);
//        intCopy.insert(6);
//        intCopy.insert(7);
//        intCopy.insert(40);
//
//        // declare string stuff (with comparator)
//        wordsSimple = new SimplePriorityQueue<String>();
//        wordsSimpleCopy = new SimplePriorityQueue<String>();
//        wordsCopy = new SimplePriorityQueue<String>();
//        wordsEmpty = new SimplePriorityQueue<String>();
//        wordsSimpleCollection = new ArrayList<String>();
//
//        wordsSimple.insert("reynaldo");
//        wordsSimple.insert("mikhail");
//        wordsSimple.insert("allergy");
//        wordsSimple.insert("sunflower");
//
//        wordsSimpleCollection.add("biomed");
//        wordsSimpleCollection.add("lab");
//        wordsSimpleCollection.add("kendrick");
//
//        wordsCopy.insert("reynaldo");
//        wordsCopy.insert("mikhail");
//        wordsCopy.insert("allergy");
//        wordsCopy.insert("sunflower");
//
//        // declare double stuff (with comparator)
//        decimalNumbersSimple = new SimplePriorityQueue<Double>();
//        decimalNumbersSimpleCopy = new SimplePriorityQueue<Double>();
//        decimalNumbersCopy = new SimplePriorityQueue<Double>();
//        decimalNumbersEmpty = new SimplePriorityQueue<Double>();
//        decimalNumbersCollection = new ArrayList<Double>();
//
//        decimalNumbersSimple.insert(1.2);
//        decimalNumbersSimple.insert(2.5);
//        decimalNumbersSimple.insert(3.4);
//        decimalNumbersSimple.insert(4.6);
//        decimalNumbersSimple.insert(5.7);
//        decimalNumbersSimple.insert(6.2);
//        decimalNumbersSimple.insert(7.3);
//        decimalNumbersSimple.insert(40.9);
//
//        decimalNumbersCollection.add(1.4);
//        decimalNumbersCollection.add(4.20);
//        decimalNumbersCollection.add(47.6);
//
//        decimalNumbersCopy.insert(1.2);
//        decimalNumbersCopy.insert(2.5);
//        decimalNumbersCopy.insert(3.4);
//        decimalNumbersCopy.insert(4.6);
//        decimalNumbersCopy.insert(5.7);
//        decimalNumbersCopy.insert(6.2);
//        decimalNumbersCopy.insert(7.3);
//        decimalNumbersCopy.insert(40.9);
//
//
//


    }
    // Integer Testing (without comparator)
    @Test
    void findMaxInt() {
        int real = 40;
        assertEquals(real, intNumbersSimple.findMax());
    }

    @Test
    void findMaxExceptionInt() {
        assertThrows(NoSuchElementException.class, () -> {
            intEmpty.findMax();
        });    }

    @Test
    void deleteMaxInt() {
        int max = intCopy.deleteMax();
        int actual = 40;
        assertEquals(actual, max);
    }

    @Test
    void deleteMaxExceptionInt() {
        assertThrows(NoSuchElementException.class, () -> {
            intEmpty.deleteMax();
        });
    }

    @Test
    void insertInt() {
        intNumbersSimpleCopy.insert(1);
        intNumbersSimpleCopy.insert(2);
        intNumbersSimpleCopy.insert(3);
        intNumbersSimpleCopy.insert(4);
        intNumbersSimpleCopy.insert(5);
        intNumbersSimpleCopy.insert(6);
        intNumbersSimpleCopy.insert(7);
        intNumbersSimpleCopy.insert(40);
        assertArrayEquals(intNumbersSimple.getArray(), intNumbersSimpleCopy.getArray());
    }

    @Test
    void insertAllInt() {

        intNumbersSimple.insert(1);
        intNumbersSimple.insert(4);
        intNumbersSimple.insert(47);
        intCopy.insertAll(intSimpleCollection);
        assertArrayEquals(intNumbersSimple.getArray(), intCopy.getArray());
    }

    @Test
    void containsInt() {
        assertTrue(intNumbersSimple.contains(4));
    }

    @Test
    void doesNotContainInt() {
        assertFalse(intNumbersSimple.contains(8));
    }

    @Test
    void sizeInt() {
        int actual = 8;
        assertEquals(actual, intNumbersSimple.size());
    }

    @Test
    void sizeEmptyInt() {
        int actual = 0;
        SimplePriorityQueue<Integer> empty = new SimplePriorityQueue<Integer>();
        assertEquals(actual, empty.size());
    }
    @Test
    void isEmptyNotTrueInt() {
        assertFalse(intNumbersSimple.isEmpty());
    }

    @Test
    void isEmptyTrueInt() {
        SimplePriorityQueue<Integer> empty = new SimplePriorityQueue<Integer>();
        assertTrue(empty.isEmpty());
    }
    @Test
    void clearInt() {
        Integer[] cleared = new Integer[]{};
        intNumbersSimple.clear();
        assertArrayEquals(cleared, intNumbersSimple.getArray());
    }

    // String Section (without comparator)

    @Test
    void findMaxWord() {
        String real = "sunflower";
        String result = wordsSimple.findMax();
        assertTrue(real.equals(result));
    }
    @Test
    void findMaxWordException() {
        assertThrows(NoSuchElementException.class, () -> {
        wordsEmpty.findMax();
    });    }

    @Test
    void deleteMaxWord() {
        String max = wordsCopy.deleteMax();
        String actual = "sunflower";
        assertTrue(actual.equals(max));
    }

    @Test
    void deleteMaxExceptionWord() {
        assertThrows(NoSuchElementException.class, () -> {
            wordsEmpty.deleteMax();
        });
    }

    @Test
    void insertWord() {
        wordsSimpleCopy.insert("reynaldo");
        wordsSimpleCopy.insert("mikhail");
        wordsSimpleCopy.insert("allergy");
        wordsSimpleCopy.insert("sunflower");

        assertArrayEquals(wordsSimple.getArray(), wordsSimpleCopy.getArray());
    }

    @Test
    void insertAllWords() {

        wordsSimple.insert("biomed");
        wordsSimple.insert("lab");
        wordsSimple.insert("kendrick");
        wordsCopy.insertAll(wordsSimpleCollection);
        assertArrayEquals(wordsSimple.getArray(), wordsCopy.getArray());
    }

    @Test
    void containsWord() {
        assertTrue(wordsSimple.contains("mikhail"));
    }

    @Test
    void doesNotContainWords() {
        assertFalse(wordsSimple.contains("mechanical"));
    }

    @Test
    void sizeWords() {
        int actual = 4;
        assertEquals(actual, wordsSimple.size());
    }

    @Test
    void sizeEmptyWords() {
        int actual = 0;
        SimplePriorityQueue<String> empty = new SimplePriorityQueue<String>();
        assertEquals(actual, empty.size());
    }

    @Test
    void isEmptyNotTrueWords() {
        assertFalse(wordsSimple.isEmpty());
    }

    @Test
    void isEmptyTrueWords() {
        SimplePriorityQueue<String> empty = new SimplePriorityQueue<String>();
        assertTrue(empty.isEmpty());
    }

    @Test
    void clearWords() {
        String[] cleared = new String[]{};
        wordsSimple.clear();
        assertArrayEquals(cleared, wordsSimple.getArray());
    }

    // Double Section (without comparator)
    @Test
    void findMaxDouble() {
        Double real = 40.9;
        assertEquals(real, decimalNumbersSimple.findMax());
    }

    @Test
    void findMaxExceptionDouble() {
        assertThrows(NoSuchElementException.class, () -> {
            decimalNumbersEmpty.findMax();
        });    }

    @Test
    void deleteMaxDouble() {
        Double max = decimalNumbersCopy.deleteMax();
        Double actual = 40.9;
        assertEquals(actual, max);
    }

    @Test
    void deleteMaxExceptionDouble() {
        assertThrows(NoSuchElementException.class, () -> {
            decimalNumbersEmpty.deleteMax();
        });
    }

    @Test
    void insertDouble() {
        decimalNumbersSimpleCopy.insert(1.2);
        decimalNumbersSimpleCopy.insert(2.5);
        decimalNumbersSimpleCopy.insert(3.4);
        decimalNumbersSimpleCopy.insert(4.6);
        decimalNumbersSimpleCopy.insert(5.7);
        decimalNumbersSimpleCopy.insert(6.2);
        decimalNumbersSimpleCopy.insert(7.3);
        decimalNumbersSimpleCopy.insert(40.9);
        assertArrayEquals(decimalNumbersSimple.getArray(), decimalNumbersSimpleCopy.getArray());
    }

    @Test
    void insertAllDouble() {

        decimalNumbersSimple.insert(1.4);
        decimalNumbersSimple.insert(4.20);
        decimalNumbersSimple.insert(47.6);
        decimalNumbersCopy.insertAll(decimalNumbersCollection);
        assertArrayEquals(decimalNumbersSimple.getArray(), decimalNumbersCopy.getArray());
    }

    @Test
    void containsDouble() {
        assertTrue(decimalNumbersSimple.contains(3.4));
    }

    @Test
    void doesNotContainDouble() {
        assertFalse(decimalNumbersSimple.contains(4.1));
    }

    @Test
    void sizeDouble() {
        double actual = 8;
        assertEquals(actual, decimalNumbersSimple.size());
    }

    @Test
    void sizeEmptyDouble() {
        int actual = 0;
        SimplePriorityQueue<Double> empty = new SimplePriorityQueue<Double>();
        assertEquals(actual, empty.size());
    }
    @Test
    void isEmptyNotTrueDouble() {
        assertFalse(decimalNumbersSimple.isEmpty());
    }

    @Test
    void isEmptyTrueDouble() {
        SimplePriorityQueue<Double> empty = new SimplePriorityQueue<Double>();
        assertTrue(empty.isEmpty());
    }
    @Test
    void clearDouble() {
        Double[] cleared = new Double[]{};
        decimalNumbersSimple.clear();
        assertArrayEquals(cleared, decimalNumbersSimple.getArray());
    }

    // Integer Section (with comparator)

    // String Section (with comparator)

    // Double Section (with comparator)


}