package movetofront;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner rl = new Scanner(System.in);
        LinkedList<Character> linkedList = new LinkedList<>();
        char a;
        int amount;

        log.debug("введите кол-во символов");
        amount = rl.nextInt();
        for (int i = 0; i < amount; i++){
            log.debug("символ = ");
            a = rl.next().charAt(0);
            linkedList.addAtStart(new Node<>(a, null));
        }


        log.debug(linkedList.toString());
        linkedList.moveToFront(linkedList);
        log.debug(linkedList.toString());
    }
}