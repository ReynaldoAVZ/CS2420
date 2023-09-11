package assign03;

import assign02.CS2420StudentGeneric;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class SimplePriorityQueue<E> implements PriorityQueue{
    private E[] array;

    private int arrSize;
    private Comparator<? super E> cmp;

    private int arrCount = 0;

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        this.arrSize = 10;
        this.array = (E[]) new Object[this.arrSize];
    }

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp){
        this.arrSize = 10;
        this.array = (E[]) new Object[this.arrSize];
        this.cmp = cmp;
    }
    @SuppressWarnings("unchecked")
    private int helpCompare (E o1, E o2){
        if (this.cmp == null)
            if(o2 == null)
                return 0;
            else
                return ((Comparable<? super E>)o1).compareTo(o2);

        else
            if (o2 == null)
                return 0;
            else
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
        if (this.arrCount == 0) {
            throw new NoSuchElementException();
        }

        E currentMax = this.array[0];
        for (int i = 1; i < arrSize - 1; i++) {
            int comparison = this.helpCompare(currentMax, array[i]);
            if (comparison < 0) {
                currentMax = array[i];
            }
        }

        return currentMax;
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
        if (this.arrSize == 0 || this.arrCount == 0) {
            throw new NoSuchElementException();
        }
        E currentMax = this.array[0];
        int maxValue = 0;
        for (int i = 1; i < arrSize - 1; i++) {
            int comparison = this.helpCompare(currentMax, array[i]);
            if (comparison < 0) {
                currentMax = array[i];
                maxValue = i;
            }
        }
        E[] tempArray = (E[]) new Object[arrSize];
        for (int i = 0; i < arrSize - 2; i++) {
            if (i < maxValue)
                tempArray[i] = this.array[i];
            else if (i > maxValue)
                tempArray[i] = this.array[i + 1];
        }
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
    public void insert(Object item) {
        if (this.arrCount + 1 == this.arrSize) {
            this.arrSize *= 2;
        }
        E[] tempArray = (E[]) new Object[this.arrSize];
        this.arrCount += 1;
        int mid = BinarySearchMethod(this.array, this.arrCount, this.arrSize, (E) item);
        for(int i = 0; i < this.arrCount; i++){
            if(i < mid){
                tempArray[i] = this.array[i];
            }
            else {
                tempArray[i + 1] = this.array[i];
            }
        }
        tempArray[mid] = (E) item;
        this.array = tempArray;
    }

    /**
     * Helper method that handles all the binary search logic for insert and insert all
     *
     * @param item - (E) item that is being inserted into the array at the correct index
     * @return array - (E[]) array that now contains the desired item at the correct index.
     */
    @SuppressWarnings("unchecked")
    private int BinarySearchMethod(E[] thisArray, int thisArrayCount, int thisArraySize, E item) {
        E[] tempArray = (E[]) new Object[thisArraySize];
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
                break;
            }
            else{
                low = mid + 1;
            }
        }
        return mid;
    }

    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll - the collection of elements to insert
     */
    @SuppressWarnings("unchecked")
    @Override
    public void insertAll(Collection coll) {
        Object[] collArray = coll.toArray();
        for(int i = 0; i < collArray.length; i++){
            this.insert(collArray[i]);
        }
    }
// check piazza
    protected E[] getArray(){
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
        int index = BinarySearchMethod(this.array, this.arrCount, this.arrSize, (E) item);
        if (item instanceof Double) {
            if (Math.abs((Double) item - (Double) this.array[index]) < .01) {
                return true;
            }
            return false;
        }
        if (this.array[index] != item)
            return false;
        return true;
    }

    /**
     * @return the number of elements in this priority queue
     */
    @Override
    public int size() {
        return this.arrCount;
    }

    /**
     * @return true if this priority queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
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
        if(this.arrSize == 0 || this.arrCount == 0)
            return;
        E[] newArray = (E[]) new Object[]{};
        this.arrCount = 0;
        this.arrSize = 10;
        this.array = newArray;
    }

    // Create the comparators for ints, doubles, and strings
    /**
     * Comparator that defines an ordering among a SimplePriorityQueue of Integers using their value.
     */
    public class OrderInt implements Comparator<Integer> {
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
    public class OrderString implements Comparator<String> {

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
    public class OrderDouble implements Comparator<Double> {

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
}
