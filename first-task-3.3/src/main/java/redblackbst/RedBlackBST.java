package redblackbst;

import edu.princeton.cs.introcs.StdOut;

public class RedBlackBST<T extends Comparable<T>, V> {
    //V - key
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        T key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(T key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    public boolean is23() {
        return is23(root);
    }

    private boolean is23(Node node) {
        if (node == null) {
            return true;
        }
        if (isRed(node.right)) {
            return false;
        }
        if (node != root && isRed(node) && isRed(node.left)) {
            return false;
        }
        return is23(node.left) && is23(node.right);
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    public boolean isBalanced() {
        int blackHeight = -1;
        return isBalanced(root, 0, blackHeight);
    }

    private boolean isBalanced(Node node, int blackCount, int blackHeight) {
        if (node == null) {
            if (blackHeight == -1) blackHeight = blackCount;
            return blackHeight == blackCount;
        }

        if (!isRed(node)) blackCount++;

        if (node.left != null && node.right != null) {
            if (isRed(node.left) && isRed(node.right)) return false;
            return isBalanced(node.left, blackCount, blackHeight) && isBalanced(node.right, blackCount, blackHeight);
        } else if (node.left != null) {
            return isBalanced(node.left, blackCount, blackHeight);
        } else if (node.right != null) {
            return isBalanced(node.right, blackCount, blackHeight);
        } else {
            if (blackHeight == -1) blackHeight = blackCount;
            return blackHeight == blackCount;
        }
    }

    public boolean isRedBlackBST() {
        return isRedBlackBST(root);
    }

    private boolean isRedBlackBST(Node node) {
        if (node == null)
            return true;

        if (node.color != RED && node.color != BLACK)
            return false;

        if (isRed(node.right) && !isRed(node.left))
            return false;
        if (isRed(node.left) && isRed(node.left.left))
            return false;
        if (isRed(node.left) && isRed(node.right))
            return false;

        if (!isBST(node))
            return false;

        return isRedBlackBST(node.left) && isRedBlackBST(node.right);
    }

    private boolean isBST(Node node) {
        return isBST(node, null, null);
    }

    private boolean isBST(Node node, T min, T max) {
        if (node == null)
            return true;
        if (min != null && node.key.compareTo(min) <= 0)
            return false;
        if (max != null && node.key.compareTo(max) >= 0)
            return false;
        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }


    public void add(T key, V val) {
        root = add(root, key, val);
        root.color = BLACK; // корень всегда черный
    }

    private Node add(Node node, T key, V value) {
        if (node == null) return new Node(key, value, RED);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = add(node.left, key, value);
        else if (cmp > 0) node.right = add(node.right, key, value);
        else node.value = value;


        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void print() {
        print(root);
    }

    private void print(Node x) {
        if (x != null) {
            print(x.left);
            StdOut.print(x.key + " ");
            print(x.right);
        }
    }


}