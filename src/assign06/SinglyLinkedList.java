package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This Java class represents an SinglyLinkedList that has built in methods to use different functions of a Singly Linked List.
 * <p></p>
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-00
 */
public class SinglyLinkedList<T> implements List<T>, Iterable<T> {

    private Node<T> head;
    private int highestIndex;

    /**
     * Zero Parameter constructor for a Singly Linked List
     */
    public SinglyLinkedList() {
        this.head = null;
        this.highestIndex = -1;
    }

    /**
     * Inserts an element at the beginning of the list.
     * O(1) for a singly-linked list.
     *
     * @param element - the element to add
     */
    public void insertFirst(T element) {
        Node<T> newHead = new Node<>(element);
        newHead.setNext(this.head);
        this.head = newHead;
        this.highestIndex++;
    }

    /**
     * Inserts an element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index   - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    public void insert(int index, T element) throws IndexOutOfBoundsException {
        if (index < 0 || index > highestIndex + 1) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> tempNode = head;

        for (int i = 1; i < index; i++) {
            tempNode = tempNode.getNext();
        }

        Node<T> holder = tempNode.getNext();
        Node<T> newNode = new Node<>(element);
        tempNode.setNext(newNode);
        newNode.setNext(holder);
        highestIndex++;
    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T getFirst() throws NoSuchElementException {
        if (head == null || highestIndex < 0)
            throw new NoSuchElementException();

        return head.value();
    }

    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > highestIndex)
            throw new IndexOutOfBoundsException();

        Node<T> tempNode = this.head;

        for (int i = 0; i < index; i++) {
            tempNode = tempNode.getNext();
        }
        return tempNode.value();
    }

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T deleteFirst() throws NoSuchElementException {
        if (this.head == null || highestIndex < 0) {
            throw new NoSuchElementException();
        }
        T temp = head.value();
        this.head = this.head.getNext();
        highestIndex--;
        return temp;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > highestIndex)
            throw new IndexOutOfBoundsException();

        Node<T> tempNode = head;
        if (index == 0) {
            T val = this.deleteFirst();
            return val;
        }

        for (int i = 1; i < index; i++)
            tempNode = tempNode.getNext();

        T value = tempNode.getNext().value();
        tempNode.setNext(tempNode.getNext().getNext());
        highestIndex--;
        return value;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    public int indexOf(T element) {
        Node<T> tempNode = this.head;
        T value = this.head.value();

        for (int i = 0; i <= this.highestIndex; i++) {
            if (i != 0) {
                tempNode = tempNode.getNext();
                value = tempNode.value();
            }
            if (value.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.highestIndex + 1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        if (this.head == null || this.highestIndex < 0)
            return true;

        else
            return false;
    }

    /**
     * Removes all the elements from this list.
     * O(1) for a singly-linked list.
     */
    @Override
    public void clear() {
        this.head = null;
        highestIndex = -1;
    }

    /**
     * Generates an array containing all the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) for a singly-linked list.
     *
     * @return an array containing all the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        this.array = new Object[highestIndex + 1];
        Node<T> tempNode = this.head;
        T value = tempNode.value();
        array[0] = value;
        for (int i = 1; i <= highestIndex; i++) {
            tempNode = tempNode.getNext();
            value = tempNode.value();
            array[i] = value;
        }
        return this.array;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> iter = new Iterator<>() {
            Node<T> currNode = null;
            Node<T> prevNode = null;
            Node<T> nextNode = head;
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
                if (this.nextNode != null) {
                    return true;
                }
                return false;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public T next() {
                canRemove = true;
                prevNode = currNode;
                currNode = nextNode;
                nextNode = nextNode.getNext();
                next_index++;
                if (currNode == null) {
                    throw new NoSuchElementException();
                }
                else
                    return currNode.value();
            }

            /**
             * Removes from the underlying collection the last element returned
             * by this iterator (optional operation).  This method can be called
             * only once per call to {@link #next}.
             * <p>
             *
             * @throws UnsupportedOperationException if the {@code remove}
             *                                       operation is not supported by this iterator
             * @throws IllegalStateException         if the {@code next} method has not
             *                                       yet been called, or the {@code remove} method has already
             *                                       been called after the last call to the {@code next}
             *                                       method
             */
            @Override
            public void remove() {
                if(!canRemove) {
                    throw new IllegalStateException();
                }
                currNode = prevNode;
                prevNode.setNext(nextNode);
                highestIndex--;
                canRemove = false;
            }
        };
        return iter;
    }
}