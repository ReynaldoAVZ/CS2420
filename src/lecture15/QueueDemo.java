package lecture15;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Demo class to explore java's Queue interface.
 *
 * @author Aaron Wood
 * @version 2023-10-17
 */
public class QueueDemo {

    public static void main(String[] args) {
        // Queue<T> is an interface that LinkedList<T> implements
        // By declaring the type Queue<String>, we are restricting
        // ourselves to using the Queue methods:
        //      - offer(T item)     --- aka enqueue, adds to the back of the queue
        //      - poll()            --- aka dequeue, pulls from the front of the queue
        //      - peek()            --- views the item at the front of the queue
        Queue<String> students = new LinkedList<>();

        System.out.println("\nenqueue: Juniper");
        students.offer("Juniper");

        System.out.println("enqueue: Digby");
        students.offer("Digby");

        System.out.println("enqueue: Reggie");
        students.offer("Reggie");

        System.out.println("enqueue: Geraldine");
        students.offer("Geraldine");

        System.out.println("student queue: " + students);  // Juniper, Digby, Reggie, Geraldine

        System.out.println("dequeue: " + students.poll());  // Juniper

        System.out.println("student queue: " + students);  // Digby, Reggie, Geraldine

        System.out.println("enqueue: Hattie");
        students.offer("Hattie");

        System.out.println("enqueue: Maximillian");
        students.offer("Maximillian");

        System.out.println("student queue: " + students);  // Digby, Reggie, Geraldine, Hattie, Max

        System.out.println("peek: " + students.peek());  // Digby
        System.out.println("dequeue: " + students.poll());  // Digby
        System.out.println("dequeue: " + students.poll());  // Reggie
        System.out.println("dequeue: " + students.poll());  // Geraldine
        System.out.println("dequeue: " + students.poll());  // Hattie

        System.out.println("student queue: " + students);  // Maximillian
    }

}
