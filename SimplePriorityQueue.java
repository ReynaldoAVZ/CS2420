package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class SimplePriorityQueue<E> implements PriorityQueue{
private SimplePriorityQueue<E> priorityQueue;


    E[] array = (E[]) new Object[16];

    private int arrSize;
    private Comparator<? super E> cmp;

    private int arrCount = 0;

    private E[] simpleArray;
    @SuppressWarnings("unchecked")


    public SimplePriorityQueue() {
        this.arrSize = 10;
        this.simpleArray = (E[]) new Object[this.arrSize];
    }

    public SimplePriorityQueue(Comparator<? super E> cmp){
        this.arrSize = 10;
        this.simpleArray = (E[]) new Object[this.arrSize];
        this.cmp = cmp;
    }

    private int compare (E o1, E o2){
        if (this.cmp == null)
            return ((Comparable<? super E>)o1).compareTo(o2);

        else
            return cmp.compare(o1, o2);
    }

    @Override
    public E findMax() throws NoSuchElementException {
        if (this.arrCount == 0) {
            throw new NoSuchElementException();
        }

        E currentMax = this.array[0];
        for (int i = 1; i < arrSize - 1; i++) {
            int comparison = this.compare(currentMax, array[i]);
            if (comparison < 0){
                currentMax = array[i];
            }
        }

        return currentMax;
    }

    @Override
    public Object deleteMax() throws NoSuchElementException {
        return null;
    }

    @Override
    public void insert(Object item) {

    }

    @Override
    public void insertAll(Collection coll) {

    }

    @Override
    public boolean contains(Object item) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
