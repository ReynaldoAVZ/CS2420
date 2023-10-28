package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private BinaryNode<Type> root;
    private int size = 0;
    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if (this.root == null) {
            this.root = new BinaryNode<>(item);
            return true;
        }
        BinaryNode<Type> currNode = this.root;
        BinaryNode<Type> itemNode = new BinaryNode<>(item);
        boolean result = this.add(currNode, itemNode);
        if (result == true) {
            this.size++;
        }
        return result;
    }
    private boolean add(BinaryNode<Type> currNode, BinaryNode<Type> item) {
        Type itemVal = item.getData();
        if (currNode == null) {
            currNode = item;
            return true;
        }
        Type currNodeVal = currNode.getData();
        if (itemVal.compareTo(currNodeVal) < 0) {
            // two cases: the currNode's left child is null and left child is not null

            // if the left child is null -> place item there
            if (currNode.getLeftChild() == null) {
                currNode.setLeftChild(item);
                return true;
            }

            // if the left child is not null -> we need to jump into that left child and add the item into the children of the left child
            else if (currNode.getLeftChild() != null) {
                return this.add(currNode.getLeftChild(), item);
            }
        }
        // two cases: the currNode's right child is null and right child is not null

        // if the right child is null -> place item there
        else if (itemVal.compareTo(currNodeVal) > 0) {
            if (currNode.getRightChild() == null) {
                currNode.setRightChild(item);
                return true;
            }
            // if the right child is not null -> we need to jump into that right child and add the item into the children of the right child
            else if (currNode.getRightChild() != null) {
                return this.add(currNode.getRightChild(), item);
            }
        }
        else { // a comparison results in 0 (currNode == item)
            return false;
        }
        return false;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean tracker = false;
        for (Type item : items) {
            boolean result = this.add(item);
            if (result == true) {
                tracker = true;
            }
        }
        return tracker;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        return this.contains(this.root, item);
    }

    private boolean contains(BinaryNode<Type> currentRoot, Type item) {

        if (currentRoot == null) {
            return false;
        }
        if (item.compareTo(currentRoot.getData()) < 0) {
            return this.contains(currentRoot.getLeftChild(), item);
        }
        else if (item.compareTo(currentRoot.getData()) > 0) {
            return this.contains(currentRoot.getRightChild(), item);
        }
        else {
            return true;
        }
    }
    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type item : items) {
            boolean result = this.contains(item);
            if (result == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if (this.root == null) {
            throw new NoSuchElementException();
        }
        return this.root.getLeftmostNode().getData();
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        if (this.root == null && this.size == 0)
            return true;

        return false;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if (this.root == null) {
            throw new NoSuchElementException();
        }
        return this.root.getRightmostNode().getData();
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        // checks if the item we're trying to remove is actually in the tree
        if (!this.contains(item))
            return false;
        // call private recursive method that removes the item
        boolean result = this.remove(this.root, item);
        if (result == true)
            this.size--;
        return result;
    }

    private boolean remove(BinaryNode<Type> currNode, Type item) {
        if (currNode == null) {
            return false;
        }
        else if (item.compareTo(currNode.getData()) < 0) {
            boolean result = this.remove(currNode.getLeftChild(), item);
            if (result == true && currNode.getLeftChild().getData() == null) {
                currNode.setLeftChild(null);
            }
            return result;
        }
        else if (item.compareTo(currNode.getData()) > 0) {
            boolean result = this.remove(currNode.getRightChild(), item);
            if (result == true && currNode.getRightChild().getData() == null) {
                currNode.setRightChild(null);
            }
            return result;
        }
        else { // node was found (item == currNode value)
            // check 3 cases: leaf node, one child, two children

            // case 1: no children
            if (currNode.getLeftChild() == null && currNode.getRightChild() == null) {
                currNode.setData(null);
                return true;
            }
            // one child on the right
            else if (currNode.getRightChild() != null) {
                BinaryNode<Type> successorNode = this.findSuccessor(currNode);
                Type successorVal = successorNode.getData();
                boolean result = this.remove(currNode.getRightChild(), successorVal);
                currNode.setData(successorVal);
                return result;
            }
            // one child on the left
            else {
                BinaryNode<Type> predecessorNode = this.findPredecessor(currNode);
                Type predecessorVal = predecessorNode.getData();
                boolean result = this.remove(currNode.getLeftChild(), predecessorVal);
                currNode.setData(predecessorVal);
                return result;
            }
        }
    }

    private BinaryNode<Type> findPredecessor(BinaryNode<Type> currNode) {
        BinaryNode<Type> node = currNode.getLeftChild();
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node;
    }

    private BinaryNode<Type> findSuccessor(BinaryNode<Type> currNode) {
        BinaryNode<Type> node = currNode.getRightChild();
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean tracker = false;
        for (Type item : items) {
            boolean result = this.remove(item);
            if (result == true) {
                tracker = true;
            }
        }
        return tracker;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        return null;
    }

    // delete this method once testing is done
    public void display() {
        displayHelper(this.root);
    }

    private void displayHelper(BinaryNode<Type> root) {
        if (root != null) {
            displayHelper(root.getLeftChild());
            System.out.println(root.getData());
            displayHelper(root.getRightChild());
        }
    }
}
