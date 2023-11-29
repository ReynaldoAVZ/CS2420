package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The BinaryMaxHeap class represents a PriorityQueue that uses a generic backing array to represent the binary max heap.
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @versions 11-28-23
 * @param <E>
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
    private E[] binaryMaxHeapList = null;
    private int size;
    private Comparator<? super E> cmp = null;

    /**
     * If this constructor is used to create an empty binary heap, it is assumed that the elements are ordered using
     * their natural ordering (i.e., E implements Comparable<? super E>).
     */
    public BinaryMaxHeap() {
        this.binaryMaxHeapList = (E[]) new Object[10];
        this.size = 0;
        this.cmp = null;
    }

    /**
     * If this constructor is used to create an empty binary heap, it is assumed that the elements are ordered using
     * the provided Comparator object.
     *
     * @param comparator
     */
    public BinaryMaxHeap(Comparator<? super E> comparator) {
        this.binaryMaxHeapList = (E[]) new Object[10];
        this.size = 0;
        this.cmp = comparator;
    }

    /**
     * If this constructor is used, then the binary heap is constructed from the given list.
     * Also, it is assumed that the elements are ordered using their natural ordering (i.e., E implements
     * Comparable<? super E>).
     * RECALL: Using the buildHeap operation, we can construct a binary heap from
     * these N items in O(N) time, which is more efficient than adding them to the binary heap one at a time.
     * This constructor must use such an operation.
     *
     * @param list
     * @param
     */
    public BinaryMaxHeap(List<? extends E> list) {
        this.binaryMaxHeapList = (E[]) new Object[list.size()];
        this.size = list.size();
        this.cmp = null;
        buildHeap(list);
    }

    /**
     * If this constructor is used, then the binary heap is constructed from the given list (see RECALL note above).
     * Also, it is assumed that the elements are ordered using the provided Comparator object.
     *
     * @param list
     * @param comparator
     */
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> comparator) {
        this.binaryMaxHeapList = (E[]) new Object[list.size()];
        this.size = list.size();
        this.cmp = comparator;
        buildHeap(list);
    }

    private void buildHeap(List<? extends E> list) {
        // add all values to the heap
        for (int i = 0; i < list.size(); i++) {
            binaryMaxHeapList[i] = list.get(i);
        }
        // percolate it so that it satisfies the max condition
        percolateDown();
    }

    /**
     * The percolateDown() method that takes no parameters is only used in the buildHeap() method. This is when we build
     * our stack from a passed list, therefore we must check all parents and place everything in the correct order.
     */
    private void percolateDown() {
        // get the last parent index (far right most object)
        int lastIndex = (this.size / 2) - 1;
        E currentVal;
        int currentValIndex;
        for (int i = lastIndex; i >= 0; i--) { // iterate from right most parent to the root
            currentVal = binaryMaxHeapList[i];
            currentValIndex = i;
            while (true) { // this will break when the currentVal no longer has a child or is placed correctly
                int leftIndex = (2 * currentValIndex) + 1;
                int rightIndex = (2 * currentValIndex) + 2;
                // case 1: no children
                if (leftIndex >= this.size && rightIndex >= this.size) {
                    break; // no children to make a comparison with, which means our current object is already at the bottom
                }
                // case 2: only left child
                else if (leftIndex <= this.size && rightIndex >= this.size) { // check that the rightIndex is greater than the size and that the leftIndex is valid
                    E leftChild = binaryMaxHeapList[leftIndex];
                    if (innerCompare(currentVal, leftChild) < 0) { // left child is greater than the parent, so swap
                        E tempVal = currentVal;
                        this.binaryMaxHeapList[currentValIndex] = leftChild;
                        this.binaryMaxHeapList[leftIndex] = tempVal;
                        currentValIndex = leftIndex;
                    }
                    else {
                        break;
                    }
                }
                // case 3: two children
                else { 
                    E leftChild = binaryMaxHeapList[leftIndex];
                    E rightChild = binaryMaxHeapList[rightIndex];
                    if (innerCompare(leftChild, rightChild) == 0) { // choose the right child for potential swap
                        if (innerCompare(currentVal, rightChild) > 0) {
                            E tempVal = currentVal;
                            this.binaryMaxHeapList[currentValIndex] = rightChild;
                            this.binaryMaxHeapList[rightIndex] = tempVal;
                            currentValIndex = rightIndex;
                        }
                        else { // currentVal is greater than the right child, so no swap
                            break;
                        }
                    }
                    else if (innerCompare(leftChild, rightChild) > 0) { // left child is greater than right child
                        if (innerCompare(currentVal, leftChild) < 0) { // swap left and currentVal
                            E tempVal = currentVal;
                            this.binaryMaxHeapList[currentValIndex] = leftChild;
                            this.binaryMaxHeapList[leftIndex] = tempVal;
                            currentValIndex = leftIndex;
                        }
                        else { // left child is less than parent
                            break;
                        }
                    } else { // right child is greater than left child
                        if (innerCompare(currentVal, rightChild) < 0) { // right child is greater than parent
                            E tempVal = currentVal;
                            this.binaryMaxHeapList[currentValIndex] = rightChild;
                            this.binaryMaxHeapList[rightIndex] = tempVal;
                            currentValIndex = rightIndex;
                        }
                        else { // right child is less than current val
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * The percolateDown() method is an overloaded method that takes in an index. This index corresponds with the value
     * that needs to be percolated down the heap.
     * @param index
     */
    private void percolateDown(int index) {
        // get the last parent index (far right most object)
        E currentVal = this.binaryMaxHeapList[index];
        int currentValIndex = index;
        while (true) { // this will break when the currentVal no longer has a child or is placed correctly
            int leftIndex = (2 * currentValIndex) + 1;
            int rightIndex = (2 * currentValIndex) + 2;
            // case 1: no children
            if (leftIndex >= this.size && rightIndex >= this.size) {
                break; // no children to make a comparison with, which means our current object is already at the bottom
            }
            // case 2: only left child
            else if (leftIndex <= this.size && rightIndex >= this.size) { // check that the rightIndex is greater than the size and that the leftIndex is valid
                E leftChild = binaryMaxHeapList[leftIndex];
                if (innerCompare(currentVal, leftChild) < 0) { // left child is greater than the parent, so swap
                    E tempVal = currentVal;
                    this.binaryMaxHeapList[currentValIndex] = leftChild;
                    this.binaryMaxHeapList[leftIndex] = tempVal;
                    currentValIndex = leftIndex;
                }
                else {
                    break;
                }
            }
            // case 3: two children
            else {
                E leftChild = binaryMaxHeapList[leftIndex];
                E rightChild = binaryMaxHeapList[rightIndex];
                if (innerCompare(leftChild, rightChild) == 0) { // choose the right child for potential swap
                    if (innerCompare(currentVal, rightChild) > 0) {
                        E tempVal = currentVal;
                        this.binaryMaxHeapList[currentValIndex] = rightChild;
                        this.binaryMaxHeapList[rightIndex] = tempVal;
                        currentValIndex = rightIndex;
                    }
                    else { // currentVal is greater than the right child, so no swap
                        break;
                    }
                }
                else if (innerCompare(leftChild, rightChild) > 0) { // left child is greater than right child
                    if (innerCompare(currentVal, leftChild) < 0) { // swap left and currentVal
                        E tempVal = currentVal;
                        this.binaryMaxHeapList[currentValIndex] = leftChild;
                        this.binaryMaxHeapList[leftIndex] = tempVal;
                        currentValIndex = leftIndex;
                    }
                    else { // left child is less than parent
                        break;
                    }
                } else { // right child is greater than left child
                    if (innerCompare(currentVal, rightChild) < 0) { // right child is greater than parent
                        E tempVal = currentVal;
                        this.binaryMaxHeapList[currentValIndex] = rightChild;
                        this.binaryMaxHeapList[rightIndex] = tempVal;
                        currentValIndex = rightIndex;
                    }
                    else { // right child is less than current val
                        break;
                    }
                }
            }
        }
    }

    private int innerCompare(E object1, E object2) {
        if (this.cmp == null) { // check if this BinaryMaxHeap has a comparator
            int result = ((Comparable<? super E>)object1).compareTo(object2);
            return result;
        }
        else { // the current BinaryMaxHeap has a comparator
            int result = cmp.compare(object1, object2);
            return result;
        }
    }
    /**
     * Adds the given item to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param item
     */
    @Override
    public void add(E item) {
        if (this.size == this.binaryMaxHeapList.length) { // check if we've reached the maximum number of elements in the backing array
            resize();
        }
        this.binaryMaxHeapList[this.size] = item;
        this.size++;
        percolateUp(); // place object in correct position
    }

    /**
     * The percolateUp method is a private helper method used for insertions.
     */

    private void percolateUp() {
        int percolatingIndex = this.size - 1; // find the index of the object that was added
        E percolatingObject = this.binaryMaxHeapList[percolatingIndex]; // get the percolating object
        while (true) { // iterate until the object is in the correct position
            // find the parent of the percolating object
            int parentIndex = (percolatingIndex - 1) / 2;
            E parent = this.binaryMaxHeapList[parentIndex];
            if (innerCompare(percolatingObject, parent) > 0) { // percolating object is greater than the parent, swap
                E temp = percolatingObject;
                this.binaryMaxHeapList[percolatingIndex] = parent;
                this.binaryMaxHeapList[parentIndex] = temp;
                percolatingIndex = parentIndex;
            }
            else {
                break;
            }
        }
    }

    /**
     * The resize method is a private helper method that grows the size of the array when the maximum capacity is reached.
     */
    private void resize() {
        E[] newArray = (E[]) new Object[this.size * 2]; // create new array that has double the capacity
        for (int i = 0; i < this.size; i++) { // add all values from old array into new array
            newArray[i] = this.binaryMaxHeapList[i];
        }
        this.binaryMaxHeapList = newArray; // reassign old array as new array
    }

    /**
     * Returns, but does not remove, the maximum item this priority queue.
     * O(1)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        // check if there are any elements in the binary max heap or if it's even been instantiated
        if (this.size == 0 || this.binaryMaxHeapList == null) {
            throw new NoSuchElementException();
        }
        // return the very first element (which has the highest priority)
        return this.binaryMaxHeapList[0];
    }

    /**
     * Returns and removes the maximum item this priority queue.
     * O(log N)
     *
     * @return the maximum item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extractMax() throws NoSuchElementException {
        if (this.size == 0 || this.binaryMaxHeapList == null) { // if the backing array is empty
            throw new NoSuchElementException();
        }
        E maxVal = this.binaryMaxHeapList[0]; // get the root of the binary max heap
        this.binaryMaxHeapList[0] = this.binaryMaxHeapList[this.size - 1]; // place last object as root
        this.binaryMaxHeapList[this.size - 1] = null; // place a null object in last position
        this.size--;
        percolateDown(0); // percolate down the root to correct position
        return maxVal;
    }

    /**
     * Returns the number of items in this priority queue.
     * O(1)
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this priority queue is empty, false otherwise.
     * O(1)
     */
    @Override
    public boolean isEmpty() {
        if (this.size == 0)
            return true;
        else
            return false;
    }

    /**
     * Empties this priority queue of items.
     * O(1)
     */
    @Override
    public void clear() {
        this.binaryMaxHeapList = (E[]) new Object[10]; // create a new empty array
        this.size = 0;
    }

    /**
     * Creates and returns an array of the items in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is needed for grading purposes. The root item
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    @Override
    public Object[] toArray() {
        Object[] newArray = (E[]) new Object[this.size]; // create a new array with same capacity as backing array
        for (int i = 0; i < this.size; i++) { // place all objects into the new array
            newArray[i] = this.binaryMaxHeapList[i];
        }
        return newArray; // return the copy array
    }
}
