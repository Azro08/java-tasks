package task;

import edu.princeton.cs.introcs.StdOut;

public class Task {
    public static void main(String[] args) {
        RedBlackBST<Character, Character> redBlackBST = new RedBlackBST<>();

        redBlackBST.add('E', 'E');
        redBlackBST.add('A', 'A');
        redBlackBST.add('S', 'S');
        redBlackBST.add('Y', 'Y');
        redBlackBST.add('Q', 'Q');
        redBlackBST.add('U', 'U');
        redBlackBST.add('T', 'T');
        redBlackBST.add('I', 'I');
        redBlackBST.add('O', 'O');
        redBlackBST.add('N', 'N');

        StdOut.println("r - red\nb - black\n");

        redBlackBST.draw();
        StdOut.println("---------------------------");
        redBlackBST.draw2();

    }
}