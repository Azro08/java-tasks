package task;

import edu.princeton.cs.introcs.StdOut;

public class RedBlackBST<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        int size;

        public Node(K key, V value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value, RED, 1);

        int compare;
        compare = key.compareTo(node.key);
        if (compare < 0) node.left = put(node.left, key, value);
        else if (compare > 0) node.right = put(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private Node rotateRight(Node node) {
        Node rotateNode = node.left;
        node.left = rotateNode.right;
        rotateNode.right = node;
        rotateNode.color = rotateNode.right.color;
        rotateNode.right.color = RED;
        rotateNode.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return rotateNode;
    }

    private Node rotateLeft(Node node) {
        Node rotateNode = node.right;
        node.right = rotateNode.left;
        rotateNode.left = node;
        rotateNode.color = rotateNode.left.color;
        rotateNode.left.color = RED;
        rotateNode.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return rotateNode;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }



    public void draw() {
        if (root == null) {
            StdOut.println("Empty tree");
            return;
        }
        draw(root, "", true);
    }

    private void draw(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }

        if (node.right != null) {
            draw(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        StdOut.print(prefix);
        StdOut.print(isLeft ? "└── " : "┌── ");
        StdOut.print(node.key + (node.color == RED ? "(r)" : "(b)"));
        StdOut.print("\n");

        if (node.left != null) {
            draw(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }


    public int leftHeight() {
        return leftHeight(root);
    }

    private int leftHeight(Node x) {
        if (x == null) {
            return 0;
        }
        return leftHeight(x.left) + (x.color == BLACK ? 1 : 0);
    }

    public int rightHeight() {
        return rightHeight(root);
    }

    private int rightHeight(Node x) {
        if (x == null) {
            return 0;
        }
        return rightHeight(x.right) + (x.color == BLACK ? 1 : 0);
    }


}


