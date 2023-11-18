package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The HashTable class represents a hashtable that is backed by an ArrayList. Each index of the array list contains a
 * LinkedList that holds the MapEntry<K, V> objects.
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-11-16
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for values type
 */
public class HashTable<K, V> implements Map<K, V> {
    private ArrayList<LinkedList<MapEntry<K, V>>> table; // backing array list that represents the HashTable
    private int capacity = 10; // initial capacity of the ArrayList
    private int size; // number of elements in the current ArrayList
    private int collisions; // Variable to keep track of collisions

    /**
     * The HashTable method is a constructor that creates a table that represents our HashTable using an ArrayList that
     * contains at each index a LinkedList made up of MapEntry objects
     */
    public HashTable() {
        // create a new ArrayList that represents our table
        this.table = new ArrayList<LinkedList<MapEntry<K, V>>>();
        // iterate over the table and fill each index with an empty LinkedList
        for(int i = 0; i < capacity; i++)
            table.add(new LinkedList<MapEntry<K, V>>());
        // declare the size as zero (no key-value pairs in the table yet)
        this.size = 0;
        this.collisions = 0;
    }
    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length) for quadratic probing or separate chaining
     */
    @Override
    public void clear() {
        // iterate over the table and in each index, place an empty LinkedList
        for (int i = 0; i < this.capacity; i++)
            this.table.set(i, new LinkedList<MapEntry<K, V>>());
        // reset the size of our table to zero (no key-value pairs)
        this.size = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key - The K key that is being searched for in our table
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        // find the corresponding index of where the key should be positioned inside our table
        int index = Math.abs(key.hashCode() % this.capacity);
        // get the LinkedList at the corresponding index inside the table
        LinkedList<MapEntry<K, V>> currentList = this.table.get(index);

        this.collisions += currentList.size();
        // iterate over every MapEntry in the chosen LinkedList
        for (MapEntry<K, V> currentMapEntry : currentList) {
            // get the K key value on the currentMapEntry from the LinkedList
            K currentKey = currentMapEntry.getKey();
            // check if the values of the currentKey and the key that we're searching for are equal
            if (currentKey.equals(key)) {
                return true;
            }
        }
        // did not find the key in the corresponding LinkedList, which means that it does not exist
        return false;
    }

    /**
     * Determines whether this map contains the specified value.
     * <p>
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @param value
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        // iterate over every LinkedList in the table
        for (LinkedList<MapEntry<K, V>> currentLinkedList : this.table) {
            // iterate over every MapEntry in the current LinkedList
            for (MapEntry<K, V> currentMapEntry : currentLinkedList) {
                // get the value that corresponds with the current MapEntry
                V currentVal = currentMapEntry.getValue();
                // if the current value of the MapEntry is equal to the value that we are searching for
                if (currentVal.equals(value)) {
                    return true;
                }
            }
        }
        // we iterated over the entire table and did not find the value
        return false;
    }


    /**
     * Returns a List view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * <p>
     * O(table length) for quadratic probing
     * O(table length + N) for separate chaining
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        // instantiating the list to contain entries
        ArrayList<MapEntry<K, V>> entriesList = new ArrayList<>();
        // looping through each LinkedList for entries
        for (int i = 0; i < this.capacity; i++) {
            LinkedList<MapEntry<K, V>> currentList = this.table.get(i);
            // adding each entry into the entries list
            entriesList.addAll(currentList);
        }
        // return the entriesList
        return entriesList;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        // ASK on how to make constant time
        // find the corresponding index of where the key should hypothetically be
        int index = Math.abs(key.hashCode() % this.capacity);
        // get the LinkedList that would be at the corresponding index
        LinkedList<MapEntry<K, V>> indexedList = this.table.get(index);
        // iterate through the linked list to find if the key is in there or not
        for (MapEntry<K, V> entry : indexedList) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // if there are no elements in the table
        if (this.size == 0)
            return true;
        // the table is not empty
        else
            return false;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @param value
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        // check the load factor such that once its 10, we increase the table size and rehash
        if (this.size >= this.capacity * 10) {
            rehash();
        }
        // check if the key is contained in our table
        boolean isContained = this.containsKey(key);
        // if the key is in the table, we are going to be replacing an old value
        if (isContained) {
            // find the corresponding index of our key
            int index = Math.abs(key.hashCode() % this.capacity);
            // get the LinkedList that corresponds with that index of our key
            LinkedList<MapEntry<K, V>> entryList = this.table.get(index);
            // iterate over the MapEntry objects of our current LinkedList
            for (MapEntry<K, V> currentEntry : entryList) {
                // get the key value of our current MapEntry object
                K keyVal = currentEntry.getKey();
                // check if the key value is equal to the key we're trying to find
                if (keyVal.equals(key)) {
                    // get the previous value that was correspondent with the key
                    V prevVal = currentEntry.getValue();
                    // set the new value of the MapEntry to the new value
                    currentEntry.setValue(value);
                    return prevVal;
                }
            }
        }
        // the key that we're placing in does not exist in the table
        else {
            // find the corresponding index of our key
            int index = Math.abs(key.hashCode() % this.capacity);
            // get the LinkedList that corresponds with the correct index and place a new MapEntry object into that LinkedList
            this.table.get(index).add(new MapEntry<K, V>(key, value));
            // increment the number of elements in the table
            this.size++;
            // return null because there is no previous value that was assigned with this key
            return null;
        }
        return null;
    }

    /**
     * The rehash() method is a private helper method that rehashes the table such that we increase the capacity and
     * reassign all the linked list's to their corresponding new index
     */
    private void rehash() {
        // creating new list with new capacity
        ArrayList<LinkedList<MapEntry<K, V>>> newList = new ArrayList<>(this.capacity * 5);
        for (int i = 0; i < this.capacity * 5; i++) {
            // adding in LinkedLists in indices
            newList.add(i, new LinkedList<MapEntry<K, V>>());
        }
        for (int i = 0; i < this.capacity; i++) {
            // rehashing
            LinkedList<MapEntry<K, V>> currentList = this.table.get(i);
            if (!currentList.isEmpty()) {
                int newIndex = Math.abs(currentList.getFirst().getKey().hashCode() % (this.capacity * 5));
                newList.add(newIndex, currentList);
            }
        }
        // updating the ArrayList and its capacity
        this.table = newList;
        this.capacity = this.capacity * 5;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        // return null if key isn't contained
        if (!this.containsKey(key)) {
            return null;
        }
        else {
            // locating the index
            int index = Math.abs(key.hashCode() % this.capacity);
            // accessing the corresponding list
            LinkedList<MapEntry<K, V>> entryList = this.table.get(index);
            Iterator<MapEntry<K, V>> currentIter = entryList.iterator();
            // utilizing iterator for constant behavior
            while(currentIter.hasNext()) {
                // looping through map entries
                MapEntry<K, V> currentEntry = currentIter.next();
                K currentEntryKey = currentEntry.getKey();
                if (currentEntryKey.equals(key)) {
                    V currentEntryValue = currentEntry.getValue();
                    currentIter.remove();
                    this.size--;
                    return currentEntryValue;
                }
            }
        }
        return null;
    }

    /**
     * Determines the number of mappings in this map.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        // returns number of elements in the ArrayList
        return this.size;
    }

    /**
     * The getCollisions method is a method used in our timing code that returns the total amount of collisions in a table
     * when adding in keys.
     * @return the number of collisions that have occurred on the current table
     */
//    public int getCollisions() {
//        return collisions;
//    }
}
