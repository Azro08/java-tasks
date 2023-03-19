package josephusqueue;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class JosephusQueue {
    private static Logger log = Logger.getLogger(JosephusQueue.class.getName());
    public static int josephus(int n, int m) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) queue.add(i);
        if (queue.isEmpty()) return 0;
        while (queue.size() > 1) {
            log.debug("  Queue: " + queue + "  k = " + m);
            for (int i=0; i < m; i++)
                queue.add(queue.remove());
            int e = queue.remove();
            log.debug("    " + e + " is out");
        }
        return queue.remove();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        int m; // N- кол-во людей во круге,   M - M-й чел который будет удаляться
        log.debug("N = ");
        n = input.nextInt();
        log.debug("M = ");
        m = input.nextInt();

        log.debug(josephus(n, m));
    }

}