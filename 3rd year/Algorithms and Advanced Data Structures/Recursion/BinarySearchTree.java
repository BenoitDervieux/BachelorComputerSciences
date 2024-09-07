public class BinarySearchTree {

    static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public void setLeft(Node node) {
            this.left = node;
        }

        public Node getLeft() {
            return left;
        }
        public void setRight(Node node) {
            this.right = node;
        }

        public Node getRight() {
            return right;
        }
    }

    public Node insertNode(Node head, int data) {
        if(head == null) {
            head = new Node(data);
            head.val = data;
            return head;
        }
        if (head.val < data) {
            head.right = insertNode(head.right, data);
        } else {
            head.left = insertNode(head.left, data);
        }
        return head;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Node head = null;
        head = tree.insertNode(head, 10);
        head = tree.insertNode(head, 20);
        head = tree.insertNode(head, 30);
        head = tree.insertNode(head, 40);
        head = tree.insertNode(head, 50);
        head = tree.insertNode(head, 60);
        head = tree.insertNode(head, 70);
        head = tree.insertNode(head, 80);
        tree.printTree(head);
        head = tree.insertNode(head, 120);
        System.out.println();
        tree.printTree(head);
    }

    public void printTree(Node node) {
        System.out.println(node.val);
        if (node.right != null) {
            printTree(node.right);
        }
        if (node.left != null) {
            printTree(node.left);
        }
        return;
    }
    
}
