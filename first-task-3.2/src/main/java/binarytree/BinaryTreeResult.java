package binarytree;

import edu.princeton.cs.introcs.StdOut;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class BinaryTreeResult {

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
            for (int i = 0; i < amount; i++){
                number = input.nextInt();
                binaryTree.put(number);
            }
        } else {
            for (int i = 0; i < amount; i++){
                binaryTree.put(random.nextInt(100));
            }
        }

        StdOut.println("Size: " + binaryTree.size());
        StdOut.println("Avg Compares: " + binaryTree.avgCompares());
    }
}
