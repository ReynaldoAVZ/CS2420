package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This Java class represents an SinglyLinkedList that has built in methods to use different functions of a Singly Linked List.
 * <p></p>
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-19
 */
public class SinglyLinkedList<T> implements List<T>, Iterable<T> {

    // Initializing instance variables
    private Node<T> head;
    private int highestIndex;

    /**
     * Zero Parameter constructor for a Singly Linked List. Sets the head equal to null and the starting index at -1.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.highestIndex = -1;
    }

    /**
     * Inserts an element at the beginning of the list.
     * Big-O Behavior: O(1) for a singly-linked list.
     *
     * @param element - the element to add to the SinglyLinkedList
     */
    public void insertFirst(T element) {
        // creating node which is going to be new head
        Node<T> newHead = new Node<>(element);
        // set the pointer of the new head to the old head
        newHead.setNext(this.head);
        // update the new head to be the head of the current SinglyLinkedList (SLL)
        this.head = newHead;
        // incrementing index
        this.highestIndex++;
    }

    /**
     * Inserts an element at a specific position in the list.
     * Big-O Behavior: O(N) for a singly-linked list.
     *
     * @param index   - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    public void insert(int index, T element) throws IndexOutOfBoundsException {
        // throwing exception if index is not within list bounds
        if (index < 0 || index > highestIndex + 1) {
            throw new IndexOutOfBoundsException();
        }
        // create a tempNode that contains the head of the SLL
        Node<T> tempNode = head;
        // iterate up to the wanted index where a value needs to be inserted
        for (int i = 1; i < index; i++) {
            tempNode = tempNode.getNext();
        }
        // inserting by changing what the surrounding nodes point to
        Node<T> holder = tempNode.getNext();
        Node<T> newNode = new Node<>(element);
        tempNode.setNext(newNode);
        newNode.setNext(holder);
        // increment index with new inserted node
        highestIndex++;
    }

    /**
     * Gets the first element in the list.
     * Big-O Behavior: O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T getFirst() throws NoSuchElementException {
        // if there is no head assigned or the index is still -1
        if (head == null || highestIndex < 0) {
            throw new NoSuchElementException();
        }
        // returns value for the first node (head)
        return head.value();
    }

    /**
     * Gets the element at a specific position in the list.
     * Big-O Behavior: O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // if the index is below 0 (not valid) or higher than the current amount of elements in the SLL
        if (index < 0 || index > highestIndex) {
            throw new IndexOutOfBoundsException();
        }
        // create a tempNode that contains the head of the current SLL
        Node<T> tempNode = this.head;

        // iterating through each node by temp Node up to the wanted index
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.getNext();
        }
        // return value of node at specified index
        return tempNode.value();
    }

    /**
     * Deletes and returns the first element from the list.
     * Big-O Behavior: O(1) for a singly-linked list.
     *
     * @return the first element in the SLL and remove it from the SLL.
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T deleteFirst() throws NoSuchElementException {
        // exception for empty list (there is no head assigned)
        if (this.head == null || highestIndex < 0) {
            throw new NoSuchElementException();
        }
        // deleting by updating head to the next node and decrementing highest index
        T temp = head.value();
        this.head = this.head.getNext();
        highestIndex--;
        // return the value of the removed head
        return temp;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * Big-O Behavior: O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        // exception if the index is below zero (not valid) or greater than the amount of elements in the SLL
        if (index < 0 || index > highestIndex) {
            throw new IndexOutOfBoundsException();
        }
        // create a tempNode that holds the head of the SLL
        Node<T> tempNode = head;
        // if the index is equal to the position of the head
        if (index == 0) {
            T val = this.deleteFirst();
            return val;
        }
        // iterating to find the previous node before deleted
        for (int i = 1; i < index; i++) {
            tempNode = tempNode.getNext();
        }
        // previous node points to node after "deleted" node
        T value = tempNode.getNext().value();
        tempNode.setNext(tempNode.getNext().getNext());
        // decrementing index count in list
        highestIndex--;
        return value;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * Big-O Behavior: O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    public int indexOf(T element) {
        // create a tempNode that holds the node of the head of the SLL
        Node<T> tempNode = this.head;
        // create a value variable that holds the value of the head of the SLL
        T value = this.head.value();
        // iterating through loop with a temporary node to return the index of the node
        for (int i = 0; i <= this.highestIndex; i++) {
            // if i is not the very first element in the loop
            if (i != 0) {
                tempNode = tempNode.getNext();
                value = tempNode.value();
            }
            // compare the current value with the element we are searching for. if they are equal, return the current index
            if (value.equals(element)) {
                return i;
            }
        }
        // if the element is not found within the SLL
        return -1;
    }

    /**
     * Returns the size of the current SLL.
     * Big-O Behavior: O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.highestIndex + 1;
    }

    /**
     * Returns the state of the SLL (whether it is empty or not)
     * Big-O Behavior: O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        // if the head has not had an assignment or the highestIndex is still -1
        if (this.head == null || this.highestIndex < 0) {
            return true;
        }
        // the SLL has values in it
        else {
            return false;
        }
    }

    /**
     * Removes all the elements from the SLL.
     * Big-O Behavior: O(1) for a singly-linked list.
     */
    @Override
    public void clear() {
        // remove the head from the SLL, such that all the nodes are no longer accessible
        this.head = null;
        // reset the highest index back to the empty status
        highestIndex = -1;
    }

