package assign09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The HashTableTest class is a testing clas for our HashTable class as well as StudentBadHash, StudentMediumHash, and StudentGoodHash.
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-11-16
 */
class HashTableTest {

    HashTable<Integer, String> table;

    HashTable<StudentBadHash, String> studentBadHashTable;
    HashTable<StudentMediumHash, String> studentMediumHashTable;
    HashTable<StudentGoodHash, String> studentGoodHashTable;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
        table.put(1, "hi");
        table.put(2, "remember");
        table.put(3, "school");
        table.put(4, "alright");

        studentBadHashTable = new HashTable<>();
        studentBadHashTable.put(new StudentBadHash(1745682, "Mikhail", "Ahmed"), "Biomed");
        studentBadHashTable.put(new StudentBadHash(1344237, "Reynaldo", "Villarreal"), "Mechanical");
        studentBadHashTable.put(new StudentBadHash(1023254, "Adin", "Ibrahim"), "CS");
        studentBadHashTable.put(new StudentBadHash(1209421, "Andrew", "Turner"), "Electrical");

        studentMediumHashTable = new HashTable<StudentMediumHash, String>();
        studentMediumHashTable.put(new StudentMediumHash(1745682, "Mikhail", "Ahmed"), "Biomed");
        studentMediumHashTable.put(new StudentMediumHash(1344237, "Reynaldo", "Villarreal"), "Mechanical");
        studentMediumHashTable.put(new StudentMediumHash(1023254, "Adin", "Ibrahim"), "CS");
        studentMediumHashTable.put(new StudentMediumHash(1209421, "Andrew", "Turner"), "Electrical");

