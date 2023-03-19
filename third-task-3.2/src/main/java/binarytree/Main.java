package binarytree;

import edu.princeton.cs.introcs.StdOut;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new SecureRandom();
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();

        int amount;
        StdOut.println("How many elements to add to binary tree: ");
        amount = input.nextInt();

        int choice;
        StdOut.println("1. Enter values manually\n2. Enter values randomly\n");
        choice = input.nextInt();

        while (choice != 1 && choice != 2){
            StdOut.println("wrong input");
            choice = input.nextInt();
        }

        int number;
        if (choice == 1){
            StdOut.println("Enter values: ");
            for (int i = 0; i < amount; i++){
                number = input.nextInt();
                binaryTree.add(number);
            }
        } else {
            for (int i = 0; i < amount; i++){
                binaryTree.add(random.nextInt(100));
            }
        }

        binaryTree.printTree(binaryTree.root);

        int minValue = binaryTree.minValue(binaryTree.root);
        int maxValue = binaryTree.maxValue(binaryTree.root);
        boolean isOrdered = binaryTree.isOrdered(binaryTree.root, minValue, maxValue);
        StdOut.println("\nMin value: " + minValue);
        StdOut.println("Max value: " + maxValue);
        StdOut.println("Дерево упорядочено: " + isOrdered);
    }
}