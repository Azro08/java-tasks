package linkedlist;


import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        int amount;
        int number;
        log.debug("amount of numbers: ");
        amount = input.nextInt();
        for (int i = 0; i < amount; i++){
            log.debug("num: ");
            number = input.nextInt();
            linkedList.add(new Node<>(number, null));
        }

        log.debug(linkedList.toString());

        int max = linkedList.max(linkedList.head);
        log.debug(max);
    }
}