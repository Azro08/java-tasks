package linkedlist;

public class LinkedList<T> {
    Node<T> head;

    public LinkedList(){
        //конструктор пустой т.к список мб пустым
    }

    public void add(Node<T> newNode){
        newNode.next = head;
        head = newNode;
    }

    public int max(Node<T> first){
        if (first == null) return 0;
        else {
            Node<T> n = first;
            int max = (int) n.value;
            while (n != null){
                int currentValue = (int)n.value;
                if (currentValue > max) max = currentValue;
                n = n.next;
            }
            return max;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("{");
        Node<T> n = head;
        if (n == null)
            return s + "}";
        while (n.next != null) {
            s.append(n.value).append("->");
            n = n.next;
        }
        s.append(n.value);
        return s + "}";
    }
}
