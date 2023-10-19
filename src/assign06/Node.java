package assign06;

public class Node<T> {
    private T current;
    private Node<T> next = null;
    public Node(T item) {
        this.current = item;
    }

    public void setNext(Node<T> nextNode) {
        this.next = nextNode;
    }

    public T value() {
        return current;
    }

    public Node<T> getNext() {
        return this.next;
    }
}
