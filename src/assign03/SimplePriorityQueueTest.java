package assign03;

import assign01.MathVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// Create the comparators for ints, doubles, and strings
/**
 * Comparator that defines an ordering among a SimplePriorityQueue of Integers using their value.
 */
class OrderInt implements Comparator<Integer> {
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
/**
 * Comparator that defines an ordering for Strings in a SimplePriorityQueue<String> by lexicographical ordering.
 */
class OrderString implements Comparator<String> {

    /**
     * This compare sorts Strings in reverse order, such that the greatest value is placed in the back and the
     * smallest value is in the front. This is a reverse ordering to the natural ordering of comparable in java.
     *
     * @param object1 - the first object to be compared.
     * @param object2 - the second object to be compared.
     * @return result - (int) result variable that determines the ordering within a SimplePriorityQueue<String>
     */
    public int compare(String object1, String object2) {
        int result;
        if ((object1.compareTo(object2)) < 0)
            result = 1;
        else if ((object1.compareTo(object2)) == 0) {
            result = 0;
        }
        else
            result = -1;
        return result;
    }
}

/**
 * Comparator that defines an ordering for Strings in a SimplePriorityQueue<String> by lexicographical ordering.
 */
class OrderDouble implements Comparator<Double> {

    /**
     * This compare sorts Double's in reverse order, such that the greatest value is placed in the back and the
     * smallest value is in the front. This is a reverse ordering to the natural ordering of comparable in java.
     *
     * @param object1 - the first object to be compared.
     * @param object2 - the second object to be compared.
     * @return result - (int) result variable that determines the ordering within a SimplePriorityQueue<String>
     */
    public int compare(Double object1, Double object2) {
        int result;
        if (object1 - object2 < 0)
            result = 1;
        else if (Math.abs(object1 - object2) < 0.001) {
            result = 0;
        }
        else
            result = -1;
        return result;
    }
}


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
    private SimplePriorityQueue<Integer> intNumbersSimpleComparator;
    private SimplePriorityQueue<Integer> intNumbersSimpleCopyComparator;
    private SimplePriorityQueue<Integer> intCopyComparator;
    private SimplePriorityQueue<Integer> intEmptyComparator;
    private Collection<Integer> intSimpleCollectionComparator;
    private Comparator<Integer> OrderInt = new OrderInt();

    // declare comparator string variables
    private SimplePriorityQueue<String> wordsSimpleComparator;
    private SimplePriorityQueue<String> wordsSimpleCopyComparator;
    private SimplePriorityQueue<String> wordsCopyComparator;
    private SimplePriorityQueue<String> wordsEmptyComparator;
    private Collection<String> wordsSimpleCollectionComparator;
    private Comparator<String> OrderString = new OrderString();

