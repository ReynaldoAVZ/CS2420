package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This Java class represents an ordered SimplePriorityQueue that uses a simple backing array.
 *
 * @author Reynaldo Villarreal and Mikhail Ahmed
 * @version 2023-09-13
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>{

    // Declaring object and primitive variables for Simple Priority Queue objects
    private E[] array;

    private int arrSize;
    private Comparator<? super E> cmp;

    private int arrCount = 0;

    /**
     * These constructors instantiate SimplePriorityQueue Object without a Comparator.
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        this.arrSize = 10;
        this.array = (E[]) new Object[this.arrSize];
    }
    /**
     * These constructors instantiate SimplePriorityQueue Object with a Comparator.
     *
     * @param cmp - The comparator object being passed in
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp){
        this.arrSize = 10;
        this.array = (E[]) new Object[this.arrSize];
        this.cmp = cmp;
    }

    /**
     * This method helps compare to objects without the use of the comparator, hence null. If there is a comparator,
     * then the method uses the comparator.
     *
     * @param o1 The object variable being compared
     * @param o2 The object variable being compared, returns 0 for the first object due to no comparison
     * @return the integer based on the comparison
     */
    @SuppressWarnings("unchecked")
    public int helpCompare (E o1, E o2){
        // if comparator is null
        if (this.cmp == null)
            if(o2 == null)
                return 0;
        // Java's natural comparison due to comparator being null
            else
                return ((Comparable<? super E>)o1).compareTo(o2);

        else
            if (o2 == null)
                return 0;
            else
                // returns the comparator's result
                return cmp.compare(o1, o2);
    }

    /**
     * Retrieves, but does not remove, the maximum element in this priority
     * queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    public E findMax() throws NoSuchElementException {
        // Throws exception if no elements are contained
        if (this.arrCount == 0) {
            throw new NoSuchElementException();
        }
        return this.array[arrCount - 1];
    }

    /**
     * Retrieves and removes the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @SuppressWarnings("unchecked")
    @Override
    public E deleteMax() throws NoSuchElementException {
        // throws exception if the array size is zero or no elements
        if (this.arrSize == 0 || this.arrCount == 0) {
            throw new NoSuchElementException();
        }
        // Optimizing to find the max values
        E currentMax = this.array[0];
        int maxValue = 0;
        for (int i = 1; i < arrSize - 1; i++) {
            int comparison = this.helpCompare(currentMax, array[i]);
            if (comparison < 0) {
                currentMax = array[i];
                maxValue = i;
            }
        }
        // Creating a new array which now contains all elements except the max value found previously
        E[] tempArray = (E[]) new Object[arrSize];
        for (int i = 0; i < arrSize - 2; i++) {
            if (i < maxValue)
                tempArray[i] = this.array[i];
            else if (i > maxValue)
                tempArray[i] = this.array[i + 1];
        }
        // Returns the max value and updates the element count and array
        arrCount -= 1;
        this.array = tempArray;
        return currentMax;
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item - the element to insert
     */
    @SuppressWarnings("unchecked")
    @Override
    public void insert(E item) {
        int mid = BinarySearchMethod(this.array, this.arrCount, this.arrSize, (E) item);
        // Grows the array if needed
        if (this.arrCount + 1 == this.arrSize) {
            this.arrSize *= 2;
            E[] tempArray = (E[]) new Object[this.arrSize];
            for(int i = 0; i < this.arrCount; i++){
                tempArray[i] = this.array[i];
            }
            this.array = tempArray;
        }
        // Creating a new array that now finds the correct position from binary search

        // Increasing array count due to new element

        for(int i = arrCount; i >= mid; i--){
                this.array[i + 1] = this.array[i];
        }
        this.arrCount += 1;
        this.array[mid] = (E) item;
    }

    /**
     * Helper method that handles all the binary search logic for insert and insert all
     *
     * @param item - (E) item that is being inserted into the array at the correct index
     * @return array - (E[]) array that now contains the desired item at the correct index.
     */
    @SuppressWarnings("unchecked")
    private int BinarySearchMethod(E[] thisArray, int thisArrayCount, int thisArraySize, E item) {
        // Takes in an array of the specified size
        // Setting low, high and mid variables. Then changing values to locate the correct position for the item.
        int low = 0;
        int high = thisArrayCount - 1;
        int mid = (high + low) / 2;
        while(low <= high) {
            mid = (high + low) / 2;
            int result = helpCompare(item, thisArray[mid]);
            if (result < 0){
                high = mid - 1;
            }
            else if (result == 0){
                return mid;
            }
            else{
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll - the collection of elements to insert
     */
    @SuppressWarnings("unchecked")
    @Override
    public void insertAll(Collection<? extends E> coll) {
        // Translating the collection to an array that can be iterated through
        E[] collArray = (E[]) coll.toArray();
        for(int i = 0; i < collArray.length; i++){
            // insert the value into the object
            this.insert(collArray[i]);
        }
    }

    // method used in tests to help with comparisons of final results
    public E[] getArray(){
        return this.array;
    }


    /**
     * Indicates whether this priority queue contains the specified element.
     *
     * @param item - the element to be checked for containment in this priority queue
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object item) {
        // Use binary search to find the corresponding index where this item would be
        int index = BinarySearchMethod(this.array, this.arrCount, this.arrSize, (E) item);
        // take care of Double instances
        if (item instanceof Double) {
            if (Math.abs((Double) item - (Double) this.array[index]) < .01) {
                return true;
            }
            return false;
        }
        // check if the item is equal to the item at the corresponding index in the array
        if (this.array[index] != item)
            return false;
        return true;
    }

    /**
     * @return the number of elements in this priority queue
     */
    @Override
    public int size() {
        // return the number of elements inside the object
        return this.arrCount;
    }

    /**
     * @return true if this priority queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // if there are no elements inside the object
        if (this.arrCount == 0) {
            return true;
        }
        return false;
    }

    /**
     * Removes all the elements from this priority queue. The queue will be
     * empty when this call returns.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        // if the size of the array is 0 or there are no elements in the array
        if(this.arrSize == 0 || this.arrCount == 0)
            // don't do anything
            return;
        // create a new blank array that will replace the current one in the object
        E[] newArray = (E[]) new Object[]{};
        // reset all the values in the object
        this.arrCount = 0;
        this.arrSize = 10;
        this.array = newArray;
    }
}