        studentGoodHashTable = new HashTable<StudentGoodHash, String>();
        studentGoodHashTable.put(new StudentGoodHash(1745682, "Mikhail", "Ahmed"), "Biomed");
        studentGoodHashTable.put(new StudentGoodHash(1344237, "Reynaldo", "Villarreal"), "Mechanical");
        studentGoodHashTable.put(new StudentGoodHash(1023254, "Adin", "Ibrahim"), "CS");
        studentGoodHashTable.put(new StudentGoodHash(1209421, "Andrew", "Turner"), "Electrical");
    }

    @Test
    void clear() {
        int real = 0;
        table.clear();
        assertEquals(table.size(), real);
    }

    @Test
    void containsKeyTrue() {
        assertTrue(table.containsKey(1));
    }

    @Test
    void containsKeyFalse() {
        assertFalse(table.containsKey(5));
    }

    @Test
    void containsValueTrue() {
        assertTrue(table.containsValue("hi"));
    }

    @Test
    void containsValueFalse() {
        assertFalse(table.containsValue("rey"));
    }

    @Test
    void entries() {
        ArrayList<MapEntry<Integer, String>> entries = new ArrayList<>();
        entries.add(new MapEntry<Integer, String>(1, "hi"));
        entries.add(new MapEntry<Integer, String>(2, "remember"));
        entries.add(new MapEntry<Integer, String>(3, "school"));
        entries.add(new MapEntry<Integer, String>(4, "alright"));
        assertArrayEquals(entries.toArray(), table.entries().toArray());
    }

    @Test
    void get() {
        String value = "alright";
        assertEquals(value, table.get(4));
    }

    @Test
    void getNull() {
        String value = null;
        assertEquals(value, table.get(9));
    }
    @Test
    void isEmptyTrue() {
        table.clear();
        assertTrue(table.isEmpty());
    }

    @Test
    void isEmptyFalse() {
        assertFalse(table.isEmpty());
    }

    @Test
    void put() {
        table.put(5, "yah");
        assertTrue(table.containsKey(5));
        String val = table.put(5, "no");
        assertEquals(val, "yah");
    }

    @Test
    void putNull() {
        String val = table.put(6, "gosh");
        assertEquals(val, null);
    }

    @Test
    void remove() {
        String real = "alright";
        assertEquals(real, table.remove(4));
    }

    @Test
    void removeNull() {
        String real = null;
        assertEquals(real, table.remove(6));
    }

    @Test
    void size() {
        int real = 4;
        assertEquals(real, 4);
    }


    // tests for StudentBadHash

    @Test
    void containsKeyTrueBadHash() {
        StudentBadHash student = new StudentBadHash(1745682, "Mikhail", "Ahmed");
        assertTrue(studentBadHashTable.containsKey(student));
    }

    @Test
    void containsKeyFalseBadHash() {
        StudentBadHash student = new StudentBadHash(1249030, "Chase", "Harkcom");
        assertFalse(studentBadHashTable.containsKey(student));
    }

    @Test
    void containsValueTrueBadHash() {
        assertTrue(studentBadHashTable.containsValue("Biomed"));
    }

    @Test
    void containsValueFalseBadHash() {
        assertFalse(studentBadHashTable.containsValue("Biology"));
    }

    @Test
    void entriesBadHash() {
        ArrayList<MapEntry<StudentBadHash, String>> entries = new ArrayList<>();
        entries.add(new MapEntry<StudentBadHash, String>(new StudentBadHash(1745682, "Mikhail", "Ahmed"), "Biomed"));
        entries.add(new MapEntry<StudentBadHash, String>(new StudentBadHash(1344237, "Reynaldo", "Villarreal"), "Mechanical"));
        entries.add(new MapEntry<StudentBadHash, String>(new StudentBadHash(1023254, "Adin", "Ibrahim"), "CS"));
        entries.add(new MapEntry<StudentBadHash, String>(new StudentBadHash(1209421, "Andrew", "Turner"), "Electrical"));
        assertArrayEquals(entries.toArray(), studentBadHashTable.entries().toArray());
    }

    @Test
    void getBadHash() {
        String result = "Mechanical";
        assertEquals(result, studentBadHashTable.get(new StudentBadHash(1344237, "Reynaldo", "Villarreal")));
    }

    @Test
    void getBadHashNull() {
        String result = null;
        assertEquals(result, studentBadHashTable.get(new StudentBadHash(1259202, "Chase", "Harkcom")));
    }

    @Test
    void isEmptyBadHashTrue() {
        studentBadHashTable.clear();
        assertTrue(studentBadHashTable.isEmpty());
    }

    @Test
    void isEmptyBadHashFalse() {
        assertFalse(studentBadHashTable.isEmpty());
    }

    @Test
    void putBadHash() {
        String result = studentBadHashTable.put(new StudentBadHash(1344237, "Reynaldo", "Villarreal"), "Accounting");
        assertEquals(result, "Mechanical");
    }

    @Test
    void putBadHashNull() {
        String result = studentBadHashTable.put(new StudentBadHash(1259202, "Chase", "Harkcom"), "CS");
        assertEquals(null, result);
    }
    @Test
    void removeBadHash() {
        String result = studentBadHashTable.remove(new StudentBadHash(1745682, "Mikhail", "Ahmed"));
        assertEquals("Biomed", result);
    }

    @Test
    void removeBadHashNull() {
        String result = studentBadHashTable.remove(new StudentBadHash(1259202, "Chase", "Harkcom"));
        assertEquals(null, result);
    }

    @Test
    void sizeBadHash() {
        int real = 4;
        assertEquals(real, studentBadHashTable.size());
    }

    // tests for StudentMediumHash

    @Test
    void containsKeyTrueMediumHash() {
        boolean result = studentMediumHashTable.containsKey(new StudentMediumHash(1209421, "Andrew", "Turner"));
        assertTrue(result);
    }

    @Test
    void containsKeyFalseMediumHash() {
        assertFalse(studentMediumHashTable.containsKey(new StudentMediumHash(1259202, "Chase", "Harkcom")));
    }

    @Test
    void containsValueTrueMediumHash() {
        assertTrue(studentMediumHashTable.containsValue("Electrical"));
    }

    @Test
    void containsValueFalseMediumHash() {
        assertFalse(studentMediumHashTable.containsValue("Biology"));
    }

    @Test
    void entriesMediumHash() {
        ArrayList<MapEntry<StudentMediumHash, String>> entries = new ArrayList<>();
        entries.add(new MapEntry<StudentMediumHash, String>(new StudentMediumHash(1209421, "Andrew", "Turner"), "Electrical"));
        entries.add(new MapEntry<StudentMediumHash, String>(new StudentMediumHash(1745682, "Mikhail", "Ahmed"), "Biomed"));
        entries.add(new MapEntry<StudentMediumHash, String>(new StudentMediumHash(1023254, "Adin", "Ibrahim"), "CS"));
        entries.add(new MapEntry<StudentMediumHash, String>(new StudentMediumHash(1344237, "Reynaldo", "Villarreal"), "Mechanical"));
        Object[] real = entries.toArray();
        Object[] result = studentMediumHashTable.entries().toArray();
        assertArrayEquals(real, result);
    }

    @Test
    void getMediumHash() {
        String result = "Electrical";
        assertEquals(result, studentMediumHashTable.get(new StudentMediumHash(1209421, "Andrew", "Turner")));
    }

    @Test
    void isEmptyMediumHash() {
        studentMediumHashTable.clear();
        int real = studentMediumHashTable.size();
        assertEquals(0, real);
    }

    @Test
    void putMediumHash() {
        String real = studentMediumHashTable.put(new StudentMediumHash(1344237, "Reynaldo", "Villarreal"), "Ballet");
        assertEquals(real, "Mechanical");
    }

    @Test
    void removeMediumHash() {
        String result = studentMediumHashTable.remove(new StudentMediumHash(1745682, "Mikhail", "Ahmed"));
        assertEquals("Biomed", result);
    }

    @Test
    void sizeMediumHash() {
        int real = studentMediumHashTable.size();
        assertEquals(4, real);
    }

    // tests for StudentGoodHash

    @Test
    void containsKeyTrueGoodHash() {
        assertTrue(studentGoodHashTable.containsKey(new StudentGoodHash(1344237, "Reynaldo", "Villarreal")));

    }

    @Test
    void containsKeyFalseGoodHash() {
        assertFalse(studentGoodHashTable.containsKey(new StudentGoodHash(1203721, "PJ", "Mannebach")));
    }

    @Test
    void containsValueTrueGoodHash() {
        assertTrue(studentGoodHashTable.containsValue("Mechanical"));
    }

    @Test
    void containsValueFalseGoodHash() {
        assertFalse(studentGoodHashTable.containsValue("Math"));
    }

    @Test
    void entriesGoodHash() {
        ArrayList<MapEntry<StudentGoodHash, String>> entries = new ArrayList<>();
        entries.add(new MapEntry<StudentGoodHash, String>(new StudentGoodHash(1209421, "Andrew", "Turner"), "Electrical"));
        entries.add(new MapEntry<StudentGoodHash, String>(new StudentGoodHash(1745682, "Mikhail", "Ahmed"), "Biomed"));
        entries.add(new MapEntry<StudentGoodHash, String>(new StudentGoodHash(1023254, "Adin", "Ibrahim"), "CS"));
        entries.add(new MapEntry<StudentGoodHash, String>(new StudentGoodHash(1344237, "Reynaldo", "Villarreal"), "Mechanical"));
        Object[] real = entries.toArray();
        Object[] result = studentGoodHashTable.entries().toArray();
        assertArrayEquals(real, result);
    }

    @Test
    void getGoodHash() {
        String result = "CS";
        assertEquals(result, studentGoodHashTable.get(new StudentGoodHash(1023254, "Adin", "Ibrahim")));
    }

    @Test
    void isEmptyGoodHash() {
        studentGoodHashTable.clear();
        int real = studentGoodHashTable.size();
        assertEquals(0, real);
    }

    @Test
    void putGoodHash() {
        String real = studentGoodHashTable.put(new StudentGoodHash(1745682, "Mikhail", "Ahmed"), "Communications");
        assertEquals(real, "Biomed");
    }

    @Test
    void removeGoodHash() {
        String result = studentGoodHashTable.remove(new StudentGoodHash(1745682, "Mikhail", "Ahmed"));
        assertEquals("Biomed", result);
    }

    @Test
    void sizeGoodHash() {
        int real = 4;
        assertEquals(real, studentGoodHashTable.size());
    }

}