    // declare comparator double variables
    private SimplePriorityQueue<Double> decimalNumbersComparator;
    private SimplePriorityQueue<Double> decimalNumbersCopyComparator;
    private SimplePriorityQueue<Double> decimalCopyComparator;
    private SimplePriorityQueue<Double> decimalEmptyComparator;
    private Collection<Double> decimalCollectionComparator;
    private Comparator<Double> OrderDouble = new OrderDouble();





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
    }
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

    @BeforeEach
    void setUpTwo() throws Exception {
        // declare int stuff (with comparator)
        intNumbersSimpleComparator = new SimplePriorityQueue<Integer>(OrderInt);
        intNumbersSimpleCopyComparator = new SimplePriorityQueue<Integer>(OrderInt);
        intEmptyComparator = new SimplePriorityQueue<Integer>(OrderInt);
        intCopyComparator = new SimplePriorityQueue<Integer>(OrderInt);
        intSimpleCollectionComparator = new ArrayList<Integer>();

        intNumbersSimpleComparator.insert(1);
        intNumbersSimpleComparator.insert(2);
        intNumbersSimpleComparator.insert(3);
        intNumbersSimpleComparator.insert(4);
        intNumbersSimpleComparator.insert(5);
        intNumbersSimpleComparator.insert(6);
        intNumbersSimpleComparator.insert(7);
        intNumbersSimpleComparator.insert(40);

        intSimpleCollectionComparator.add(1);
        intSimpleCollectionComparator.add(4);
        intSimpleCollectionComparator.add(47);

        intCopyComparator.insert(1);
        intCopyComparator.insert(2);
        intCopyComparator.insert(3);
        intCopyComparator.insert(4);
        intCopyComparator.insert(5);
        intCopyComparator.insert(6);
        intCopyComparator.insert(7);
        intCopyComparator.insert(40);

        // declare string stuff (with comparator)
        wordsSimpleComparator = new SimplePriorityQueue<String>(OrderString);
        wordsSimpleCopyComparator = new SimplePriorityQueue<String>(OrderString);
        wordsCopyComparator = new SimplePriorityQueue<String>(OrderString);
        wordsEmptyComparator = new SimplePriorityQueue<String>(OrderString);
        wordsSimpleCollectionComparator = new ArrayList<String>();

        wordsSimpleComparator.insert("reynaldo");
        wordsSimpleComparator.insert("mikhail");
        wordsSimpleComparator.insert("allergy");
        wordsSimpleComparator.insert("sunflower");

        wordsSimpleCollectionComparator.add("biomed");
        wordsSimpleCollectionComparator.add("lab");
        wordsSimpleCollectionComparator.add("kendrick");

        wordsCopyComparator.insert("reynaldo");
        wordsCopyComparator.insert("mikhail");
        wordsCopyComparator.insert("allergy");
        wordsCopyComparator.insert("sunflower");

        // declare double stuff (with comparator)
        decimalNumbersComparator = new SimplePriorityQueue<Double>(OrderDouble);
        decimalNumbersCopyComparator = new SimplePriorityQueue<Double>(OrderDouble);
        decimalCopyComparator = new SimplePriorityQueue<Double>(OrderDouble);
        decimalEmptyComparator = new SimplePriorityQueue<Double>(OrderDouble);
        decimalCollectionComparator = new ArrayList<Double>();

        decimalNumbersComparator.insert(1.2);
        decimalNumbersComparator.insert(2.5);
        decimalNumbersComparator.insert(3.4);
        decimalNumbersComparator.insert(4.6);
        decimalNumbersComparator.insert(5.7);
        decimalNumbersComparator.insert(6.2);
        decimalNumbersComparator.insert(7.3);
        decimalNumbersComparator.insert(40.9);

        decimalCollectionComparator.add(1.4);
        decimalCollectionComparator.add(4.20);
        decimalCollectionComparator.add(47.6);

        decimalNumbersCopyComparator.insert(1.2);
        decimalNumbersCopyComparator.insert(2.5);
        decimalNumbersCopyComparator.insert(3.4);
        decimalNumbersCopyComparator.insert(4.6);
        decimalNumbersCopyComparator.insert(5.7);
        decimalNumbersCopyComparator.insert(6.2);
        decimalNumbersCopyComparator.insert(7.3);
        decimalNumbersCopyComparator.insert(40.9);
    }

    @Test
    void findMaxIntComparatorReverse() {
        int real = 1;
        assertEquals(real, intNumbersSimpleComparator.findMax());
    }

    @Test
    void findMaxExceptionIntComparator() {
        assertThrows(NoSuchElementException.class, () -> {
            intEmptyComparator.findMax();
        });    }

    @Test
    void deleteMaxIntComparatorReverse() {
        int max = intCopyComparator.deleteMax();
        int actual = 1;
        assertEquals(actual, max);
    }

    @Test
    void deleteMaxExceptionIntComparator() {
        assertThrows(NoSuchElementException.class, () -> {
            intEmptyComparator.deleteMax();
        });
    }

    @Test
    void insertIntComparator() {
        intNumbersSimpleCopyComparator.insert(1);
        intNumbersSimpleCopyComparator.insert(2);
        intNumbersSimpleCopyComparator.insert(3);
        intNumbersSimpleCopyComparator.insert(4);
        intNumbersSimpleCopyComparator.insert(5);
        intNumbersSimpleCopyComparator.insert(6);
        intNumbersSimpleCopyComparator.insert(7);
        intNumbersSimpleCopyComparator.insert(40);
        assertArrayEquals(intNumbersSimpleComparator.getArray(), intNumbersSimpleCopyComparator.getArray());
    }

    @Test
    void insertAllIntComparator() {

        intNumbersSimpleComparator.insert(1);
        intNumbersSimpleComparator.insert(4);
        intNumbersSimpleComparator.insert(47);
        intCopyComparator.insertAll(intSimpleCollectionComparator);
        assertArrayEquals(intNumbersSimpleComparator.getArray(), intCopyComparator.getArray());
    }

    @Test
    void containsIntComparator() {
        assertTrue(intNumbersSimpleComparator.contains(4));
    }

    @Test
    void doesNotContainIntComparator() {
        assertFalse(intNumbersSimpleComparator.contains(8));
    }

    @Test
    void sizeIntComparator() {
        int actual = 8;
        assertEquals(actual, intNumbersSimpleComparator.size());
    }

    @Test
    void sizeEmptyIntComparator() {
        int actual = 0;
        SimplePriorityQueue<Integer> empty = new SimplePriorityQueue<Integer>(OrderInt);
        assertEquals(actual, empty.size());
    }
    @Test
    void isEmptyNotTrueIntComparator() {
        assertFalse(intNumbersSimpleComparator.isEmpty());
    }

    @Test
    void isEmptyTrueIntComparator() {
        SimplePriorityQueue<Integer> empty = new SimplePriorityQueue<Integer>(OrderInt);
        assertTrue(empty.isEmpty());
    }
    @Test
    void clearIntComparator() {
        Integer[] cleared = new Integer[]{};
        intNumbersSimpleComparator.clear();
        assertArrayEquals(cleared, intNumbersSimpleComparator.getArray());
    }

    // String tests w/ comparator
    @Test
    void findMaxWordComparator() {
        String real = "allergy";
        String result = wordsSimpleComparator.findMax();
        assertTrue(real.equals(result));
    }
    @Test
    void findMaxWordExceptionComparator() {
        assertThrows(NoSuchElementException.class, () -> {
            wordsEmptyComparator.findMax();
        });    }

    @Test
    void deleteMaxWordComparatorReverse() {
        String max = wordsCopyComparator.deleteMax();
        String actual = "allergy";
        assertTrue(actual.equals(max));
    }

    @Test
    void deleteMaxExceptionWordComparator() {
        assertThrows(NoSuchElementException.class, () -> {
            wordsEmptyComparator.deleteMax();
        });
    }

    @Test
    void insertWordComparator() {
        wordsSimpleCopyComparator.insert("reynaldo");
        wordsSimpleCopyComparator.insert("mikhail");
        wordsSimpleCopyComparator.insert("allergy");
        wordsSimpleCopyComparator.insert("sunflower");

        assertArrayEquals(wordsSimpleComparator.getArray(), wordsSimpleCopyComparator.getArray());
    }

    @Test
    void insertAllWordsComparator() {

        wordsSimpleComparator.insert("biomed");
        wordsSimpleComparator.insert("lab");
        wordsSimpleComparator.insert("kendrick");
        wordsCopyComparator.insertAll(wordsSimpleCollectionComparator);
        assertArrayEquals(wordsSimpleComparator.getArray(), wordsCopyComparator.getArray());
    }

    @Test
    void containsWordComparator() {
        assertTrue(wordsSimpleComparator.contains("mikhail"));
    }

    @Test
    void doesNotContainWordsComparator() {
        assertFalse(wordsSimpleComparator.contains("mechanical"));
    }

    @Test
    void sizeWordsComparator() {
        int actual = 4;
        assertEquals(actual, wordsSimpleComparator.size());
    }

    @Test
    void sizeEmptyWordsComparator() {
        int actual = 0;
        SimplePriorityQueue<String> empty = new SimplePriorityQueue<String>(OrderString);
        assertEquals(actual, empty.size());
    }

    @Test
    void isEmptyNotTrueWordsComparator() {
        assertFalse(wordsSimpleComparator.isEmpty());
    }

    @Test
    void isEmptyTrueWordsComparator() {
        SimplePriorityQueue<String> empty = new SimplePriorityQueue<String>(OrderString);
        assertTrue(empty.isEmpty());
    }

    @Test
    void clearWordsComparator() {
        String[] cleared = new String[]{};
        wordsSimpleComparator.clear();
        assertArrayEquals(cleared, wordsSimpleComparator.getArray());
    }

    // Double testing with comparator
    @Test
    void findMaxDoubleComparatorReverse() {
        Double real = 1.2;
        assertEquals(real, decimalNumbersComparator.findMax());
    }

    @Test
    void findMaxExceptionDoubleComparator() {
        assertThrows(NoSuchElementException.class, () -> {
            decimalEmptyComparator.findMax();
        });    }

    @Test
    void deleteMaxDoubleComparatorReverse() {
        Double max = decimalNumbersComparator.deleteMax();
        Double actual = 1.2;
        assertEquals(actual, max);
    }

    @Test
    void deleteMaxExceptionDoubleComparator() {
        assertThrows(NoSuchElementException.class, () -> {
            decimalEmptyComparator.deleteMax();
        });
    }

    @Test
    void insertDoubleComparator() {
        decimalCopyComparator.insert(1.2);
        decimalCopyComparator.insert(2.5);
        decimalCopyComparator.insert(3.4);
        decimalCopyComparator.insert(4.6);
        decimalCopyComparator.insert(5.7);
        decimalCopyComparator.insert(6.2);
        decimalCopyComparator.insert(7.3);
        decimalCopyComparator.insert(40.9);
        assertArrayEquals(decimalNumbersComparator.getArray(), decimalNumbersCopyComparator.getArray());
    }

    @Test
    void insertAllDoubleComparator() {

        decimalNumbersComparator.insert(1.4);
        decimalNumbersComparator.insert(4.20);
        decimalNumbersComparator.insert(47.6);
        decimalNumbersCopyComparator.insertAll(decimalCollectionComparator);
        assertArrayEquals(decimalNumbersComparator.getArray(), decimalNumbersCopyComparator.getArray());
    }

    @Test
    void containsDoubleComparator() {
        assertTrue(decimalNumbersComparator.contains(3.4));
    }

    @Test
    void doesNotContainDoubleComparator() {
        assertFalse(decimalNumbersComparator.contains(4.1));
    }

    @Test
    void sizeDoubleComparator() {
        double actual = 8;
        assertEquals(actual, decimalNumbersComparator.size());
    }

    @Test
    void sizeEmptyDoubleComparator() {
        int actual = 0;
        SimplePriorityQueue<Double> empty = new SimplePriorityQueue<Double>(OrderDouble);
        assertEquals(actual, empty.size());
    }
    @Test
    void isEmptyNotTrueDoubleComparator() {
        assertFalse(decimalNumbersComparator.isEmpty());
    }

    @Test
    void isEmptyTrueDoubleComparator() {
        SimplePriorityQueue<Double> empty = new SimplePriorityQueue<Double>(OrderDouble);
        assertTrue(empty.isEmpty());
    }
    @Test
    void clearDoubleComparator() {
        Double[] cleared = new Double[]{};
        decimalNumbersComparator.clear();
        assertArrayEquals(cleared, decimalNumbersComparator.getArray());
    }

}