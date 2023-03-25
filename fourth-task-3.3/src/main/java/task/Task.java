package task;

import edu.princeton.cs.introcs.StdOut;

import java.security.SecureRandom;
import java.util.Random;

public class Task {
    private static final Random random = new SecureRandom();
    private static final RedBlackBST<Integer, Integer> redBlackTree = new RedBlackBST<>();
    private static final RedBlackBST<Integer, Integer> redBlackTree2 = new RedBlackBST<>();
    private static final BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
    private static final BinarySearchTree<Integer> binaryTree2 = new BinarySearchTree<>();
    public static void main(String[] args) {

        int randomNumber;

        StdOut.println("First random numbers: ");
        for (int i = 0; i < 16; i++){
            randomNumber = random.nextInt(100);
            StdOut.print(randomNumber + " ");
            redBlackTree.put(i+1, i+1);
            binaryTree.put(randomNumber);
        }

        StdOut.println("\nSecond random numbers");
        for (int i = 0; i < 16; i++){
            randomNumber = random.nextInt(100);
            StdOut.print(randomNumber + " ");
            redBlackTree2.put(randomNumber, randomNumber);
            binaryTree2.put(randomNumber);
        }

        StdOut.println("\nBalanced RedBlack Tree1");
        redBlackTree.draw();
        StdOut.println("============================================\nUnbalanced Binary Search Tree1");
        binaryTree.draw();
        StdOut.println("============================================\nBalanced RedBlack Tree2");
        redBlackTree2.draw();
        StdOut.println("============================================\nUnbalanced Binary Search Tree2");
        binaryTree2.draw();

        StdOut.println("============================================");
        StdOut.println("RedBlack Tree 1 left & right heights: " + redBlackTree.leftHeight() + ", " + redBlackTree.rightHeight());
        StdOut.println("Binary Tree 1 left & right heights: " + binaryTree.leftHeight() + ", " + binaryTree.rightHeight());
        StdOut.println("RedBlack Tree 2 left & right heights: " + redBlackTree2.leftHeight() + ", " + redBlackTree2.rightHeight());
        StdOut.println("Binary Tree 2 left & right heights: " + binaryTree2.leftHeight() + ", " + binaryTree2.rightHeight());
    }
}