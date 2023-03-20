package redblackbst;

import edu.princeton.cs.introcs.StdOut;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Task {
    private static final Scanner input = new Scanner(System.in);
    private static final Random random = new SecureRandom();
    private static final RedBlackBST<Integer, Integer> redBlackBST = new RedBlackBST<>();

    public static void main(String[] args) {

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
                redBlackBST.put(number, number);
            }
        } else {
            for (int i = 0; i < amount; i++){
                randomNumber = random.nextInt(100);
                redBlackBST.put(randomNumber, randomNumber);
            }
        }

        redBlackBST.print();

        int key;
        StdOut.println("\nEnter a key to get: ");
        key = input.nextInt();
        if (redBlackBST.contains(key)){
            redBlackBST.get(key);
            StdOut.println("Last accessed node: " + redBlackBST.getLastAccessedNodeValue());
        }
        else {
            StdOut.println("Key was not found!");
        }

    }

}