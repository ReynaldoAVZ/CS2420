package assign07;

import java.util.*;

/**
 * Graph represents a generic directed graph with various graph-related operations. This class allows you to create and
 * manipulate directed graphs, perform depth-first search, breadth-first search, and topological sort on the graph, and
 * retrieve nodes by their values.
 * <p></p>
 * @author Reynaldo Villarreal Zambrano
 * @version October 26, 2023
 */
public class Graph<Type> {
    /**
     * Lookup for vertices by their value.
     */
    private final HashMap<Type, Node<Type>> map;
    private Queue<Node<Type>> enqueue;

    /**
     * Construct empty graph.
     */
    public Graph() {
        this.map = new HashMap<>();
    }

    /**
     * Adds a new node with the specified value to the graph if it doesn't already exist.
     *
     * @param value - The value to be added to the graph as a new node.
     */
    protected void addVal(Type value) {
        // if the map does not contain already the current value we're trying to add in
        if (!this.map.containsKey(value)) {
            // create a new node with this value
            Node<Type> node = new Node<>(value);
            // put this node into our map
            this.map.put(value, node);
        }
    }

    /**
     * The DepthFirstSearch method implements a depth-first search algorithm in order to determine which other vertex
     * (the neighbors) are visited from this current vertex.
     * <p></p>
     * @param vertex - The node at which our Depth-First Search algorithm begins.
     */
    protected void DepthFirstSearch(Node<Type> vertex) {
        this.enqueue = new LinkedList<>();
        // set the current vertex's internal flag as visited (true)
        vertex.setVisited();
        // iterate over the neighbors of the current vertex
        for (Node<Type> neighbor : vertex.getNeighbors()) {
            // if the current neighbor of the vertex has not been visited yet
            if (!neighbor.getVisited()) {
                // set the previous node to point towards the previous node before recursive call
                neighbor.setPrev(vertex);
                // recursive call on neighbor vertex
                DepthFirstSearch(neighbor);
            }
        }
    }

    /**
     * The BreadthFirstSearch method implements a breadth-first search algorithm in order to determine the shortest path
     * to a vertex in our graph.
     * <p></p>
     * @param startVertex - The node at which our Breadth-First Search algorithm begins.
     * @param searchVertex - The node which we are searching for in our graph.
     * @param shortestPathList - The list in which the shortest path to the node we're searching for goes in.
     */
    protected void BreadthFirstSearch(Node<Type> startVertex, Node<Type> searchVertex, List<Type> shortestPathList) {
        // instantiate the enqueue with a LinkedList
        this.enqueue = new LinkedList<>();
        // add the starting vertex into the queue
        this.enqueue.add(startVertex);
        // set the starting vertex as having been visited
        startVertex.setVisited();
        // while the queue is not empty, perform the code below
        while(!this.enqueue.isEmpty()) {
            // get the current node that is next in the enqueue
            Node<Type> currNode = this.enqueue.remove();
            // for each of the neighbors that the current node points too
            for (Node<Type> neighbor : currNode.getNeighbors()) {
                // if the current neighbor has not been visited
                if (!neighbor.getVisited()) {
                    // set the neighbor as having been visited
                    neighbor.setVisited();
                    // assign the previous node that points to the current node
                    neighbor.setPrev(currNode);
                    // if the neighbor node equals the node we are looking for
                    if(neighbor.equals(searchVertex)) {
                        // call a private helper method that simply creates and alters the shortestPathList
                        createShortestPath(startVertex, neighbor, shortestPathList);
                        // get out of this method call since we've now found the shortest path and no longer need to search
                        return;
                    }
                    // because the neighbor is not equal to the node we're searching for, add the neighbor to the queue
                    this.enqueue.add(neighbor);
                }
            }
        }
    }

    /**
     * The createShortestPath method is a helper method used in BreadthFirstSearch that is called only when a certain
     * condition has been met inside the BreadthFirstSearch method. This method fills the passed in list such that
     * the list is now containing the nodes in which create the shortest path from a startVertex to a searchVertex (the
     * vertex that is being searched for) in order from startVertex to searchVertex.
     * <p></p>
     * @param startVertex - (Node<Type></Type>) The starting node for the BreadthFirstSearch method call.
     * @param neighbor -
     * @param shortestPathList
     */
    private void createShortestPath(Node<Type> startVertex, Node<Type> neighbor, List<Type> shortestPathList) {
        // assign a currNode that represents the last element we ended on
        Node<Type> currNode = neighbor;
        // create a temp list used to place things in correctly
        List<Type> tempList = new LinkedList<>();
        // iterate while our currNode has a node that came before it (won't include the fist element that we started at)
        while(currNode.getPrev() != null) {
            // add to the head our currNode to the shortest path list (linked list)
            tempList.add(currNode.getValue());
            // reassign our currNode to be the previous node that came before it
            currNode = currNode.getPrev();
        }
        // add the final element into the list
        if (currNode.equals(startVertex)) {
            tempList.add(currNode.getValue());
        }
        // add the value of the nodes from tempList in the correct order into shortestPathList
        for (int i = tempList.size() - 1; i >= 0; i--) {
            shortestPathList.add(tempList.remove(i));
        }
    }

    /**
     * The TopologicalSort method performs a topological sort on the entire graph, considering all nodes.
     * This method finds a linear ordering of nodes in the graph such that for every directed edge
     * (u, v), node 'u' comes before node 'v' in the ordering. The resulting ordering is stored
     * in the 'sortList'.
     * <p></p>
     * @param sortList The list to store the topologically sorted nodes.
     * @throws IllegalStateException if the graph contains a cycle, making a topological sort impossible.
     */
    protected void TopologicalSort(List<Type> sortList) {
        // Create a list to keep track of nodes with in-degrees.
        List<Node<Type>> nodesWithZeroInDegree = new ArrayList<>();

        // Find nodes with zero in-degree and add them to the list.
        for (Node<Type> node : map.values()) {
            if (node.getInDegree() == 0) {
                nodesWithZeroInDegree.add(node);
            }
        }

        // Perform topological sort using a queue.
        Queue<Node<Type>> queue = new LinkedList<>();
        // add all nodes with zero inDegree into the queue
        queue.addAll(nodesWithZeroInDegree);
        // while the queue is not empty
        while (!queue.isEmpty()) {
            // get the next node in the queue
            Node<Type> node = queue.poll();
            // add this node to the sortList that will be the final result
            sortList.add(node.getValue());
            // for each of the neighbors of the current node
            for (Node<Type> neighbor : node.getNeighbors()) {
                // decrement the InDegree of each neighbor by one
                neighbor.changeInDegree(-1);
                // if the current neighbor's inDegree is equal to zero (no more pointers)
                if (neighbor.getInDegree() == 0) {
                    // add this neighbor into the queue
                    queue.add(neighbor);
                }
            }
        }
        // Check for cycles (if not all nodes were included in the sort).
        if (sortList.size() != map.size()) {
            throw new IllegalArgumentException("The graph contains a cycle.");
        }
    }

    /**
     * Retrieves a node from the graph by its value.
     *
     * @param value The value associated with the node to be retrieved.
     * @return The node with the specified value, or null if not found.
     */
    protected Node<Type> get(Type value) {
        return this.map.get(value);
    }
}