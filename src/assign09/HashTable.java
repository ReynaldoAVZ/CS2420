package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
    private ArrayList<LinkedList<MapEntry<K, V>>> table;
    private int capacity = 10;
    private int size;

    /**
     * The HashTable method is a constructor that creates a table that represents our HashTable using an ArrayList that
     * contains at each index a LinkedList made up of MapEntry objects
     */
    public HashTable() {
        this.table = new ArrayList<LinkedList<MapEntry<K, V>>>();
        for(int i = 0; i < capacity; i++)
            table.add(new LinkedList<MapEntry<K, V>>());
        this.size = 0;
    }
    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length) for quadratic probing or separate chaining
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.capacity; i++)
            this.table.set(i, new LinkedList<MapEntry<K, V>>());
        this.size = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     * <p>
     * O(1) for quadratic probing or separate chaining
     *
     * @param key
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int index = Math.abs(key.hashCode() % this.capacity);
        LinkedList<MapEntry<K, V>> currentList = this.table.get(index);
        for (MapEntry<K, V> currentMapEntry : currentList) {
            K currentKey = currentMapEntry.getKey();
            if (currentKey.equals(key)) {
                return true;
            }
        }
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
        for (LinkedList<MapEntry<K, V>> currentLinkedList : this.table) {
            for (MapEntry<K, V> currentMapEntry : currentLinkedList) {
                V currentVal = currentMapEntry.getValue();
                if (currentVal.equals(value)) {
                    return true;
                }
            }
        }
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
        if (this.size == 0)
            return true;

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
        boolean isContained = this.containsKey(key);
        if (isContained) {
            int index = Math.abs(key.hashCode() % this.capacity);
            LinkedList<MapEntry<K, V>> entryList = this.table.get(index);
            for (MapEntry<K, V> currentEntry : entryList) {
                K keyVal = currentEntry.getKey();
                if (keyVal.equals(key)) {
                    V prevVal = currentEntry.getValue();
                    currentEntry.setValue(value);
                    return prevVal;
                }
            }
        }
        else {
            int index = Math.abs(key.hashCode() % this.capacity);
            this.table.get(index).add(new MapEntry<K, V>(key, value));
            this.size++;
            return null;
        }
        return null;
    }

    /**
     * The rehash() method is a private helper method that rehashes the table such that we increase the capacity and
     * reassign all the linked list's to their corresponding new index
     */
    private void rehash() {
        ArrayList<LinkedList<MapEntry<K, V>>> newList = new ArrayList<>(this.capacity * 5);
        for (int i = 0; i < this.capacity * 5; i++) {
            newList.add(i, new LinkedList<MapEntry<K, V>>());
        }
        for (int i = 0; i < this.capacity; i++) {
            LinkedList<MapEntry<K, V>> currentList = this.table.get(i);
            int newIndex = currentList.getFirst().getKey().hashCode() % (this.capacity * 5);
            newList.add(newIndex, currentList);
        }
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
        // ASK on how to make constant time
        if (!this.containsKey(key)) {
            return null;
        }
        else {
            int index = Math.abs(key.hashCode() % this.capacity);
            LinkedList<MapEntry<K, V>> entryList = this.table.get(index);
            for (MapEntry<K, V> currentEntry : entryList) {
                if (currentEntry.getKey().equals(key)) {
                    V currentEntryValue = currentEntry.getValue();
                    entryList.remove(currentEntry);
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
        return this.size;
    }
}
