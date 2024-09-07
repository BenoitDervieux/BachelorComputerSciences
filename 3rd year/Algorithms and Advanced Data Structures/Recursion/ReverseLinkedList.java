public class ReverseLinkedList {

    static class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public Node getNext() {
            return next;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(8);
        Node n3 = new Node(22);
        Node n4 = new Node(40);
        Node n1_1 = new Node(4);
        Node n1_2 = new Node(11);
        Node n1_3 = new Node(16);
        Node n1_4 = new Node(20);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        n1_1.setNext(n1_2);
        n1_2.setNext(n1_3);
        n1_3.setNext(n1_4);

        // printLinkedList(n1);
        // Node reversed = reverseList(n1);
        // System.out.println("");
        // printLinkedList(reversed);
        SortedMerge(n1, n1_1);
        printLinkedList(n1);
    }

    public static Node reverseList(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node p = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return p;
    }

    private static void printLinkedList(Node node) {
        Node tmp = node;
        while (tmp != null) {
            System.out.println(tmp.val + " ");
            tmp = tmp.getNext();
        }
    }

    public static Node SortedMerge(Node A, Node B) {
        if (A == null) return B;
        if (B == null) return A;

        if (A.val < B.val) {
            A.next = SortedMerge(A.next, B);
            return A;
        } else {
            B.next = SortedMerge(A, B.next);
            return B;
        }
    }
    
}
