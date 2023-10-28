package assign07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GraphUtility.java that tests Depth-First Search, Breadth-First Search, and Topological sorting.
 */
class GraphUtilityTest {
    private List<Integer> intSourceList;
    private List<Integer> intDestinationList;
    private List<String> stringSourceList;
    private List<String> stringDestinationList;
    @BeforeEach
    void setUp() {
        intSourceList = new ArrayList<>();
        intSourceList.add(1);
        intSourceList.add(1);
        intSourceList.add(1);
        intSourceList.add(2);
        intSourceList.add(2);
        intSourceList.add(3);

        intDestinationList = new ArrayList<>();
        intDestinationList.add(2);
        intDestinationList.add(3);
        intDestinationList.add(4);
        intDestinationList.add(3);
        intDestinationList.add(4);
        intDestinationList.add(7);

        stringSourceList = new ArrayList<>();
        stringSourceList.add("a");
        stringSourceList.add("a");
        stringSourceList.add("a");
        stringSourceList.add("b");
        stringSourceList.add("b");
        stringSourceList.add("c");

        stringDestinationList = new ArrayList<>();
        stringDestinationList.add("b");
        stringDestinationList.add("c");
        stringDestinationList.add("d");
        stringDestinationList.add("c");
        stringDestinationList.add("d");
        stringDestinationList.add("g");

    }
    @Test
    void areConnectedIntegerTrue() {
        Integer source = 1;
        Integer destination = 7;
        assertTrue(GraphUtility.areConnected(intSourceList, intDestinationList, source, destination));
    }
    @Test
    void areConnectedIntegerFalse() {
        intSourceList.add(1);
        intDestinationList.add(6);
        intSourceList.add(6);
        intDestinationList.add(9);
        Integer source = 2;
        Integer destination = 9;
        assertFalse(GraphUtility.areConnected(intSourceList, intDestinationList, source, destination));
    }
    @Test
    void areConnectedDSTNotAVertexIntegerError() {
        Integer source = 1;
        // this destination value isn't an actual vertex, should throw an error
        Integer destination = 20;
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.areConnected(intSourceList, intDestinationList, source, destination);
        });
    }
    @Test
    void areConnectedSRCNotAVertexIntegerError() {
        // this source value isn't an actual vertex, should throw an error
        Integer source = 45;
        Integer destination = 3;
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.areConnected(intSourceList, intDestinationList, source, destination);
        });
    }
    @Test
    void areConnectedDifferentSizeIntegerError() {
        intSourceList.add(8);
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.areConnected(intSourceList, intDestinationList, 1, 4);
        });
    }

    @Test
    void shortestPathIntegerTrue() {
        Integer[] real = new Integer[]{1, 3, 9};
        intSourceList.add(3);
        intDestinationList.add(9);
        List<Integer> result = GraphUtility.shortestPath(intSourceList, intDestinationList, 1, 9);
        Object[] newresult = result.toArray();
        assertArrayEquals(real, newresult);
    }

    @Test
    void shortestPathIntegerFalse() {
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(intSourceList, intDestinationList, 3, 4);
        });
    }

    @Test
    void shortestPathDSTNotAVertexIntegerError() {
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(intSourceList, intDestinationList, 3, 20);
        });
    }
    @Test
    void shortestPathSRCNotAVertexIntegerError() {
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(intSourceList, intDestinationList, 76, 4);
        });
    }
    @Test
    void shortestPathDifferentSizeIntegerError() {
        intSourceList.add(1);
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(intSourceList, intDestinationList, 1, 7);
        });
    }
    @Test
    void sortIntegerTrue() {
        Integer[] real = new Integer[]{1, 2, 3, 4, 7};
        List<Integer> returnedList = GraphUtility.sort(intSourceList, intDestinationList);
        Object[] result = returnedList.toArray();
        assertArrayEquals(real, result);
    }

    @Test
    void sortDifferentSizeIntegerError() {
        intSourceList.add(1);
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.sort(intSourceList, intDestinationList);
        });
    }

    @Test
    void sortCycleIntegerError() {
        intSourceList.add(7);
        intDestinationList.add(3);
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.sort(intSourceList, intDestinationList);
        });
    }
    @Test
    void areConnectedStringTrue() {
        String source = "a";
        String destination = "g";
        assertTrue(GraphUtility.areConnected(stringSourceList, stringDestinationList, source, destination));
    }
    @Test
    void areConnectedStringFalse() {
        stringSourceList.add("a");
        stringDestinationList.add("f");
        stringSourceList.add("f");
        stringDestinationList.add("i");
        String source = "b";
        String destination = "i";
        assertFalse(GraphUtility.areConnected(stringSourceList, stringDestinationList, source, destination));
    }
    @Test
    void areConnectedDSTNotAVertexStringError() {
        String source = "a";
        // this destination value isn't an actual vertex, should throw an error
        String destination = "t";
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.areConnected(stringSourceList, stringDestinationList, source, destination);
        });
    }
    @Test
    void areConnectedSRCNotAVertexStringError() {
        // this source value isn't an actual vertex, should throw an error
        String source = "acj";
        String destination = "c";
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.areConnected(stringSourceList, stringDestinationList, source, destination);
        });
    }
    @Test
    void areConnectedDifferentSizeStringError() {
        stringSourceList.add("h");
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.areConnected(stringSourceList, stringDestinationList, "a", "d");
        });
    }

    @Test
    void shortestPathStringTrue() {
        String[] real = new String[]{"a", "c", "i"};
        stringSourceList.add("c");
        stringDestinationList.add("i");
        List<String> result = GraphUtility.shortestPath(stringSourceList, stringDestinationList, "a", "i");
        Object[] newResult = result.toArray();
        assertArrayEquals(real, newResult);
    }

    @Test
    void shortestPathStringFalse() {
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(stringSourceList, stringDestinationList, "c", "d");
        });
    }

    @Test
    void shortestPathDSTNotAVertexStringError() {
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(stringSourceList, stringDestinationList, "c", "t");
        });
    }
    @Test
    void shortestPathSRCNotAVertexStringError() {
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(stringSourceList, stringDestinationList, "res", "d");
        });
    }
    @Test
    void shortestPathDifferentSizeStringError() {
        stringSourceList.add("a");
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestPath(stringSourceList, stringDestinationList, "a", "g");
        });
    }
    @Test
    void sortStringTrue() {
        String[] real = new String[]{"a", "b", "c", "d", "g"};
        List<String> returnedList = GraphUtility.sort(stringSourceList, stringDestinationList);
        Object[] result = returnedList.toArray();
        assertArrayEquals(real, result);
    }

    @Test
    void sortDifferentSizeStringError() {
        stringSourceList.add("a");
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.sort(stringSourceList, stringDestinationList);
        });
    }

    @Test
    void sortCycleStringError() {
        stringSourceList.add("g");
        stringDestinationList.add("c");
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.sort(stringSourceList, stringDestinationList);
        });
    }
}