package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @param <Type>
 *
 * @author Reynaldo Villarreal Zambrano and Mikhail Ahmed
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private BinaryNode<Type> root;
    private int size = 0;
    private ArrayList<Type> arrayList;

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
            this.size++;
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
        } else { // a comparison results in 0 (currNode == item)
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
        this.arrayList = null;
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
        } else if (item.compareTo(currentRoot.getData()) > 0) {
            return this.contains(currentRoot.getRightChild(), item);
        } else {
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
        // if we did remove something, decrement the size
        if (result == true)
            this.size--;
        // return the boolean
        return result;
    }

    /**
     * This Remove method is a private recursive method that removes an element from the BST.
     *
     * @param currNode
     * @param item
     * @return
     */
    private boolean remove(BinaryNode<Type> currNode, Type item) {
        // if the currNode is null, it means there's no such node in the BST that matches item
        if (currNode == null) {
            return false;
        }
        // if the item value is less than the currNode, move left
        else if (item.compareTo(currNode.getData()) < 0) {
            // recursive call on left node of currNode and item
            boolean result = this.remove(currNode.getLeftChild(), item);
            // if an item was removed and the new leftChild value is null, make the object null
            if (result == true && currNode.getLeftChild().getData() == null) {
                currNode.setLeftChild(null);
            }
            // return the result of the final recursive call
            return result;
        }
        // if the item value is greater than the currNode, move right
        else if (item.compareTo(currNode.getData()) > 0) {
            // recursive call on the right node of the currNode and item
            boolean result = this.remove(currNode.getRightChild(), item);
            // if an item was removed and the new rightChild value is null, make the object null
            if (result == true && currNode.getRightChild().getData() == null) {
                currNode.setRightChild(null);
            }
            // return the result of the final recursive call
            return result;
        } else { // node was found (item == currNode value)
            // check 3 cases: leaf node, one child, two children

            // case 1: no children
            if (currNode.getLeftChild() == null && currNode.getRightChild() == null) {
                currNode.setData(null);
                currNode = null;
                return true;
            }
            // one child on the right (true if the item we're removing has two children) and replaces with its successor
            else if (currNode.getRightChild() != null) {
                // find the successorNode that would replace currNode (using private helper method)
                BinaryNode<Type> successorNode = this.findSuccessor(currNode);
                // get the value of the successorNode
                Type successorVal = successorNode.getData();
                // remove the successorNode from the BST
                boolean result = this.remove(currNode.getRightChild(), successorVal);
                // set the currNode value to be the successor value
                currNode.setData(successorVal);
                // return the result of the final recursive call
                return result;
            }
            // one child on the left (true if the item we're removing only has a left child) and replaces with its predecessor
            else {
                // find the predecessorNode that would replace currNode (using private helper method)
                BinaryNode<Type> predecessorNode = this.findPredecessor(currNode);
                // get the value of the predecessorNode
                Type predecessorVal = predecessorNode.getData();
                // remove the predecessorNode from the BST
                boolean result = this.remove(currNode.getLeftChild(), predecessorVal);
                // set the currNode value to be the predecessor value
                currNode.setData(predecessorVal);
                // return the result of the final recursive call
                return result;
            }
        }
    }

    /**
     * The findPredecessor method is a private helper method that finds the predecessor node that should replace the node
     * which we are trying to remove from the BST.
     *
     * @param currNode
     * @return
     */
    private BinaryNode<Type> findPredecessor(BinaryNode<Type> currNode) {
        // move our node to the left once
        BinaryNode<Type> node = currNode.getLeftChild();
        // iterate while the node has a right child (finds the predecessor where the value is the greatest smaller value)
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        // return the found predecessor node
        return node;
    }

    /**
     * The findSuccessor method is a private helper method that finds the successor node that should replace the node
     * which we are trying to remove from the BST.
     *
     * @param currNode
     * @return
     */
    private BinaryNode<Type> findSuccessor(BinaryNode<Type> currNode) {
        // move our node to the right once
        BinaryNode<Type> node = currNode.getRightChild();
        // iterate while the node has a left child (finds the successor where the value is the smallest greater value)
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        // return the found successor node
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
        // if the size of the BST is zero or the root is null
        if (this.size() == 0 || this.root == null) {
            // return an empty array list
            this.arrayList = new ArrayList<>();
            return this.arrayList;
        }
        // create a new arrayList with the size of our BST
        this.arrayList = new ArrayList<>(this.size());
        // call private recursive toArrayList method on this BST
        this.toArrayList(this.root, this.arrayList);
        // return the new created arrayList
        return this.arrayList;
    }

    /**
     * The toArrayList method is a private overloaded method that is recursively called to find values and returns a
     * generic ArrayList
     *
     * @param root      - The node that we are currently looking at
     * @param arrayList - The ArrayList that will be returned in the driver method
     */
    private void toArrayList(BinaryNode<Type> root, ArrayList<Type> arrayList) {
        // if the current node that we're on does not equal null (which means it can possibly have children)
        if (root != null) {
            // recursive call on the left child of the node we're currently on
            toArrayList(root.getLeftChild(), arrayList);
            // add the data of the final node that we find after we break out of recursive call
            if (root.getData() != null) {
                arrayList.add(root.getData());
            }
            // recursive call on the right child of the node we're currently on
            toArrayList(root.getRightChild(), arrayList);
        }
    }
}
