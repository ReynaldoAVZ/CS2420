package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
private BinarySearchTree<Integer> BST;
private BinarySearchTree<Integer> BSTEmpty;

    @BeforeEach
    void setUp() {
        BST = new BinarySearchTree<>();
        BST.add(5);
        BST.add(0);
        BST.add(9);
        BST.add(3);
        BST.add(7);
        BST.add(2);
        BST.add(6);
        BST.add(4);
        BST.add(8);
        BST.remove(9);
        BST.remove(0);
        BSTEmpty = new BinarySearchTree<>();
    }

    @Test
    void add() {;
        BST.display();
    }

    @Test
    void addAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void contains() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void first() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void last() {
    }

    @Test
    void remove() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void size() {
    }

    @Test
    void toArrayList() {
    }
}