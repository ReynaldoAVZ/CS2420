package lecture15;

public class LinkedListReverser {

    public static class LinkedList {
        private final int length;
        private Node head;

        public LinkedList() {
            this.length = 0;
            this.head = null;
        }

        private static class Node {
            public int value;
            public Node next;

            public Node(int value, Node next) {
                this.value = value;
                this.next = next;
            }
        }

        /**
         * Reverse the list using O(N) storage.
         */
        public void reverse1() {
            // store values in temp array
            int[] temp = new int[this.length];
            int index = 0;
            Node node = this.head;
            while (node != null) {
                temp[index++] = node.value;
                node = node.next;
            }

            // set values of nodes from the back of the array to the front
            node = this.head;
            while (node != null) {
                node.value = temp[index--];
                node = node.next;
            }
        }

        /**
         * Reverse the list using O(1) storage.
         */
        public void reverse2() {
            // keep track of prev and curr Node
            Node prev = null;
            Node curr = this.head;

            while (curr != null) {
                // store node after curr
                Node next = curr.next;

                // reverse the reference to be from curr to prev
                curr.next = prev;

                // advance the pair of nodes forward
                prev = curr;
                curr = next;
            }

            // set head to be the last node
            this.head = prev;
        }
    }

}
