package assign05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This Java class represents an ArrayListSorterTester that has built in tests to use various different sorting algorithms
 * (specifically merge sort and quicksort) and ensure that the implementation is correct.
 * <p></p>
 * We will test all methods and sorting algorithms in direct ways as well as passing in edge-cases / complex situations
 * to ensure our code is working correctly.
 * <p></p>
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-00
 */
class ArrayListSorterTest {
private ArrayList<Integer> integerList;
private ArrayList<Integer> emptyIntegerList;
private ArrayList<Integer> repeatedIntegerList;
private ArrayList<Integer> sortedRepeatedIntegerList;
private ArrayList<Integer> sortedIntegerList;
private ArrayList<String> stringList;
private ArrayList<String> emptyStringList;
private ArrayList<String> repeatedStringList;
private ArrayList<String> sortedRepeatedStringList;
private ArrayList<String> sortedStringList;
private ArrayList<Integer> ascendingIntegerList;
private ArrayList<Integer> descendingIntegerList;
    @BeforeEach
    void setUp() {

        // Integer section for mergesort and quicksort
        integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(5);
        integerList.add(7);
        integerList.add(3);
        integerList.add(8);
        integerList.add(2);
        integerList.add(-6);

        emptyIntegerList = new ArrayList<Integer>();

        repeatedIntegerList = new ArrayList<Integer>();
        repeatedIntegerList.add(1);
        repeatedIntegerList.add(2);
        repeatedIntegerList.add(1);
        repeatedIntegerList.add(3);
        repeatedIntegerList.add(1);
        repeatedIntegerList.add(4);

        sortedRepeatedIntegerList = new ArrayList<Integer>();
        sortedRepeatedIntegerList.add(1);
        sortedRepeatedIntegerList.add(1);
        sortedRepeatedIntegerList.add(1);
        sortedRepeatedIntegerList.add(2);
        sortedRepeatedIntegerList.add(3);
        sortedRepeatedIntegerList.add(4);

        sortedIntegerList = new ArrayList<Integer>();
        sortedIntegerList.add(-6);
        sortedIntegerList.add(1);
        sortedIntegerList.add(2);
        sortedIntegerList.add(3);
        sortedIntegerList.add(5);
        sortedIntegerList.add(7);
        sortedIntegerList.add(8);

        // String section for mergesort and quicksort
        stringList = new ArrayList<String>();
        stringList.add("zebra");
        stringList.add("at");
        stringList.add("enter");
        stringList.add("lose");
        stringList.add("columbus");
        stringList.add("platypus");
        stringList.add("hillbilly");

        emptyStringList = new ArrayList<String>();

        repeatedStringList = new ArrayList<String>();
        repeatedStringList.add("subway");
        repeatedStringList.add("mcdonald's");
        repeatedStringList.add("subway");
        repeatedStringList.add("wendy's");
        repeatedStringList.add("subway");
        repeatedStringList.add("carl's jr");

        sortedRepeatedStringList = new ArrayList<String>();
        sortedRepeatedStringList.add("carl's jr");
        sortedRepeatedStringList.add("mcdonald's");
        sortedRepeatedStringList.add("subway");
        sortedRepeatedStringList.add("subway");
        sortedRepeatedStringList.add("subway");
        sortedRepeatedStringList.add("wendy's");

        sortedStringList = new ArrayList<String>();
        sortedStringList.add("at");
        sortedStringList.add("columbus");
        sortedStringList.add("enter");
        sortedStringList.add("hillbilly");
        sortedStringList.add("lose");
        sortedStringList.add("platypus");
        sortedStringList.add("zebra");

        // Integer list for generateAscending()
        ascendingIntegerList = new ArrayList<Integer>();
        ascendingIntegerList.add(1);
        ascendingIntegerList.add(2);
        ascendingIntegerList.add(3);
        ascendingIntegerList.add(4);
        ascendingIntegerList.add(5);
        ascendingIntegerList.add(6);
        ascendingIntegerList.add(7);
        ascendingIntegerList.add(8);
        ascendingIntegerList.add(9);

        // Integer list for generateDescending()
        descendingIntegerList = new ArrayList<Integer>();
        descendingIntegerList.add(9);
        descendingIntegerList.add(8);
        descendingIntegerList.add(7);
        descendingIntegerList.add(6);
        descendingIntegerList.add(5);
        descendingIntegerList.add(4);
        descendingIntegerList.add(3);
        descendingIntegerList.add(2);
        descendingIntegerList.add(1);
    }

