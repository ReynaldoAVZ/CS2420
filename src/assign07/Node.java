package assign07;

import java.util.LinkedList;

/**
 * This class represents a single generic node that contains a value as well as a LinkedList of all the neighbors of
 * this node. A node can be thought of as a vertex.
 * <p></p>
 * @author Reynaldo Villarreal Zambrano
 * @version October 26, 2023
 */
public class Node<Type> {
    private Node<Type> prev; // Previous node in the path.
    private Type val; // Value stored in the node.
    private LinkedList<Node<Type>> neighbors; // List of neighboring nodes.
    private boolean visited; // Flag to mark if the node has been visited during traversal.
    private int inDegree; // Number of incoming edges (used in directed graphs).

    /**
     * Constructs a new node with the specified value.
     *
     * @param value - The value to be stored in the node.
     */
    protected Node(Type value) {
        this.val = value;
        this.visited = false;
        this.neighbors = new LinkedList<>();
        this.prev = null;
        this.inDegree = 0;
    }

    /**
     * Sets the previous node in the path.
     *
     * @param node - The previous node.
     */
    protected void setPrev(Node<Type> node) {
        this.prev = node;
    }

    /**
     * Adds a neighbor to the list of neighbors for this node.
     *
     * @param neighbor - The neighbor node to be added.
     */
    protected void setNeighbor(Node<Type> neighbor) {
        this.neighbors.add(neighbor);
    }

    /**
     * Marks the node as visited during traversal.
     */
    protected void setVisited() {
        this.visited = true;
    }

    /**
     * Checks if the node has been visited during traversal.
     *
     * @return True if the node has been visited, otherwise false.
     */
    protected boolean getVisited() {
        return this.visited;
    }

    /**
     * Retrieves the list of neighboring nodes.
     *
     * @return A list of neighboring nodes.
     */
    protected LinkedList<Node<Type>> getNeighbors() {
        return this.neighbors;
    }

    /**
     * Retrieves the value stored in the node.
     *
     * @return The value stored in the node.
     */
    protected Type getValue() {
        return this.val;
    }

    /**
     * Retrieves the previous node in the path.
     *
     * @return The previous node in the path.
     */
    protected Node<Type> getPrev() {
        return this.prev;
    }

    /**
     * Increments or decrements the in-degree of the node (used in directed graphs).
     */
    protected void changeInDegree(int value) {
        this.inDegree = this.inDegree + value;
    }

    /**
     * Returns the value of in-degree of the node.
     * @return inDegree - Number of edges to this node (used for topological sort)
     */
    protected int getInDegree() {
        return this.inDegree;
    }
}
