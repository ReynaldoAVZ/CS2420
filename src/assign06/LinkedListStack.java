package assign06;

import java.util.NoSuchElementException;

/**
 * This Java class represents a LinkedListStack that has built in methods to use different functions of a
 * SinglyLinkedList. This class is backed by an object of SinglyLinkedList.
 * <p></p>
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 * @version 2023-10-19
 */
public class LinkedListStack<E> implements Stack<E> {
    // Initializing the SinglyLinkedList structure that backs this class
    private SinglyLinkedList<E> SLL;

    /**
     * Constructor method for creating a LinkedListStack backed by a SinglyLinkedList
     */
    public LinkedListStack() {
        this.SLL = new SinglyLinkedList<>();
    }

    /**
     * Removes all the elements from the stack.
     */
    @Override
    public void clear() {
        this.SLL.clear();
    }

    /**
     * @return true if the stack contains no elements; false, otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.SLL.isEmpty();
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        return this.SLL.getFirst();
    }

    /**
     * Returns and removes the item at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() throws NoSuchElementException {
        return this.SLL.deleteFirst();
    }

    /**
     * Adds a given element to the stack, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    @Override
    public void push(E element) {
        this.SLL.insertFirst(element);
    }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return this.SLL.size();
    }
}
