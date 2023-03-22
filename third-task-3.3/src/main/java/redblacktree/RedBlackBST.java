package redblacktree;

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

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }
    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    public void add(K key, V val) {
        root = add(root, key, val);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value) {
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
        Node rotateNode = node.right;
        node.right = rotateNode.left;
        rotateNode.left = node;
        rotateNode.color = node.color;
        node.color = RED;
        return rotateNode;
    }

    private Node rotateRight(Node node) {
        Node rotateNode = node.left;
        node.left = rotateNode.right;
        rotateNode.right = node;
        rotateNode.color = node.color;
        node.color = RED;
        return rotateNode;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void draw(){
        draw(root, 0);
    }

    private void draw(Node node, int level){
        if(node == null){
            return;
        }

        draw(node.right, level + 1);

        for(int i = 0; i < level; i++){
            StdOut.print("    ");
        }

        if(node.color == RED){
            StdOut.println("(r)" + node.key);
        }
        else{
            StdOut.println("(b)" + node.key);
        }

        draw(node.left, level + 1);

    }

    public void draw2() {
        if (root == null) {
            StdOut.println("Дерево пустое");
            return;
        }
        draw2(root, "", true);
    }

    private void draw2(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }

        if (node.right != null) {
            draw2(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        StdOut.print(prefix);
        StdOut.print(isLeft ? "└── " : "┌── ");
        StdOut.print(node.key + (node.color == RED ? " (r)" : " (b)"));
        StdOut.print("\n");

        if (node.left != null) {
            draw2(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

}