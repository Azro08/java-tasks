package redblackbst;

import edu.princeton.cs.introcs.StdOut;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new SecureRandom();
        RedBlackBST<Integer, Integer> redBlackBST = new RedBlackBST<>();

        int amount;
        StdOut.println("How many elements to add: ");
        amount = input.nextInt();

        int choice;
        StdOut.println("1. Enter values manually\n2. Enter values randomly\n");
        choice = input.nextInt();

        while (choice != 1 && choice != 2){
            StdOut.println("wrong input");
            choice = input.nextInt();
        }

        int number;
        int randomNumber;
        if (choice == 1){
            StdOut.println("Enter values: ");
            for (int i = 0; i < amount; i++){
                number = input.nextInt();
                redBlackBST.add(number, number);
            }
        } else {
            for (int i = 0; i < amount; i++){
                randomNumber = random.nextInt(100);
                redBlackBST.add(randomNumber, randomNumber);
            }
        }

        redBlackBST.print();

        StdOut.println("\nis32: " + redBlackBST.is23());
        StdOut.println("isBalanced: " + redBlackBST.isBalanced());
        StdOut.println("isRedBlackBST: " + redBlackBST.isRedBlackBST());

    }
}