    /**
     * Generates an array containing all the elements in this list in proper sequence
     * (from first element to last element).
     * Big-O Behavior: O(N) for a singly-linked list.
     *
     * @return an array containing all the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        // Initializing object array with specified "size" of SinglyLinkedList
        Object[] array = new Object[highestIndex + 1];
        Node<T> tempNode = this.head;
        // holder variable for node values
        T value = tempNode.value();
        array[0] = value;
        // storing node values into array
        for (int i = 1; i <= highestIndex; i++) {
            tempNode = tempNode.getNext();
            value = tempNode.value();
            array[i] = value;
        }
        return array;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    @Override
    public Iterator<T> iterator() {
        // Initializing iterator for SinglyLinkedList and nodes
        Iterator<T> iter = new Iterator<>() {
            Node<T> currNode = null;
            Node<T> prevNode = null;
            Node<T> nextNode = head;
            // boolean flag
            boolean canRemove = false;
            int next_index = 0;
            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                // if the next node isn't null (which means there is a current valid node placed in the iterator)
                if (this.nextNode != null) {
                    return true;
                }
                // the next node is null so there are no more elements that can be traversed
                return false;
            }

            /**
             * Returns the next element in the iteration and moves the iterator by one.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public T next() {
                // flag variable used to keep track of whether remove() and next() have been properly called
                canRemove = true;
                // updating nodes to show iterator has moved to next
                prevNode = currNode;
                currNode = nextNode;
                nextNode = nextNode.getNext();
                next_index++;
                // if the current node that the iterator is on, throw an error because that's not good
                if (currNode == null) {
                    throw new NoSuchElementException();
                }
                // the current node is not null so return that value
                else {
                    return currNode.value();
                }
            }

            /**
             * Removes from the underlying collection the last element returned
             * by this iterator (optional operation).  This method can be called
             * only once per call to {@link #next}.
             * <p>
             * @throws IllegalStateException         if the {@code next} method has not
             *                                       yet been called, or the {@code remove} method has already
             *                                       been called after the last call to the {@code next}
             *                                       method
             */
            @Override
            public void remove() {
                // update flag variable so remove() can't be called twice in a row without calling next()
                if(!canRemove) {
                    throw new IllegalStateException();
                }
                // setting current node to previous
                currNode = prevNode;

                // setting previous node to next if not null
                if (prevNode != null) {
                    prevNode.setNext(nextNode);
                }
                // decrementing index values and setting boolean to false
                highestIndex--;
                canRemove = false;
            }
        };
        return iter;
    }

    /**
     * Node class represents a single object that has a value and a pointer to the next node. this backs the singly linked list.
     * @param <T> - Any generic type that the node holds
     */
    private static class Node<T> {
        // Instance variables for Node object
        private T current;
        private Node<T> next = null;

        /**
         * This constructor method allows to instantiate Node objects
         *
         * @param item the data stored in each node
         */
        public Node(T item) {
            this.current = item;
        }

        /**
         * This method allows to specify what node is set to be next after
         * another node.
         *
         * @param nextNode The node being set next
         */
        public void setNext(Node<T> nextNode) {
            this.next = nextNode;
        }

        /**
         * This method accesses the data or value that each node contains
         *
         * @return the value of the node
         */
        public T value() {
            return current;
        }

        /**
         * This method accesses the next node of the specified node
         *
         * @return the next node
         */
        public Node<T> getNext() {
            return this.next;
        }
    }
}