    @Test
    void mergesortInteger() {
        ArrayListSorter.mergesort(integerList);
        assertArrayEquals(integerList.toArray(), sortedIntegerList.toArray());
    }

    @Test
    void mergesortEmptyInteger() {
        ArrayList<Integer> emptyList = ArrayListSorter.generatePermuted(0);
        ArrayListSorter.mergesort(emptyList);
        assertArrayEquals(emptyIntegerList.toArray(), emptyList.toArray());
    }

    @Test
    void mergesortRepeatedInteger() {
        ArrayListSorter.mergesort(repeatedIntegerList);
        assertArrayEquals(repeatedIntegerList.toArray(), sortedRepeatedIntegerList.toArray());
    }

    @Test
    void mergesortString() {
        ArrayListSorter.mergesort(stringList);
        assertArrayEquals(stringList.toArray(), sortedStringList.toArray());
    }

    @Test
    void mergeSortEmptyString() {
        ArrayList<String> emptyList = new ArrayList<String>();
        ArrayListSorter.mergesort(emptyStringList);
        assertArrayEquals(emptyStringList.toArray(), emptyList.toArray());
    }

    @Test
    void mergeSortRepeatedString() {
        ArrayListSorter.mergesort(repeatedStringList);
        assertArrayEquals(repeatedStringList.toArray(), sortedRepeatedStringList.toArray());
    }

    @Test
    void quicksortInteger() {
        ArrayListSorter.quicksort(integerList);
        assertArrayEquals(integerList.toArray(), sortedIntegerList.toArray());
    }

    @Test
    void quicksortEmptyInteger() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        ArrayListSorter.quicksort(emptyIntegerList);
        assertArrayEquals(emptyIntegerList.toArray(), emptyList.toArray());
    }

    @Test
    void quicksortRepeatedInteger() {
        ArrayListSorter.quicksort(repeatedIntegerList);
        assertArrayEquals(repeatedIntegerList.toArray(), sortedRepeatedIntegerList.toArray());
    }

    @Test
    void quicksortString() {
        ArrayListSorter.quicksort(stringList);
        assertArrayEquals(stringList.toArray(), sortedStringList.toArray());
    }

    @Test
    void quicksortEmptyString() {
        ArrayList<String> emptyList = new ArrayList<>();
        ArrayListSorter.quicksort(emptyStringList);
        assertArrayEquals(emptyStringList.toArray(), emptyList.toArray());
    }

    @Test
    void quicksortRepeatedString() {
        ArrayListSorter.quicksort(repeatedStringList);
        assertArrayEquals(repeatedStringList.toArray(), sortedRepeatedStringList.toArray());

    }
    @Test
    void generateAscending() {
        ArrayList<Integer> generatedList = ArrayListSorter.generateAscending(9);
        assertArrayEquals(generatedList.toArray(), ascendingIntegerList.toArray());
        assertTrue(generatedList.size() == 9);
    }

    @Test
    void generatePermuted() {
        ArrayList<Integer> generatedList = ArrayListSorter.generatePermuted(9);
        Object[] generatedArray = generatedList.toArray();
        assertTrue(ascendingIntegerList.contains(generatedArray[5]));
        assertTrue(generatedArray.length == 9);
    }

    @Test
    void generateDescending() {
        ArrayList<Integer> generatedList = ArrayListSorter.generateDescending(9);
        assertArrayEquals(generatedList.toArray(), descendingIntegerList.toArray());
        assertTrue(generatedList.size() == 9);
    }

    @Test
    void generateAscendingZero() {
        ArrayList<Integer> generatedList = ArrayListSorter.generateAscending(0);
        ArrayList<Integer> list = new ArrayList<>();
        assertArrayEquals(generatedList.toArray(), list.toArray());
    }
    @Test
    void generatePermutedZero() {
        ArrayList<Integer> generatedList = ArrayListSorter.generatePermuted(0);
        ArrayList<Integer> list = new ArrayList<>();
        assertArrayEquals(generatedList.toArray(), list.toArray());
    }
    @Test
    void generateDescendingZero() {
        ArrayList<Integer> generatedList = ArrayListSorter.generateDescending(0);
        ArrayList<Integer> list = new ArrayList<>();
        assertArrayEquals(generatedList.toArray(), list.toArray());
    }
}