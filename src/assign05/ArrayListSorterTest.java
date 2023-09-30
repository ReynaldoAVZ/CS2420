package assign05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
private ArrayList<Integer> sortedIntegerList;
private ArrayList<String> stringList;
private ArrayList<String> sortedStringList;
    @BeforeEach
    void setUp() {
        integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(5);
        integerList.add(7);
        integerList.add(3);
        integerList.add(8);
        integerList.add(2);

        sortedIntegerList = new ArrayList<Integer>();
        sortedIntegerList.add(1);
        sortedIntegerList.add(2);
        sortedIntegerList.add(3);
        sortedIntegerList.add(5);
        sortedIntegerList.add(7);
        sortedIntegerList.add(8);

        stringList = new ArrayList<String>();
        stringList.add("zebra");
        stringList.add("at");
        stringList.add("enter");
        stringList.add("lose");
        stringList.add("columbus");
        stringList.add("platypus");
        stringList.add("hillbilly");



        sortedStringList = new ArrayList<String>();
        sortedStringList.add("at");
        sortedStringList.add("columbus");
        sortedStringList.add("enter");
        sortedStringList.add("hillbilly");
        sortedStringList.add("lose");
        sortedStringList.add("platypus");
        sortedStringList.add("zebra");
    }

    @Test
    void mergeSortInteger() {
        ArrayListSorter.mergesort(integerList);
        assertArrayEquals(integerList.toArray(), sortedIntegerList.toArray());
    }

    @Test
    void mergeSortString() {
        ArrayListSorter.mergesort(stringList);
        assertArrayEquals(stringList.toArray(), sortedStringList.toArray());
    }

    @Test
    void quicksort() {
    }

    @Test
    void generateAscending() {
    }

    @Test
    void generatePermuted() {
    }

    @Test
    void generateDescending() {
    }
}